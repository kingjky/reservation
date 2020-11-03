package kr.or.connect.todo.enumeration;

public enum TodoColumn {
	ID("id"),
	TITLE("title"),
	NAME("name"),
	SEQUENCE("sequence"),
	TYPE("type"),
	REGDATE("regdate");
	
	private String name;
	
	TodoColumn(String name) {
		this.name= name;
	}

	public String getName() {
		return name;
	}
}
