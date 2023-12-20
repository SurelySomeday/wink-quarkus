package top.yxlgx.system.controller;

import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.RestPath;
import top.yxlgx.orm.data.Page;
import top.yxlgx.system.dto.RoleQueryDTO;
import top.yxlgx.system.entity.Role;
import top.yxlgx.system.service.RoleService;

/**
 * @author yanxin
 * @description 角色管理
 */
@Path("/role")
@Produces(MediaType.APPLICATION_JSON)
public class RoleResource {

    @Inject
    RoleService roleService;

    @GET
    public Uni<Page<Role>> list(@BeanParam RoleQueryDTO roleQueryDTO,
                                @BeanParam Page<Role> page) {
        return roleService.findAll(roleQueryDTO, page);
    }

    @POST
    public Uni<Role> add(Role user) {
        return roleService.save(user);
    }

    @PUT
    public Uni<Role> update(Role user) {
        return roleService.save(user);
    }

    @DELETE
    @Path("/{id}")
    public Uni<Void> delete(@RestPath Long id) {
        return roleService.deleteById(id);
    }
}
