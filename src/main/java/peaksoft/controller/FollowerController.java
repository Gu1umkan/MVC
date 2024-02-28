package peaksoft.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.User;
import peaksoft.service.FollowerService;
import peaksoft.service.PostService;
import peaksoft.service.UserService;

import java.util.List;

import static peaksoft.controller.UserController.currentUser;

@Controller
@RequestMapping("follower")
@RequiredArgsConstructor
public class FollowerController {
    private final FollowerService followerService;
    private final UserService userService;
    private final PostService postService;
    @GetMapping("/search")
    public String findUser(Model model){
        return "search";
    }

    @GetMapping("/searchUser")
    public String search(Model model, @RequestParam("searchName") String searchName){
        try{
        User userByName = userService.findUserByName(searchName);
        model.addAttribute("foundUser",userByName);
        model.addAttribute("subcribers", followerService.subcriberSize(userByName.getFollower().getId()));
        model.addAttribute("subcribtions", followerService.subcriptionSize(userByName.getFollower().getId()));
        model.addAttribute("post",postService.getAllPostByUserId(userByName.getId()).size());
        return "foundUser";
        }catch (Exception e){
             return "search";
      }
    }

    @GetMapping("/follow/{foundUserId}")
    public String following(Model model,@PathVariable Long foundUserId){
         model.addAttribute("foundUserId",foundUserId);
        followerService.following(currentUser.getId(),foundUserId);
        model.addAttribute("currentUser",currentUser);
        model.addAttribute("subcribers", followerService.getSubscribersByUserId(currentUser.getId()).size());
        model.addAttribute("subcribtions", followerService.getSubscriptionsByUserId(currentUser.getId()).size());
        model.addAttribute("post",postService.getAllPostByUserId(currentUser.getId()).size());
        return "profile";
    }

    @GetMapping("/getSubscribtion")
    public String getLikes( Model model){
        List<User> users = followerService.getSubscriptionsByUserId(currentUser.getId());
        model.addAttribute("users", users);
        model.addAttribute("userId", currentUser.getId());
        return "redirect:/follower/searchUser";
    }

}
