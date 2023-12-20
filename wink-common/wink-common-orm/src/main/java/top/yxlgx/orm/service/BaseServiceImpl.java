package top.yxlgx.orm.service;

import io.quarkus.hibernate.reactive.panache.PanacheQuery;
import io.quarkus.hibernate.reactive.panache.PanacheRepositoryBase;
import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.quarkus.panache.hibernate.common.runtime.PanacheJpaUtil;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;
import org.hibernate.reactive.mutiny.Mutiny;
import org.hibernate.reactive.stage.Stage;
import top.yxlgx.orm.data.BaseQuery;
import top.yxlgx.orm.data.Page;
import top.yxlgx.orm.util.QueryHelp;

import java.awt.print.Book;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import static io.quarkus.hibernate.reactive.panache.runtime.JpaOperations.INSTANCE;

/**
 * @author yanxin
 * @Description:
 */
@WithSession
@SuppressWarnings("unchecked")
public class BaseServiceImpl<R extends PanacheRepositoryBase<T,ID>,T,ID> implements BaseService<T, ID> {

    @Inject
    protected R repository;

    @Inject
    Mutiny.SessionFactory sessionFactory;


    @Override
    public Uni<List<T>> findAll() {
        return repository.findAll().list();
    }

    @Override
    public Uni<List<T>> findAll(PanacheQuery<T> panacheQuery) {
        return panacheQuery.list();
    }

    @Override
    public Uni<Page<T>> findAll(PanacheQuery<T> panacheQuery, Page<T> page) {
        if(page.getCurrent()<=0){
            page.setCurrent(1);
        }
        if(page.getSize()<=0){
            page.setSize(5);
        }
        Uni<Long> count = panacheQuery.count();
        Uni<List<T>> list = panacheQuery.page(
                io.quarkus.panache.common.Page.of((page.getCurrent()-1)*page.getSize(), page.getSize())
        ).list();
        return Uni.combine().all().unis(count, list).with((c, l) -> {
            Page<T> pageList = new Page<>();
            pageList.setCurrent(page.getCurrent());
            pageList.setSize(page.getSize());
            pageList.setTotal(c);
            pageList.setContent(l);
            pageList.setTotalPage(c % page.getSize() == 0 ? c / page.getSize() : c / page.getSize() + 1);
            return pageList;
        });
    }

    @Override
    public Uni<Page<T>> findAll(BaseQuery baseQuery, Page<T> page) {
        Class<T> entityClazz = getEntityClazz();

        //查询
        Uni<Long> count = sessionFactory.openSession().chain(session -> {
            //获取CriteriaBuilder
            CriteriaBuilder criteriaBuilder = session.getFactory().getCriteriaBuilder();
            CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);
            Root<T> root = query.from(entityClazz);
            //获取构造条件
            Predicate predicate = QueryHelp.getPredicate(root, baseQuery, criteriaBuilder);

            //统计
            CriteriaQuery<Long> criteriaQuery = query.where(predicate).select(criteriaBuilder.count(root));
            return session.createQuery(criteriaQuery).getSingleResult().eventually(session::close);
        });
        Uni<List<T>> list = sessionFactory.openSession().chain(session -> {
            //获取CriteriaBuilder
            CriteriaBuilder criteriaBuilder = session.getFactory().getCriteriaBuilder();
            CriteriaQuery<T> query = criteriaBuilder.createQuery(entityClazz);
            Root<T> root = query.from(entityClazz);
            //获取构造条件
            Predicate predicate = QueryHelp.getPredicate(root, baseQuery, criteriaBuilder);
            CriteriaQuery<T> criteriaQuery = query.where(predicate);

            return session.createQuery(criteriaQuery)
                    .setMaxResults(page.getSize())
                    .setFirstResult((page.getCurrent() - 1) * page.getSize())
                    .getResultList().eventually(session::close);
        });
        return Uni.combine().all().unis(count, list).with((c, l) -> {
            Page<T> pageList = new Page<>();
            pageList.setCurrent(page.getCurrent());
            pageList.setSize(page.getSize());
            pageList.setTotal(c);
            pageList.setContent(l);
            pageList.setTotalPage(c % page.getSize() == 0 ? c / page.getSize() : c / page.getSize() + 1);
            return pageList;
        });
    }

    @Override
    public Uni<T> findById(ID id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public Uni<T> save(T entity) {
        return repository.persist(entity);
    }

    @Transactional
    @Override
    public Uni<Void> saveAll(Iterable<T> entities) {
        return repository.persist(entities);
    }

    @Transactional
    @Override
    public Uni<Void> deleteAllById(List<ID> ids) {
        Class<T> actualTypeArgument = getEntityClazz();
        return repository.getSession().chain(
                session -> session.createQuery("DELETE FROM " + PanacheJpaUtil.getEntityName(actualTypeArgument))
                        .executeUpdate()).chain(v -> null);
    }

    @Transactional
    @Override
    public Uni<Void> deleteById(ID id) {
        return repository.deleteById(id).map(v -> null);
    }

    private Class<T> getEntityClazz(){
        Type genericSuperclass = getClass().getSuperclass().getGenericSuperclass();
        if(genericSuperclass instanceof ParameterizedType parameterizedType){
            return (Class<T>)parameterizedType.getActualTypeArguments()[1];
        }
        throw new IllegalArgumentException("cannot found entity class");

    }
}
