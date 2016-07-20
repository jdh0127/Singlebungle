package Controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.AuthService;
import spring.IdPasswordNotMatchingException;
import spring.Member;

@Controller
@RequestMapping("/main")
public class LoginController {
	private AuthService authService;

	public void setAuthService(AuthService authService){
		this.authService = authService;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String form(Member member, @CookieValue(value="REMEMBER", required=false) Cookie rCookie){
		if(rCookie != null){
			member.setEmail(rCookie.getValue());
			member.setRememberEmail(true);
		}
		return "main";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String submit(Member member, Errors errors, HttpSession session, HttpServletRequest request, HttpServletResponse response){
		new LoginCommandValidator().validate(member, errors);
		if(errors.hasErrors()){
			return "main_login";
		}
		
		try{
			member = authService.authenticate(member.getEmail(), member.getPwd());
			member.getEmail();
			member.getPwd();
			
			session.setAttribute("loginUser", member.getNick());
			
			Cookie rememberCookie = new Cookie("REMEMBER", member.getEmail());
			member.setRememberEmail(true);

			rememberCookie.setPath("/");
			
			
			if(member.isRememberEmail()){
				rememberCookie.setMaxAge(60*60*24*30);
			}else{
				rememberCookie.setMaxAge(0);
			}
			response.addCookie(rememberCookie);
			return "main";
			
		}
		catch(IdPasswordNotMatchingException e){
			errors.reject("id.and.pwd");
			return "main_login";
		}
	}

}
