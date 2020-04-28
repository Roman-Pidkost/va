package roman.pidkostelnyi.victoriaarmario.config;

import com.google.common.collect.Sets;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("roman.pidkostelnyi.victoriaarmario.controller"))
                .build()
                .securitySchemes(Collections.singletonList(apiKey()))
                .consumes(Sets.newHashSet(MediaType.APPLICATION_JSON_VALUE))
                .produces(Sets.newHashSet(MediaType.APPLICATION_JSON_VALUE));
    }


    private ApiKey apiKey() {
        return new ApiKey("token", "Authorization", "header");
    }
}