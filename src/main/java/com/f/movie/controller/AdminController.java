package com.f.movie.controller;

import com.f.movie.entity.Movie;
import com.f.movie.entity.State;
import com.f.movie.entity.User;
import com.f.movie.service.MovieService;
import com.f.movie.service.UserService;
import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    MovieService movieService;

    @Autowired
    UserService userService;

    @GetMapping("/movielist")
    public String movieList(){
        return "redirect:/admin/movielist/1";
    }

    @RequestMapping("/movielist/{page}")
    public String adminMovie(Model model, @PathVariable int page){
        //电影总数
        int movieNums = movieService.getMovieNum();
        //每页电影数
        int perPage = 10;
        //页数
        int pages = movieNums / perPage + 1;
        //当前页
        int pageNow = page;
        //下一页
        int nextPage = Math.min(pageNow + 1, pages);
        //上一页
        int prePage = Math.max(pageNow - 1, 1);

        List<Movie> movieList = movieService.getMovies((page-1)*perPage,10);
        model.addAttribute("movieList",movieList);
        model.addAttribute("movieNums",movieNums);
        model.addAttribute("pages",pages);
        model.addAttribute("pageNow",pageNow);
        model.addAttribute("nextPage",nextPage);
        model.addAttribute("prePage",prePage);
        return "movies.html";
    }
    @RequestMapping("/addmovie")
    public String addMovie(Model model){
        return "add_movie.html";
    }

    @PostMapping("/addmovie")
    public String addMovie1(HttpServletRequest req,@RequestParam int page){
        String tags = req.getParameter("tags");
        String[] styles = tags.split(",");

        Movie movie = new Movie(req.getParameter("movieId"),
                req.getParameter("name"),
                req.getParameter("english_name"),
                req.getParameter("directors"),
                req.getParameter("writer"),
                req.getParameter("actors"),
                req.getParameter("date"),
                styles[0],
                styles[1],
                styles[2],
                req.getParameter("country"),
                req.getParameter("rate"),
                req.getParameter("dataID"),
                req.getParameter("language"),
                req.getParameter("duration"),
                req.getParameter("introduction"),
                req.getParameter("url"),
                req.getParameter("pic_url"));
        movieService.insertMovie(movie);

        return "redirect:/admin/movielist/" + page;
    }

    @GetMapping("/updatemovie")
    public String updateMovie(@RequestParam String id,@RequestParam int page,Model model){
        Movie movie = movieService.getMovieById(id);
        model.addAttribute("movie",movie);
        model.addAttribute("page",page);
        return "update_movie.html";
    }

    @PostMapping("/updatemovie")
    public String updateMovie1(HttpServletRequest req,@RequestParam int page){
        String tags = req.getParameter("tags");
        String[] styles = tags.split(",");

        Movie movie = new Movie(req.getParameter("movieId"),
                req.getParameter("name"),
                req.getParameter("english_name"),
                req.getParameter("directors"),
                req.getParameter("writer"),
                req.getParameter("actors"),
                req.getParameter("date"),
                styles[0],
                styles[1],
                styles[2],
                req.getParameter("country"),
                req.getParameter("rate"),
                req.getParameter("dataID"),
                req.getParameter("language"),
                req.getParameter("duration"),
                req.getParameter("introduction"),
                req.getParameter("url"),
                req.getParameter("pic_url"));
        movieService.updateMovie(movie);

        return "redirect:/admin/movielist/" + page;
    }

    @RequestMapping("/delmovie")
    public String delMovie(@RequestParam String id,@RequestParam int page){
        movieService.delMovie(id);
        return "redirect:/admin/movielist/" + page;
    }

    @PostMapping("/delmovies")
    public String delMovies(HttpServletRequest req,@RequestParam int page){
        String[] ids = req.getParameterValues("ids[]");
        for (String id : ids) {
            movieService.delMovie(id);
        }
        return "redirect:/admin/movielist/" + page;
    }

    @GetMapping("/userlist")
    public String userList(){
        return "redirect:/admin/userlist/1";
    }

    @RequestMapping("/userlist/{page}")
    public String adminUser(Model model, @PathVariable int page){
        //System.out.println("start users");
        //每页用户数
        int perPage = 10;

        Pair<Integer, List<User>> result = userService.getUsers((page-1)*perPage,perPage);

        List<User> userList = result.getValue1();
        //用户总数
        int userNums = result.getValue0();
        //页数
        int pages = userNums / perPage + 1;
        //当前页
        int pageNow = page;
        //下一页
        int nextPage = Math.min(pageNow + 1, pages);
        //上一页
        int prePage = Math.max(pageNow - 1, 1);

        model.addAttribute("userList",userList);
        model.addAttribute("userNums",userNums);
        model.addAttribute("pages",pages);
        model.addAttribute("pageNow",pageNow);
        model.addAttribute("nextPage",nextPage);
        model.addAttribute("prePage",prePage);
        System.out.println("to userlist html");
        return "users.html";
    }

    @PostMapping("/user")
    public String searchUser(Model model, HttpServletRequest req) {
        System.out.println("start searching user");

        String key = (String) req.getParameter("search_field");
        String content = (String) req.getParameter("keyword");
        User user = userService.getUser(key, content);
        if(user==null){
            user = new User();
            model.addAttribute("error", "您查询的用户不存在");
        }

        List<User> userList = new ArrayList<>();
        userList.add(user);

        model.addAttribute("userList",userList);
        model.addAttribute("userNums",1);
        model.addAttribute("pages",1);
        model.addAttribute("pageNow",1);
        model.addAttribute("nextPage",1);
        model.addAttribute("prePage",1);
        System.out.println("to userlist html");

        return "users.html";

    }

    @RequestMapping("/adduser")
    public String addUser(Model model){
        return "add_user.html";
    }

    @PostMapping("/adduser")
    public String addUser(HttpServletRequest req, @RequestParam int page, Model model) {

        //检查用户是否重复
        User user = userService.getUser("username", req.getParameter("username"));
        if(user != null){
            model.addAttribute("error", "该用户名已存在，请更改用户名");
            return "add_user.html";
        }
        user = userService.getUser("nickname", req.getParameter("nickname"));
        if(user != null){
            model.addAttribute("error", "该昵称已存在，请更改昵称");
            return "add_user.html";
        }
        if(isMail(req.getParameter("email"))){
            model.addAttribute("error", "邮箱格式错误，请更改邮箱");
            return "add_user.html";
        }
        if(isPhone(req.getParameter("phone"))){
            model.addAttribute("error", "手机号格式错误，请更手机号");
            return "add_user.html";
        }

        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        user = new User(uuid,
                req.getParameter("username"),
                req.getParameter("password"),
                req.getParameter("nickname"),
                req.getParameter("email"),
                req.getParameter("phone"),
                req.getParameter("hobbies"),
                "正常");
        userService.addUser(user);
        userService.insertUser(user);

        /*
        要加上判断，这地方还要修改
         */
        System.out.println("add user success");
        return "redirect:/admin/userlist/" + page;
    }

    @RequestMapping("/deluser")
    public String delUser(@RequestParam String id,@RequestParam int page){
        System.out.println("del user id: " + id);
        userService.delUser(id);
        return "redirect:/admin/userlist/" + page;
    }

    @PostMapping("/delusers")
    public String delUsers(HttpServletRequest req,@RequestParam int page){
        String[] ids = req.getParameterValues("ids[]");
        for (String id : ids) {
            userService.delUser(id);
        }
        return "redirect:/admin/userlist/" + page;
    }

    public boolean isMail(String str) {
        String regEx1 = "^[a-zA-Z0-9][\\\\w\\\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\\\w\\\\.-]*[a-zA-Z0-9]\\\\.[a-zA-Z][a-zA-Z\\\\.]*[a-zA-Z]$";
        Pattern p = Pattern.compile(regEx1);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    public boolean isPhone(String str) {
        String regExp = "^1[3|4|5|7|8][0-9]\\\\d{4,8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }
}
