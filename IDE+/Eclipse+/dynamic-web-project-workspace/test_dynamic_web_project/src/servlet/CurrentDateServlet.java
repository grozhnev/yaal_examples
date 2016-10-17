package servlet;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.reflect.Reflection;

@WebServlet("/CurrentDateServlet")
public final class CurrentDateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.getOutputStream().print(LocalDateTime.now().toString());
		response.getOutputStream().print("<br/>");
		response.getOutputStream().print("Package: " + Reflection.getPackageName(CurrentDateServlet.class));

	}
}
