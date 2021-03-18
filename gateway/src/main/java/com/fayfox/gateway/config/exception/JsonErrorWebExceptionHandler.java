package com.fayfox.gateway.config.exception;

import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.DefaultErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.web.reactive.function.server.*;

import java.util.HashMap;
import java.util.Map;

public class JsonErrorWebExceptionHandler extends DefaultErrorWebExceptionHandler {
    public JsonErrorWebExceptionHandler(
            ErrorAttributes errorAttributes,
            ResourceProperties resourceProperties,
            ErrorProperties errorProperties,
            ApplicationContext applicationContext) {
        super(errorAttributes, resourceProperties, errorProperties, applicationContext);
    }

    @Override
    protected Map<String, Object> getErrorAttributes(ServerRequest request, boolean includeStackTrace) {
        // 可以根据异常类型进行定制化逻辑
        // 现在就先直接返回报错信息好了
        Throwable error = super.getError(request);
        Map<String, Object> errorAttributes = new HashMap<>();
        errorAttributes.put("msg", error.getMessage());
        errorAttributes.put("code", 10001);
        errorAttributes.put("data", null);
        errorAttributes.put("alert", null);

        return errorAttributes;
    }

    /**
     * 没看懂这个方法和父类方法的区别，但是不能少
     */
    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
        return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
    }

    @Override
    protected int getHttpStatus(Map<String, Object> errorAttributes) {
        // 这里拿到的就是上面方法里的Map，先固定返回500
        System.out.println(errorAttributes.get("msg"));
        return 500;
    }
}
