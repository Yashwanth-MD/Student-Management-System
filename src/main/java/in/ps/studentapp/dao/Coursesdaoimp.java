package in.ps.studentapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import in.ps.studentapp.connection.connector;
import in.ps.studentapp.dto.Courses;

public class Coursesdaoimp implements Coursesdao{
	private Connection con;
	
	public Coursesdaoimp() {
		this.con=connector.requestConnection();
	}

	@Override
	public boolean insertCourse(Courses c) {
		String query="INSERT INTO COURSES VALUES (0,?,?,?,?)"; 
		  int i=0; 
		  try { 
		   PreparedStatement ps=con.prepareStatement(query); 
		   ps.setString(1,c.getCourseName()); 
		   ps.setString(2,c.getCourseInfo()); 
		   ps.setInt(3,c.getMonth());
		   ps.setDouble(4,c.getfees()); 
		   i=ps.executeUpdate(); 
		  }catch(SQLException e) {
			  e.printStackTrace();
		  }
		if(i>0) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean updatCourse(Courses c) {
		 String query="UPDATE COURSES SET CourseName=?,CourseInfo=?,Month=?,Fees=? WHERE CourseId=?"; 
				   int i=0; 
				   try { 
				    PreparedStatement ps=con.prepareStatement(query); 
				    ps.setString(1,c.getCourseName()); 
				    ps.setString(2,c.getCourseInfo()); 
				    ps.setInt(3,c.getMonth()); 
				    ps.setDouble(4,c.getfees()); 
				    ps.setInt(5,c.getCourseId()); 
				    i=ps.executeUpdate(); 
				   }catch(SQLException e) {
					   e.printStackTrace();
				   }
				   if(i>0) {
					   return true;
				   }
				   else {
					   return false;
				   }
	}

	@Override
	public boolean deleteCourse(int CourseId) {
		 String query="DELETE FROM COURSES WHERE CourseId=?"; 
		  int i=0; 
		  try { 
		   PreparedStatement ps=con.prepareStatement(query); 
		   ps.setInt(1,CourseId); 
		   i=ps.executeUpdate(); 
		  }catch(SQLException e) {
			  e.printStackTrace();
		  }
		  if(i>0) {
			  return true;
		  }
		  else {
			  return false;
		  }
	}

	@Override
	public Courses getCourse(int CourseId) {
		Courses c=null; 
		  String query="SELECT * FROM COURSES WHERE COURSEID=?"; 
		  try { 
		   PreparedStatement ps=con.prepareStatement(query); 
		   ps.setInt(1,CourseId); 
		   ResultSet rs=ps.executeQuery(); 
		   while(rs.next()) { 
		    c=new Courses(); 
		    c.setCourseid(rs.getInt("CourseId"));
		    c.setCourseName(rs.getString("CourseName"));
		    c.setCourseinfo(rs.getString("courseInfo"));
		    c.setMonth(rs.getInt("month"));
		    c.setfees(rs.getDouble("fees"));
		   }
		  }catch(SQLException e) {
			   e.printStackTrace();
		   }
		return c;
	}

	@Override
	public ArrayList<Courses> getCourses() {
		ArrayList<Courses> courses=new ArrayList<Courses>();
		 Courses c=null; 
		  String query="SELECT * FROM COURSES"; 
		  try { 
		   PreparedStatement ps=con.prepareStatement(query); 
		   ResultSet rs=ps.executeQuery(); 
		   while(rs.next()) { 
		    c=new Courses(); 
		    c.setCourseid(rs.getInt("CourseId"));
		    c.setCourseName(rs.getString("CourseName"));
		    c.setCourseinfo(rs.getString("courseInfo"));
		    c.setMonth(rs.getInt("month"));
		    c.setfees(rs.getDouble("fees"));
		    courses.add(c); 
		   }
		  }catch(SQLException e) {
			  e.printStackTrace();
		  }
		return courses;
	}
	
}
	