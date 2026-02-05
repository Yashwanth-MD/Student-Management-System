package in.ps.studentapp.test;

import java.util.Scanner;

import in.ps.studentapp.dao.studentdao;
import in.ps.studentapp.dao.studentdaoimp;
import in.ps.studentapp.dto.Student;

public class Password {
	public static void forget() {
		System.out.println("<=== Student Forget Password ===>");
		studentdao sdao=new studentdaoimp();
		Scanner sc=new Scanner(System.in);
		System.out.println("enter your phone number:");
		long phone=sc.nextLong();
		System.out.println("enter the mail:");
		String mail=sc.next();
		Student s=sdao.getStudent(phone, mail);
		if(s!=null) {
			System.out.println("enter the password to be updated:");
			String password=sc.next();
			System.out.println("confirm the password:");
			String confirmpassword=sc.next();
			if(password.equals(confirmpassword)) {
				s.setPassword(confirmpassword);
				boolean result=sdao.updateStudent(s);
				if(result) {
					System.out.println("password updated successfull:");
				}
				else {
					System.out.println("password is not updated! please try agian:");
				}
			}
			else {
				System.out.println("password donot matched! try again:");
			}
			}
		else {
			System.out.println("account does not exit:");
		}
		}
	}


