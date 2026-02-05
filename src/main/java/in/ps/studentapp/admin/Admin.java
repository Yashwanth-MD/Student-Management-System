package in.ps.studentapp.admin;

import java.util.ArrayList;
import java.util.Scanner;

import in.ps.studentapp.dao.Coursesdao;
import in.ps.studentapp.dao.Coursesdaoimp;
import in.ps.studentapp.dao.Paymentdaoimp;
import in.ps.studentapp.dao.Paymentsdao;
import in.ps.studentapp.dao.studentdao;
import in.ps.studentapp.dao.studentdaoimp;
import in.ps.studentapp.dto.Courses;
import in.ps.studentapp.dto.Payment;
import in.ps.studentapp.dto.Student;

public class Admin {
	public static void admin(Student s) {
		 studentdao sdao=new studentdaoimp(); 
		  Coursesdao cdao=new Coursesdaoimp(); 
		  Paymentsdao pdao= new Paymentdaoimp(); 
		  Scanner sc=new Scanner(System.in);
	   
	    int choice=0;
	    do {
	        System.out.println("<===== Admin Panel =====>");
	        System.out.println("1. View All Users");
	        System.out.println("2. Delete User");
	        System.out.println("3. Add Course");
	        System.out.println("4. Update Course");
	        System.out.println("5. Delete Course");
	        System.out.println("6. View Payments");
	        System.out.println("7. Approve Payments");
	        System.out.println("8. Exit");
	        
	        choice = sc.nextInt();

	        switch (choice) {
	            case 1:
	                 ArrayList<Student>students=sdao.getStudent();
	                 for(Student st:students) {
	                	 System.out.println(st);
	                 }
	                break;
	            case 2:
	                System.out.print("Enter Student ID to Delete: ");
//	                int id = sc.nextInt();
	                boolean result=sdao.deleteStudent(sc.nextInt());
	                if(result) {
	                	System.out.println("Account deleted sussessfully");
	                }
	                else {
	                	System.out.println("Failed to delete the account!");
	                }
	                break;
	            case 3:
	            	Courses c=new Courses();
	            	System.out.println("enter the name of the courses");
	            	sc.nextLine();
	            	c.setCourseName(sc.nextLine());
	            	System.out.println("enter the courses information");
	            	c.setCourseinfo(sc.nextLine());
	            	System.out.println("enter the courses duration in month");
	            	c.setMonth(sc.nextInt());
	            	System.out.println("enter the course fees");
	            	c.setfees(sc.nextDouble());
	                boolean res=cdao.insertCourse(c);
	                if(res) {
	                	System.out.println("courses added successfully");
	                }
	                else {
	                	System.out.println("failed to add course");
	                }
	                break;
	            case 4:
	            	CourseInfo.update();
	                break;
	            case 5:
	                CourseInfo.delete();
	                
	                break;
	            case 6:
	            	 ArrayList<Payment> payments=pdao.getPayment(); 
	                 for(Payment p:payments) { 
	                  System.out.println(p); 
	                 } 
	                break;
	            case 7:
	            	 ArrayList<Payment> payment=pdao.getPayment(); 
	            	   for(Payment p:payment) { 
	            	    System.out.println(p); 
	            	   } 
	            	   System.out.println("Enter the payment Id to be updated:"); 
	            	   Payment p=pdao.getPayment(sc.nextInt()); 
	            	   System.out.println("1. Approve"); 
	            	   System.out.println("2. Decline"); 
	            	   int i=sc.nextInt(); 
	            	   if(i==1) { 
	            	    p.setStatus("Successful"); 
	            	   } 
	            	   else if(i==2) { 
	            	    p.setStatus("Failed"); 
	            	   } 
	            	   else { 
	            	    System.out.println("Invalid response"); 
	            	   } 
	            	   boolean results=pdao.updatePayment(p);
	            	   if(results) { 
	            		    System.out.println("Payment status updated!"); 
	            		   } 
	            		   else { 
	            		    System.out.println("Failed to update the payment status"); 
	            		   }
	                break;
	            case 8:
	                System.out.println("going back to main menu----");
	                break;
	            default:
	                System.out.println("Invalid choice! Try again.");
	        }
	    } while (choice != 8);

	}

}
