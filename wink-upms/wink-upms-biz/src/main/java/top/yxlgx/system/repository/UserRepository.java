package top.yxlgx.system.repository;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import top.yxlgx.system.entity.User;

/**
 * @author yanxin
 * @description
 */
@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {

}
