package wifi.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import wifi.model.service.WifiService;

/**
 * Servlet implementation class WifiInsertServlet
 */
@WebServlet("/insert.wifi")
public class WifiInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WifiInsertServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		WifiService ws = new WifiService();
		 
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(60*5);//세션유지시간
		
		String sessionId = session.getId();
		
		int listTotalCount = ws.getWifiInfo(sessionId);
		
		session.setAttribute("sessionId", sessionId);
		session.setAttribute("listTotalCount", listTotalCount);
		response.sendRedirect("views/load-wifi.jsp");

	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
