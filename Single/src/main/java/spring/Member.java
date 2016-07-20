package spring;

public class Member {
	private String nick;
	private String email;
	private String name;
	private boolean rememberEmail;
	private String birth;
	private String pwd;
	private String phone;
	private String loc;
	private int locCheck;
	private int point;
	private int admin;
	
	public Member(){}
	
	public Member(String nick, String email){
		this.nick = nick;
		this.email = email;
	}
	
	public Member(String nick, String email, String name, String pwd, String birth, String phone, String loc){
		this.nick = nick;
		this.email = email;
		this.name = name;
		this.pwd = pwd;
		this.birth = birth;
		this.phone = phone;
		this.loc = loc;
		
	}
	
	public Member(String nick, String email, String name, String pwd, String phone, String loc, int locCheck){
		this.nick = nick;
		this.email =email;
		this.name=name;
		this.pwd=pwd;
		this.phone=phone;
		this.loc=loc;
		this.locCheck=locCheck;
	}

	public Member(String nick, String email, String name, String pwd,String birth, String phone, String loc, int locCheck, int point, int admin){
		this.nick = nick;
		this.email =email;
		this.name=name;
		this.pwd=pwd;
		this.birth = birth;
		this.phone=phone;
		this.loc=loc;
		this.locCheck=locCheck;
		this.point = point;
		this.admin = admin;
	}
	
	
	
	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

	public int getLocCheck() {
		return locCheck;
	}

	public void setLocCheck(int locCheck) {
		this.locCheck = locCheck;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}
	
	public boolean isRememberEmail() {
		return rememberEmail;
	}
	
	public void setRememberEmail(boolean rememberEmail) {
		this.rememberEmail = rememberEmail;
	}
	

	
	public boolean matchPassword(String pwd) {
		return this.pwd.equals(pwd);
	}

	
	
	public int getAdmin() {
		return admin;
	}

	public void setAdmin(int admin) {
		this.admin = admin;
	}

	@Override
	public String toString() {
		return "Member [nick=" + nick + ", email=" + email + ", name=" + name + ", rememberEmail=" + rememberEmail
				+ ", pwd=" + pwd + ", phone=" + phone + ", loc=" + loc + ", locCheck=" + locCheck + ", point=" + point
				+ "]";
	}
	
	
}
