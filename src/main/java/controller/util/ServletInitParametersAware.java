package controller.util;

import javax.servlet.ServletContext;

import org.springframework.web.context.ServletContextAware;

/**
 * Class allowing its daughters to retrieve some parameters defined in the
 * web.xml file
 */
public class ServletInitParametersAware implements ServletContextAware {

	private ServletContext ctx;

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.ctx = servletContext;
	}

	protected final String getRessourcePath() {
		return ctx.getInitParameter("ressourcePath");
	}

	protected final String getName() {
		return ctx.getInitParameter("name");
	}

	protected final String getRoot() {
		return ctx.getInitParameter("root");
	}
}
