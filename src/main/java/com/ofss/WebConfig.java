package com.ofss;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
class WebConfig {
	
	@Bean
    public WebMvcConfigurer corsConfigurer() {
		System.out.println("WebConfig - corsConfigurer method called - CORS enabled");
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")  // Allow CORS on all endpoints
                        .allowedOrigins("http://localhost:8000") // Your frontend origin
                        .allowedMethods("GET", "POST", "PUT", "DELETE","PATCH") // Allowed HTTP methods
                        .allowCredentials(true);
            }
        };
    }

}
