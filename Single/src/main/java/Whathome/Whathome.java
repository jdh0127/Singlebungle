package Whathome;

public class Whathome {
	private int mid;
	private String title;
	private String nick;
	private String content;
	private int hit;
	private int fav;
	private String time;
	
	public Whathome(){
		super();
	}
	
	

	public Whathome(String title, String nick, String content, String time) {
		super();
		this.title = title;
		this.nick = nick;
		this.content = content;
		this.time = time;
	}

	public Whathome(int mid, String title, String nick, String content, int hit, int fav, String time) {
		super();
		this.mid = mid;
		this.title = title;
		this.nick = nick;
		this.content = content;
		this.hit = hit;
		this.fav = fav;
		this.time = time;
	}



	public int getMid() {
		return mid;
	}



	public void setMid(int mid) {
		this.mid = mid;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getNick() {
		return nick;
	}



	public void setNick(String nick) {
		this.nick = nick;
	}



	public String getContent() {
		return content;
	}



	public void setContent(String content) {
		this.content = content;
	}



	public int getHit() {
		return hit;
	}



	public void setHit(int hit) {
		this.hit = hit;
	}



	public int getFav() {
		return fav;
	}



	public void setFav(int fav) {
		this.fav = fav;
	}



	public String getTime() {
		return time;
	}



	public void setTime(String time) {
		this.time = time;
	}
	
}
