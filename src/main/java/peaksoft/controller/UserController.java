package peaksoft.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.User;
import peaksoft.exception.NotFoundException;
import peaksoft.service.FollowerService;
import peaksoft.service.PostService;
import peaksoft.service.UserService;

@Controller
@RequestMapping("/instagram")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final FollowerService followerService;
    private final PostService postService;
    public static User currentUser;
//
//    @GetMapping
//    public String getAllUsers(Model model){
//        model.addAttribute("allUsers",userService.getAllUser());
//        return "instagram";
//    }

    @GetMapping("/newUser")
    public String create(Model model) {
        model.addAttribute("newUser", new User());
        return "/sign-up";
    }

    @PostMapping("/saveUser")
    public String signUp(@ModelAttribute("newUser") User user,Model model) {
        try {
          currentUser = userService.signUp(user);
          model.addAttribute("currentUser",currentUser);
            model.addAttribute("subcribers", followerService.subcriberSize(currentUser.getFollower().getId()));
            model.addAttribute("subcribtions", followerService.subcriptionSize(currentUser.getFollower().getId()));
            model.addAttribute("post",postService.getAllPostByUserId(currentUser.getId()).size());
            return "profile";
        } catch (Exception e) {
            return "error-page";
        }
    }

    @GetMapping()
    public String loginModel(Model model) {
        User user = new User();
        model.addAttribute("currentUser", user);
        return "login-page";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute("currentUser") User user,Model model) {
        try {
        currentUser = userService.signIn(user);
            model.addAttribute("currentUser",currentUser);
            model.addAttribute("subcribers", followerService.subcriberSize(currentUser.getFollower().getId()));
            model.addAttribute("subcribtions", followerService.subcriptionSize(currentUser.getFollower().getId()));
            model.addAttribute("post",postService.getAllPostByUserId(currentUser.getId()).size());
            return "profile";
        } catch (NotFoundException e) {
            return "/error-page";
        }
    }
    @GetMapping("/home")
    public String home(Model model){
        model.addAttribute("currentUser",currentUser);
        return "/home";
    }
    @PostMapping("/homePage")
    public String  homePage(@ModelAttribute("currentUser")User user){
        return "/home";
    }





}

