package peaksoft.repo.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import peaksoft.entity.UserInfo;
import peaksoft.repo.UserInfoRepository;

@Repository
@Transactional
@RequiredArgsConstructor
public class UserInfoRepositoryImpl implements UserInfoRepository {
    @PersistenceContext
    private final EntityManager entityManager;
    @Override
    public UserInfo findUserInfoById(Long id) {
        return entityManager.find(UserInfo.class,id);
    }

    @Override
    public UserInfo update(Long userInfoId,UserInfo newUserInfo) {
        UserInfo current = entityManager.find(UserInfo.class, userInfoId);
        current.setFullName(newUserInfo.getFullName());
        current.setBiography(newUserInfo.getBiography());
        current.setImage(newUserInfo.getImage());
        current.setGender(newUserInfo.getGender());
        return current;
    }


}
