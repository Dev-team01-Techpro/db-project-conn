import java.sql.*;

public class StudentRepository {

    private Connection conn;
    private Statement st;
    private PreparedStatement prst;

    private void setConnection() {
        try {
            this.conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/example", "techpront", "password");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void setStatement() {
        try {
            this.st = conn.createStatement();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void setPreparedStatement(String sql) {
        try {
            this.prst = conn.prepareStatement(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getStudents() {
        setConnection();
        setStatement();
        try {
            ResultSet rst = st.executeQuery("SELECT * FROM students");
            System.out.println("Ogrenci Listesi");
            while (rst.next()) {
                int studentId = rst.getInt("id");
                String studentName = rst.getString("name");
                System.out.println(studentId + " " + studentName);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                st.close();
                conn.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void addStudent(String name) {
        setConnection();
        String addQuery = "INSERT INTO students(name) VALUES(?)";
        setPreparedStatement(addQuery);
        try {
            prst.setString(1, name);
            prst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                prst.close();
                conn.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void deleteStudent(int StudentId) {
        setConnection();
        String deleteQuery = "DELETE FROM students WHERE id = ?";
        setPreparedStatement(deleteQuery);
        try {
            prst.setInt(1, StudentId);
            int result = prst.executeUpdate();
            if (result > 0) {
                System.out.println("Silme islemi basariyla tamamlandi");
            } else {
                System.out.println("Kayit bulunamadi");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                prst.close();
                conn.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void updateStudent(int studentId, String studentName) {
        setConnection();
        String deleteQuery = "UPDATE students SET name = ? WHERE id = ?";
        setPreparedStatement(deleteQuery);
        try {
            prst.setString(1, studentName);
            prst.setInt(2, studentId);
            int result = prst.executeUpdate();
            if (result > 0) {
                System.out.println("Guncelleme islemi basariyla tamamlandi");
            } else {
                System.out.println("Kayit bulunamadi");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                prst.close();
                conn.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }


}
