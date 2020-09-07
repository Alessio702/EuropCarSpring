package com.example.demo.configuration;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// CLASSE PER IL REDIRECT FROM HTTP TO HTTPS
@Configuration
public class RedirectConfig 
{
	@Bean
	public ServletWebServerFactory servletContainer() 
	{
		// Enable SLL Traffic
		TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() 
		{
			@Override
			protected void postProcessContext(Context context) 
			{
				SecurityConstraint securityConstraint = new SecurityConstraint();
				securityConstraint.setUserConstraint("CONFIDENTIAL");

				SecurityCollection collection = new SecurityCollection();
				collection.addPattern("/*");
				securityConstraint.addCollection(collection);

				context.addConstraint(securityConstraint);
			}
		};
		
		// Add HTTP to HTTPS redirect
		tomcat.addAdditionalTomcatConnectors(redirectConnector());

		return tomcat;
	}

	private Connector redirectConnector() 
	{
		Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
		connector.setScheme("http");
		connector.setPort(8080);
		connector.setSecure(false);
		connector.setRedirectPort(8443);

		return connector;
	}
}