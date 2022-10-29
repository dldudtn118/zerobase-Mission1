package wifi.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import wifi.model.service.WifiService;
import wifi.model.vo.WifiInfo;

/**
 * Servlet implementation class WifiSelectServlet
 */
@WebServlet("/select.Wifi")
public class WifiSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WifiSelectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		double lat = Double.parseDouble(request.getParameter("lat"));
		double lnt = Double.parseDouble(request.getParameter("lnt"));
		HttpSession session = request.getSession();
		String sessionId = (String)session.getAttribute("sessionId");
		
		WifiService ws = new WifiService();
		RequestDispatcher view = null;
		ArrayList<WifiInfo> alw = new ArrayList<WifiInfo>();
		alw =  ws.selectWifiInfo(sessionId,lat,lnt);
		view = request.getRequestDispatcher("/?lat="+lat+"&lnt="+lnt);
		if(alw == null) {
		request.setAttribute("noSession", "no");
		}else {
		request.setAttribute("alw", alw);
		}
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
