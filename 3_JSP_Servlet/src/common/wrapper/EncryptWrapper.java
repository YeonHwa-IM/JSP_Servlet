package common.wrapper;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class EncryptWrapper extends HttpServletRequestWrapper{ //회원가입, 로그인, 비밀번호변경
	//클래스 명에 에러가 뜨는데 무조건!! 매개변수 생성자 무조건!! 만들어줘야함
	//객체생성해서 쓸일 딱히 없으니까 기본생성자 같은건 생략~
	
	public EncryptWrapper(HttpServletRequest request) {
		super(request);
		// TODO Auto-generated constructor stub
	}
	
	//getParameter 오버라이딩해라!
	@Override
	public String getParameter(String name) {
		// 값들을 받아서 암호화해서 getParameter(name); 해서 인코딩해서 보내주겠다
		
		String value ="";
		
		//name 에 들어올수있는건? 회원가입, 로그인, 비밀번호변경 에 사용. 뷰에 있는 네임태그값 말하는것! 
		// joinUserPwd 회원가입에서 가져올수있는 네임 파라미터값.
		// userPwd 로그인에서 가져오는 네임 파라미터 값.
		// newPwd
		if(name != null && (name.equals("joinUserPwd") || name.equals("userPwd") || name.equals("newPwd"))) {
			//내가 네임으로 받아온 값이 널이 아니면서 뒤의 이름 세개 중 하나면서 비어있지 않아야함
			
			value = getSha512(super.getParameter(name)); //암호화된 스트림이 반환되서 담김, 아래에 다시 메소드 만들어주기.
		}else {
			value = super.getParameter(name);
		}
		return value;
	}
	
	public static String getSha512(String userPwd) { //매개변수 userPwd? 
		//SHA-512 암호화 방식 : 많이 사용하지 않음. =>비크립트(Bcrypt)를 더 많이 사용하는데 스프링가서 배움!
		String encPwd = null; //암호화될 비번 담을 변수
		
		MessageDigest md =null; //MessageDigest가 필요함 java.security 안에 있음
		
		try {
			md = MessageDigest.getInstance("SHA-512"); //어떤 알고리즘을 사용할 것인지 ? 샤512 방식으로 하겠다.
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		byte[] bytes = userPwd.getBytes(Charset.forName("UTF-8"));//내가 받은 문자열을 바이트로 바꿔줌(그안에서 Charset.forName("UTF-8")로 해주고) 그걸 바이트 배열로 넣어줌
		md.update(bytes); // md.안에 다이제스트와 업데이트가 있음. / 바이트의 특정 배열을 통해서 갱신해주겠다. 
		encPwd = Base64.getEncoder().encodeToString(md.digest());
		
		return encPwd; //암호화된 스트링이 반환됨.
	}
	
	
}
