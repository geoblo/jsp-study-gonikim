package ch12.com.filter;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpFilter;

public class LogFileFilter extends HttpFilter implements Filter {
       
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("LogFileFilter 초기화...");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("LogFileFilter 수행...");
		
		chain.doFilter(request, response);
	}

	public void destroy() {
		System.out.println("LogFileFilter 해제...");
	}

}
