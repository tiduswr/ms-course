package com.tiduswr.hrgateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import static org.springframework.cloud.gateway.support.RouteMetadataUtils.CONNECT_TIMEOUT_ATTR;
import static org.springframework.cloud.gateway.support.RouteMetadataUtils.RESPONSE_TIMEOUT_ATTR;

@Configuration
public class GatewayRoutesConfig {

    private final static int GLOBAL_TIMEOUT = 10000;

    @Bean
    RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
            .route("hr-worker-route", r -> r
                .path("/workers/**")
                .metadata(RESPONSE_TIMEOUT_ATTR, GLOBAL_TIMEOUT)
                .metadata(CONNECT_TIMEOUT_ATTR, GLOBAL_TIMEOUT)
                .uri("lb://HR-WORKER"))
            .route("hr-payroll-route", r -> r
                .path("/payments/**")
                .metadata(RESPONSE_TIMEOUT_ATTR, GLOBAL_TIMEOUT)
                .metadata(CONNECT_TIMEOUT_ATTR, GLOBAL_TIMEOUT)
                .uri("lb://HR-PAYROLL"))
            .build();
    }
}
