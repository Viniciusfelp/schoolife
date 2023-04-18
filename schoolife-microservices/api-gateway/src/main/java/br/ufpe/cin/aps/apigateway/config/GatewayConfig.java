package br.ufpe.cin.aps.apigateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Value("${academic.service.url}")
    private String academicServiceUrl;

    @Value("${atividade.service.url}")
    private String atividadeServiceUrl;

    @Value("${nota.service.url}")
    private String notaServiceUrl;

    @Value("${auth.service.url}")
    private String authServiceUrl;

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/academic/**")
                        .uri(academicServiceUrl))
                .route(r -> r.path("/atividade/**")
                        .uri(atividadeServiceUrl))
                .route(r -> r.path("/notafrequencia/**")
                        .uri(notaServiceUrl))
                .route(r -> r.path("/auth/**")
                        .uri(authServiceUrl))
                .build();
    }
}
