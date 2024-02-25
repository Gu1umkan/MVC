package peaksoft.service;

import peaksoft.entity.Post;
import peaksoft.entity.User;

import java.util.List;
import java.util.Map;

public interface LikeService {
    void saveLike(Long userId, Long postId);
    void isLikeComment(Long userId, Long commentId);
    int contLike(Long postId);
    Map<Post, List<User>> getLikes();
}
