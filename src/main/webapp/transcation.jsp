<%@page import="in.ps.studentapp.dao.Coursesdaoimp"%>
<%@page import="in.ps.studentapp.dao.Coursesdao"%>
<%@page import="in.ps.studentapp.test.TransactionID"%>
<%@page import="in.ps.studentapp.dto.Courses"%>
<%@page import="in.ps.studentapp.dto.Student"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Payment Transaction</title>
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }

        :root {
            --primary: #3498db;
            --secondary: #2c3e50;
            --success: #2ecc71;
            --danger: #e74c3c;
            --gray: #95a5a6;
            --dark: #34495e;
        }

        body {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 20px;
        }

        .container {
            max-width: 800px;
            width: 100%;
            background: white;
            border-radius: 15px;
            box-shadow: 0 20px 40px rgba(0, 0, 0, 0.1);
            overflow: hidden;
        }

        .header {
            background: linear-gradient(135deg, var(--secondary), var(--dark));
            color: white;
            padding: 25px;
            text-align: center;
        }

        .header h1 {
            font-size: 28px;
            margin-bottom: 10px;
        }

        .back-btn {
            display: inline-flex;
            align-items: center;
            padding: 10px 20px;
            background: rgba(255, 255, 255, 0.1);
            color: white;
            text-decoration: none;
            border-radius: 25px;
            margin-bottom: 20px;
            transition: all 0.3s;
        }

        .back-btn:hover {
            background: rgba(255, 255, 255, 0.2);
            transform: translateY(-2px);
        }

        .content {
            padding: 30px;
        }

        .bill-section {
            background: #f8f9fa;
            border-radius: 10px;
            padding: 25px;
            margin-bottom: 30px;
        }

        .bill-title {
            font-size: 20px;
            color: var(--secondary);
            margin-bottom: 20px;
            display: flex;
            align-items: center;
        }

        .bill-title i {
            margin-right: 10px;
            color: var(--primary);
        }

        .bill-details {
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 15px;
            margin-bottom: 20px;
        }

        .bill-item {
            display: flex;
            justify-content: space-between;
            padding: 10px 0;
            border-bottom: 1px solid #e0e0e0;
        }

        .bill-item:last-child {
            border-bottom: none;
            font-weight: 700;
            font-size: 18px;
            color: var(--primary);
        }

        .bill-label {
            color: var(--gray);
        }

        .bill-value {
            font-weight: 600;
            color: var(--secondary);
        }

        .transaction-info {
            background: #fff3cd;
            border: 1px solid #ffeaa7;
            border-radius: 8px;
            padding: 15px;
            margin-bottom: 20px;
            text-align: center;
        }

        .transaction-id {
            font-family: monospace;
            font-size: 18px;
            font-weight: 700;
            color: var(--secondary);
            letter-spacing: 1px;
        }

        .payment-form {
            background: white;
            border-radius: 10px;
            padding: 25px;
            border: 2px solid #f0f0f0;
        }

        .form-title {
            font-size: 20px;
            color: var(--secondary);
            margin-bottom: 20px;
            display: flex;
            align-items: center;
        }

        .form-title i {
            margin-right: 10px;
            color: var(--primary);
        }

        .form-group {
            margin-bottom: 20px;
        }

        .form-label {
            display: block;
            margin-bottom: 8px;
            font-weight: 600;
            color: var(--secondary);
        }

        .form-select {
            width: 100%;
            padding: 12px 15px;
            border: 2px solid #e0e0e0;
            border-radius: 8px;
            font-size: 16px;
            background: white;
            transition: all 0.3s;
        }

        .form-select:focus {
            outline: none;
            border-color: var(--primary);
            box-shadow: 0 0 0 3px rgba(52, 152, 219, 0.1);
        }

        .payment-methods {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(120px, 1fr));
            gap: 15px;
            margin-top: 10px;
        }

        .payment-method {
            border: 2px solid #e0e0e0;
            border-radius: 8px;
            padding: 15px;
            text-align: center;
            cursor: pointer;
            transition: all 0.3s;
        }

        .payment-method:hover {
            border-color: var(--primary);
        }

        .payment-method.selected {
            border-color: var(--primary);
            background: rgba(52, 152, 219, 0.05);
        }

        .payment-icon {
            font-size: 24px;
            margin-bottom: 8px;
            color: var(--primary);
        }

        .payment-name {
            font-size: 14px;
            font-weight: 600;
            color: var(--secondary);
        }

        .submit-btn {
            width: 100%;
            background: linear-gradient(135deg, var(--success), #27ae60);
            color: white;
            border: none;
            padding: 16px;
            font-size: 18px;
            font-weight: 600;
            border-radius: 10px;
            cursor: pointer;
            transition: all 0.3s;
            display: flex;
            align-items: center;
            justify-content: center;
            gap: 10px;
            margin-top: 20px;
        }

        .submit-btn:hover {
            transform: translateY(-2px);
            box-shadow: 0 8px 20px rgba(46, 204, 113, 0.4);
        }

        @media (max-width: 768px) {
            .bill-details {
                grid-template-columns: 1fr;
            }
        }
    </style>
</head>
<body>
<div class="container">
    <div class="header">
        <a href="courses.jsp" class="back-btn">
            <i class="fas fa-arrow-left"></i> Back to Courses
        </a>
        <h1><i class="fas fa-credit-card"></i> Payment Gateway</h1>
        <p>Complete your course enrollment</p>
    </div>

    <div class="content">
        <%
            Student s = (Student)session.getAttribute("student");
            if (s != null) {
                Coursesdao cdao = new Coursesdaoimp();
                int courseId = Integer.parseInt(request.getParameter("courseId"));
                Courses c = cdao.getCourse(courseId);
                long tranId = TransactionID.generateID();

                if (c != null) {
        %>

        <div class="transaction-info">
            <i class="fas fa-receipt"></i>
            <strong>Transaction ID: </strong>
            <span class="transaction-id"><%=tranId%></span>
        </div>

        <div class="bill-section">
            <h3 class="bill-title"><i class="fas fa-file-invoice"></i> Bill Details</h3>

            <div class="bill-details">
                <div>
                    <div class="bill-item">
                        <span class="bill-label">Student Name:</span>
                        <span class="bill-value"><%= s.getName() %></span>
                    </div>
                    <div class="bill-item">
                        <span class="bill-label">Email:</span>
                        <span class="bill-value"><%= s.getMail() %></span>
                    </div>
                    <div class="bill-item">
                        <span class="bill-label">Phone:</span>
                        <span class="bill-value"><%= s.getPhone() %></span>
                    </div>
                </div>
                <div>
                    <div class="bill-item">
                        <span class="bill-label">Course:</span>
                        <span class="bill-value"><%= c.getCourseName() %></span>
                    </div>
                    <div class="bill-item">
                        <span class="bill-label">Duration:</span>
                        <span class="bill-value"><%= c.getMonth() %> months</span>
                    </div>
                    <div class="bill-item">
                        <span class="bill-label">Course Fee:</span>
                        <span class="bill-value">₹<%= c.getfees() %></span>
                    </div>
                </div>
            </div>

            <div class="bill-item">
                <span class="bill-label">Total Amount:</span>
                <span class="bill-value">₹<%= c.getfees() %></span>
            </div>
        </div>

        <form class="payment-form" action="transaction" method="post">
            <input type="hidden" name="courseId" value="<%= c.getCourseId() %>">
            <input type="hidden" name="amount" value="<%= c.getfees() %>">
            <input type="hidden" name="tranId" value="<%= tranId %>">

            <h3 class="form-title"><i class="fas fa-wallet"></i> Select Payment Method</h3>

            <div class="payment-methods">
                <div class="payment-method" data-method="Credit Card">
                    <div class="payment-icon"><i class="fas fa-credit-card"></i></div>
                    <div class="payment-name">Credit Card</div>
                </div>
                <div class="payment-method" data-method="Debit Card">
                    <div class="payment-icon"><i class="fas fa-credit-card"></i></div>
                    <div class="payment-name">Debit Card</div>
                </div>
                <div class="payment-method" data-method="UPI">
                    <div class="payment-icon"><i class="fas fa-mobile-alt"></i></div>
                    <div class="payment-name">UPI</div>
                </div>
                <div class="payment-method" data-method="Cash">
                    <div class="payment-icon"><i class="fas fa-money-bill-wave"></i></div>
                    <div class="payment-name">Cash</div>
                </div>
            </div>

            <div class="form-group">
                <label class="form-label">Payment Mode:</label>
                <select class="form-select" name="method" required>
                    <option value="">Select Payment Method</option>
                    <option value="Credit Card">Credit Card</option>
                    <option value="Debit Card">Debit Card</option>
                    <option value="UPI">UPI Payment</option>
                    <option value="Cash">Cash</option>
                </select>
            </div>

            <button type="submit" class="submit-btn">
                <i class="fas fa-lock"></i> Pay ₹<%= c.getfees() %>
            </button>
        </form>

        <% } else { %>
        <div class="alert alert-error">
            <i class="fas fa-exclamation-circle"></i> Course not found!
        </div>
        <% } %>
        <% } else {
            request.setAttribute("error", "Session expired! Please login again.");
            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);
        } %>
    </div>
</div>

<script>
    document.querySelectorAll('.payment-method').forEach(method => {
        method.addEventListener('click', function() {
            document.querySelectorAll('.payment-method').forEach(m => {
                m.classList.remove('selected');
            });
            this.classList.add('selected');
            const methodValue = this.getAttribute('data-method');
            document.querySelector('select[name="method"]').value = methodValue;
        });
    });

    document.querySelector('form').addEventListener('submit', function(e) {
        const paymentMethod = document.querySelector('select[name="method"]').value;
        if (!paymentMethod) {
            e.preventDefault();
            alert('Please select a payment method');
        }
    });
</script>
</body>
</html>
