package peaksoft.repo;

import peaksoft.entity.Post;

import java.util.List;

public interface PostRepository {
    void savePost(Post post,Long id);

    List<Post> getAllPost();
    List<Post> getAllPostByUserId(Long id);
}
