package top.yxlgx.system.controller;

import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.RestPath;
import top.yxlgx.orm.data.Page;
import top.yxlgx.system.dto.UserQueryDTO;
import top.yxlgx.system.entity.User;
import top.yxlgx.system.service.UserService;

/**
 * @author yanxin
 * @description 角色管理
 */
@Path("/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    UserService userService;


    @GET
    public Uni<Page<User>> list(@BeanParam UserQueryDTO userQueryDTO,
                                @BeanParam Page<User> page) {
        return userService.findAll(userQueryDTO,page);
    }

    @POST
    public Uni<User> add(User user) {
        return userService.save(user);
    }

    @PUT
    public Uni<User> update(User user) {
        return userService.save(user);
    }

    @DELETE
    @Path("/{id}")
    public Uni<Void> delete(@RestPath Long id) {
        return userService.deleteById(id);
    }
}
