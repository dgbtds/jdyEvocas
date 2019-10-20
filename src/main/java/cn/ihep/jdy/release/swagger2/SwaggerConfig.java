package cn.ihep.jdy.release.swagger2;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;
/**
 * @Description swagger在线接口插件配置类
 * @Date 2019年10月17日
 * @author wuye
 */
@Configuration
@EnableSwagger2 //开启在线接口文档
public class SwaggerConfig {

    /**
     * @Description
     * @Date 2019年10月17日
     * @author wuye
     * @return
     */
    @Bean
    public Docket accessToken() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("标题：jdy Web 接口文档")//标题
                        .description("本系统接口主要包括manager,customer,accepter等功能模块")//描述
                        .contact(new Contact("wuye", "http://www.xxx.com/web", "wuye@ihep.ac.cn"))
                        .version("1.0.0")//版本号

                        .build()
                )

                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.ihep.jdy.release"))//配置扫描的控制器类包
                .paths(paths())//配置符合指定规则的路径接口才生成在线接口，用于定制那些接口在ui中展示
                .build();
    }
    @SuppressWarnings("unchecked")
    private Predicate<String> paths() {
        return or(regex("/*/*/.*?"));
    }

}