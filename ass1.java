Code for CollegeManagementSystem.java  
package CollegeManagement; 
import java.sql.*; 
import java.util.Scanner; 
public class CollegeManagementSystem { 
public static void main(String[] args) { 
Connection con = null; 
Scanner sc = new Scanner(System.in); 
try { 
// Load MySQL JDBC Driver 
Class.forName("com.mysql.cj.jdbc.Driver"); 
// Establish Connection 
            con = 
DriverManager.getConnection("jdbc:mysql://localhost:
 3306/CollegeManagement", "root", "student"); 
 
            int chs; 
            do { 
                System.out.println("\nEnter Choice: "); 
                System.out.println("1. Add Student Record"); 
                System.out.println("2. Display Student 
Records"); 
                System.out.println("3. Update Student 
Record"); 
                System.out.println("4. Delete Student 
Record"); 
                System.out.print("Enter your choice: "); 
                int ch = sc.nextInt(); 
 
                switch (ch) { 
                    case 1: 
                        PreparedStatement ps = 
con.prepareStatement("INSERT INTO students 
(StudentID, StudentName, Department, Year) VALUES 
(?, ?, ?, ?)"); 
 
                        System.out.print("Enter Student ID: "); 
                        int studentID = sc.nextInt(); 
                        sc.nextLine(); // Consume newline 
 
                        System.out.print("Enter Student Name: "); 
                        String studentName = sc.nextLine(); 
 
                        System.out.print("Enter Department: "); 
                        String department = sc.nextLine(); 
 
                        System.out.print("Enter Year: "); 
                        int year = sc.nextInt(); 
 
                        ps.setInt(1, studentID); 
                        ps.setString(2, studentName); 
                        ps.setString(3, department); 
                        ps.setInt(4, year); 
 
                        int i = ps.executeUpdate(); 
                        System.out.println("✅ Student record 
added successfully!"); 
                        break; 
 
                    case 2: 
                        Statement st = con.createStatement(); 
                        ResultSet rs = st.executeQuery("SELECT * 
FROM students"); 
 
                        System.out.println("\nStudent 
ID\tStudent Name\tDepartment\tYear"); 
                        System.out.println("---------------------------------------------------------"); 
                        while (rs.next()) { 
                            System.out.println(rs.getInt(1) + "\t" + 
rs.getString(2) + "\t" + rs.getString(3) + "\t" + 
rs.getInt(4)); 
                        } 
                        break; 
 
                    case 3: 
                        PreparedStatement psUpdate = 
con.prepareStatement("UPDATE students SET 
StudentName=?, Department=?, Year=? WHERE 
StudentID=?"); 
 
                        System.out.print("Enter Student ID to 
update: "); 
                        int updateStudentID = sc.nextInt(); 
                        sc.nextLine(); // Consume newline 
 
                        System.out.print("Enter new Student 
Name: "); 
                        String newStudentName = sc.nextLine(); 
 
                        System.out.print("Enter new Department: 
"); 
                        String newDepartment = sc.nextLine(); 
 
                        System.out.print("Enter new Year: "); 
                        int newYear = sc.nextInt(); 
 
                        psUpdate.setString(1, newStudentName); 
                        psUpdate.setString(2, newDepartment); 
                        psUpdate.setInt(3, newYear); 
                        psUpdate.setInt(4, updateStudentID); 
 
                        int updatedRows = 
psUpdate.executeUpdate(); 
                        System.out.println(updatedRows > 0 ? 
"✅ Student record updated successfully!" : "⚠ No 
student found with that ID."); 
                        break; 
 
                    case 4: 
                        PreparedStatement psDelete = 
con.prepareStatement("DELETE FROM students 
WHERE StudentID = ?"); 
 
                        System.out.print("Enter Student ID to 
delete: "); 
                        int deleteStudentID = sc.nextInt(); 
 
                        psDelete.setInt(1, deleteStudentID); 
                        int deletedRows = 
psDelete.executeUpdate(); 
                        System.out.println(deletedRows > 0 ? 
"✅ Student record deleted successfully!" : "⚠ No 
student found with that ID."); 
                        break; 
 
                    default: 
                        System.out.println("❌ Invalid choice! 
Please enter a valid option."); 
                } 
 
                System.out.print("\nPress 0 to continue, any 
other key to exit: "); 
                chs = sc.nextInt(); 
            } while (chs == 0); 
 
        } catch (Exception e) { 
            System.out.println("❌ Error: " + 
e.getMessage()); 
            e.printStackTrace(); 
        } finally { 
            try { 
                if (con != null) con.close(); 
                if (sc != null) sc.close(); 
            } catch (Exception ex) { 
                ex.printStackTrace(); 
            } 
        } 
    } 
} 
  
Output:- 
