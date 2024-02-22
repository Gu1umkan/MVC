package peaksoft.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.entity.User;
import peaksoft.exception.NotFoundException;
import peaksoft.repo.UserRepository;
import peaksoft.service.UserService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private  final UserRepository userRepository;
    @Override
    public User signUp(User user) throws NotFoundException {
        userRepository.sugnUp(user);
        return signIn(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.getAllUser();
    }
    @Override
    public User signIn(User user) throws NotFoundException {
      return   userRepository.signIn(user);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User findUserByName(String name) throws NotFoundException {
        return userRepository.findUserByName(name);
    }


}
