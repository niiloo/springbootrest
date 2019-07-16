package com.ecdevco.springbootrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.faces.webapp.FacesServlet;
import javax.servlet.ServletContext;

@SpringBootApplication
public class SpringBootRestApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootRestApplication.class, args);
    }

    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        FacesServlet servlet = new FacesServlet();
        return new ServletRegistrationBean(servlet, "*.jsf");
    }

    @Configuration
    @Profile("dev")
    static class ConfigureJSFContextParameters implements ServletContextInitializer {

        @Override
        public void onStartup(ServletContext servletContext) {

            servletContext.setInitParameter("javax.faces.DEFAULT_SUFFIX", ".xhtml");
            servletContext.setInitParameter("javax.faces.PARTIAL_STATE_SAVING_METHOD", "true");
            servletContext.setInitParameter("javax.faces.PROJECT_STAGE", "Development");
            servletContext.setInitParameter("facelets.DEVELOPMENT", "true");
            servletContext.setInitParameter("javax.faces.FACELETS_REFRESH_PERIOD", "1");

        }
    }

    @Configuration
    @Profile("production")
    static class ConfigureJSFContextParametersProd implements ServletContextInitializer {

        @Override
        public void onStartup(ServletContext servletContext) {

            servletContext.setInitParameter("javax.faces.DEFAULT_SUFFIX", ".xhtml");
            servletContext.setInitParameter("javax.faces.PARTIAL_STATE_SAVING_METHOD", "true");
            servletContext.setInitParameter("javax.faces.PROJECT_STAGE", "Production");
            servletContext.setInitParameter("facelets.DEVELOPMENT", "false");
            servletContext.setInitParameter("javax.faces.FACELETS_REFRESH_PERIOD", "-1");

        }
    }
}
