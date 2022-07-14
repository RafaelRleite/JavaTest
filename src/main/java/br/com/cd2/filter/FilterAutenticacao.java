package br.com.cd2.filter;

import java.io.IOException;
import java.io.Serializable;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import br.com.cd2.jpautil.JPAUtil;

@WebFilter(urlPatterns = { "/*" })
public class FilterAutenticacao implements Filter, Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private JPAUtil jpaUtil;

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		jpaUtil.getEntityManager();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	}

}
