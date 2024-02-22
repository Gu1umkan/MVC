package peaksoft.service;

public interface FollowerService {
    int subcriberSize(Long followerId);
    int subcriptionSize(Long followerId);
    void following(Long currentUserId,Long foundUserId);
}
