package peaksoft.repo.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Follower;
import peaksoft.entity.User;
import peaksoft.entity.UserInfo;
import peaksoft.exception.NotFoundException;
import peaksoft.repo.UserRepository;

import java.util.List;

@Transactional
@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public String sugnUp(User user) throws NotFoundException {
        try {
            Follower follower = new Follower();
            UserInfo userInfo = new UserInfo();
            user.setUserInfo(userInfo);
            user.setFollower(follower);
            entityManager.persist(user);
           return "Success";
        } catch (Exception e) {
            throw new NotFoundException(e.getMessage());
        }
    }

    @Override
    public List<User> getAllUser() {
        return entityManager.createQuery("select u from User u ",
                User.class).getResultList();
    }

    @Override
    public User signIn(User user) throws NotFoundException {
        try{
       return   entityManager.createQuery("select u from User u " +
                "where userName = :userName and password = :password",User.class)
                .setParameter("userName",user.getUserName())
                .setParameter("password",user.getPassword()).getSingleResult();
        }catch (Exception e){
            throw new NotFoundException("Not found user!");
        }
    }

    @Override
    public User findById(Long id) {
        return entityManager.find(User.class, id);
    }

    public User findUserById(Long id){
       return entityManager.find(User.class,id);
     }

}
