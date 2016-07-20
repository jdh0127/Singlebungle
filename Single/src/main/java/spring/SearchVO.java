package spring;

public class SearchVO {
	@Override
	public String toString() {
		return "SearchVO [search=" + search + ", sec=" + sec + "]";
	}

	private String search;
	private String sec;

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getSec() {
		return sec;
	}

	public void setSec(String sec) {
		this.sec = sec;
	}
}
