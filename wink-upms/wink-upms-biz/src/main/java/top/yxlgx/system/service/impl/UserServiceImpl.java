package top.yxlgx.system.service.impl;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import top.yxlgx.system.entity.User;
import top.yxlgx.system.repository.UserRepository;
import top.yxlgx.system.service.UserService;
import top.yxlgx.orm.service.BaseServiceImpl;

/**
 * @author yanxin
 * @description
 */
@ApplicationScoped
public class UserServiceImpl extends BaseServiceImpl<UserRepository, User,Long> implements UserService {
    @Override
    public Uni<User> findByUserName(String username) {
        return repository.find("username = ?1", username).firstResult();
    }
}
