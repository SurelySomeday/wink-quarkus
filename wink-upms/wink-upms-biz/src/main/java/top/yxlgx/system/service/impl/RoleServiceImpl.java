package top.yxlgx.system.service.impl;

import jakarta.enterprise.context.ApplicationScoped;
import top.yxlgx.system.entity.Role;
import top.yxlgx.system.repository.RoleRepository;
import top.yxlgx.system.service.RoleService;
import top.yxlgx.orm.service.BaseServiceImpl;

/**
 * @author yanxin
 * @description
 */
@ApplicationScoped
public class RoleServiceImpl extends BaseServiceImpl<RoleRepository, Role,Long> implements RoleService {
}
