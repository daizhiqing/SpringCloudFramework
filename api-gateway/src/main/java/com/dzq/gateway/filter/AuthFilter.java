package com.dzq.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.RedisServer;

import javax.servlet.http.HttpServletRequest;

@Component
public class AuthFilter extends ZuulFilter {

    private  Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private RedisServer redisServer;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        //通过request可以获取到很多请求的信息，校验修改

        /**
         *
        这里设置401不做API网关将不再进行请求转发到后续服务
        context.setSendZuulResponse(false);
        context.setResponseStatusCode(401);
        context.setResponseBody("<script>window.location='https://daizhiqing.github.io'</script>");
         */
        return null;
    }
}
