
import java.sql.*;

public class JdbcDemo {

    public static void main(String[] args) throws Exception {

        // 1. MySQL Driver load
        Class.forName("com.mysql.cj.jdbc.Driver");

        // 2. MySQL se connection
        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/college",
            "root",
                System.getenv("DB_PASSWORD")
        );

        System.out.println("Database Connected!");

        // 3. Insert query
        PreparedStatement ps = con.prepareStatement(
                "INSERT INTO student VALUES (?, ?, ?)"
        );

        ps.setInt(1, 101);
        ps.setString(2, "Abha");
        ps.setString(3, "IT");

        ps.executeUpdate();

        System.out.println("Record Inserted!");

        // 4. Data retrieve
        Statement st = con.createStatement();

        ResultSet rs = st.executeQuery(
                "SELECT * FROM student"
        );

        while (rs.next()) {
            System.out.println(
                    rs.getInt("id") + " " +
                    rs.getString("name") + " " +
                    rs.getString("branch")
            );
        }

        // 5. Connection close
        con.close();
    }
}