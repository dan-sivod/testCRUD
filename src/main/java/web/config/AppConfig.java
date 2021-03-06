package web.config;

import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import web.security.SecurityConfig;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;


public class AppConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

    // Метод, указывающий на класс конфигурации
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{ DBConfig.class, SecurityConfig.class};
    }

    // Добавление конфигурации, в которой инициализируем ViewResolver, для корректного отображения jsp.
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{ WebConfig.class };
    }

    /* Данный метод указывает url, на котором будет базироваться приложение */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
        AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
        appContext.register(WebConfig.class,DBConfig.class,SecurityConfig.class);
        registerHiddenFieldFilter(servletContext);
    }

    private void registerHiddenFieldFilter(ServletContext servletContext) {
        servletContext.addFilter("hiddenHttpMethodFilter",
                new HiddenHttpMethodFilter()).addMappingForUrlPatterns(null,true,"/*");
    }
}

