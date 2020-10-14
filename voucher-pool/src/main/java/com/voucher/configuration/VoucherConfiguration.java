package com.voucher.configuration;

import java.io.File;

import org.apache.commons.lang3.StringUtils;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

import com.google.common.base.Predicates;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;



@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.voucher")
@EnableSwagger2
public class VoucherConfiguration extends WebMvcConfigurerAdapter{
	private static final Logger LOGGER = LoggerFactory.getLogger(VoucherConfiguration.class);
	
	
	 @Bean
	    public Docket api() {
	        // @formatter:off
	        //Register the controllers to swagger
	        //Also it is configuring the Swagger Docket
	        return new Docket(DocumentationType.SWAGGER_2).select()
	                // .apis(RequestHandlerSelectors.any())
	                .apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot")))
	                // .paths(PathSelectors.any())
	                // .paths(PathSelectors.ant("/swagger2-demo"))
	                .build();
	        // @formatter:on
	    }
	 
	    @Override
	    public void addResourceHandlers(ResourceHandlerRegistry registry) 
	    {
	        //enabling swagger-ui part for visual documentation
	        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
	        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	    }
	
	
	
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		return messageSource;
	}

	@Bean(name = "messageSource")
	public ReloadableResourceBundleMessageSource getMessageSource() {
		ReloadableResourceBundleMessageSource resource = new ReloadableResourceBundleMessageSource();
		resource.setBasename("classpath:messages");
		resource.setDefaultEncoding("UTF-8");
		return resource;
	}

	@Bean
	public Mapper dozerMapper() {
		return new DozerBeanMapper();
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		PropertySourcesPlaceholderConfigurer ppc = new PropertySourcesPlaceholderConfigurer();
		
		ppc.setLocations(new ClassPathResource("voucher.properties"));
		ppc.setIgnoreUnresolvablePlaceholders(false);
		return ppc;
	}

	private static String propertyPath;

	public static String getPropertyPath() {
		if (!StringUtils.isEmpty(propertyPath)) {
			return propertyPath;
		}
		
		// Get from TOMCAT server
		File file = null;
		String propertyHome = System.getProperty(ConfigConstants.PATH_CATALINA_HOME) != null
				? System.getProperty(ConfigConstants.PATH_CATALINA_HOME)
				: System.getProperty(ConfigConstants.PATH_CATALINA_BASE);
		if (!StringUtils.isEmpty(propertyHome)) {
			propertyHome = propertyHome + File.separator + "conf";
		}
		LOGGER.info("CATALINA HOME >> " + propertyHome);
		file = new File(propertyHome + ConfigConstants.FILE_SYS_RESOURCE);
		if (!file.exists())
			propertyHome = null;

		

		if (!StringUtils.isEmpty(propertyHome)) {
			file = new File(propertyHome + ConfigConstants.FILE_SYS_RESOURCE);
			if (file.exists() && !file.isDirectory()) {
				propertyPath = propertyHome + File.separator + ConfigConstants.PROPERTY_FILENAME;
			} else {
				LOGGER.info("Missing properties file >> " + propertyHome + ConfigConstants.FILE_SYS_RESOURCE);
			}
		}

		// Get from Application CLASSPATH
		propertyPath = propertyPath != null ? propertyPath : ConfigConstants.PROPERTY_CLASSPATH;

		LOGGER.info("\n[Application Properties :: \n\tPath : " + propertyPath + "\n]");

		return propertyPath;
	}
	

}