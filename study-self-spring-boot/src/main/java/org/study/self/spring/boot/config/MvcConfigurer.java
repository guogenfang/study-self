package org.study.self.spring.boot.config;

import java.util.stream.Stream;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.util.UrlPathHelper;

@Configuration
public class MvcConfigurer extends WebMvcConfigurerAdapter {
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
//		registry.addViewController("/error").setViewName("error.html");
//		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
	}

	/**
	 * 函数让开发人员可以根据需求定制URL路径的匹配规则。
	 */
	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		super.configurePathMatch(configurer);
		//url 默认匹配大小写
		AntPathMatcher matcher = new AntPathMatcher();
        matcher.setCaseSensitive(true);
		configurer.setPathMatcher(matcher);
		
		UrlPathHelper urlPathHelper = new UrlPathHelper();  
		urlPathHelper.setRemoveSemicolonContent(true);
		configurer.setUrlPathHelper(urlPathHelper);
		
		//系统对外暴露的URL识别和匹配.*后缀
		configurer.setUseSuffixPatternMatch(true);
		//表示系统区分URL的最后一个字符是否是斜杠/
		configurer.setUseTrailingSlashMatch(true);
		
	}
	
    private final static String[] ALLOWED_METHODS = Stream.of(HttpMethod.values())
            .map(Enum::toString)
            .toArray(String[]::new);

    /**
     * [简要描述]：跨域
     * @author ggf
     * @date 2017年6月1日 下午8:40:00
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods(ALLOWED_METHODS);
    }
    
}
