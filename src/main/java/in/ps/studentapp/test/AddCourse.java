package in.ps.studentapp.test;

import java.util.Scanner;

import in.ps.studentapp.admin.CourseInfo;
import in.ps.studentapp.dao.Coursesdao;
import in.ps.studentapp.dao.Coursesdaoimp;
import in.ps.studentapp.dao.Paymentdaoimp;
import in.ps.studentapp.dao.Paymentsdao;
import in.ps.studentapp.dto.Courses;
import in.ps.studentapp.dto.Payment;
import in.ps.studentapp.dto.Student;

public class AddCourse {
	public static void addCourse(Student s) {
		Scanner sc=new Scanner(System.in);
		Paymentsdao pdao=new Paymentdaoimp();
		Courses c=CourseInfo.courseInfo();
		boolean res=false;
		
		if(c!=null) {
			System.out.println("Bill amount is rs."+c.getfees()+"/-");
			Payment p=new Payment();
			p.setCourseId(c.getCourseId());
			p.setStudentId(s.getId());
			p.setTransactionId(TransactionID.generateID());
			
			System.out.println("enter the payment mode:");
			System.out.println("1. cash");
			System.out.println("2. UPI");
			System.out.println("3. credit card");
			System.out.println("4. debit card");
			int payment=0;
			payment=sc.nextInt();
			switch(payment) {
			case 1: p.setMethod("cash");
			break;
			case 2: p.setMethod("UPI");
			break;
			case 3: p.setMethod("credit card");
			break;
			case 4: p.setMethod("debit card");
			break;
			}
			p.setPath("NA");
			res=pdao.insertPayment(p);
			if(res) {
				System.out.println("payment successful! course added to your list");
			}
			else {
				System.out.println("payment failed");
			}
		}
		else {
			System.out.println("failed to option the course");
		}
	}

}
