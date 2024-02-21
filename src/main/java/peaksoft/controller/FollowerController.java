package peaksoft.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.service.FollowerService;
import peaksoft.service.UserService;

@Controller
@RequestMapping("follower")
@RequiredArgsConstructor
public class FollowerController {
    private final FollowerService followerService;
    private final UserService userService;
    @GetMapping("/search")
    public String findUser(Model model){
//       model.addAttribute("searchName", name);
        return "search";
    }

    @GetMapping("/searchUser")
    public String search(Model model, @RequestParam("searchName") String searchName){
        model.addAttribute("foundUser",userService.findUserByName(searchName));
        return "foundUser";
    }
}
