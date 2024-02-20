package peaksoft.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Post;
import peaksoft.service.FollowerService;
import peaksoft.service.PostService;
import peaksoft.service.UserService;

import static peaksoft.controller.UserController.currentUser;

//import static jdk.internal.org.jline.reader.impl.LineReaderImpl.CompletionType.List;

//import static jdk.internal.org.jline.reader.impl.LineReaderImpl.CompletionType.List;

@Controller
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final UserService userService;
    private final FollowerService followerService;



    @GetMapping("/all")
    public String getAllPosts(Model model){
        model.addAttribute("allPost",postService.getAllPost());
        return "/home";
    }

    @GetMapping("/newPost/{userId}")
    public String createPost(Model model,@PathVariable Long userId){
        Post post = new Post();
        model.addAttribute("newPost",post);
        model.addAttribute("userId",userId);
        return "new-post";
    }

    @PostMapping("/save/{userId}")
    public String savePost(@ModelAttribute("newPost") Post post,
                           @PathVariable Long userId
                            , Model model){
        postService.savePost(post,userId);
        model.addAttribute("currentUser ", currentUser);
        model.addAttribute("subcribers", followerService.subcriberSize(currentUser.getFollower().getId()));
        model.addAttribute("subcribtions", followerService.subcriptionSize(currentUser.getFollower().getId()));
        model.addAttribute("post",postService.getAllPostByUserId(currentUser.getId()).size());
        return "profile";
    }


@GetMapping("userPosts/{userId}")
    public String displayPost(Model model, @PathVariable Long userId){
       model.addAttribute("userPost", postService.getAllPostByUserId(userId));
      return "my-post";
    }

}
