package com.tiduswr.hrgateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.GatewayFilterSpec;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.gateway.support.RouteMetadataUtils;

@Configuration
public class GatewayRoutesConfig {

    private static final int GLOBAL_TIMEOUT = 10000;

    @Bean
    RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
            .route("hr-worker-route", r -> buildDeafultPredicateSpec(r, "hr-worker"))
            .route("hr-payroll-route", r -> buildDeafultPredicateSpec(r, "hr-payroll"))
            .route("hr-user-route", r -> buildDeafultPredicateSpec(r, "hr-user"))
            .route("hr-oauth-route", r -> buildDeafultPredicateSpec(r, "hr-oauth"))
            .build();
    }

    private Buildable<Route> buildDeafultPredicateSpec(PredicateSpec r, String serviceName){
        return r.path(String.format("/%s/**", serviceName))
            .filters(f -> f.rewritePath(String.format("/%s/(?<segment>.*)", serviceName), "/$\\{segment}"))            
            .metadata(RouteMetadataUtils.RESPONSE_TIMEOUT_ATTR, GLOBAL_TIMEOUT)
            .metadata(RouteMetadataUtils.CONNECT_TIMEOUT_ATTR, GLOBAL_TIMEOUT)
            .uri(String.format("lb://%s", serviceName.toUpperCase()));
    }

}
