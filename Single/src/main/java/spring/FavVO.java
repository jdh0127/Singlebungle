package spring;

public class FavVO {
	private String nick;
	private int mid;
	
	public FavVO(){}
	
	public FavVO(int mid, String nick) {
		this.mid = mid;
		this.nick = nick;
	}



	public String getNick() {
		return nick;
	}
	
	
	
	public void setNick(String nick) {
		this.nick = nick;
	}
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	
	
	
}
