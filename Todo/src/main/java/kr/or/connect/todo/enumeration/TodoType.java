package kr.or.connect.todo.enumeration;

public enum TodoType {
	TODO,
	DOING,
	DONE;

	public String getNextType() {
		if(this == DONE)
			return this.name();
		return values()[ordinal() + 1].name();
	}
}
