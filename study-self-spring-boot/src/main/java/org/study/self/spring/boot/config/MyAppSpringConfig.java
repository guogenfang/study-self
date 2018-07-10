package org.study.self.spring.boot.config;

//import java.util.Collection;
//
//import org.springframework.boot.actuate.autoconfigure.EndpointAutoConfiguration;
//import org.springframework.boot.actuate.autoconfigure.HealthIndicatorAutoConfiguration;
//import org.springframework.boot.actuate.autoconfigure.PublicMetricsAutoConfiguration;
//import org.springframework.boot.actuate.endpoint.HealthEndpoint;
//import org.springframework.boot.actuate.endpoint.mvc.EndpointHandlerMapping;
//import org.springframework.boot.actuate.endpoint.mvc.HealthMvcEndpoint;
//import org.springframework.boot.actuate.endpoint.mvc.MvcEndpoint;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Import;
//
////@Configuration
//@Import({ EndpointAutoConfiguration.class, 
//			PublicMetricsAutoConfiguration.class,
//			HealthIndicatorAutoConfiguration.class })
//public class MyAppSpringConfig {
//	@Bean
//	public EndpointHandlerMapping endpointHandlerMapping(Collection<? extends MvcEndpoint> endpoints) {
//		return new EndpointHandlerMapping(endpoints);
//	}
//
//	@Bean
//	public HealthMvcEndpoint healthMvcEndpoint(HealthEndpoint delegate) {
//		return new HealthMvcEndpoint(delegate, false);
//	}
//}