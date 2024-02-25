package peaksoft.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Comment;
import peaksoft.entity.Post;
import peaksoft.service.CommentService;
import peaksoft.service.PostService;
import peaksoft.service.UserService;

import static peaksoft.controller.UserController.currentUser;

@Controller
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final PostService postService;
    private final UserService userService;

    @GetMapping("/createComment/{postId}")
    public String createComment(@PathVariable Long postId, Model model){
        model.addAttribute("newComment",new Comment());
        model.addAttribute("postId",postId);
        Post postById = postService.findPostById(postId);
        model.addAttribute("foundPost",postById );
       model.addAttribute("foundPostComments",commentService.getCommentsByPostId(postId));
        return "/comment";
    }
    @PostMapping("/addComment/{postId}")
    public String saveComment(@PathVariable Long postId, @ModelAttribute("newComment") Comment newComment){
        commentService.createComment(currentUser.getId(),postId,newComment);
        return "redirect:/post/all";
    }

}
