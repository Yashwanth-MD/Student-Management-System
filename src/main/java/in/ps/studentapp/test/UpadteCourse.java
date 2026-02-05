package in.ps.studentapp.test;

import java.util.ArrayList;
import java.util.Scanner;

import in.ps.studentapp.dao.Coursesdao;
import in.ps.studentapp.dao.Coursesdaoimp;
import in.ps.studentapp.dto.Courses;

public class UpadteCourse {
	 public static void update()
	  {
	    int choice=0;
	    Scanner sc=new Scanner(System.in);
	    Coursesdao cdao=new Coursesdaoimp();
	      ArrayList<Courses> li=cdao.getCourses();
	      for(Courses c:li)
	      {
	        System.out.println(c);
	      }
	      System.out.println("Enter the Course Id To to be updated");
	      Courses c=cdao.getCourse(sc.nextInt());
	      System.out.println(c);
	      do
	      {
	        System.out.println("1.To Update CourseName");
	        System.out.println("2.To Update CourseInfo");
	        System.out.println("3.To Update Month");
	        System.out.println("4.To Update Fees");
	        System.out.println("Enter 5 to Exit");
	        System.out.println("Choose the option ");
	        choice=sc.nextInt();
	        switch(choice)
	        {
	        case 1:System.out.println("Enter the Coursename to be Updated");
	             c.setCourseName(sc.next()); 
	             break;
	        case 2:System.out.println("Enter the CourseInfo to be Updated");
	                c.setCourseName(sc.next()); 
	                break;
	        case 3:System.out.println("Enter the Month to be Updated");
	                c.setMonth(sc.nextInt()); 
	                break;
	        case 4:System.out.println("Enter the Fees to be Updated");
	                c.setfees(sc.nextDouble()); 
	                break;
	        case 5:System.out.println("Enter 5 to Exit");
	                  break;
	         default:System.out.println("Invalid Option");
	        }
	        boolean flag=cdao.updatCourse(c);
	        if(flag)System.out.println("Updated successfully");
	        else System.out.println("Failed To Update");
	        
	        
	      }while(choice!=5);
	  }

	}

