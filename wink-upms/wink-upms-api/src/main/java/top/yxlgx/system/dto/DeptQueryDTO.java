package top.yxlgx.system.dto;

import jakarta.ws.rs.QueryParam;
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
public class DeptQueryDTO implements BaseQuery {
    @QueryParam("name")
    @Query(type = Query.Type.INNER_LIKE)
    private String name;

}
