package com.f.movie.service;

import cn.tdchain.Trans;
import cn.tdchain.TransHead;
import cn.tdchain.jbcc.Connection;
import cn.tdchain.jbcc.Result;
import cn.tdchain.jbcc.bql.BQL;
import cn.tdchain.jbcc.bql.BQLResult;
import cn.tdchain.jbcc.bql.Condition;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.f.movie.entity.IdMap;
import com.f.movie.entity.MovieTop;
import com.f.movie.entity.User;
import com.f.movie.jbcc.util.Tools;
import com.f.movie.mapper.MapMapper;
import com.f.movie.mapper.MovieTopMapper;
import com.f.movie.mapper.UserMapper;
import org.javatuples.Pair;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    @Autowired
    BaseService baseService;
    @Autowired
    MapMapper mapMapper;
    @Autowired
    MovieTopMapper movieTopMapper;

    @Autowired
    UserMapper userMapper;
    private Connection connection;
    private Logger log;

    public UserService() {
    }

    /*
    * 插入用户信息
    * 输入：用户实体
    * 成功返回用户实体，失败返回null
    * */
    @CachePut(cacheNames="user",key="#user.id")
    public User addUser(User user){
        //# 构建出一笔交易信息
        Trans trans = trans(user.getId(),user.getUsername(),user.getPassword(),user.getNickname(),user.getEmail(),user.getPhone(),user.getHobbies(), user.getState());
        //# 发起一笔交易到云区块链服务中
        Result<TransHead> result = baseService.getConnection().addTrans(trans);

        if (result.isSuccess()) {
            //# 根据返回结果状态判断是否成功
            baseService.getLog().info("\n===> add trans success.");
            //Tools.printResult(result);
            return user;
        } else {
            baseService.getLog().info("\n===> add trans fail.");
            return null;
        }

//        Tools.printResult(result);

    }


    public Trans trans(String id,String username,String password, String nickname, String email, String phone, String hobbies, String state) {
        Trans trans = new Trans();
        trans.setKey(id);//# key是当前交易的维度
        Map<String, Object> data = new HashMap<>();
        data.put("username", username);
        data.put("password", password);
        data.put("nickname", nickname);
        data.put("email", email);
        data.put("phone", phone);
        data.put("hobbies", hobbies);
        data.put("state", state);
        trans.setData(JSON.toJSONString(data));
        //trans.setType("Test");
        trans.setTimestamp(new Date().getTime());

        trans.setType("user");

        return trans;
    }

    /*
    * 查询用户信息
    * 输入：用户id
    * 成功返回
    * */

    @Cacheable(cacheNames = "user",key="#key")
    public User queryUser(String key){
        int startIndex = 0;
        int endIndex = 10;
        User user = new User();
        System.out.println("query user info");
        try {
            Result<List<Trans>> result = baseService.getConnection().getTransHistoryByKey(key, startIndex, endIndex);

            if(result.getEntity().isEmpty()){
                return null;
            }
            String data = result.getEntity().get(0).getData();
            String id = result.getEntity().get(0).getKey();
            JSONObject jsonObject =  JSONObject.parseObject(data);
            String username = jsonObject.get("username").toString();
            String password = jsonObject.get("password").toString();
            String nickname = jsonObject.get("nickname").toString();
            String email = jsonObject.get("email").toString();
            String phone = jsonObject.get("phone").toString();
            String hobbies = jsonObject.get("hobbies").toString();
            String state = jsonObject.get("state").toString();

            user.setId(id);
            user.setPassword(password);
            user.setUsername(username);
            user.setNickname(nickname);
            user.setEmail(email);
            user.setPhone(phone);
            user.setHobbies(hobbies);
            user.setState(state);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return user;
    }

    /*
    * 通过用户id查询映射
    * */
    public List<IdMap> getMapByUser(String id){
        return mapMapper.getByUser(id);
    }

    public IdMap insertIdMap(String userId,String commentId,String movieId){
        IdMap idMap = new IdMap(commentId,movieId,userId);
        int state = mapMapper.insertMap(idMap);
        if(state == 1) {
            return idMap;
        }else{
            return null;
        }
    }

    /*
    * 获取推荐电影
    * */
    public MovieTop getMovieTop(String id){
        return movieTopMapper.getById(id);
    }

    /*
    * 插入推荐电影
    * */
    public MovieTop insertMovieTop(String userId,List<String> top){
        MovieTop movieMap = new MovieTop(userId);
        movieMap.setTop1(top.get(0));
        movieMap.setTop2(top.get(1));
        movieMap.setTop3(top.get(2));
        movieMap.setTop4(top.get(3));
        movieMap.setTop5(top.get(4));
        movieMap.setTop6(top.get(5));
        movieMap.setTop7(top.get(6));
        movieMap.setTop8(top.get(7));
        movieMap.setTop9(top.get(8));
        movieMap.setTop10(top.get(9));
        int state = movieTopMapper.insertMovieTop(movieMap);
        System.out.println(state);
        if(state >= 1) {
            return movieMap;
        }else{
            return null;
        }
    }

    public User insertUser(User user){
        int state = userMapper.insertUser(user);
        if(state == 1) {
            return user;
        }else{
            return null;
        }
    }

    public User getUser(String username){
        return userMapper.getUser(username);
    }

    /*
     * 删除用户
     * 输入：用户id
     * 成功返回
     * */

    @Cacheable(cacheNames = "user",key="#key")
    public User delUser(String key){
        User user = new User();
        System.out.println("delete user id: " + key);
        try {
            user = queryUser(key);
            if(user.getState().equals("正常")) {
                user.setState("已注销");
                user = addUser(user);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    /*
     * 查询指定的用户
     * 输入：起始位置和数量
     * 成功返回<用户总数,用户列表>
     * */
    public Pair<Integer, List<User>> getUsers(int start, int count){
        Integer num = 0;
        List<User> users = new ArrayList<>();
        System.out.println("get users info");
        try {

            Condition c = new Condition("state", BQL.Relationship.equal, "正常");
            BQL bql = new BQL();
            int page = (start-1)/30+1;
            bql.setPage(page);
            bql.setCondition(c);
            Result<BQLResult> result = baseService.getConnection().getNewTransByBQL(bql);

            if(!result.isSuccess()){
                return Pair.with(0, users);
            }

            BQLResult bqlResult = result.getEntity();
            num = bqlResult.getSize();
            for(int i=0; i< count && start/30+i< bqlResult.getSize(); i++){
                Trans trans = bqlResult.getList().get(start/30+i);
                String data = trans.getData();
                String id = trans.getKey();
                JSONObject jsonObject =  JSONObject.parseObject(data);
                String username = jsonObject.get("username").toString();
                String password = jsonObject.get("password").toString();
                String nickname = jsonObject.get("nickname").toString();
                String email = jsonObject.get("email").toString();
                String phone = jsonObject.get("phone").toString();
                String hobbies = jsonObject.get("hobbies").toString();
                String state = jsonObject.get("state").toString();

                User user = new User();
                user.setId(id);
                user.setPassword(password);
                user.setUsername(username);
                user.setNickname(nickname);
                user.setEmail(email);
                user.setPhone(phone);
                user.setHobbies(hobbies);
                user.setState(state);

                users.add(user);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Pair.with(num, users);
    }

    /**
     * 通过sql语句查询符合键值对的用户
     * @param searchKey
     * @param searchContent
     * @return
     */
    public User getUser(String searchKey, String searchContent){
        System.out.println("get user info by "+ searchKey);
        User user = new User();
        try {
            Condition c = new Condition(searchKey, BQL.Relationship.equal, searchContent);
            BQL bql = new BQL();
            bql.setPage(1);
            bql.setCondition(c);
            Result<BQLResult> result = baseService.getConnection().getNewTransByBQL(bql);

            if(!result.isSuccess()){
                return null;
            }

            BQLResult bqlResult = result.getEntity();
            Trans trans = bqlResult.getList().get(0);
            String data = trans.getData();
            String id = trans.getKey();
            JSONObject jsonObject =  JSONObject.parseObject(data);
            String username = jsonObject.get("username").toString();
            String password = jsonObject.get("password").toString();
            String nickname = jsonObject.get("nickname").toString();
            String email = jsonObject.get("email").toString();
            String phone = jsonObject.get("phone").toString();
            String hobbies = jsonObject.get("hobbies").toString();
            String state = jsonObject.get("state").toString();

            user.setId(id);
            user.setPassword(password);
            user.setUsername(username);
            user.setNickname(nickname);
            user.setEmail(email);
            user.setPhone(phone);
            user.setHobbies(hobbies);
            user.setState(state);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return user;
    }
}
