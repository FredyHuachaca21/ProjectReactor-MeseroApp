package com.pe.fhuachaca.app.springreactor.exceptions;

import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Component
@Order(-1)
public class GlobalErrorWebExceptionHandler extends AbstractErrorWebExceptionHandler {
    public GlobalErrorWebExceptionHandler(ErrorAttributes errorAttributes,
                                          ResourceProperties resourceProperties,
                                          ApplicationContext applicationContext,
                                          ServerCodecConfigurer configurer) {
        super(errorAttributes, resourceProperties, applicationContext);
        this.setMessageWriters(configurer.getWriters());
    }

    /*public GlobalErrorWebExceptionHandler(ErrorAttributes errorAttributes,
                                            WebProperties.Resources resources,
                                            ApplicationContext applicationContext) {
        super(errorAttributes, resources, applicationContext);
    }*/

    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
        return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
    }

    private Mono<ServerResponse> renderErrorResponse(ServerRequest request){

        Map<String, Object> errorGeneral = getErrorAttributes(request, true);
        Map<String, Object> mapExceptions = new HashMap<>();

        var httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        String statusCode = String.valueOf(errorGeneral.get("status"));

        switch (statusCode){
            case "500":
                mapExceptions.put("error", "500");
                mapExceptions.put("Exception", "Error general del Backend");
                httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
                break;
            case "400":
                mapExceptions.put("error", "400");
                mapExceptions.put("Exception", "Petición Incorrecta");
                httpStatus = HttpStatus.BAD_REQUEST;
                break;
            default:
                mapExceptions.put("error", "900");
                mapExceptions.put("Exception", errorGeneral.get("error"));
                httpStatus = HttpStatus.CONFLICT;
                break;

        }
        return ServerResponse.status(httpStatus)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(mapExceptions));
    }
}
