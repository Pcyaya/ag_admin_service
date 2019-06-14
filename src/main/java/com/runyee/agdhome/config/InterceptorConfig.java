package com.runyee.agdhome.config;

import com.runyee.agdhome.interceptor.URLInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.resource.GzipResourceResolver;
import org.springframework.web.servlet.resource.VersionResourceResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc // 使用时 WebMvcAutoConfiguration中配置就不会生效 需重新指定静态资源路径
public class InterceptorConfig extends WebMvcConfigurerAdapter {

    /**
     * 指定 首页
     * */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //registry.addViewController("/").setViewName("forward:/index.html");
        registry.addViewController("/").setViewName("forward:login.html");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        super.addViewControllers(registry);
    }

    /**
     * 指定 首页时 指定的 ResourceViewResolver 视图解析器链 否则 filter 异常
     *  Could not resolve view with name 'forward:index.html' in servlet with name 'dispatcherServlet'
     * */
    @Bean
    public InternalResourceViewResolver htmlViewResolver() {
        InternalResourceViewResolver resolver= new InternalResourceViewResolver();
        /* resolver.setPrefix("classpath:/static/");
        resolver.setSuffix(".html");*/
        return resolver;
    }

    /**
     * 指定静态资源  PathResourceResolver
     * */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/**")
                .addResourceLocations("/")
                .addResourceLocations("classpath:/static/")
                .resourceChain(false)
                .addResolver(new GzipResourceResolver())
                .addResolver(new VersionResourceResolver().addContentVersionStrategy("/**"))
        ;
        super.addResourceHandlers(registry);
     }

    @Bean
    public URLInterceptor locale_URLInterceptor() {
        return new URLInterceptor();
    }

    /**
     * 指定加拦器
     * */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // addPathPatterns 用于添加拦截规则, 这里假设拦截 /url 后面的全部链接
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(locale_URLInterceptor()).addPathPatterns("/**").excludePathPatterns("/");
        super.addInterceptors(registry);
    }
}
