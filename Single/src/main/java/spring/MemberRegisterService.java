package spring;


import org.springframework.beans.factory.annotation.Autowired;

public class MemberRegisterService {
	private MemberDao memberDao;
	
	@Autowired
	public MemberRegisterService(MemberDao memberDao){
		this.memberDao = memberDao;
		
	}
	public MemberRegisterService(){
		
	}
	
	public void regist(Member req){
	System.out.println("1");
		Member newMember = new Member(
				req.getNick(), req.getEmail(),  req.getName(), req.getPwd(),
				req.getBirth(), req.getPhone(),  req.getLoc());
		memberDao.insert(newMember);
		
	}
}
