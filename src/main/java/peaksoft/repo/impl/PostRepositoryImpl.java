package peaksoft.repo.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Post;
import peaksoft.entity.User;
import peaksoft.repo.PostRepository;

import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository {
    @PersistenceContext
    private final EntityManager entityManager;
    @Override
    public void savePost(Post post,Long userId) {
//        entityManager.getTransaction().begin();
        User user = entityManager.find(User.class, userId);
        post.setCreatedAt(LocalDate.now());
        post.setUser(user);
        entityManager.persist(post);
//        entityManager.getTransaction().commit();
    }

    @Override
    public List<Post> getAllPost() {
        return entityManager.createQuery("select p from Post p ", Post.class).getResultList();
    }
    @Override
    public List<Post> getAllPostByUserId(Long id) {
        return entityManager.createQuery("select p from Post p where user.id = :id", Post.class).setParameter("id",id).getResultList();
    }


}