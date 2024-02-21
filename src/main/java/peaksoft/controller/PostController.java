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

    @GetMapping("/newPost")
    public String createPost(Model model){
        Post post = new Post();
        model.addAttribute("newPost",post);
        return "new-post";
    }

    @PostMapping("/save")
    public String savePost(@ModelAttribute("newPost") Post post, Model model){
        postService.savePost(post,currentUser.getId());
        model.addAttribute("currentUser ", currentUser);
        model.addAttribute("subcribers", followerService.subcriberSize(currentUser.getFollower().getId()));
        model.addAttribute("subcribtions", followerService.subcriptionSize(currentUser.getFollower().getId()));
        model.addAttribute("post",postService.getAllPostByUserId(currentUser.getId()).size());
        model.addAttribute("userPost", postService.getAllPostByUserId(currentUser.getId()));
        return "/my-post";
    }


@GetMapping("userPosts/{userId}")
    public String displayPost(Model model, @PathVariable Long userId){
       model.addAttribute("userPost", postService.getAllPostByUserId(userId));
      return "my-post";
    }

    @GetMapping ("/delete/{postId}")
    public String deleteCompanyById(@PathVariable("postId") Long postId,Model model) {
        postService.remove(currentUser.getId(),postId);
        model.addAttribute("userPost", postService.getAllPostByUserId(currentUser.getId()));
        return "my-post";
    }

    @GetMapping("/update/{postId}")
    public String updateForm(@PathVariable("postId") Long postID, Model model){
        Post post = postService.findPostById(postID);
        model.addAttribute("post",post);
        return "/updatePost";
    }

    @PostMapping("/editUpdate/{postId}")
    public String updateCompany(@ModelAttribute("post") Post post ,@PathVariable ("postId")Long postId){
        postService.update(postId,post);
        return "my-post";
    }

}
