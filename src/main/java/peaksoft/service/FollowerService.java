package peaksoft.service;

import peaksoft.entity.User;

import java.util.List;

public interface FollowerService {
    int subcriberSize(Long followerId);
    int subcriptionSize(Long followerId);
    void following(Long currentUserId,Long foundUserId);
    List<User> getSubscriptionsByUserId(Long userId);
    List<User> getSubscribersByUserId(Long userId);
}
