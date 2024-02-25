package peaksoft.repo;

import peaksoft.entity.Like;
import peaksoft.entity.Post;
import peaksoft.entity.User;

import java.util.List;
import java.util.Map;

public interface LikeRepo {
    void saveLike(Long userId, Long postId, Like like);
    void isLikeComment(Long userId,Long commentId,Like like);
    int contLike(Long postId);
    Map<Post, List<User>> getLikes();
}
