package com.trash.collection.trash.common.config;

import com.trash.collection.trash.common.LoginedDotGo;
import com.trash.collection.trash.common.NotLoginedDotGo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class uploadFileConfig extends WebMvcConfigurationSupport {

    @Autowired
    private LoginedDotGo loginedDotGo;

    @Autowired
    private NotLoginedDotGo notLoginedDotGo;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/images/**").addResourceLocations("file:/d:/home/data/image/");
        super.addResourceHandlers(registry);
    }

    /**
     * 添加拦截器
     * addInterceptors：添加你自定义拦截器的实现逻辑类
     * addPathPatterns：添加你要拦截的请求路径，如果有多个路径，就继续addPathPatterns
     * excludePathPatterns：添加你不需要拦截的请求路径
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // String[] patterns = new String[]  {"/login","/*.html","/image"};
        String[] pathPatterns = new String[] {
                "/user/user/login",
                "/user/user/register",
                "/product/product/productList"
        };
        String[] pathPatterns2 = new String[] {
                "/product/donationGoods",
                "/product/donationLogisticsMsg",
                "/product/productKind",
                "/product/workerMessage",
                "/score/**",
                "/address/**",

        };
        registry.addInterceptor(loginedDotGo)
                .addPathPatterns(pathPatterns);
        registry.addInterceptor(notLoginedDotGo)
                .addPathPatterns(pathPatterns2);

    }

    /**
     * 使用filter来设置允许cors
     * @return*/

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*");
        config.setAllowCredentials(true);
        config.addAllowedMethod("*");
        config.addAllowedHeader("*");
        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", config);
        return new CorsFilter(configSource);
    }
}
