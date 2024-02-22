package peaksoft.service;

import peaksoft.entity.User;

public interface CommentService {
    void createComment(User user,Long postId,String comment);
}
