package com.servicio.zuul.server.filters;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class PreFilterTiempo extends ZuulFilter {
		
	private static final Logger log = LoggerFactory.getLogger(PreFilterTiempo.class);

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		
		
		RequestContext context = RequestContext.getCurrentContext();
		
		HttpServletRequest request = context.getRequest();
		
		Long tiempoInicio = System.currentTimeMillis();
		
		request.setAttribute("tiempoInicio", tiempoInicio);

		log.info("%s request enrutado a %s", request.getMethod(), request.getRequestURL().toString());		
		
		return null;
	}

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

}
