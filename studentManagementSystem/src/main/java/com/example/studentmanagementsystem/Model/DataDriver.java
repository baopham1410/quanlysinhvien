package com.example.studentmanagementsystem.Model;

import java.sql.*;

public class DataDriver {
    Statement stmt;
    Connection con;

    public DataDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try {
                this.con = DriverManager.getConnection("jdbc"
                        + ":mysql://localhost:3306/systemstudent?useUnicode=yes&characterEncoding=UTF-8", "root", "");
                this.stmt = con.createStatement();
                System.out.println("ok");
                //stmt.executeUpdate("insert into student values(\"183\", \"Nguyet\", \"20\", \"18T3\")");
                //stmt.executeUpdatoe ("insert into lop values(\"183\", \"18T3\")");
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public ResultSet getClientData() {
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = this.con.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM admin ");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet connectDb() {
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = this.con.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM student");
            System.out.println("được");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet connectD() {
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = this.con.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM course");
            System.out.println("được");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }


    public void insertCourse(String course, String degree, String desciption) {
        String insertQuery = "INSERT INTO course (course , degree , description) VALUES (?, ?, ?);";
        try {
            PreparedStatement pstm = con.prepareStatement(insertQuery);
            pstm.setString(1, course);
            pstm.setString(2, degree);
            pstm.setString(3, desciption);


            int rowsAffected = pstm.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Transaction inserted successfully.");
            } else {
                System.out.println("Failed to insert transaction.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error inserting transaction.");
        }
    }

    public void updateCourse(String course, String degree, String description, String originalCourse) {
        String updateQuery = "UPDATE course SET course = ?, degree = ?, description = ? WHERE course = ?";
        try {
            PreparedStatement pstm = con.prepareStatement(updateQuery);
            pstm.setString(1, course);
            pstm.setString(2, degree);
            pstm.setString(3, description);
            pstm.setString(4, originalCourse);  // Thêm tham số cho điều kiện WHERE

            int rowsAffected = pstm.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Course updated successfully.");
            } else {
                System.out.println("Failed to update course.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error updating course.");
        }
    }

    public void deleteCourse(String course) {
        String deleteQuery = "DELETE FROM course WHERE course = ?";

        try {
            PreparedStatement pstm = con.prepareStatement(deleteQuery);
            pstm.setString(1, course);

            int rowsAffected = pstm.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Course deleted successfully.");
            } else {
                System.out.println("Failed to delete course.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error deleting course.");
        }
    }

    public ResultSet connectD3() {
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = this.con.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM course");
            System.out.println("được");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public void insertData(int studentNum, String year, String course, String firstName, String lastName, String gender, String birth, String status, String image) {
        String insertData = "INSERT INTO student (studentNum,year,course,firstName,lastName,gender,birth, status,image) VALUES (?, ?, ?,?,?,?,?,?,?);";
        try {
            PreparedStatement pstm = con.prepareStatement(insertData);
            pstm.setInt(1, studentNum);
            pstm.setString(2, year);
            pstm.setString(3, course);
            pstm.setString(4, firstName);
            pstm.setString(5, lastName);
            pstm.setString(6, gender);
            pstm.setString(7, birth);
            pstm.setString(8, status);
            pstm.setString(9, image);


            int rowsAffected = pstm.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Transaction inserted successfully.");
            } else {
                System.out.println("Failed to insert transaction.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error inserting transaction.");
        }
    }

    public void updateDataStudentNum(int studentNum, int oldNum) {
        String updateData = "UPDATE student SET studentNum = ? WHERE  studentNum= ?";
        try {

            PreparedStatement pstm = con.prepareStatement(updateData);
            pstm.setInt(1, studentNum);
            pstm.setInt(2, oldNum);

            int rowsAffected = pstm.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Student updated successfully.");
            } else {
                System.out.println("Failed to update student.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error updating course.");
        }
    }
    public void updateDataYear(String year, int oldNum) {
        String updateData = "UPDATE student SET year = ? WHERE  studentNum= ?";
        try {
            PreparedStatement pstm = con.prepareStatement(updateData);
            pstm.setString(1, year);
            pstm.setInt(2, oldNum);

            int rowsAffected = pstm.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Student updated successfully.");
            } else {
                System.out.println("Failed to update student.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error updating course.");
        }
    }

    public void updateDataFname(String fname, int oldNum) {
        String updateData = "UPDATE student SET firstName = ? WHERE  studentNum= ?";
        try {
            PreparedStatement pstm = con.prepareStatement(updateData);
            pstm.setString(1, fname);
            pstm.setInt(2, oldNum);

            int rowsAffected = pstm.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Student updated successfully.");
            } else {
                System.out.println("Failed to update student.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error updating course.");
        }
    }
    public void updateDatalastName(String lastName, int oldNum) {
        String updateData = "UPDATE student SET lastName = ? WHERE  studentNum= ?";
        try {
            PreparedStatement pstm = con.prepareStatement(updateData);
            pstm.setString(1, lastName);
            pstm.setInt(2, oldNum);

            int rowsAffected = pstm.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Student updated successfully.");
            } else {
                System.out.println("Failed to update student.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error updating course.");
        }
    }
    public void updateDatagender(String gender, int oldNum) {
        String updateData = "UPDATE student SET gender = ? WHERE  studentNum= ?";
        try {
            PreparedStatement pstm = con.prepareStatement(updateData);
            pstm.setString(1, gender);
            pstm.setInt(2, oldNum);

            int rowsAffected = pstm.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Student updated successfully.");
            } else {
                System.out.println("Failed to update student.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error updating course.");
        }
    }
    public void updateDatabirth(String birth, int oldNum) {
        String updateData = "UPDATE student SET birth = ? WHERE  studentNum= ?";
        try {
            PreparedStatement pstm = con.prepareStatement(updateData);
            pstm.setString(1, birth);
            pstm.setInt(2, oldNum);

            int rowsAffected = pstm.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Student updated successfully.");
            } else {
                System.out.println("Failed to update student.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error updating course.");
        }
    }
    public void updateDatastatus(String status, int oldNum) {
        String updateData = "UPDATE student SET status = ? WHERE  studentNum= ?";
        try {
            PreparedStatement pstm = con.prepareStatement(updateData);
            pstm.setString(1, status);
            pstm.setInt(2, oldNum);

            int rowsAffected = pstm.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Student updated successfully.");
            } else {
                System.out.println("Failed to update student.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error updating course.");
        }
    }
    public void deleteData(int student) {
        String deleteData = "DELETE FROM student WHERE studentNum = ?";

        try {
            PreparedStatement pstm = con.prepareStatement(deleteData);
            pstm.setInt(1, student);

            int rowsAffected = pstm.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Course deleted successfully.");
            } else {
                System.out.println("Failed to delete course.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error deleting course.");
        }
    }
    public void deleteDataGrade(int studentNum) {
        String deleteData = "DELETE FROM student_grade WHERE studentNum = ?";

        try {
            PreparedStatement pstm = con.prepareStatement(deleteData);
            pstm.setInt(1, studentNum);

            int rowsAffected = pstm.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Course deleted successfully.");
            } else {
                System.out.println("Failed to delete course.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error deleting course.");
        }
    }
    public void insertStudentGrade(int studentNum, String year, String course, Double first_sem, Double last_sem,Double final1) {
        String insertStudentGrade = "INSERT INTO student_grade (studentNum,year,course,first_sem,second_sem,final) VALUES (?, ?, ?,?,?,?);";
        try {
            PreparedStatement pstm = con.prepareStatement(insertStudentGrade);
            pstm.setInt(1, studentNum);
            pstm.setString(2, year);
            pstm.setString(3, course);
            pstm.setDouble(4, first_sem);
            pstm.setDouble(5, last_sem);
            pstm.setDouble(6, final1);



            int rowsAffected = pstm.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Transaction inserted successfully.");
            } else {
                System.out.println("Failed to insert transaction.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error inserting transaction.");
        }
    }

    public ResultSet connectDataGrade() {
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = this.con.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM student_grade");
            System.out.println("được");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public void updateDataGrade(int studentNum, Double first_sem, Double last_sem,Double final1) {
        String updateData = "UPDATE student_grade SET first_sem=?,second_sem=?,final=? WHERE  studentNum= ?";
        try {
            PreparedStatement pstm = con.prepareStatement(updateData);
            pstm.setDouble(1, first_sem);
            pstm.setDouble(2, last_sem);
            pstm.setDouble(3, final1);
            pstm.setInt(4, studentNum);


            int rowsAffected = pstm.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Student updated successfully.");
            } else {
                System.out.println("Failed to update student.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error updating course.");
        }
    }

    public ResultSet connectD2() {
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = this.con.createStatement();
            resultSet = statement.executeQuery("SELECT  COUNT(id) FROM student");
            System.out.println("được");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet connectD4() {
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = this.con.createStatement();
            resultSet = statement.executeQuery("SELECT  COUNT(id) FROM student WHERE gender = 'Female'");
            System.out.println("được");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet connectD5() {
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = this.con.createStatement();
            resultSet = statement.executeQuery("SELECT  COUNT(id) FROM student WHERE gender = 'Male'");
            System.out.println("được");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
//    public ResultSet connectD6() {
//        Statement statement;
//        ResultSet resultSet = null;
//        try {
//            statement = this.con.createStatement();
//            resultSet = statement.executeQuery("SELECT date,  COUNT(id) FROM student WHERE  status ='Enrolled'GROUP BY date ORDER BY TIMESTAMP(date)ASC LIMIT 5");
//            System.out.println("được");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return resultSet;
//    }
//    public ResultSet connectD7() {
//        Statement statement;
//        ResultSet resultSet = null;
//        try {
//            statement = this.con.createStatement();
//            resultSet = statement.executeQuery("SELECT date,  COUNT(id) FROM student WHERE  status ='Enrolled'and gender ='Female'GROUP BY date ORDER BY TIMESTAMP(date)ASC LIMIT 5");
//            System.out.println("được");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return resultSet;
//    }
//    public ResultSet connectD8() {
//        Statement statement;
//        ResultSet resultSet = null;
//        try {
//            statement = this.con.createStatement();
//            resultSet = statement.executeQuery("SELECT date,  COUNT(id) FROM student WHERE  status ='Enrolled'and gender ='Male'GROUP BY date ORDER BY TIMESTAMP(date)ASC LIMIT 5");
//            System.out.println("được");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return resultSet;
//    }
}




