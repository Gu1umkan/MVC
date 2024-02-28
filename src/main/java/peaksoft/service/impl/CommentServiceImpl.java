package peaksoft.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.entity.Comment;
import peaksoft.repo.CommentRepo;
import peaksoft.service.CommentService;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepo commentRepo;
    @Override
    public void createComment(Long userId, Long postId, Comment comment) {
        comment.setCreatedAt(LocalDate.now());
        commentRepo.createComment(userId,postId,comment);
    }

    @Override
    public List<Comment> getCommentsByPostId(Long postId) {
        return commentRepo.getCommentsByPostId(postId);
    }

    @Override
    public void deleteComment(Long commentId) {
        commentRepo.deleteComment(commentId);
    }

    @Override
    public void updateComment(Long commentId, Comment newComment) {
        commentRepo.updateComment(commentId, newComment);
    }
}
