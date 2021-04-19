package com.f.movie.service;

import cn.tdchain.jbcc.Connection;
import cn.tdchain.jbcc.ConnectionFactory;
import com.f.movie.Base;
import com.f.movie.jbcc.util.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.w3c.dom.ls.LSOutput;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

@Service
public class BaseService {
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
    private Connection connection = null;
    public SnowFlake snowFlake = null;

    public BaseService() {
//        String keystorePath = Base.class.getClassLoader().getResource("0x043086bb88a993387dcdebea6da18e98.pfx").getFile().toString();

        System.out.println(keystorePath);
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

    public Connection getConnection(){
        return this.connection;
    }

    public Logger getLog(){
        return this.log;
    }

}
