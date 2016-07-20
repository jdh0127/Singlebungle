package Whathome;

public class Hobby {

	private int mid;
	private String title;
	private String nick;
	private String sort;
	private String content;
	private int hit;
	private int fav;
	private String time;
	

	public Hobby() {
		super();
	}
	
	public Hobby(String title, String nick, String content, String time, String sort){
		this.title = title;
		this.nick = nick;
		this.content = content;
		this.time = time;
		this.sort = sort;
	}

	public Hobby(int mid, String title,  String nick, String sort, String content, int hit, int fav, String time){
		this.mid =mid;
		this.title = title;
		this.nick = nick;
		this.sort = sort;
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
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
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
