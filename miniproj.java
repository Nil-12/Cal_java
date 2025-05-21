CREATE DATABASE college;

USE college;

CREATE TABLE students (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    email VARCHAR(100),
    course VARCHAR(100)
);

CREATE TABLE courses (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100)
);

CREATE DATABASE college;

USE college;

CREATE TABLE students (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    email VARCHAR(100),
    course VARCHAR(100)
);

CREATE TABLE courses (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100)
);


package com.college;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/college", "root", "yourpassword");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}


package com.college;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.*;
import javax.servlet.http.*;

public class AddStudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String course = req.getParameter("course");

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO students(name, email, course) VALUES (?, ?, ?)");
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, course);
            ps.executeUpdate();

            res.sendRedirect("viewStudents.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


<form action="AddStudentServlet" method="post">
    Name: <input type="text" name="name" required><br>
    Email: <input type="email" name="email" required><br>
    Course: <input type="text" name="course" required><br>
    <input type="submit" value="Add Student">
</form>


  <%@ page import="java.sql.*" %>
<%@ page import="com.college.DBConnection" %>

<table border="1">
    <tr>
        <th>ID</th><th>Name</th><th>Email</th><th>Course</th>
    </tr>
<%
    Connection con = DBConnection.getConnection();
    Statement stmt = con.createStatement();
    ResultSet rs = stmt.executeQuery("SELECT * FROM students");
    while (rs.next()) {
%>
    <tr>
        <td><%=rs.getInt("id")%></td>
        <td><%=rs.getString("name")%></td>
        <td><%=rs.getString("email")%></td
