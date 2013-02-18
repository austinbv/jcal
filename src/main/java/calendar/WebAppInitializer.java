package calendar;

import org.h2.server.web.WebServlet;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.*;
import java.util.EnumSet;

public class WebAppInitializer implements WebApplicationInitializer {

  @Override
  public void onStartup(ServletContext servletContext) throws ServletException {
    XmlWebApplicationContext appContext = new XmlWebApplicationContext();
    appContext.getEnvironment().setActiveProfiles("resthub-jpa", "resthub-web-server");
    String[] locations = {"classpath*:resthubContext.xml", "classpath*:applicationContext.xml"};
    appContext.setConfigLocations(locations);

    ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(appContext));
    dispatcher.setLoadOnStartup(1);
    dispatcher.addMapping("/*");

//    FilterRegistration.Dynamic securityFilter = servletContext.addFilter("springSecurityFilterChain", new DelegatingFilterProxy());
//    securityFilter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");

    servletContext.addListener(new ContextLoaderListener(appContext));

    ServletRegistration.Dynamic h2Servlet = servletContext.addServlet("h2console", WebServlet.class);
    h2Servlet.setLoadOnStartup(2);
    h2Servlet.addMapping("/console/database/*");
  }
}
