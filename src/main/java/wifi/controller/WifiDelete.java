package wifi.controller;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import wifi.model.service.WifiService;

@WebListener
public class WifiDelete implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent hs) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent hs) {
		WifiService ws = new WifiService();
		String sessionId = hs.getSession().getId();
		System.out.println(sessionId);
		if(sessionId != null && sessionId != "") {
		ws.deleteSession(sessionId);
		}
	}

}
