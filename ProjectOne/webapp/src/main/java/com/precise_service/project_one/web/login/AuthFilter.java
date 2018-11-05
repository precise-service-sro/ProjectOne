package com.precise_service.project_one.web.login;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.precise_service.project_one.entity.osoba.Osoba;
import com.precise_service.project_one.service.osoba.IOsobaService;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.precise_service.project_one.web.URL_CONST.INDEX_URL;
import static com.precise_service.project_one.web.login.LoginBean.SESSION_ATTRIBUTE_PRIHLASENY_UZIVATEL;

@NoArgsConstructor
@Component
@Order(1)
@Slf4j
public class AuthFilter implements Filter {

  @Autowired
  private IOsobaService osobaService;

  @Override
  public void init(FilterConfig filterConfig) {
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
    try {

      // check whether session variable is set
      HttpServletRequest httpServletRequest = (HttpServletRequest) request;
      HttpServletResponse httpServletResponse = (HttpServletResponse) response;
      HttpSession session = httpServletRequest.getSession(false);

      //  allow user to proceed if url is login.xhtml or user logged in or user is accessing any page in //public folder
      String requestURI = httpServletRequest.getRequestURI();
      if ( requestURI.indexOf("/index.xhtml") >= 0
          || (session != null && session.getAttribute(SESSION_ATTRIBUTE_PRIHLASENY_UZIVATEL) != null)
          || requestURI.indexOf("/public/") >= 0
          || requestURI.indexOf("/css/") >= 0
          || requestURI.indexOf("/icons/") >= 0
          || requestURI.indexOf("/images/") >= 0
          || requestURI.contains("javax.faces.resource")
          ) {

        log.trace("--- public web request ---");
        chain.doFilter(request, response);
        return;
      }

      if ( requestURI.indexOf("/api/") >= 0) {
        if (isAuthorizedApiRequest(httpServletRequest)) {
          log.trace("--- authorized api request ---");
          chain.doFilter(request, response);
          return;
        }
        log.trace("--- unauthorized api request ---");
        httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "--- unauthorized api request ---");
        return;
      }

      log.trace("--- unauthorized request ---");
      httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + INDEX_URL);
    }
    catch(Throwable t) {
      System.out.println( t.getMessage());
    }
  }

  private boolean isAuthorizedApiRequest(HttpServletRequest httpServletRequest) {
    String prihlasovaciJmeno = httpServletRequest.getHeader("prihlasovaciJmeno");
    String heslo = httpServletRequest.getHeader("heslo");

    if (StringUtils.isBlank(prihlasovaciJmeno) || StringUtils.isBlank(heslo)) {
      return false;
    }

    Osoba osoba = osobaService.getOsobaByPrihlasovaciJmenoAndHeslo(prihlasovaciJmeno, heslo);
    return (osoba != null);
  }

  @Override
  public void destroy() {

  }
}