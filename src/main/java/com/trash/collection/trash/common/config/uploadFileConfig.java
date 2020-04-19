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
                "/user/user/register"
        };
        String[] pathPatterns2 = new String[] {
                "/product/**",
                "/reply/replyWrite",
                "/article/myArticles/**",
                "/article/articleWrite",
                "/article/articleDeleteMarkTrue/**",
                "/article/articleModify/**",
                "/article/uploadImage",
                "/common/leaveMessage",

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

    /*@Override
    public void addCorsMappings(CorsRegistry registry) {
        //设置允许跨域的路径
        registry.addMapping("/**")
                //设置允许跨域请求的域名
                //当**Credentials为true时，**Origin不能为星号，需为具体的ip地址【如果接口不带cookie,ip无需设成具体ip】
                .allowedOrigins("http://localhost:9527")
                //是否允许证书 不再默认开启
                .allowCredentials(true)
                //设置允许的方法
                .allowedMethods("*")
                //跨域允许时间
                .maxAge(3600);
    }*/

}
