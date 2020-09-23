package br.evoluum.robot.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.service.Contact;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Mychell Teixeira (mychellt@gmail.com)
 * @since 22/09/2020
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Value("${my.application.version}")
    private String applicationVersion;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("br.evoluum.robot.controller"))
                .paths(PathSelectors.regex("/.*"))
                .build().apiInfo(apiEndPointsInfo());
    }

    private ApiInfo apiEndPointsInfo() {
        return new ApiInfoBuilder().title("Evoluum Mars Robot")
                .description("REST Service Mars Robot")
                .contact(new Contact("Mychell", "", "mychell@gmail.com"))
                .version(applicationVersion)
                .build();
    }
}
