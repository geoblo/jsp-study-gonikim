package ch12.com.filter;

import java.io.FileWriter;
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
	PrintWriter writer;
       
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("LogFileFilter 초기화...");
		
		String filename = fConfig.getInitParameter("filename");
		
		if (filename == null) {
			throw new ServletException("로그 파일의 이름을 찾을 수 없습니다.");
		}
		
		try {
			// 파일에 쓸 수 있는 문자 스트림을 만들고, 그 위에 출력 편의 기능을 덧씌움
			writer = new PrintWriter(new FileWriter(filename, true)); // 기존 파일 끝에 새로운 내용 추가
		} catch (IOException e) {
			throw new ServletException("로그 파일을 열 수 없습니다.");
		}
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("LogFileFilter 수행...");
		
		chain.doFilter(request, response);
	}

	public void destroy() {
		System.out.println("LogFileFilter 해제...");
	}

}
