package com.xcc.promotion.lookup.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.xcc.promotion.lookup"))
                .paths(PathSelectors.regex("/rest/promos.*"))
                .build()
                .apiInfo(loadMetaData());
    }

    private ApiInfo loadMetaData() {
        return new ApiInfo(
                "Promotion Lookup Service",
                "Service to Fetch all active promotion from XCC",
                "1.0", "",
                new Contact("Daniel Raj kumar", "", "danielpremkumar87@gmail.com"),
                "", "", new ArrayList<VendorExtension>()
        );
    }
}
