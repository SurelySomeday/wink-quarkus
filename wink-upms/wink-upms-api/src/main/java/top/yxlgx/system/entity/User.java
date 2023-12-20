package top.yxlgx.system.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import top.yxlgx.system.entity.base.BaseEntity;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * @author yanxin
 * @Description: 用户
 */
@Getter
@Setter
@Entity
@Table(name = "sys_user", indexes = {
        @Index(name = "sys_user_username",columnList = "username", unique = true)
    }
)
@NamedEntityGraph(
        name = "user.all",
        attributeNodes =  {
                @NamedAttributeNode(value = "dept")
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "dept-details",
                        attributeNodes = {
                                @NamedAttributeNode("children")
                        }
                ),
                @NamedSubgraph(
                        name = "role-details",
                        attributeNodes = {
                                @NamedAttributeNode("users"),
                                @NamedAttributeNode("depts"),
                                @NamedAttributeNode("menus")
                        }
                )
        }
)
@SQLDelete(sql = "update sys_user set deleted = 1 where user_id = ?")
@Where(clause = "deleted = 0")
@JsonIgnoreProperties("password")
public class User extends BaseEntity implements Serializable {
    @Id
    @Column(name = "user_id")
    @GeneratedValue
    @Comment("主键")
    private Long userId;

    /**
     * 真实姓名
     */
    @Comment("真实姓名")
    private String realName;

    /**
     * 年龄
     */
    @Comment("年龄")
    private Integer age;

    /**
     * 用户名
     */
    @Comment("用户名")
    String username;

    /**
     * 密码
     */
    @Comment("密码")
    private String password;

    /**
     * 头像
     */
    @Comment("头像")
    private String avatar;

    /**
     * 是否删除
     */
    @Column(name = "deleted")
    @Comment("是否删除")
    private Integer deleted=0;

    /**
     * 角色列表
     */
    @ManyToMany(cascade= CascadeType.DETACH,fetch=FetchType.EAGER)
    @JoinTable(name = "sys_users_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "role_id")}
    )
    private Set<Role> roles;


    /**
     * 用户部门
     */
    @OneToOne
    @JoinColumn(name = "dept_id")
    private Dept dept;

    @PreRemove
    public void deleteUser() {
        this.deleted = 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(userId, user.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}
