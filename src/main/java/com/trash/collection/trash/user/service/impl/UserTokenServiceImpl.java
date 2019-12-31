package com.trash.collection.trash.user.service.impl;

import com.trash.collection.trash.user.domain.UserToken;
import com.trash.collection.trash.user.dao.UserTokenMapper;
import com.trash.collection.trash.user.service.UserTokenService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 系统用户Token 服务实现类
 *
 * @author seth
 * @since 2019-12-28
 */
@Service
public class UserTokenServiceImpl extends ServiceImpl<UserTokenMapper, UserToken>implements UserTokenService {

}
