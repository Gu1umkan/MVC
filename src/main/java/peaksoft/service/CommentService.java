package peaksoft.service;

import peaksoft.entity.Comment;

import java.util.List;

public interface CommentService {
    void createComment(Long  userId, Long postId, Comment comment);
    List<Comment> getCommentsByPostId(Long postId);
    void deleteComment(Long commentId);
    void updateComment(Long commentId, Comment newComment);
}
