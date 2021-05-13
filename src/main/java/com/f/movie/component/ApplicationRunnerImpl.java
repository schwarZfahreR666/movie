package com.f.movie.component;

import com.f.movie.controller.AdminController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Component
public class ApplicationRunnerImpl implements ApplicationRunner {

    @Value("${server.port}")
    private String port ;

    @Autowired
    AdminController adminController;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        InetAddress localHost = null;
        try {
            localHost = Inet4Address.getLocalHost();
        } catch (UnknownHostException e) {
            System.out.println(e);
        }
        String ip = localHost.getHostAddress();  // 返回格式为：xxx.xxx.xxx
// localHost.getHostName() 一般是返回电脑用户名
        System.out.println("请使用该地址登录后台系统：" + ip + ":" + port + "/admin/movielist/" + adminController.baseToken);
    }
}
