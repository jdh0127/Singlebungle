package Honey;

public class Honey {
	private int mid;
	private String title;
	private String nick;
	private String content;
	private int hit;
	private int fav;
	private String time;
	
	
	public Honey() {
		super();
	}
	
	public Honey(String title, String nick, String content, String time){
		this.title = title;
		this.nick = nick;
		this.content = content;
		this.time = time;
	}

	public Honey(int mid, String title, String nick, String content, int hit, int fav, String time){
		this.mid =mid;
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
