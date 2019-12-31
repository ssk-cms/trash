package com.trash.collection.trash.user.service.impl;

import com.trash.collection.trash.user.domain.UserOrg;
import com.trash.collection.trash.user.dao.UserOrgMapper;
import com.trash.collection.trash.user.service.UserOrgService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 用户与组织机构对应关系 服务实现类
 *
 * @author seth
 * @since 2019-12-28
 */
@Service
public class UserOrgServiceImpl extends ServiceImpl<UserOrgMapper, UserOrg>implements UserOrgService {

}
