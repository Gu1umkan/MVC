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

import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepo {
    @PersistenceContext
  private final EntityManager entityManager;
    @Override
    public void createComment(Long userId, Long postId, Comment comment) {
//        entityManager.getTransaction().begin();
        User user = entityManager.find(User.class, userId);
        Post post = entityManager.find(Post.class, postId);
        user.addComment(comment);
        post.addComment(comment);
        comment.setPost(post);
        comment.setUser(user);
        entityManager.persist(comment);
//        entityManager.getTransaction().commit();
    }

    @Override
    public List<Comment> getCommentsByPostId(Long postId) {
        return entityManager.createQuery("select c from Comment c where c.post.id=:postId",Comment.class).setParameter("postId",postId).getResultList();
    }
}
