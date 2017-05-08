package evolution.entity;

public class Course {
	private Integer id;
	private String name;
	private Integer hours;
	private Integer professorId;
	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", hours=" + hours + ", professorId=" + professorId + "]";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getHours() {
		return hours;
	}
	public void setHours(Integer hours) {
		this.hours = hours;
	}
	public Integer getProfessorId() {
		return professorId;
	}
	public void setProfessorId(Integer professorId) {
		this.professorId = professorId;
	}
}
