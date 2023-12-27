package top.yxlgx.core.rest.filter;

import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;
import top.yxlgx.core.exception.LoginFailedException;

/**
 * @author yanxin
 * @description
 */
@Priority(Priorities.USER - 1)
public class ExceptionMappers {
    @ServerExceptionMapper
    public RestResponse<String> mapException(LoginFailedException x) {

        return RestResponse.ResponseBuilder.create(Response.Status.UNAUTHORIZED, x.msg)
                .type(MediaType.APPLICATION_JSON_TYPE).build();
    }
}
