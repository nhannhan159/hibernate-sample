package com.nhannhan159.weather.web.config;

import com.fasterxml.classmate.TypeResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.*;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebFlux;

import java.lang.reflect.WildcardType;
import java.time.LocalDate;

import static java.util.Collections.singletonList;
import static springfox.documentation.schema.AlternateTypeRules.newRule;

/**
 * @author tien.tan
 */
@RequiredArgsConstructor
@Configuration
@EnableSwagger2WebFlux
@EnableConfigurationProperties(SwaggerConfigProperties.class)
public class SwaggerConfig {

    private final TypeResolver typeResolver;
    private final SwaggerConfigProperties properties;

    @Bean
    Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo())
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any())
            .build()
            .pathMapping("/")
            .directModelSubstitute(LocalDate.class, String.class)
            .genericModelSubstitutes(ResponseEntity.class)
            .alternateTypeRules(
                newRule(typeResolver.resolve(DeferredResult.class,
                    typeResolver.resolve(ResponseEntity.class, WildcardType.class)),
                    typeResolver.resolve(WildcardType.class)))
            .useDefaultResponseMessages(false)
            .globalResponseMessage(RequestMethod.GET,
                singletonList(new ResponseMessageBuilder()
                    .code(500)
                    .message("500 message")
                    .responseModel(new ModelRef("Error"))
                    .build()))
            .enableUrlTemplating(true)
            .globalOperationParameters(
                singletonList(new ParameterBuilder()
                    .name("someGlobalParameter")
                    .description("Description of someGlobalParameter")
                    .modelRef(new ModelRef("string"))
                    .parameterType("query")
                    .required(true)
                    .build()))
            .tags(new Tag("Weather Service", "Demo service for spring webflux"));
    }

    @Bean
    UiConfiguration uiConfig() {
        return UiConfigurationBuilder.builder()
            .deepLinking(true)
            .displayOperationId(false)
            .defaultModelsExpandDepth(1)
            .defaultModelExpandDepth(1)
            .defaultModelRendering(ModelRendering.EXAMPLE)
            .displayRequestDuration(false)
            .docExpansion(DocExpansion.NONE)
            .filter(false)
            .maxDisplayedTags(null)
            .operationsSorter(OperationsSorter.ALPHA)
            .showExtensions(false)
            .tagsSorter(TagsSorter.ALPHA)
            .supportedSubmitMethods(UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS)
            .validatorUrl(null)
            .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .description(properties.getApiDescription())
            .title(properties.getApiTitle())
            .version(properties.getApiVersion())
            .build();
    }
}
