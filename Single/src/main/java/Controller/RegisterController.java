package Controller;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import Whatdo.Whatdo;
import spring.MailingSender;
import spring.Member;
import spring.MemberDao;
import spring.MemberRegisterService;


@Controller
public class RegisterController {
   
   @Autowired
   private DataSource dataSource;
   
   private MemberRegisterService memberRegisterService;
   
   public void setMemberRegisterService(
         MemberRegisterService memberRegisterService){
      this.memberRegisterService = memberRegisterService;
   }
   
   @RequestMapping(value="/Memberjoin", method=RequestMethod.POST)
   public String insert(Member req, Errors errors){
      try{
         memberRegisterService.regist(req);
         return "redirect:/main";
      }catch(Exception e){
         errors.reject("e");
         return "redirect:/main_login";
      }
   }
   
   @RequestMapping(value="/mypage", method=RequestMethod.GET)
   public String mypagepwd(@ModelAttribute("cmd") Member req, Errors errors, Model model, HttpServletRequest request){
      String nick = request.getParameter("nick");
      model.addAttribute("nick", nick);
         return "mypage1";
   }
   
   @RequestMapping(value="/confirmpwd", method=RequestMethod.POST)
   public String confirmpwd(@ModelAttribute("cmd") Member req, Errors errors, Model model, HttpServletRequest request){
      String nick = request.getParameter("nick");
      String pwd = request.getParameter("pwd");
      MemberDao memberDao = new MemberDao(dataSource);   
      
      int result = memberDao.pwdcheck(nick, pwd);
      if(result==0){
         request.setAttribute("nick", nick);
         request.setAttribute("result", result);
         request.setAttribute("mes", "비밀번호가 일치하지 않습니다.");
         List<Member> member = memberDao.selectall(nick);
         model.addAttribute("members", member);
         return "mypage1";
      }
      else{
         request.setAttribute("nick", nick);
         request.setAttribute("result", result);
         List<Member> member = memberDao.selectall(nick);
         model.addAttribute("members", member);
         return "mypageview";
         
      }
   }
   
   @RequestMapping(value="/updatemem", method=RequestMethod.POST)
   public String updatemem(@ModelAttribute("cmd") Member req, Errors errors, Model model, HttpServletRequest request) {
      String nick = request.getParameter("nick");
      
      MemberDao memberDao = new MemberDao(dataSource);   
      
      List<Member> member = memberDao.selectall(nick);
      request.setAttribute("nick", nick);
      model.addAttribute("members", member);
      return "mypageup";
   }
   
   @RequestMapping(value="/confirm_update", method=RequestMethod.POST)
   public String confirm_update(@ModelAttribute("cmd") Member req, Errors errors, Model model, HttpServletRequest request){
      String nick = request.getParameter("nick");
      Member newMember = new Member(
            nick, req.getEmail(), req.getName(), req.getPwd(),
            req.getBirth(), req.getPhone(), req.getLoc());
      MemberDao memberDao = new MemberDao(dataSource);   
      memberDao.update(newMember);
      request.setAttribute("nick", nick);
      List<Member> member = memberDao.selectall(nick);
      model.addAttribute("members", member);
      return "mypageview";
   }
   
   // 회원탈퇴
   @RequestMapping(value="/memberdelete", method=RequestMethod.GET)
   public String delete(HttpServletRequest request, HttpSession session){
      String email = request.getParameter("email");
      MemberDao memberDao = new MemberDao(dataSource);
      memberDao.delete(email);
      session.invalidate();
      return "redirect:/main";
   }
   
   
   @RequestMapping(value="/admin")
   public String allmember(@ModelAttribute("cmd") Whatdo whatdo, Errors errors, Model model, HttpServletRequest request){
      if(errors.hasErrors()){
         return "mypageview";
      }
      MemberDao memberDao = new MemberDao(dataSource);
      List<Member> member = memberDao.selectmember();
      model.addAttribute("member", member);
      return "admin";
   }
   
   // 운영자가 삭제
   @RequestMapping("/deletemember")
   public String deletemember(HttpServletRequest request){
      String[] nick = request.getParameterValues("nick");
      MemberDao memberDao = new MemberDao(dataSource);
      for(int i=0; i<nick.length; i++){
         memberDao.deletemember(nick[i]);

      }
      return "redirect:/admin";
   }
   
   // 메일보내기
   @RequestMapping(value="/mailsend", method=RequestMethod.POST)
   public String sendmail(Model model, HttpServletRequest request, HttpSession session){
      String user =(String) session.getAttribute("loginUser");
      String sort = request.getParameter("sort");
      String title = request.getParameter("title");
      String content = request.getParameter("content");
      
      try {
         MailingSender.getInstance().initPost("www.sing.com", "jjj3742@naver.com", user + "님이 보낸 메일입니다.", user + "님이 보낸 메일입니다.\n문의사항 : " + sort+"\n"+"문의 내용 : " + content).send();
      } catch (AddressException e) {
         e.printStackTrace();
      } catch (MessagingException e) {
         e.printStackTrace();
      }

      String msg = "<script>alert('메일이 성공적으로 전송되었습니다.');</script>";
      
      
      session.setAttribute("alert", msg);
      return "redirect:/customer";
   }
   
   @RequestMapping(value="/Emailcheck", method=RequestMethod.GET)
   public String emailcheck(Member req, Errors errors, HttpServletRequest request){
   String email = request.getParameter("email");

   int result = MemberDao.emailcheck(email);
   
   request.setAttribute("email", email);
   request.setAttribute("result", result);
   return "emailcheck";
   }

   @RequestMapping(value="/Nickcheck", method=RequestMethod.GET)
   public String nickcheck(Member req, Errors errors, HttpServletRequest request){
      String nick = request.getParameter("nick");
      
      int result = MemberDao.nickcheck(nick);
      
      request.setAttribute("nick", nick);
      request.setAttribute("result", result);
      return "nickcheck";
   }
   @RequestMapping(value="/customer", method=RequestMethod.GET)
   public String customer(){
      return "customer";
   }

   
}