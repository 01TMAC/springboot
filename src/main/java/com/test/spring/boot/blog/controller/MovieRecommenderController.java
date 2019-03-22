package com.test.spring.boot.blog.controller;


import com.test.spring.boot.blog.entity.Blog;
import com.test.spring.boot.blog.entity.User;
import com.test.spring.boot.blog.service.BlogService;
import com.test.spring.boot.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.test.spring.boot.blog.recommender.MovieRecommender;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/mahout")
public class MovieRecommenderController {
    final private int RECOMMENDER_NUM = 10;    //推荐结果个数
    final private int PAGE_SIZE = 20;    //推荐结果个数
    @Autowired
    private UserService userService;
    @Autowired
    private BlogService blogService;
    @Resource
    private MovieRecommender movieRecommender;

    @RequestMapping(value = "/userlist")
//    @ResponseBody
    public String userlist(@RequestParam(value = "userName", defaultValue = "1")String userName, Model model) throws  Exception{
       String listStr = "";
     // List list =  movieRecommender.itemBasedRecommender(userId,5);
     // listStr = list.toString();
        if(userName.equals("anonymousUser")){return "";}
        User user = userService.findByUsername(userName);
        long userId = user.getId();
         List<Long> movieList =  movieRecommender.itemBasedRecommender(userId,5);
         List<Blog> list = new ArrayList<>();
        for(long movieId : movieList ){
             Blog blog = blogService.getBlogById(movieId);
             if(blog!= null) {
                 list.add(blog);
             }
         }
        listStr =  list.toString();
        model.addAttribute("blogList", list);
        return  "index :: #mainContainerRepleace";
    }

//    @RequestMapping(value = "/recommend")
//    public ModelAndView recommendlist(@RequestParam(value = "userid", defaultValue = "1") int userID, ModelAndView mv) {
//        List moviesRBU=movieService.recommendMoviesBasedUser(userID, RECOMMENDER_NUM);
//        List moviesRBI=movieService.recommendMoviesBasedItem(userID, RECOMMENDER_NUM);
//        List lookedMovies=movieService.queryLookedMoviesByUser(userID);
//        mv.setViewName("/recommendlist");
//        mv.addObject("lookedMovies",lookedMovies);
//        mv.addObject("moviesRBU",moviesRBU);
//        mv.addObject("moviesRBI",moviesRBI);
//        return mv;
//    }

}
