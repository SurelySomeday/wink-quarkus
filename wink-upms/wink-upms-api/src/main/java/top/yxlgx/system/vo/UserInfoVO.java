package top.yxlgx.system.vo;

import lombok.Getter;
import lombok.Setter;
import top.yxlgx.system.entity.Dept;
import top.yxlgx.system.entity.Role;

import java.util.Set;

/**
 * @author yanxin
 * @description
 */
@Getter
@Setter
public class UserInfoVO {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 用户名
     */
    String username;

    /**
     * 头像
     */
    private String avatar;


    /**
     * 角色列表
     */
    private Set<Role> roles;


    /**
     * 用户部门
     */
    private Dept dept;
}
