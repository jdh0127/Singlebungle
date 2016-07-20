package spring;

public class AuthService {
	private MemberDao memberDao;
	
	public void setMemberDao(MemberDao memberDao){
		this.memberDao = memberDao;
	}
	
	@SuppressWarnings("unused")
	public Member authenticate(String email, String pwd){
		
		Member member = memberDao.selectByEmail(email);
		if(member == null){
				
			throw new IdPasswordNotMatchingException();
		}
		if(!member.matchPassword(pwd)){
			throw new IdPasswordNotMatchingException();

		}
		return new Member(member.getNick(), member.getEmail());
	}
}
