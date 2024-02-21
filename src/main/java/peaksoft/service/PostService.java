package peaksoft.service;

import peaksoft.entity.Post;

import java.util.List;

public interface PostService {
    void savePost(Post post,Long id);
    List<Post> getAllPost();
    List<Post> getAllPostByUserId(Long id);

    Post findPostById(Long postID);
    void remove(Long userId,Long postId);

    void update(Long postId, Post post);
}
