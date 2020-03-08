package me.akoala.onlinelog.swagger;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

/**
 * 轮播图(Banner)表控制层
 *
 * @author xiaohongxin
 * @since 2019-07-13 10:49:10
 */
@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI
public class SwaggerConfig {

    @Value("${spring.application.name:DEFAULT-NAME}")
    private String AppName;

    private List<Parameter> pars;

    public SwaggerConfig() {
        ParameterBuilder tokenPar = new ParameterBuilder();
        pars = Lists.newArrayList();
        tokenPar.name("token").description("令牌").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(tokenPar.build());
    }

    @Bean
    public Docket getApiInfoManager() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(true)
                .produces(Sets.newHashSet("application/json;charset=UTF-8"))
                .select()
                .paths(PathSelectors.any())
                .build()
                .apiInfo(outApiInfo())
                .groupName(AppName)
//                .globalOperationParameters(pars)
                ;

    }


    private ApiInfo outApiInfo() {
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title(AppName + "接口")
                .version("V1.0")
                .description(AppName + "相关接口")
                .build();
        return apiInfo;
    }

}
