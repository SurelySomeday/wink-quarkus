package top.yxlgx.system.service.impl;

import jakarta.enterprise.context.ApplicationScoped;
import top.yxlgx.system.entity.Dept;
import top.yxlgx.system.repository.DeptRepository;
import top.yxlgx.system.service.DeptService;
import top.yxlgx.orm.service.BaseServiceImpl;

/**
 * @author yanxin
 * @description
 */
@ApplicationScoped
public class DeptServiceImpl extends BaseServiceImpl<DeptRepository, Dept,Long> implements DeptService {
}
