package peaksoft.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.entity.UserInfo;
import peaksoft.repo.UserInfoRepository;
import peaksoft.service.UserInfoService;

@Service
@RequiredArgsConstructor
public class UserInfoServiceImpl implements UserInfoService {
    private final UserInfoRepository userInfoRepository;


    @Override
    public UserInfo findUserInfoById(Long id) {
        return userInfoRepository.findUserInfoById(id);
    }

    @Override
    public UserInfo update(Long user, UserInfo userInfo) {
        return userInfoRepository.update(user,userInfo);
    }
}
