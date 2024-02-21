package peaksoft.repo;

import peaksoft.entity.Post;

import java.util.List;

public interface PostRepository {
    void savePost(Post post,Long id);

    List<Post> getAllPost();
    List<Post> getAllPostByUserId(Long id);

    Post findPostById(Long postID);
    void remove(Long userId, Long postID);
    void update(Long postId,Post post);
}
