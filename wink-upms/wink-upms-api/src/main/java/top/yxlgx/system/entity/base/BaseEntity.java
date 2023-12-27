package top.yxlgx.system.entity.base;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import top.yxlgx.core.annotation.Password;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.Timestamp;

/**
 * @author yanxin
 * @Description:
 */
@Getter
@Setter
@MappedSuperclass
//@EntityListeners(BaseEntity.EntityListener.class)
public class BaseEntity extends PanacheEntityBase implements Serializable {
    @Column(name = "create_by", updatable = false)
    private String createBy;

    @Column(name = "update_by")
    private String updateBy;

    @CreationTimestamp
    @Column(name = "create_time", updatable = false)
    private Timestamp createTime;

    @UpdateTimestamp
    @Column(name = "update_time")
    private Timestamp updateTime;

    /* 分组校验 */
    public @interface Create {}

    /* 分组校验 */
    public @interface Update {}

    public static class EntityListener{

        @PreUpdate
        public void onUpdate(BaseEntity entity){
            System.out.println("onUpdate");
        }

        @PrePersist
        public void onPersist(BaseEntity entity){
            System.out.println("onPersist");
        }
    }
}
