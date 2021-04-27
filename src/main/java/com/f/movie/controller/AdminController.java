package com.f.movie.controller;

import com.f.movie.entity.Movie;
import com.f.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    MovieService movieService;

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
}
