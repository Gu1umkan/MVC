package peaksoft.repo;

import peaksoft.entity.User;

public interface CommentRepo {
    void createComment(User user, Long postId, String comment);
}
