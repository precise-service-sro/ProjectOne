package com.precise_service.project_one;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.precise_service.project_one.web.login.AuthorizationFilterBean;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class ProjectOne extends AbstractMongoConfiguration implements CommandLineRunner {

  public static void main(String[] args) {
    log.info("!!! PROJECT ONE ... STARTUJEME !!!");
    SpringApplication.run(ProjectOne.class, args);
  }

  @Override
  public void run(String... args) {
    log.trace("ProjectOne...starting and loading input data");
    log.trace("ProjectOne...starting and loading input data...finished");
  }

  @Bean
  public FilterRegistrationBean<AuthorizationFilterBean> loggingFilter(){
    FilterRegistrationBean<AuthorizationFilterBean> registrationBean
        = new FilterRegistrationBean<>();

    registrationBean.setFilter(new AuthorizationFilterBean());
    registrationBean.addUrlPatterns("/nemovitost/*");

    return registrationBean;
  }

  @Bean
  public GridFsTemplate gridFsTemplate() throws Exception {
    return new GridFsTemplate(mongoDbFactory(), mappingMongoConverter());
  }

  @Override
  protected String getDatabaseName() {
    return "ProjectOne";
  }

  @Override
  @Bean
  public MongoClient mongoClient() {
    return new MongoClient("127.0.0.1");
  }

  @Bean
  public ServletContextInitializer servletContextInitializer() {
    return servletContext -> {
      servletContext.setInitParameter("com.sun.faces.forceLoadConfiguration", Boolean.TRUE.toString());
      servletContext.setInitParameter("primefaces.THEME", "bootstrap");
      servletContext.setInitParameter("primefaces.CLIENT_SIDE_VALIDATION", Boolean.TRUE.toString());
      servletContext.setInitParameter("javax.faces.FACELETS_SKIP_COMMENTS", Boolean.TRUE.toString());
      servletContext.setInitParameter("primefaces.FONT_AWESOME", Boolean.TRUE.toString());
      servletContext.setInitParameter("primefaces.UPLOADER", "commons");
    };
  }
}