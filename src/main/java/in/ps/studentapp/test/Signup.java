package in.ps.studentapp.test;

import java.util.Scanner;

import in.ps.studentapp.dao.studentdao;
import in.ps.studentapp.dao.studentdaoimp;
import in.ps.studentapp.dto.Student;

public class Signup {
	public static void signup() {
		System.out.println("<==Student Signup Page==>");
		// create object of poja class

		Student s = new Student();
        //collecting data from the user
		Scanner sc = new Scanner(System.in);
		System.out.println("enter the name:");
		// String name=sc.next(); 
		 // s.setName(name);
		s.setName(sc.next());

		System.out.println("enter the phone:");
		s.setPhone(sc.nextLong());

		System.out.println("enter the mail:");
		s.setMail(sc.next());

		System.out.println("enter new password");
		String newpassword = sc.next();

		System.out.println("confirm password");
		String confirmpassword = sc.next();

		if (newpassword.equals(confirmpassword)) {
			s.setPassword(confirmpassword);
			
            //creating reference of studentdao
			studentdao sdao = new studentdaoimp();
			boolean result=sdao.insertStudent(s);
			if (result) {

				System.out.println("Signup is done successful.");
			}

			else {
				System.out.println("invalid cerdientionl.");
			}
		} else {
			System.out.println("Invalid password! New password and Confirm password do not match.");
		}
	}

}
