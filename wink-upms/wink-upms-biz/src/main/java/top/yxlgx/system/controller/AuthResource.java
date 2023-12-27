package top.yxlgx.system.controller;

import cn.hutool.core.bean.BeanUtil;
import io.quarkus.elytron.security.common.BcryptUtil;
import io.smallrye.jwt.build.Jwt;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.unchecked.Unchecked;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.jwt.JsonWebToken;
import top.yxlgx.core.exception.LoginFailedException;
import top.yxlgx.orm.data.Page;
import top.yxlgx.system.dto.UserLoginDTO;
import top.yxlgx.system.entity.Role;
import top.yxlgx.system.entity.User;
import top.yxlgx.system.service.UserService;
import top.yxlgx.system.vo.UserInfoVO;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yanxin
 * @description
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/auth")
public class AuthResource {

    @Inject
    JsonWebToken jwt;

    @Inject
    UserService userService;

    /**
     * 登陆
     * @param userLoginDTO
     * @return
     */
    @POST
    @PermitAll
    @Path("/login")
    public Uni<String> login(UserLoginDTO userLoginDTO) {
        Uni<Page<User>> result = userService.findAll(userLoginDTO, Page.of(1, 1));
        return result.chain(Unchecked.function(page -> {
            List<User> content = page.getContent();
            //查找用户
            if(content != null && !content.isEmpty()){
                User user = content.get(0);
                if(BcryptUtil.matches(userLoginDTO.getPassword(), user.getPassword())){
                    String token = Jwt.issuer("https://yxlgx.top")
                            .subject(user.getUsername())
                            .groups(user.getRoles().stream().map(Role::getName).collect(Collectors.toSet()))
                            .sign();
                    return Uni.createFrom().item(token);
                }
                throw new LoginFailedException("用户名或密码错误");
            }
            throw new LoginFailedException("用户名或密码错误");
        }));
    }

    /**
     * 获取用户信息
     * @return
     */
    @GET
    @Path("/info")
    public Uni<UserInfoVO> info() {
        Uni<User> result = userService.findByUserName(jwt.getSubject());
        return result.map(user -> {
            UserInfoVO userInfoVO = new UserInfoVO();
            BeanUtil.copyProperties(user,userInfoVO);
            return userInfoVO;
        });
    }

}
