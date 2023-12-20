package top.yxlgx.orm.data;

import jakarta.ws.rs.QueryParam;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @author yanxin
 * @description
 */
@Getter
@Setter
public class Page<T> implements Serializable {
    @QueryParam("current")
    private Integer current = 1;
    @QueryParam("size")
    private Integer size = 5;

    private List<T> content;

    private Long total;
    private Long totalPage;

    public Page() {

    }

    public Page(Integer current, Integer size) {
        this.current = current;
        this.size = size;
    }

    public Page(io.quarkus.panache.common.Page page) {
        int i = page.index / page.size;
        int j = page.index % page.size;
        this.current = j == 0 ? i : i + 1;
        this.size = page.size;
    }
}
