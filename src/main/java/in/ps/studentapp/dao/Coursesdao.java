package in.ps.studentapp.dao;

import java.util.ArrayList;

import in.ps.studentapp.dto.Courses;

public interface Coursesdao {
	public boolean insertCourse(Courses c);
	public boolean updatCourse(Courses c);
	public boolean deleteCourse(int CourseId);
	public Courses getCourse(int CourseId);
	public ArrayList<Courses> getCourses();
	
}
