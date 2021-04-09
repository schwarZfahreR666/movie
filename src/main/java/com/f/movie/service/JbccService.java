package com.f.movie.service;


import cn.tdchain.Trans;
import cn.tdchain.TransHead;
import cn.tdchain.jbcc.Connection;
import cn.tdchain.jbcc.ConnectionFactory;
import cn.tdchain.jbcc.Result;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.f.movie.pojo.User;
import com.f.movie.jbcc.util.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JbccService {

    protected final Logger log = LoggerFactory.getLogger("jbcc-samples");
    protected String[] iptables = new String[]{
            "open-tdcb-node1.tdchain.cn",
            "open-tdcb-node2.tdchain.cn",
            "open-tdcb-node3.tdchain.cn",
            "open-tdcb-node4.tdchain.cn"};
    protected final String keystorePath = "src/main/resources/0x043086bb88a993387dcdebea6da18e98.pfx";
    protected final String keystorePasswd = "06240118";


    //# 访问天德云区块链的端口, 默认为18088
    protected final int port = 18088;
    //# 连接超时时间，默认3秒,
    protected final long timeout = 3000;

    //# 访问天德云区块链的token, 申请证书成功之后即可获取有效token
    protected final String token = "32581b83-705d-40ef-9f62-1f9a4118fbe1";
    //# 访问天德云区块链时需要的授权文件,登录成功之后即可下载证书, keystorePath为证书存放路径


    //# 声明一个全局连接器connection
    public Connection connection = null;
    public SnowFlake snowFlake = null;

    public JbccService() {
        try {
            //# 构建配置信息
            ConnectionFactory factory = ConnectionFactory.ConnectionConfig.builder()
                    .iptables(iptables)
                    .port(port)  //# 不配置即使用默认值 18088
                    .timeout(timeout)  //# 不配置即使用默认3秒
                    .token(token)
                    //.showPrint()
                    .keystorePath(keystorePath)
                    .keystorePassword(keystorePasswd).build();

            this.connection = factory.getConnection();

            this.snowFlake = new SnowFlake();
        } catch (Exception e) {
            log.info("请检查配置===> error: {}", e);
        }

    }

    @CachePut(cacheNames="user",key="#user.id")
    public User addTrans(User user){
        //# 构建出一笔交易信息
        Trans trans = trans(user.getId(),user.getUsername(),user.getPassword());
        //# 发起一笔交易到云区块链服务中
        Result<TransHead> result = connection.addTrans(trans);

        if (result.isSuccess()) {
            //# 根据返回结果状态判断是否成功
            log.info("\n===> add trans success.");
        } else {
            log.info("\n===> add trans fail.");
        }

//        Tools.printResult(result);
        return user;
    }


    public static Trans trans(String transKey,String username,String password) {
        Trans trans = new Trans();
        trans.setKey(transKey);//# key是当前交易的维度
        Map<String, Object> data = new HashMap<>();
        data.put("username", username);
        data.put("password", password);
        trans.setData(JSON.toJSONString(data));
        trans.setType("Test");
        trans.setTimestamp(new Date().getTime());

        return trans;
    }

    @Cacheable(cacheNames = "user",key="#key")
    public User queryByKey(String key){
        int startIndex = 0;
        int endIndex = 10;
        User user = new User();
        System.out.println("query");
        try {
            Result<List<Trans>> result = connection.getTransHistoryByKey(key, startIndex, endIndex);

            String data = result.getEntity().get(0).getData();
            String id = result.getEntity().get(0).getKey();
            JSONObject jsonObject =  JSONObject.parseObject(data);
            String username = jsonObject.get("username").toString();
            String password = jsonObject.get("password").toString();

            user.setId(id);
            user.setPassword(password);
            user.setUsername(username);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}
