package peaksoft.repo;

import peaksoft.entity.User;
import peaksoft.exception.NotFoundException;

import java.util.List;

public interface UserRepository {
  String sugnUp(User user) throws NotFoundException;
  List<User> getAllUser();
  User signIn(User user) throws NotFoundException;

    User findById(Long id);
}
