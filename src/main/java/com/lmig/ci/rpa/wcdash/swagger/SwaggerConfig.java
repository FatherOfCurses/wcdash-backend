package com.lmig.ci.rpa.wcdash.swagger;

import io.swagger.annotations.Contact;
import io.swagger.annotations.ExternalDocs;
import io.swagger.annotations.Info;
import io.swagger.annotations.SwaggerDefinition;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Configuration
@EnableSwagger2

public class SwaggerConfig {
        private static final String API_REGEX = "/api.*";
        public static final int CURRENT_YEAR = LocalDate.now().getYear();
        public static final String COPYRIGHT = String.format("Copyright (C) %d, Liberty Mutual Insurance", CURRENT_YEAR);


        @Value("${spring.application.name}")
        private String applicationName;

        @Value("${swagger.api.description:}")
        private String apiDescription;

        @Bean
        public ApiInfo swaggerApiInfo() {
                return swaggerApiInfo(applicationName, apiDescription);
        }

        /**
         * Generate ApiInfo for any custom dockets you have.
         *
         * @param title The title of the docket.
         * @param description The description the user will see.
         * @return An ApiInfo with the title/description/copyright/version and license filled.
         */
        public ApiInfo swaggerApiInfo(String title, String description) {
                return new ApiInfoBuilder()
                        .title("Blue Prism Reporting API")
                        .description("Serving data from Blue Prism transaction database for reporting purposes.")
                        .version("1.0.0")
                        .license(COPYRIGHT)

                        .build();
        }

        /**
         * Swagger Springfox configuration.
*/
        @Bean
        public Docket swaggerSpringfoxDocketApi() {

                return new Docket(DocumentationType.SWAGGER_2)
                        .groupName("UserOperations")
                        .apiInfo(swaggerApiInfo())
                        .directModelSubstitute(LocalDate.class, java.sql.Date.class)
                        .directModelSubstitute(LocalDateTime.class, java.util.Date.class)
                        .select()
                        .apis(RequestHandlerSelectors.any())
                        .paths(PathSelectors.regex(API_REGEX))
                        .build();
        }


}
