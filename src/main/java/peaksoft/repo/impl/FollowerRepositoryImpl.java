package peaksoft.repo.impl;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Follower;
import peaksoft.repo.FollowerRepository;

@Repository
@Transactional
@RequiredArgsConstructor
public class FollowerRepositoryImpl implements FollowerRepository {
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public int subcriberSize(Long followerId) {
        Follower follower = entityManager.find(Follower.class, followerId);
        return follower.getSubscribers().size();

    }

    @Override
    public int subcriptionSize(Long followerId) {
        Follower follower = entityManager.find(Follower.class, followerId);
        return follower.getSubscription().size();
    }
}
