package com.sist.app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

	@Autowired
	private HttpSession session;
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	@RequestMapping("login")
	public String login() {
		return "login";
	}
	
	@RequestMapping("/login/kakao")
	public ModelAndView login(String code) {
		// 인자는 카카오서버로 부터 받는 값이다.
		ModelAndView mv = new ModelAndView();
		
		// 카카오서버가 인자로 전달해 준 "인증코드"가 code라는 변수로 넣어준다.
		// System.out.println("code: "+code);
		
		// 받은 인증코드를 가지고 2번째 카카오서버와 통신을 하기 위해
		// 토큰을 요청하여 받아야 한다.
		String access_Token = "";
		String refresh_Token = "";
		
		String req_url = "https://kauth.kakao.com/oauth/token";
		
		try {
			// 웹 상의 경로를 객체화한다.
			URL url = new URL(req_url);
			
			// 웹 상의 경로와 연결한다.
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			// 카카오 서버 쪽에서 POST 방식의 요청을 원하므로
			// method를 POST로 지정해야함
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			
			// 전달하고자 하는 파라미터들을 보낼 OutputStream 생성!
			BufferedWriter bw = new BufferedWriter(
					new OutputStreamWriter(conn.getOutputStream())
					);
			
			// 카카오API문서에 있는 4개의 파라미터들을 정의하기 위해
			// 문자열 편집이 필요함. 예) grant_type = authorization_code&client_id=...
			StringBuffer sb = new StringBuffer();
			sb.append("grant_type=authorization_code");
			sb.append("&client_id=620accc387610feefacbb41cfd1a8d39");
			sb.append("&redirect_uri=http://localhost:8080/login/kakao");
			sb.append("&code=");
			sb.append(code);
			
			bw.write(sb.toString()); // 준비된 파라미터들을 카카오서버로 보낸다.
			bw.flush();
			
			// 카카오서버에 요청을 보낸 후 응답결과가 성공적인 경우(200)에만
			// 토큰을 받아내야 한다.
			
			int res_code = conn.getResponseCode();
			// System.out.println("RES CODE: " + res_code);
			if(res_code == 200) {
				// 요청을 통해 얻은 JSON 타입의 결과 메시지를 읽어온다.
				// 그 결과를 받기위해 스트림 생성!
				
				BufferedReader br = new BufferedReader(
						new InputStreamReader(conn.getInputStream())
						);
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return mv;
	}
	
	
	
}
