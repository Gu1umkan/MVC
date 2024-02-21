package peaksoft.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.entity.Image;
import peaksoft.entity.Post;
import peaksoft.repo.PostRepository;
import peaksoft.service.PostService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    @Override
    public void savePost(Post post,Long id) {
        String imageUrl = post.getImageURL();
        Image image = new Image();
        image.setImageURL(imageUrl);
        image.setPost(post);
        post.addImage(image);
        postRepository.savePost(post,id);
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

    @Override
    public void remove(Long userId,Long postId) {
    postRepository.remove(userId,postId);
    }

    @Override
    public void update(Long postId, Post post) {
      postRepository.update(postId,post);
    }


}
