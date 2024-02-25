package peaksoft.repo.impl;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Follower;
import peaksoft.entity.User;
import peaksoft.repo.FollowerRepository;

import java.util.List;

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

    @Override
    public void following(Long currentUserId, Long foundUserId) {
//        User currentUser = entityManager.find(User.class, currentUserId);
//        User foundUser = entityManager.find(User.class,foundUserId);
//      if(currentUser.getFollower().getSubscription() == null) {
//          currentUser.getFollower().setSubscription(new ArrayList<>());
//          currentUser.getFollower().setSubscription(new ArrayList<>());
//          foundUser.getFollower().setSubscribers(new ArrayList<>());
//          foundUser.getFollower().setSubscribers(new ArrayList<>());
//      }
//        HashSet<Long> setCurrent = new HashSet<>(currentUser.getFollower().getSubscription());
//        HashSet<Long> setFound = new HashSet<>(currentUser.getFollower().getSubscription());
//
//        setCurrent.add(foundUserId);
//        setFound.add(currentUserId);
//
//        currentUser.getFollower().getSubscription().clear();
//        foundUser.getFollower().getSubscribers().clear();
//
//        currentUser.getFollower().getSubscription().addAll(setCurrent);
//        foundUser.getFollower().getSubscribers().addAll(setCurrent);
        User currentUser = entityManager.find(User.class, currentUserId);
        User foundUser = entityManager.find(User.class, foundUserId);

        List<Long> subscriptions = currentUser.getFollower().getSubscription();
        List<Long> subscribers = foundUser.getFollower().getSubscribers();

        boolean foundUs = false;

        for (Long subscriptionId : subscriptions) {
            if (subscriptionId.equals(foundUserId)){
                subscriptions.remove(subscriptionId);
                foundUs = true;
                break;
            }
        }
        if (!foundUs) subscriptions.add(foundUserId);


        for (Long subscriber : subscribers) {
            if (subscriber.equals(currentUserId)){
                subscribers.remove(subscriber);
                foundUs = true;
                break;
            }
        }
        if (!foundUs) subscribers.add(currentUserId);

    }


}
