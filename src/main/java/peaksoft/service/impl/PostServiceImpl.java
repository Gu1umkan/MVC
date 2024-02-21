package peaksoft.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.entity.Image;
import peaksoft.entity.Post;
import peaksoft.entity.User;
import peaksoft.repo.PostRepository;
import peaksoft.repo.UserRepository;
import peaksoft.service.PostService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Override
    public void savePost(Post post, Long id) {
        String imageUrl = post.getImageURL();
        Image image = new Image();
        image.setImageURL(imageUrl);
        image.setPost(post);
        post.addImage(image);
        postRepository.savePost(post, id);
    }

    @Override
    public List<Post> getAllPost() {
        return postRepository.getAllPost();
    }

    @Override
    public List<Post> getAllPostByUserId(Long id) {
        return postRepository.getAllPostByUserId(id);
    }

    @Override
    public Post findPostById(Long postID) {
        return postRepository.findPostById(postID);
    }

    @Override @Transactional
    public void remove(Long userId, Long postId) {
        Post post = findPostById(postId);
        User user = userRepository.findById(userId);
        post.setUser(null);
        user.getPosts().remove(post);
        postRepository.remove(userId, postId);
    }

    @Override
    @Transactional
    public void update(Long postId, Post post) {
        System.out.println("Test");
        Post foundPost = postRepository.findPostById(postId);
        System.out.println("foundPost.getId() = " + foundPost.getId());
        foundPost.setDescription(post.getDescription());
        foundPost.setTitle(post.getTitle());
        System.out.println("test");
    }


}
