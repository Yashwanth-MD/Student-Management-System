package in.ps.studentapp.dto;

//instance variables
public class Courses {
	private int courseId;
	private String courseName;
	private String courseInfo;
	private int months;
	private double fees;
	
	//setter and getters
	public int getCourseId() {
		return courseId;
	}
	public void setCourseid(int courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getCourseInfo() {
		return courseInfo;
	}
	public void setCourseinfo(String courseInfo) {
		this.courseInfo = courseInfo;
	}
	public int getMonth() {
		return months;
	}
	public void setMonth(int months) {
		this.months = months;
	}
	public double getfees() {
		return fees;
	}
	public void setfees(double fees) {
		this.fees = fees;
	}
	@Override 
	 public String toString() { 
	  return "Courses [courseId=" + courseId + ", courseName=" + 
	courseName + ", courseInfo=" + courseInfo 
	    + ", months=" + months + ", fees=" + fees + "]";
	}
	
}

