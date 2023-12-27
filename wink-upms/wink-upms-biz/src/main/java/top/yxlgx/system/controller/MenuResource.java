package top.yxlgx.system.controller;

import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.RestPath;
import top.yxlgx.orm.data.Page;
import top.yxlgx.system.dto.MenuQueryDTO;
import top.yxlgx.system.entity.Menu;
import top.yxlgx.system.service.MenuService;

/**
 * @author yanxin
 * @description 菜单管理
 */
@Path("/menu")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MenuResource {

    @Inject
    MenuService menuService;


    @GET
    public Uni<Page<Menu>> list(@BeanParam MenuQueryDTO menuQueryDTO,
                                @BeanParam Page<Menu> page) {
        return menuService.findAll(menuQueryDTO,page);
    }

    @POST
    public Uni<Menu> add(Menu menu) {
        return menuService.save(menu);
    }

    @PUT
    public Uni<Menu> update(Menu menu) {
        return menuService.save(menu);
    }

    @DELETE
    @Path("/{id}")
    public Uni<Void> delete(@RestPath Long id) {
        return menuService.deleteById(id);
    }
}
