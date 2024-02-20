package peaksoft.service;

import peaksoft.entity.UserInfo;

public interface UserInfoService {
    UserInfo findUserInfoById(Long  userId);
    public UserInfo update(Long userInfoId,UserInfo newUserInfo);


}
