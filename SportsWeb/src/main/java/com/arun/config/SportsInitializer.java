package com.arun.config;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.arun.interceptors.RunWebInterceptor;
import com.arun.web.security.MyAuthenticationFailureHandler;
import com.arun.web.security.MyAuthenticationFilter;
import com.arun.web.security.MyAuthenticationProvider;
import com.arun.web.security.MyAuthenticationSuccessHandler;
import com.arun.web.security.RunPermissionEvaluator;

@Configuration
@ComponentScan(basePackages = "com.arun")
@EnableAutoConfiguration
@EnableRetry
@EnableAspectJAutoProxy(proxyTargetClass = true)
@PropertySource("classpath:web-config-local.properties")
@EnableGlobalMethodSecurity(prePostEnabled=true )
public class SportsInitializer extends SpringBootServletInitializer {
	
	@Autowired
	private RunWebInterceptor runWebInterceptor;

	@Autowired
	private AuthenticationManager  authenticationManager;

	@Autowired
	private MyAuthenticationFailureHandler authenticationFailureHandler;
	
	@Autowired
	private MyAuthenticationSuccessHandler authenticationSuccessHandler;
	
	@Autowired
	private MyAuthenticationProvider authenticationProvider;
	
	@Autowired
	private RunPermissionEvaluator permissionEvaluator;
	        
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SportsConfig.class);
	}
	
	@Bean(name = "org.dozer.Mapper")
	public Mapper mapper() {
		return new DozerBeanMapper();
	}
	
	@Bean
	public RestTemplate restTemplate() {		
		return new RestTemplate();
	}
	
	@Bean
	public CommonsMultipartResolver multipartResolver() {
		return new CommonsMultipartResolver();
	}
	
	@Bean
    public WebMvcConfigurerAdapter adapter() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
               registry.addInterceptor(runWebInterceptor);
               super.addInterceptors(registry);
            }
            
            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
            }
        };
    }
	
	@Bean
	public DefaultMethodSecurityExpressionHandler expressionHandler() {
		DefaultMethodSecurityExpressionHandler methodSecurityExpressionHandler = new DefaultMethodSecurityExpressionHandler();
		methodSecurityExpressionHandler.setPermissionEvaluator(permissionEvaluator);
		return methodSecurityExpressionHandler;
	}
	
	@Bean
	public DefaultWebSecurityExpressionHandler webSecurityExpressionHandler() {
		DefaultWebSecurityExpressionHandler webSecurityExpressionHandler = new DefaultWebSecurityExpressionHandler();
		webSecurityExpressionHandler.setPermissionEvaluator(permissionEvaluator);
		return webSecurityExpressionHandler;
	}
	
	public MyAuthenticationFilter authenticationFilter() throws Exception {
		MyAuthenticationFilter authenticationFilter = new MyAuthenticationFilter("/");
		authenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler);
		authenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
		authenticationFilter.setAuthenticationManager(authenticationManager);
		return authenticationFilter;
	}
	
	 @Bean
	 public AuthenticationProvider authenticator() {
	    return authenticationProvider;
	 }
		 
	@Bean
	@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
	public WebSecurityConfigurerAdapter securityAdapter() {
		return new WebSecurityConfigurerAdapter(true) {
			
			@Override
			protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
			   auth.authenticationProvider(authenticator());
			   super.configure(auth);
			}

			@Override
			protected void configure(HttpSecurity http) throws Exception {
				http
		        	.authorizeRequests()                                                                
		            	.anyRequest().authenticated() 
		            	.and()
		            .addFilter(new SecurityContextPersistenceFilter())
		            .addFilterAfter(authenticationFilter(), UsernamePasswordAuthenticationFilter.class)
		            .authenticationProvider(authenticationProvider)
		            .exceptionHandling().authenticationEntryPoint(authenticationFilter());
			}
			
			@Override
			public void configure(WebSecurity web) throws Exception {
			    web.ignoring().antMatchers("/resources/**", "/login", "/addUser", "/errorPage", "/WEB-INF/pages/Login.jsp", "/register/addUser", "/WEB-INF/pages/Error.jsp", "/WEB-INF/pages/Register.jsp", "/health", "/mappings");
			}
			
			
		};
	}
	
	
}
