package br.com.feltex.clienteapi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ServerBuilder;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;

@SpringBootApplication
//@OpenAPIDefinition(
//        info = @Info(title = "API de Clientes", version = "3.0.1", description = "API de acesso aos dados de clientes"),
//        servers =
//                {
//                        @Server(url = "http://cliente-api"),
//                        @Server(url = "http://localhost:8080")
//                }
//
//)
@Slf4j
public class ClienteApiApplication {

    private static final String ACCESS_CONTROL_ALLOW_ORIGIN = "Access-Control-Allow-Origin";

    public static void main(String[] args) {
        SpringApplication.run(ClienteApiApplication.class, args);
    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200", "http://cliente-ui"));
        corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", ACCESS_CONTROL_ALLOW_ORIGIN, "Content-Type",
                "Accept", "Authorization", "Origin, Accept", "X-Requested-With",
                "Access-Control-Request-Method", "Access-Control-Request-Headers"));
        corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization",
                ACCESS_CONTROL_ALLOW_ORIGIN, ACCESS_CONTROL_ALLOW_ORIGIN, "Access-Control-Allow-Credentials"));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }

//    @Bean
//    public Docket clienteApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.any())
//                .build()
//                .pathMapping("/")
//                .directModelSubstitute(LocalDate.class, String.class)
//                .genericModelSubstitutes(ResponseEntity.class)
//                .globalResponses(HttpMethod.GET,
//                        singletonList(new ResponseBuilder()
//                                .code(HttpStatus.INTERNAL_SERVER_ERROR.value() + "")
//                                .description(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
//                                .representation(MediaType.APPLICATION_JSON)
//                                .apply(r ->
//                                        r.model(m ->
//                                                m.referenceModel(ref ->
//                                                        ref.key(k ->
//                                                                k.qualifiedModelName(q ->
//                                                                        q.namespace("some:namespace")
//                                                                                .name("ERROR"))))))
//                                .build()))
//                .enableUrlTemplating(true)
//                .globalRequestParameters(
//                        singletonList(new RequestParameterBuilder()
//                                .name("someGlobalParameter")
//                                .description("Description of someGlobalParameter")
//                                .in(ParameterType.QUERY)
//                                .required(true)
//                                .query(q -> q.model(m -> m.scalarModel(ScalarType.STRING)))
//                                .build()))
//                .tags(new Tag("Pet Service", "All apis relating to pets"));
//    }

    @Bean
    public Docket publicApi() {

        return new Docket(DocumentationType.OAS_30)
                .apiInfo(new ApiInfoBuilder()
                        .description("API de acesso aos dados de clientes")
                        .title("API de Clientes")
                        .version("3.0.1")
                        .build())
                .host("clienteUI")
                .servers(new ServerBuilder()
                                .url("http://cliente-api")
                                .build(),
                        new ServerBuilder()
                                .url("http://localhost:8080")
                                .build(),
                        new ServerBuilder()
                                .url("http://feltex.com.br")
                                .build());

    }
}

//https://springdoc.org/
//https://medium.com/javarevisited/api-documentation-using-swagger-3-with-spring-boot-2-spring-security-5a0d2b0996ee