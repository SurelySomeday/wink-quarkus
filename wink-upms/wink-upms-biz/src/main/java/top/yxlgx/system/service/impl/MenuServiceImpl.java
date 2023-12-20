package top.yxlgx.system.service.impl;

import jakarta.enterprise.context.ApplicationScoped;
import top.yxlgx.system.entity.Menu;
import top.yxlgx.system.repository.MenuRepository;
import top.yxlgx.system.service.MenuService;
import top.yxlgx.orm.service.BaseServiceImpl;

/**
 * @author yanxin
 * @description
 */
@ApplicationScoped
public class MenuServiceImpl extends BaseServiceImpl<MenuRepository, Menu,Long> implements MenuService {
}
