package in.ps.studentapp.test;

import java.util.ArrayList;
import java.util.Scanner;

import com.mysql.cj.x.protobuf.MysqlxCrud.Update;

import in.ps.studentapp.dao.Coursesdao;
import in.ps.studentapp.dao.Coursesdaoimp;
import in.ps.studentapp.dao.Paymentdaoimp;
import in.ps.studentapp.dao.Paymentsdao;
import in.ps.studentapp.dao.studentdao;
import in.ps.studentapp.dao.studentdaoimp;
import in.ps.studentapp.dto.Courses;
import in.ps.studentapp.dto.Payment;
import in.ps.studentapp.dto.Student;

public class App {
	public static void options(Student s) {
		studentdao sdao=new studentdaoimp();
		Paymentsdao pdao=new Paymentdaoimp();
		Coursesdao cdao=new Coursesdaoimp();
		Scanner sc=new Scanner(System.in);
		int choice=0;
		System.out.println("good day"+s.getName());
		do {
			ArrayList<Payment>payments=pdao.getPaymentByStudentId(s.getId());
			
			System.out.println("1.view purshased course");
			System.out.println("2. add courses");
			System.out.println("3. view account");
			System.out.println("4. update account");
			System.out.println("5. view payments and status");
			System.out.println("6. exit");
			
			choice=sc.nextInt();
			
			switch(choice) {
			case 1:if(payments==null||payments.isEmpty()) {
				System.out.println("No courses found");
			}
			else {
				for(Payment p:payments) {
					Courses c=cdao.getCourse(p.getCourseId());
					if(p.getStatus().equalsIgnoreCase("pending")) {
						System.out.println("course name:"+c.getCourseName());
						System.out.println("status:"+p.getStatus());
						System.out.println("course will be purchased once admin approves");
					}
					else {
						System.out.println(c);
					}
				}
			}
			break;
			case 2: AddCourse.addCourse(s);
			break;
			case 3:System.out.println(s);
			break;
			case 4: UpadteCourse.update();
			break;
			case 5:for(Payment p:payments) {
				System.out.println(p);
			}
			break;
			}
		}
		while(choice!=6);
	}

}
