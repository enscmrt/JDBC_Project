package day01;

import java.sql.*;

public class C01_Connection {
    public static void main(String[] args) throws SQLException {
        Connection conn=null;
        String url = "jdbc:mysql://localhost:3306/javaCan"; //Syntax kullanılan DBMS e göre değişir
        String user = "root";
        String password = "123456";
        // 1- Create Connection
        try {
            conn = DriverManager.getConnection(url,user,password);
            System.out.println("Bağlantı kuruldu...:)");
        } catch (SQLException e) {
            System.out.println("Bağlantı kurulamadı...:(");
        }
        // 2- Create Statement/Query
        Statement st = conn.createStatement();

        // 3- Execute Statement/Query
        String query ="select * from personel";

        // 4- Store results in ResultSet
        ResultSet rs = st.executeQuery(query);

        // 5- Close Connection
        try {
            conn.close();
            System.out.println("Bağlantı sonlandırıldı..");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
