package top.yxlgx.orm.service;


import io.quarkus.hibernate.reactive.panache.PanacheQuery;
import io.smallrye.mutiny.Uni;
import top.yxlgx.orm.data.BaseQuery;
import top.yxlgx.orm.data.Page;

import java.util.List;

/**
 * @author yanxin
 * @Description:
 */
public interface BaseService<T, ID> {

    Uni<List<T>> findAll();

    Uni<List<T>> findAll(PanacheQuery<T> panacheQuery);
    Uni<Page<T>> findAll(PanacheQuery<T> panacheQuery, Page<T> page);
    Uni<Page<T>> findAll(BaseQuery baseQuery, Page<T> page);

    Uni<T> findById(ID id);

    Uni<T> save(T entity);

    Uni<Void> saveAll(Iterable<T> entities);
    Uni<Void> deleteAllById(List<ID> ids);
    Uni<Void> deleteById(ID id);



}
