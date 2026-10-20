import java.sql.*;

public class JdbcDemo {
    public static void main(String args[]) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = System.getenv("DB_URL");
String username = System.getenv("DB_USERNAME");
String password = System.getenv("DB_PASSWORD");

Connection con = DriverManager.getConnection(url, username, password);        System.out.println("Connection Successful");
        PreparedStatement ps = con.prepareStatement(
                "insert into student values(?,?,?)");
        ps.setInt(1, 104);
        ps.setString(2, "Rahul");
        ps.setString(3, "CSE");
        ps.executeUpdate();
        System.out.println("Record Inserted");
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(
                "select * from student");
        while (rs.next()) {
            System.out.println(
                    rs.getInt(1) + " " +
                            rs.getString(2) + " " +
                            rs.getString(3));
        }
        con.close();
    }
}
