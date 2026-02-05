package in.ps.studentapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import in.ps.studentapp.connection.connector;
import in.ps.studentapp.dto.Student;

public class studentdaoimp implements studentdao {
	private Connection con;

	public studentdaoimp() {
		this.con = connector.requestConnection();
	}

	@Override
	public boolean insertStudent(Student s) {
        String query="insert into student values(0,?,?,?,?,sysdate())";
		int i = 0;
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, s.getName());
			ps.setLong(2, s.getPhone());
			ps.setString(3, s.getMail());
			ps.setString(4, s.getPassword());
			i = ps.executeUpdate();
		}
//		catch (SQLIntegrityConstraintViolationException e) {
//            
//            System.out.println("User already registered in this app. Please login.");
//        } 
		catch (SQLException e) {
		
			e.printStackTrace();
		}
       if(i > 0) {
		return true;
       }
       else {
    	   return false;
       }
	}

	@Override
	public boolean updateStudent(Student s) {

String query="update student set name=?,phone=?,mail=?password=?, where id=?";
         int i=0;
		try {
			
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, s.getName());
			ps.setLong(2, s.getPhone());
			ps.setString(3, s.getMail());
			ps.setString(4, s.getPassword());
			ps.setInt(5, s.getId());
		      i=ps.executeUpdate();
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
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
	public boolean deleteStudent(int id) {
         String query="delete from student where id=?";
         int i=0;
		try {
			PreparedStatement ps=con.prepareStatement(query);
			ps.setInt(1, id);
			i=ps.executeUpdate();
		} catch (SQLException e) {
		
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
	public Student getStudent(String mail, String password) {

		String query = "select * from student where mail=? and password=?";
		Student s = null;
		
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, mail);
			ps.setString(2, password);
			ResultSet res = ps.executeQuery();
			
			while (res.next()) {
				s = new Student();
//				int id=res.getInt("id");
//				s.setId(id);
				s.setId(res.getInt("id"));
				s.setName(res.getString("name"));
				s.setPhone(res.getLong("phone"));
				s.setMail(res.getString("mail"));
				s.setPassword(res.getString("password"));
				s.setDate(res.getString("date"));
			
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return s ;
	}

	@Override
	public Student getStudent(long phone, String mail) {
		// TODO Auto-generated method stub
		String query = "select * from student where phone=? and mail=?";
		Student s = null;
		
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setLong(1, phone);
			ps.setString(2, mail);
			ResultSet res = ps.executeQuery();
			
			while (res.next()) {
				s = new Student();
				s.setId(res.getInt("id"));
				s.setName(res.getString("name"));
				s.setPhone(res.getLong("phone"));
				s.setMail(res.getString("mail"));
				s.setPassword(res.getString("password"));
				s.setDate(res.getString("date"));
			
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return s ;
	}

	@Override
	public ArrayList<Student> getStudent() {
		ArrayList<Student>students=new ArrayList<Student>();
		Student s=null;
		String query="select * from Student";
		try {
			PreparedStatement ps=con.prepareStatement(query);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				s=new Student();
				s.setId(rs.getInt("id")); 
			    s.setName(rs.getString("name")); 
			    s.setPhone(rs.getLong("phone")); 
			    s.setMail(rs.getString("mail")); 
			    s.setPassword(rs.getString("password")); 
			    s.setDate(rs.getString("date")); 
			    students.add(s);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return students;
	}

}
