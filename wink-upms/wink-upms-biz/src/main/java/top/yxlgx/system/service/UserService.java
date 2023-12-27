package top.yxlgx.system.service;

import io.smallrye.mutiny.Uni;
import top.yxlgx.system.entity.User;
import top.yxlgx.orm.service.BaseService;

/**
 * @author yanxin
 * @description
 */
public interface UserService extends BaseService<User,Long> {

    Uni<User> findByUserName(String username);
}
