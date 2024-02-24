package peaksoft.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.entity.Like;
import peaksoft.entity.Post;
import peaksoft.entity.User;
import peaksoft.repo.LikeRepo;
import peaksoft.service.LikeService;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {
    private final LikeRepo likeRepo;

    @Override
    public void saveLike(Long userId, Long postId) {
        Like like = new Like();
        like.setIsLike(true);
        likeRepo.saveLike(userId,postId,like);
    }

    @Override
    public int contLike(Long postId) {
        return likeRepo.contLike(postId);
    }

    @Override
    public Map<Post, List<User>> getLikes() {
        return likeRepo.getLikes();
    }
}
