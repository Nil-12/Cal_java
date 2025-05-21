Inside the util/ package, create a class Customer_Regeistration_Connection.java.

package net.airline.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import net.airline.model.Customer;

public class Customer_Registration_DAO {

    public int registerCustomer(Customer customer) throws ClassNotFoundException {
        String INSERT_SQL = "INSERT INTO customer (user_id, name, email, password, phone) VALUES (?, ?, ?, ?, ?);";
        int result = 0;

        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/airline", "root", "your_password");
             PreparedStatement stmt = conn.prepareStatement(INSERT_SQL)) {

            stmt.setInt(1, customer.getUser_Id());
            stmt.setString(2, customer.getName());
            stmt.setString(3, customer.getEmail());
            stmt.setString(4, customer.getPassword());
            stmt.setLong(5, customer.getPhone());

            result = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
Step 6: Create the Model Classes
Inside model/, create Customer.java.

package net.airline.model;

public class Customer {
    private int user_Id;
    private String name;
    private String email;
    private String password;
    private long phone;

    // Getters and Setters
    public int getUser_Id() { return user_Id; }
    public void setUser_Id(int user_Id) { this.user_Id = user_Id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public long getPhone() { return phone; }
    public void setPhone(long phone) { this.phone = phone; }
}

Step 7: Create the Servlets (Controllers)
Inside controller/, create Customer_Registration_Servlet.java in controller/

package net.airline.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

import net.airline.model.Customer;
import net.airline.dao.Customer_Registration_DAO;

@WebServlet("/register")
public class Customer_Registration_Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("user_id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        long phone = Long.parseLong(request.getParameter("phone"));

        Customer customer = new Customer();
        customer.setUser_Id(id);
        customer.setName(name);
        customer.setEmail(email);
        customer.setPassword(password);
        customer.setPhone(phone);

        Customer_Registration_DAO dao = new Customer_Registration_DAO();
        try {
            int result = dao.registerCustomer(customer);
            if (result > 0) {
                request.setAttribute("message", "Customer registered successfully!");
            } else {
                request.setAttribute("message", "Registration failed.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "Error: " + e.getMessage());
        }

        request.getRequestDispatcher("Customer_Registration.jsp").forward(request, response);
    }
}


Step 8: Create the JSP Views
 Customer_Registration.jsp


<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Airline Customer Registration</title>
</head>
<body>
    <h2>Customer Registration Form</h2>
    <form action="register" method="post">
        <label>User ID:</label><input type="number" name="user_id" required><br><br>
        <label>Name:</label><input type="text" name="name" required><br><br>
        <label>Email:</label><input type="email" name="email" required><br><br>
        <label>Password:</label><input type="password" name="password" required><br><br>
        <label>Phone:</label><input type="number" name="phone" required><br><br>
        <button type="submit">Register</button>
    </form>

    <p style="color: green;"><%= request.getAttribute("message") != null ? request.getAttribute("message") : "" %></p>
</body>
</html>
   


Step 11: Run the Project

Right-click the project → Run As → Run on Server (Eclipse) or Deploy to Tomcat (IntelliJ).
Open http://localhost:8080/AirlineManagementSystem/Customer_Registration.jspEnter a tracking number and check the s
