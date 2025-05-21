<!DOCTYPE hibernate-configuration PUBLIC
    "-//Hibernate/Hibernate Configuration DTD 3.0//EN"
    "http://www.hibernate.org/dtd/hibernate-configuration-3.0.dtd">

<hibernate-configuration>
    <session-factory>

        <!-- Database Connection -->
        <property name="hibernate.connection.driver_class">com.mysql.cj.jdbc.Driver</property>
        <property name="hibernate.connection.url">jdbc:mysql://localhost:3306/Courier_Hibernate</property>
        <property name="hibernate.connection.username">root</property>
        <property name="hibernate.connection.password">Swapnil@25</property>

        <!-- Hibernate Dialect -->
        <property name="hibernate.dialect">org.hibernate.dialect.MySQL8Dialect</property>

        <!-- Show SQL Queries in Console -->
        <property name="hibernate.show_sql">true</property>
        <property name="hibernate.format_sql">true</property>

        <!-- Auto Schema Management -->
        <property name="hibernate.hbm2ddl.auto">update</property>

        <!-- Entity Mappings -->
        <mapping class="com.Courier_Tracking.Entities.User"/>

    </session-factory>   </hibernate-configuration>

 7. Implementation
7.1 Hibernate Entities(Flight.java)
package com. Bus_Reservation_System;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "flights")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String flightNumber;
    private String source;
    private String destination;
    private String departureTime;
    private String arrivalTime;
    // Getters and Setters
}
7.2 Model Class (User.java)
package com.turf.model;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String userType;
    
    // Getters and Setters
}



7.3 DAO Class (FlightDAO.java)
package com.turf.dao;
import com.turf.config.HibernateUtil;
import com.turf.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class FlightDAO {
    public void addFlight(Flight flight) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.save(flight);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

7.4 Main JAVA file (App.java)
package com.Courier_Tracking.HibernateCurd;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com. Bus_Reservation_System  .Entities.User;


  Hello world!
 
public class App {
    public static void main(String[] args) {
        Flight flight = new Flight();
        flight.setFlightNumber("AI101");
        flight.setSource("Mumbai");
        flight.setDestination("Delhi");
        flight.setDepartureTime("10:00 AM");
        flight.setArrivalTime("12:00 PM");

        FlightDAO dao = new FlightDAO();
        dao.addFlight(flight);
    }
}

8. Deployment & Testing
8.1 Running the Application
Steps to Run a Java Program in Eclipse
1️ Open Eclipse & Your Project
Launch Eclipse IDE.
Open your Java project from the Package Explorer.
2️  Locate the Main Java File
In the src folder, find your main file (e.g., App.java).
