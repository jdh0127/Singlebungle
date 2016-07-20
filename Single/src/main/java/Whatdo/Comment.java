package Whatdo;


public class Comment {

	private int num;
	private int ref;
	private String writer, content;
	private String date;
	
	public Comment(){
		
	}
	public Comment(int num, String content){
		this.num=num;
		this.content = content;
	}
	
	public Comment(int ref, String writer, String content, String date) {
		this.ref = ref;
		this.writer = writer;
		this.content = content;
		this.date = date;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getRef() {
		return ref;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
}
