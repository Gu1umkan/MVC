package peaksoft.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.entity.User;
import peaksoft.repo.FollowerRepository;
import peaksoft.service.FollowerService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowerServiceImpl implements FollowerService {
    private final FollowerRepository followerRepository;

    @Override
    public int subcriberSize(Long followerId) {
        return followerRepository.subcriberSize(followerId);
    }

    @Override
    public int subcriptionSize(Long followerId) {
        return followerRepository.subcriptionSize(followerId);
    }

    @Override
    public void following(Long currentUserId, Long foundUserId) {
        followerRepository.following(currentUserId,foundUserId);
    }

    @Override
    public List<User> getSubscriptionsByUserId(Long userId) {
        return followerRepository.getSubscriptionsByUserId(userId);
    }

    @Override
    public List<User> getSubscribersByUserId(Long userId) {
        return followerRepository.getSubscribersByUserId(userId);
    }
}
