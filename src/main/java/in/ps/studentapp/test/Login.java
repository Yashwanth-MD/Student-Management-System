package in.ps.studentapp.test;

import java.util.Scanner;

import in.ps.studentapp.admin.Admin;
import in.ps.studentapp.dao.studentdao;
import in.ps.studentapp.dao.studentdaoimp;
import in.ps.studentapp.dto.Student;

public class Login {
	public static void login() {
		//to connect to db
		System.out.println("<===Student Login Page===>");
		studentdao sdao=new studentdaoimp();
	    Scanner sc=new Scanner(System.in);

		System.out.println("enter the mail:");
		String mail = sc.next();

		System.out.println("enter the password:");
		String password = sc.next();
		
		Student s = sdao.getStudent(mail, password);
		
		if (s != null) {
			System.out.println("Login is done successful." + s.getName());
			if(s.getId()==2) {
				Admin.admin(s);  //admin
			}
			else {
				App.options(s);  //normal user
			}
		} 
		else {
			System.out.println("Invalid mail or password failed to login");
		}
	}

}
