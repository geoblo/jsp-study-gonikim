package filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// 필터 처리로 로그 기록 파일 만들기
@WebFilter(
	urlPatterns = {"/*"},
	initParams = {
		@WebInitParam(name = "filename", value = "d:\\logs\\bookmarket.log")
	}
)
public class LogFileFilter extends HttpFilter implements Filter {
	PrintWriter writer;

	public void init(FilterConfig fConfig) throws ServletException {
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
		long start = System.currentTimeMillis();
		writer.println("접속한 클라이언트 IP: " + request.getRemoteAddr());
		writer.println("접근한 URL 경로: " + getURLPath(request));
		writer.println("요청 처리 시작 시각: " + getCurrentTime());
		
		chain.doFilter(request, response);
		
		long end = System.currentTimeMillis();
		writer.println("요청 처리 종료 시각: " + getCurrentTime());
		writer.println("요청 처리 소요 시간: " + (end - start) + "ms");
		writer.println("=====================================================");
		writer.flush();
	}
	
	private String getURLPath(ServletRequest request) {
		if (request instanceof HttpServletRequest req) {
			String currentPath = req.getRequestURI();
			String queryString = req.getQueryString();
			return (queryString == null) ? currentPath : currentPath + "?" + queryString;
		}
		return "";
	}
	
	private String getCurrentTime() {
		LocalDateTime dateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		return dateTime.format(formatter);
	}

	public void destroy() {
		writer.close();
	}

}
