package kr.or.connect.todo.dto;

public class TodoDto {
	private long id;
	private String name;
	private String regDate;
	private int sequence;
	private String title;
	private String type;

	public static class Builder {
		private long id;
		private String name;
		private String regDate;
		private int sequence;
		private String title;
		private String type;

		public Builder id(long id) {
			this.id = id;
			return this;
		}

		public Builder type(String type) {
			this.type = type;
			return this;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder regDate(String regDate) {
			this.regDate = regDate;
			return this;
		}

		public Builder sequence(int sequence) {
			this.sequence = sequence;
			return this;
		}

		public Builder title(String title) {
			this.title = title;
			return this;
		}

		public TodoDto build() {
			return new TodoDto(this);
		}
	}

	public TodoDto(Builder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.regDate = builder.regDate;
		this.sequence = builder.sequence;
		this.title = builder.title;
		this.type = builder.type;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "TodoDto [id=" + id + ", name=" + name + ", regDate=" + regDate +
			",\n sequence=" + sequence + ", title=" + title + ", type=" + type + "]";
	}

}
