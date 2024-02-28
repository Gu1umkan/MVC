package peaksoft.service;

import peaksoft.entity.User;

import java.util.List;

public interface LikeService {
    void saveLike(Long userId, Long postId);
    void isLikeComment(Long userId, Long commentId);
    int contLike(Long postId);
    List<User> getLikes(Long postId);
}
