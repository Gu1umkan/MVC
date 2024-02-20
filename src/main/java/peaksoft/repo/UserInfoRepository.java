package peaksoft.repo;

import peaksoft.entity.UserInfo;

public interface UserInfoRepository {
   UserInfo findUserInfoById(Long  userId);
   public UserInfo update(Long userInfoId,UserInfo newUserInfo);
}
