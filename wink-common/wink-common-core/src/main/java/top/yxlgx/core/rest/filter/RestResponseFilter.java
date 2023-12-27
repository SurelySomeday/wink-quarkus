package top.yxlgx.core.rest.filter;

import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.server.ServerResponseFilter;
import top.yxlgx.core.rest.Result;

/**
 * @author yanxin
 * @description 请求拦截器
 */
@Priority(Priorities.USER -2)
public class RestResponseFilter {

    @ServerResponseFilter
    public void getFilter(ContainerResponseContext responseContext) {
        if(responseContext.getMediaType()!=null && responseContext.getMediaType().isCompatible(MediaType.APPLICATION_JSON_TYPE)){
            Object entity = responseContext.getEntity();
            if(responseContext.getStatus() == Response.Status.OK.getStatusCode()) {
                responseContext.setEntity(Result.success(entity));
            }else{
                responseContext.setEntity(Result.failed(entity != null?entity.toString():""));
            }
        }
    }
}
