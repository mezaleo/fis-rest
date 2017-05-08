package com.rramalho.rest;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.servlet.CamelHttpTransportServlet;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RestSetup extends RouteBuilder{
	
	@Value("${server.port}")
	String serverPort;

	@Bean
	ServletRegistrationBean servletRegistrationBean() {
		ServletRegistrationBean servlet = new ServletRegistrationBean(
				new CamelHttpTransportServlet(), "/camel-rest-sql/*");
		servlet.setName("CamelServlet");
		return servlet;
	}

	@Override
	public void configure() {
		restConfiguration()
			.contextPath("/camel-rest-sql").apiContextPath("/api-doc")
			.port(serverPort)
			.apiProperty("api.title", "Camel REST API")
			.apiProperty("api.version", "1.0")
			.apiProperty("cors", "true")
			.apiContextRouteId("doc-api")
			.component("servlet")
			.bindingMode(RestBindingMode.json);
	}
	
}
