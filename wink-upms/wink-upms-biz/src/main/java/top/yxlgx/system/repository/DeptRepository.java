package top.yxlgx.system.repository;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import top.yxlgx.system.entity.Dept;

/**
 * @author yanxin
 * @description
 */
@ApplicationScoped
public class DeptRepository implements PanacheRepository<Dept> {

}
