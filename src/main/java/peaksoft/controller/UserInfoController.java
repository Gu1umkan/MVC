package peaksoft.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.UserInfo;
import peaksoft.service.FollowerService;
import peaksoft.service.PostService;
import peaksoft.service.UserInfoService;
import peaksoft.service.UserService;

import static peaksoft.controller.UserController.currentUser;

@Controller
@RequestMapping("/userInfo")
@RequiredArgsConstructor
public class UserInfoController {
    private final UserInfoService userInfoService;
    private final UserService userService;
    private final UserController userController;
    private final FollowerService followerService;
    private final PostService postService;

//    @PostMapping("/saveInfo")
//    public String saveInfo(@ModelAttribute("currentUser") User UserController.UserController.currentUser)){
//
//    }
    @GetMapping
    public String profile(Model model){
        model.addAttribute("currentUser", userService.findById(currentUser.getId()));
        model.addAttribute("subcribers", followerService.subcriberSize(currentUser.getFollower().getId()));
        model.addAttribute("subcribtions", followerService.subcriptionSize(currentUser.getFollower().getId()));
        model.addAttribute("post",postService.getAllPostByUserId(currentUser.getId()).size());
        return "profile";
    }
    @GetMapping("/update/{userInfoId}")
    public String updateForm(Model model, @PathVariable Long userInfoId){
        UserInfo userInfo = userInfoService.findUserInfoById(userInfoId);
        model.addAttribute("userInfo",userInfo);
        model.addAttribute("userInfoId",userInfoId);
        return "updateUserInfoForm";
    }

    @PostMapping("/edit/{userInfoId}")
    public String editUserInfo(@ModelAttribute ("userInfo") UserInfo userInfo,
                               @PathVariable Long userInfoId){
        userInfoService.update(userInfoId,userInfo);
        return "redirect:/userInfo";
    }

}
