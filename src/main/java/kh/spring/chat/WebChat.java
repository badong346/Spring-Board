package kh.spring.chat;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import kh.spring.config.HttpSessionConfigurator;
import kh.spring.dto.MemberDTO;

@ServerEndpoint(value="/chat",configurator = HttpSessionConfigurator.class)
public class WebChat {

	private static Set<Session> clients = Collections.synchronizedSet( new HashSet<>());
	private HttpSession session;

	@OnOpen
	public void onConnect(Session client, EndpointConfig config) {
		System.out.println(client.getId()+" 클라이언트가 접속했습니다.");
		clients.add(client);
		this.session = (HttpSession) config.getUserProperties().get("session");
	}

	@OnMessage
	public void onMessage(Session session, String message) {
		MemberDTO mdto = (MemberDTO) this.session.getAttribute("loginInfo");

		String ip_address = (String) this.session.getAttribute("ip_address");
		
		synchronized(clients){
			for(Session client : clients) {
				if(!client.getId().contentEquals(session.getId())) {
					Basic basic = client.getBasicRemote();
					try {
						basic.sendText(mdto.getName()+" ("+ip_address+ ") : "+message);
					} catch (IOException e) { 
						e.printStackTrace();
					}
				}
			}
		}
	}

	@OnClose
	public void onClose(Session session) {
		clients.remove(session);
	}

	@OnError
	public void onError(Session session, Throwable t) {
		clients.remove(session);
	}

}
