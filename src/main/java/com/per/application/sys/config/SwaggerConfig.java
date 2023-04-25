package com.per.application.sys.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Value("${swagger.enable}")
	private Boolean enable;
	
	public static final String SWAGGER_SCAN_ADMIN_PACKAGE = "com.per.application.controller.admin";
	public static final String ADMIN_VERSION = "1.0.0";
	public static final String SWAGGER_SCAN_SYS_PACKAGE = "com.per.application.controller.sys";
	public static final String SYS_VERSION = "1.0.0";
	public static final String SWAGGER_SCAN_API_PACKAGE = "com.per.application.controller.api";
	public static final String WX_VERSION = "1.0.0";

	@Bean
	public Docket createAdminRestApi() {
		return new Docket(DocumentationType.SWAGGER_2).enable(enable).groupName("后台API").apiInfo(apiAdminInfo()).select()
				.apis(RequestHandlerSelectors.basePackage(SWAGGER_SCAN_ADMIN_PACKAGE))// api接口包扫描路径
				//.paths(PathSelectors.regex(".*/admin/.*"))// 可以根据url路径设置哪些请求加入文档，忽略哪些请求
				.build();
	}

	private ApiInfo apiAdminInfo() {
		return new ApiInfoBuilder().title("后台API")// 设置文档的标题
				.description("后台数据管理API")// 设置文档的描述->1.Overview
				.version(ADMIN_VERSION)// 设置文档的版本信息-> 1.1 Version information
				.build();
	}
	
	@Bean
	public Docket createSysRestApi() {
		return new Docket(DocumentationType.SWAGGER_2).enable(enable).groupName("系统API").apiInfo(apiSysInfo()).select()
				.apis(RequestHandlerSelectors.basePackage(SWAGGER_SCAN_SYS_PACKAGE))// api接口包扫描路径
				//.paths(PathSelectors.regex(".*/admin/.*"))// 可以根据url路径设置哪些请求加入文档，忽略哪些请求
				.build();
	}
	
	private ApiInfo apiSysInfo() {
		return new ApiInfoBuilder().title("系统API")// 设置文档的标题
				.description("后台数据管理API")// 设置文档的描述->1.Overview
				.version(SYS_VERSION)// 设置文档的版本信息-> 1.1 Version information
				.build();
	}

	@Bean
	public Docket createWxRestApi() {
		return new Docket(DocumentationType.SWAGGER_2).enable(enable).groupName("前端API").apiInfo(apiWxInfo()).select()
				.apis(RequestHandlerSelectors.basePackage(SWAGGER_SCAN_API_PACKAGE))// api接口包扫描路径
				//.paths(PathSelectors.regex(".*/api/.*"))// 可以根据url路径设置哪些请求加入文档，忽略哪些请求
				.build();
	}

	private ApiInfo apiWxInfo() {
		return new ApiInfoBuilder().title("前端API")// 设置文档的标题
				.description("对前端开放的API")// 设置文档的描述->1.Overview
				.version(WX_VERSION)// 设置文档的版本信息-> 1.1 Version information
				.build();
	}
}