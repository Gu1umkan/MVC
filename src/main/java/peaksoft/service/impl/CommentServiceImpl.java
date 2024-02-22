package peaksoft.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.entity.User;
import peaksoft.repo.CommentRepo;
import peaksoft.service.CommentService;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepo commentRepo;
    @Override
    public void createComment(User user, Long postId, String comment) {
     commentRepo.createComment(user,postId,comment);
    }
}
