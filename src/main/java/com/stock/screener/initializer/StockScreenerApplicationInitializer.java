package com.stock.screener.initializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePropertySource;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.stock.screener.config.DatabaseConfig;
import com.stock.screener.config.WebConfig;

public class StockScreenerApplicationInitializer implements
		WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext)
			throws ServletException {

		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();

		context.register(DatabaseConfig.class, WebConfig.class);
		servletContext.addListener(new ContextLoaderListener(context));

		AnnotationConfigWebApplicationContext dispatchContext = new AnnotationConfigWebApplicationContext();
		ServletRegistration.Dynamic dispatcher;

		dispatcher = servletContext.addServlet("dispatcher",
				new DispatcherServlet(dispatchContext));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");

		try {

			Resource resource = context
					.getResource("classpath:application.properties");
			context.getEnvironment().getPropertySources()
					.addFirst(new ResourcePropertySource(resource));

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

}
