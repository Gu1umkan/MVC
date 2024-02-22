package peaksoft.repo;

public interface FollowerRepository {

    int subcriberSize(Long followerId);
    int subcriptionSize(Long followerId);

    void following(Long currentUserId,Long foundUserId);
}
