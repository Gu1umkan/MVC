package peaksoft.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import peaksoft.entity.User;
import peaksoft.service.LikeService;
import peaksoft.service.PostService;

import java.util.List;

import static peaksoft.controller.UserController.currentUser;

@Controller
@RequestMapping("/likes")
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;
    private  final PostService postService;
    @GetMapping("/like/{postId}")
    public String isLike(Model model, @PathVariable Long postId){
         likeService.saveLike(currentUser.getId(),postId);
//         model.addAttribute("countLike",likeService.contLike(postId));
//         model.addAttribute("likePostUser",likeService.getLikes());
//         model.addAttribute("allPost",postService.getAllPost());
         return "redirect:/post/all";
    }
    @GetMapping("/likeComment/{commentId}")
    public String isLikeComment(Model model, @PathVariable Long commentId){
        likeService.isLikeComment(currentUser.getId(),commentId);
      //  model.addAttribute("countLike",likeService.contLike(postId));
       // model.addAttribute("likePostUser",likeService.getLikes());
        //model.addAttribute("allPost",postService.getAllPost());
        return "redirect:/post/all";
    }

    @GetMapping("/getLikes/{postId}")
    public String getLikes(@PathVariable Long postId, Model model){
        List<User> users = likeService.getLikes(postId);
        model.addAttribute("users", users);
        model.addAttribute("userId", currentUser.getId());
        return "likesUsers";
    }
}
