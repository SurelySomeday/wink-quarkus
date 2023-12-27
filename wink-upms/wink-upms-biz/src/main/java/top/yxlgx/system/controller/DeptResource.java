package top.yxlgx.system.controller;

import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.RestPath;
import top.yxlgx.orm.data.Page;
import top.yxlgx.system.dto.DeptQueryDTO;
import top.yxlgx.system.entity.Dept;
import top.yxlgx.system.service.DeptService;

/**
 * @author yanxin
 * @description 角色管理
 */
@Path("/dept")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DeptResource {

    @Inject
    DeptService deptService;


    @GET
    public Uni<Page<Dept>> list(@BeanParam DeptQueryDTO deptQueryDTO,
                                @BeanParam Page<Dept> page) {
        return deptService.findAll(deptQueryDTO,page);
    }

    @POST
    public Uni<Dept> add(Dept dept) {
        return deptService.save(dept);
    }

    @PUT
    public Uni<Dept> update(Dept dept) {
        return deptService.save(dept);
    }

    @DELETE
    @Path("/{id}")
    public Uni<Void> delete(@RestPath Long id) {
        return deptService.deleteById(id);
    }
}
