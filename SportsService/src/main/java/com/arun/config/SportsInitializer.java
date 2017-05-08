package com.arun.config;

import java.util.List;

import javax.servlet.Filter;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.arun.common.FilterDefinitionArgumentResolver;
import com.arun.common.SportsAuditorAware;
import com.arun.interceptor.RunInterceptor;
import com.arun.security.SecurityFilter;

import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableCaching
@ComponentScan(basePackages = "com.arun")
@EntityScan(basePackages = "com.arun.domain")
@EnableJpaRepositories(basePackages = "com.arun.repository")
@EnableJpaAuditing
@EnableTransactionManagement
//@EnableSpringDataWebSupport //Cannot use this as we are overriding default HandlerMethodArgumentResolver
@EnableSpringConfigured
@EnableAutoConfiguration
public class SportsInitializer extends SpringBootServletInitializer {
	
	@Autowired
	private RunInterceptor runInterceptor;

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SportsConfig.class);
	}
	
	@Bean
	public Filter securityFilter() {
		return new SecurityFilter();
	}
	
	@Bean(name = "org.dozer.Mapper")
	public Mapper mapper() {
		return new DozerBeanMapper();
	}
	
	@Bean
	public CacheManager getEhCacheManager(){
	        return new EhCacheCacheManager(getEhCacheFactory().getObject());
	}
	
	@Bean
	public EhCacheManagerFactoryBean getEhCacheFactory(){
		EhCacheManagerFactoryBean factoryBean = new EhCacheManagerFactoryBean();
		factoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
		factoryBean.setShared(true);
		return factoryBean;
	}
	
	@Bean
    public AuditorAware<String> auditorProvider(){
        return new SportsAuditorAware();
    }
	
	@Bean
    public WebMvcConfigurerAdapter adapter() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
               registry.addInterceptor(runInterceptor);
               super.addInterceptors(registry);
            }
            
            @Override
		    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		        argumentResolvers.add(filterDefinitionArgumentResolver());
		        super.addArgumentResolvers(argumentResolvers);
		    }
            
            
        };
    }
	
	@Bean
    public FilterDefinitionArgumentResolver filterDefinitionArgumentResolver() {
        return new FilterDefinitionArgumentResolver();
    }
	
	
}
