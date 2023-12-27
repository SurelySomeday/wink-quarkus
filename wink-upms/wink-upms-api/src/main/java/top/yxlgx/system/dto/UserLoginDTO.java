package top.yxlgx.system.dto;

import lombok.Getter;
import lombok.Setter;
import top.yxlgx.orm.annotation.Query;
import top.yxlgx.orm.data.BaseQuery;

/**
 * @author yanxin
 * @description
 */
@Getter
@Setter
public class UserLoginDTO implements BaseQuery {
    @Query(type = Query.Type.EQUAL)
    private String username;

    private String password;
}
