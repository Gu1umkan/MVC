package peaksoft.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import peaksoft.service.LikeService;
import peaksoft.service.PostService;

import static peaksoft.controller.UserController.currentUser;

@Controller
@RequestMapping("/likes")
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;
    private  final PostService postService;
    @GetMapping("like/{postId}")
    public String isLike(Model model, @PathVariable Long postId){
         likeService.saveLike(currentUser.getId(),postId);
         model.addAttribute("countLike",likeService.contLike(postId));
         model.addAttribute("likePostUser",likeService.getLikes());
         model.addAttribute("allPost",postService.getAllPost());
         return "home";
    }
}
