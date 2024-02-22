package peaksoft.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.repo.FollowerRepository;
import peaksoft.service.FollowerService;

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
}
