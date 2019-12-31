package com.trash.collection.trash.user.service.impl;

import com.trash.collection.trash.user.domain.Org;
import com.trash.collection.trash.user.dao.OrgMapper;
import com.trash.collection.trash.user.service.OrgService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 组织架构 服务实现类
 *
 * @author seth
 * @since 2019-12-28
 */
@Service
public class OrgServiceImpl extends ServiceImpl<OrgMapper, Org>implements OrgService {

}
