package peaksoft.service;

import peaksoft.entity.User;
import peaksoft.exception.NotFoundException;

import java.util.List;

public interface UserService {
    User signUp(User user) throws NotFoundException;

    List<User> getAllUser();
    User signIn(User user) throws NotFoundException;

    User findById(Long id);
    User findUserByName(String name) throws NotFoundException;
}
