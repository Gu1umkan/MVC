package peaksoft.repo.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Comment;
import peaksoft.entity.Post;
import peaksoft.entity.User;
import peaksoft.repo.CommentRepo;

import java.time.LocalDate;

@Repository
@Transactional
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepo {
    @PersistenceContext
  private final EntityManager entityManager;
    @Override
    public void createComment(User user, Long postId, String comment) {
        Comment comment1 = new Comment();
        comment1.setUser(user);
        comment1.setCreatedAt(LocalDate.now());
        comment1.setComment(comment);
        Post post = entityManager.find(Post.class, postId);
        post.getComments().add(comment1);
    }
}
