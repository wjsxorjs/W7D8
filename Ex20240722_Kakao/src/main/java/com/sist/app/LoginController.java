package com.sist.app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpSession;
import javax.swing.text.html.HTMLEditorKit.Parser;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
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
		String access_token = "";
		String refresh_token = "";
		
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
				
				StringBuffer result = new StringBuffer();
				String line = null;
				
				// 한 줄 단위로 읽어서 result라는 StringBuffer에 적재한다.
				while( ( line = br.readLine() ) != null) {
					result.append(line);
				}
				
				// System.out.println("RESULT: "+ result.toString());
				
				// JSON 파싱 처리: "access_token"과
				// "refresh_token"을 찾아내어 값을 얻어내야 한다.
				JSONParser pars = new JSONParser();
				// 위 객체는 mvnrepository에서 json-simple로 검색하여
				// 첫번째로 나오는 Google의 라이브러리를 얻었다.
				// 이 파서를 통해 문자열로 되어있는 json표기법을 객체로 만든다.
				
				Object obj = pars.parse(result.toString());
				JSONObject json = (JSONObject) obj;
				
				access_token = (String) json.get("access_token");
				refresh_token = (String) json.get("refresh_token");
				
				// System.out.println("a_token: "+ access_token);
				// System.out.println("r_token: "+ refresh_token);
				
				// 이제 받은 토큰을 가진 마지막 3번째 호출인
				// 사용자 정보를 요청해야한다.
				
				String apiURL = "https://kapi.kakao.com/v2/user/me";
				String header  = "Bearer "+access_token;
				
				
				// 자바에서 특정 웹 상의 경로(URL)를 호툴하기 위해서는
				// 먼저 URL객체를 생성한다.
				URL url2 = new URL(apiURL);
				
				HttpURLConnection conn2 = (HttpURLConnection) url2.openConnection();
				
				conn2.setRequestMethod("POST");
				conn2.setDoOutput(true);
				
				// 카카오API의 문서 상에 조건이 access토큰을 header에 담아 보내라고
				// 명시되어 있으니 헤더 설정을 하자!
				conn2.setRequestProperty("Authorization", header);
				
				
				res_code = conn2.getResponseCode();
				 // System.out.println("res_code: " + res_code);
				 // System.out.println("HTTP_OK: " + HttpURLConnection.HTTP_OK);
				
				if(res_code == HttpsURLConnection.HTTP_OK) {
					BufferedReader brdm = new BufferedReader(
							new InputStreamReader(conn2.getInputStream())
							);
					
					String str = null;
					StringBuffer res = new StringBuffer();
					while( ( str = brdm.readLine()  ) != null) {
						res.append(str);
						
					}
					
					 System.out.println(res.toString());
					
					// 받은 JSON표기번의 문자열 값을 JSON 객체로 변환 후
					// 원하는 정보(nickname, profile_image)를 얻어낸다.
					
					JSONObject json2 =  (JSONObject) pars.parse(res.toString());
					
					JSONObject properties = (JSONObject) json2.get("properties");
					
					String nickname = (String) properties.get("nickname");
					String profile_image = (String) properties.get("profile_image");
					
					System.out.println("nickname: "+ nickname);
					System.out.println("profile_image: "+ profile_image);
					
				}
				
			} // res_code == 200의 끝
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return mv;
	}
	
	
	
}
