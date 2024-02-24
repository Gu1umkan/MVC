package peaksoft.repo;

import peaksoft.entity.Comment;

import java.util.List;

public interface CommentRepo {
    void createComment(Long userId, Long postId, Comment comment);
    List<Comment> getCommentsByPostId(Long postId);
}
