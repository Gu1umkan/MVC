package peaksoft.repo.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Like;
import peaksoft.entity.Post;
import peaksoft.entity.User;
import peaksoft.repo.LikeRepo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
@RequiredArgsConstructor
public class LikeRepoImpl implements LikeRepo {
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public void saveLike(Long userId, Long postId, Like like) {
//        Post post = entityManager.find(Post.class, postId);
//        User user = entityManager.find(User.class, userId);
//
//        boolean userLikedPost = post.getLikes().stream()
//                .anyMatch(existingLike -> existingLike.getUser().getId().equals(userId));
//        if (userLikedPost) {
//            post.getLikes().removeIf(existingLike -> existingLike.getUser().getId().equals(userId));
//            entityManager.createQuery("delete from Like l where l.user.id =:userId").setParameter("userId",userId);
//        } else {
//            like.setUser(user);
//            like.setPost(post);
//            post.addLike(like);
//            entityManager.persist(like);
//        }
        Post post = entityManager.find(Post.class, postId);
        User user = entityManager.find(User.class, userId);
        boolean islike = true;
        for (Like like1: post.getLikes()){
            if(like1.getUser().getId().equals(userId)){
                like1.setIsLike(false);
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

    public Map<Post,List<User>> getLikes(){
        HashMap<Post,List<User>> hashMap = new HashMap<>();
        List<Post> selectPFromPostP = entityManager.createQuery("select p from Post p ", Post.class).getResultList();
        selectPFromPostP.forEach(post ->{
            List<User> users = entityManager.createQuery(
                            "select l.user from Like l where l.post.id = :postId", User.class)
                    .setParameter("postId", post.getId())
                    .getResultList();
            hashMap.put(post, users);
        });
        return hashMap;
    }
}
