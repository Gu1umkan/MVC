package peaksoft.repo.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Comment;
import peaksoft.entity.Like;
import peaksoft.entity.Post;
import peaksoft.entity.User;
import peaksoft.repo.LikeRepo;

import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class LikeRepoImpl implements LikeRepo {
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public void saveLike(Long userId, Long postId, Like like) {
        Post post = entityManager.find(Post.class, postId);
        User user = entityManager.find(User.class, userId);
        boolean islike = true;
        for (Like like1: post.getLikes()){
            if(like1.getUser().getId().equals(userId)){
                like1.setUser(null);
                like1.setPost(null);
                post.getLikes().remove(like1);
                entityManager.remove(like1);
                islike = false;
                break;
            }
        }
        if (islike){like.setUser(user);
            post.addLike(like);
            like.setPost(post);
            entityManager.persist(like);}
    }

    public int contLike(Long postId){
        Post post = entityManager.find(Post.class, postId);
        return post.getLikes().size();
    }

    public List<User> getLikes(Long postId){
            return entityManager.createQuery(
                            "select l.user from Like l where l.post.id = :postId", User.class)
                    .setParameter("postId", postId)
                    .getResultList();
    }

    public void isLikeComment(Long userId,Long commentId,Like like){
        Comment comment = entityManager.find(Comment.class, commentId);
        User user = entityManager.find(User.class, userId);
        boolean islike = true;
        for (Like like1: comment.getLikes()){
            if(like1.getUser().getId().equals(userId)){
                like1.setUser(null);
                like1.setPost(null);
                comment.getLikes().remove(like1);
                entityManager.remove(like1);
                islike = false;
                break;
            }
        }
        if (islike){like.setUser(user);
            comment.addLike(like);
            like.setComment(comment);
            entityManager.persist(like);}
    }

}
