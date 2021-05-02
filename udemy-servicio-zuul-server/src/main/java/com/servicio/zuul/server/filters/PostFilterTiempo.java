package com.servicio.zuul.server.filters;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class PostFilterTiempo extends ZuulFilter {
		
	private static final Logger log = LoggerFactory.getLogger(PostFilterTiempo.class);

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		
		
		RequestContext context = RequestContext.getCurrentContext();
		
		HttpServletRequest request = context.getRequest();
		
		log.info("Entrando a post filter.");
		
		Long tiempoInicio = (Long) request.getAttribute("tiempoInicio");
		Long tiempoFinal = System.currentTimeMillis();
		
		Long tiempoTranscurrido = tiempoFinal - tiempoInicio;
		
		//request.setAttribute("tiempoInicio", tiempoInicio);

		log.info(String.format("Tiempo transcurrido %s segundos", (tiempoTranscurrido/1000)));		
		log.info(String.format("Tiempo transcurrido %s segundos", tiempoTranscurrido));		
		
		return null;
	}

	@Override
	public String filterType() {
		return "post";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

}
