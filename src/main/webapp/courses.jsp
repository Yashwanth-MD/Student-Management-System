<%@page import="in.ps.studentapp.dao.Coursesdaoimp"%>
<%@page import="in.ps.studentapp.dao.Coursesdao"%>
<%@page import="in.ps.studentapp.dto.Courses"%> 
<%@page import="java.util.ArrayList"%>  
<%@page import="in.ps.studentapp.dto.Student"%> 
<%@ page language="java" contentType="text/html; charset=UTF-8" 
pageEncoding="UTF-8"%> 
<!DOCTYPE html> 
<html> 
<head> 
    <meta charset="UTF-8"> 
    <title>Available Courses</title> 
    <link rel="stylesheet" 
href="https://cdnjs.cloudflare.com/ajax/libs/font
awesome/6.4.0/css/all.min.css"> 
    <style> 
        * { 
            margin: 0; 
            padding: 0; 
            box-sizing: border-box; 
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sansserif; 
        } 
         
        :root { 
            --primary: #3498db; 
            --primary-dark: #2980b9; 
            --secondary: #2c3e50; 
            --light: #ecf0f1; 
            --success: #2ecc71; 
            --warning: #f39c12; 
            --danger: #e74c3c; 
            --gray: #95a5a6; 
            --dark: #34495e; 
        } 
         
        body { 
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); 
            color: #333; 
            min-height: 100vh; 
            padding: 20px; 
        } 
         
        .container { 
            max-width: 1200px; 
            margin: 0 auto; 
             } 
         
        .header { 
            text-align: center; 
            margin-bottom: 30px; 
            color: white; 
        } 
         
        .header h1 { 
            font-size: 2.5rem; 
            margin-bottom: 10px; 
            text-shadow: 2px 2px 4px rgba(0,0,0,0.3); 
        } 
         
        .header p { 
            font-size: 1.1rem; 
            opacity: 0.9; 
        } 
         
        .back-btn { 
            display: inline-flex; 
            align-items: center; 
            padding: 10px 20px; 
            background: rgba(255,255,255,0.2); 
            color: white; 
            text-decoration: none; 
            border-radius: 25px; 
            margin-bottom: 20px; 
            transition: all 0.3s; 
        } 
         
        .back-btn:hover { 
            background: rgba(255,255,255,0.3); 
            transform: translateY(-2px); 
        } 
         
        .back-btn i { 
            margin-right: 8px; 
        } 
         
        .alert { 
            padding: 15px 20px; 
            border-radius: 10px; 
            margin-bottom: 25px; 
            text-align: center; 
            font-weight: 500; 
        } 
         
        .alert-success { 
            background-color: rgba(46, 204, 113, 0.9); 
            color: white; 
        } 
         
        .alert-error { 
            background-color: rgba(231, 76, 60, 0.9); 
            color: white; 
        } 
         
        .courses-grid { 
            display: grid; 
            grid-template-columns: repeat(auto-fit, minmax(350px, 1fr)); 
            gap: 25px; 
            margin-top: 30px; 
        } 
         
        .course-card { 
            background: white; 
            border-radius: 15px; 
            overflow: hidden; 
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.2); 
            transition: all 0.3s ease; 
            position: relative; 
        } 
         
        .course-card:hover { 
            transform: translateY(-10px); 
            box-shadow: 0 20px 40px rgba(0, 0, 0, 0.3); 
        } 
         
        .course-header { 
            height: 160px; 
            background: linear-gradient(135deg, var(--primary), var(--primary-dark)); 
            display: flex; 
            align-items: center; 
            justify-content: center; 
            color: white; 
            font-size: 24px; 
            font-weight: 700; 
            text-align: center; 
            padding: 20px; 
            position: relative; 
            overflow: hidden; 
        } 
         
        .course-header::before { 
            content: ''; 
             position: absolute; 
            top: -50%; 
            left: -50%; 
            width: 200%; 
            height: 200%; 
            background: linear-gradient(45deg, transparent, rgba(255,255,255,0.1), transparent); 
            transform: rotate(45deg); 
            animation: shine 3s infinite; 
        } 
         
        @keyframes shine { 
            0% { transform: translateX(-100%) translateY(-100%) rotate(45deg); } 
            100% { transform: translateX(100%) translateY(100%) rotate(45deg); } 
        } 
         
        .course-body { 
            padding: 25px; 
        } 
         
        .course-title { 
            font-size: 22px; 
            font-weight: 700; 
            color: var(--secondary); 
            margin-bottom: 15px; 
            line-height: 1.3; 
        } 
         
        .course-description { 
            color: #666; 
            line-height: 1.6; 
            margin-bottom: 20px; 
            font-size: 15px; 
        } 
         
        .course-details { 
            display: flex; 
            justify-content: space-between; 
            margin-bottom: 20px; 
            padding: 15px; 
            background: #f8f9fa; 
            border-radius: 10px; 
        } 
         
        .course-detail { 
            text-align: center; 
            flex: 1;
             } 
         
        .detail-label { 
            font-size: 12px; 
            color: var(--gray); 
            text-transform: uppercase; 
            font-weight: 600; 
            margin-bottom: 5px; 
        } 
         
        .detail-value { 
            font-size: 16px; 
            font-weight: 700; 
            color: var(--secondary); 
        } 
         
        .course-price { 
            text-align: center; 
            margin-bottom: 20px; 
        } 
         
        .price { 
            font-size: 28px; 
            font-weight: 800; 
            color: var(--primary); 
        } 
         
        .price-period { 
            font-size: 14px; 
            color: var(--gray); 
        } 
         
        .purchase-form { 
            text-align: center; 
        } 
         
        .purchase-btn { 
            background: linear-gradient(135deg, var(--success), #27ae60); 
            color: white; 
            border: none; 
            padding: 15px 30px; 
            font-size: 16px; 
            font-weight: 600; 
            border-radius: 50px; 
            cursor: pointer; 
            transition: all 0.3s; 
            width: 100%; 
            display: flex;
             align-items: center; 
            justify-content: center; 
            gap: 10px; 
            box-shadow: 0 5px 15px rgba(46, 204, 113, 0.4); 
        } 
         
        .purchase-btn:hover { 
            transform: translateY(-3px); 
            box-shadow: 0 8px 20px rgba(46, 204, 113, 0.6); 
        } 
         
        .no-courses { 
            text-align: center; 
            padding: 60px 20px; 
            color: white; 
        } 
         
        .no-courses i { 
            font-size: 80px; 
            margin-bottom: 20px; 
            opacity: 0.7; 
        } 
         
        .no-courses h3 { 
            font-size: 28px; 
            margin-bottom: 15px; 
        } 
         
        .student-info { 
            background: rgba(255,255,255,0.1); 
            padding: 15px 25px; 
            border-radius: 10px; 
            margin-bottom: 20px; 
            display: flex; 
            justify-content: space-between; 
            align-items: center; 
            color: white; 
            backdrop-filter: blur(10px); 
        } 
         
        .student-welcome { 
            font-size: 18px; 
            font-weight: 600; 
        } 
         
        .student-email { 
            opacity: 0.9; 
        }
        
         @media (max-width: 768px) { 
            .courses-grid { 
                grid-template-columns: 1fr; 
            } 
             
            .student-info { 
                flex-direction: column; 
                text-align: center; 
                gap: 10px; 
            } 
             
            .header h1 { 
                font-size: 2rem; 
            } 
        } 
    </style> 
</head> 
<body> 
    <div class="container"> 
        <!-- Student Info Bar --> 
        <% Student s = (Student)session.getAttribute("student"); %> 
        <% if(s != null) { %> 
        <div class="student-info"> 
            <div> 
                <div class="student-welcome">Welcome, <%= s.getName() %>!</div> 
                <div class="student-email"><%= s.getMail() %></div> 
            </div> 
            <a href="dashboard.jsp" class="back-btn"> 
                <i class="fas fa-arrow-left"></i> Back to Dashboard 
            </a> 
        </div> 
        <% } %> 
         
        <!-- Header --> 
        <div class="header"> 
            <h1>Available Courses</h1> 
            <p>Enhance your skills with our comprehensive course catalog</p> 
        </div> 
         
        <!-- Success/Error Messages --> 
        <%  
            String success = (String)request.getAttribute("success"); 
            String error = (String)request.getAttribute("error"); 
        %> 
         
        <% if(success != null) { %>
         <div class="alert alert-success"> 
            <i class="fas fa-check-circle"></i> <%= success %> 
        </div> 
        <% } %> 
         
        <% if(error != null) { %> 
        <div class="alert alert-error"> 
            <i class="fas fa-exclamation-circle"></i> <%= error %> 
        </div> 
        <% } %> 
         
        <!-- Courses Grid --> 
        <%  
            if(s != null) { 
                Coursesdao cdao = new Coursesdaoimp(); 
                ArrayList<Courses> courses = cdao.getCourses(); 
                 
                if(courses != null && !courses.isEmpty()) { 
        %> 
        <div class="courses-grid"> 
            <% for(Courses c : courses) { %> 
            <div class="course-card"> 
                <div class="course-header"> 
                    <%= c.getCourseName() %> 
                </div> 
                <div class="course-body"> 
                    <h3 class="course-title"><%= c.getCourseName() %></h3> 
                    <p class="course-description"> 
                        <%= c.getCourseInfo() != null ? c.getCourseInfo() : "A comprehensive course designed to enhance your skills and knowledge in this field." %> 
                    </p> 
                     
                    <div class="course-details"> 
                        <div class="course-detail"> 
                            <div class="detail-label">Duration</div> 
                            <div class="detail-value"><%= c.getMonth() %> months</div> 
                        </div> 
                    </div> 
                     
                    <div class="course-price"> 
                        <div class="price">₹<%= c.getfees()%></div> 
                        <div class="price-period">one-time payment</div> 
                    </div>
                    
                     <form class="purchase-form" action="transaction.jsp" method="post"> 
                        <input type="hidden" name="courseId" value="<%= c.getCourseId() %>"> 
                        <button type="submit" class="purchase-btn"> 
                            <i class="fas fa-shopping-cart"></i> Enroll Now 
                        </button> 
                    </form> 
                </div> 
            </div> 
            <% } %> 
        </div> 
        <% } else { %> 
        <div class="no-courses"> 
            <i class="fas fa-book"></i> 
            <h3>No Courses Available</h3> 
            <p>We're currently updating our course catalog. Please check back later.</p> 
        </div> 
        <% }  
        } else {  
            request.setAttribute("error", "Session expired! Please login again."); 
            RequestDispatcher rd = request.getRequestDispatcher("login.jsp"); 
            rd.forward(request, response); 
        } %> 
    </div> 
 
    <script> 
        // Add animation delay to course cards 
        document.addEventListener('DOMContentLoaded', function() { 
            const cards = document.querySelectorAll('.course-card'); 
            cards.forEach((card, index) => { 
                card.style.animationDelay = `${index * 0.1}s`; 
            }); 
        }); 
    </script> 
</body> 
</html>