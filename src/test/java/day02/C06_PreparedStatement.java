package day02;

import java.sql.*;

public class C06_PreparedStatement {
    public static void main(String[] args) throws SQLException {

        String url = "jdbc:mysql://localhost:3306/javaCan";      // format kullanılan DBMS göre değişir, bu MySql için kullanılılan format
        String user = "root";
        String password = "123456";

        // 1- Create Connection
        Connection conn = DriverManager.getConnection(url, user, password);

        // 2- Create PreStatement/Query
        PreparedStatement pst = conn.prepareStatement("select * from calisanlar where isim like ? and sehir = ?");
        pst.setString(1,"__l%");
        pst.setString(2,"Hatay");

        ResultSet rs = pst.executeQuery();
        while (rs.next()){
            System.out.println(rs.getString(1) +"  "+
                    rs.getString(2) +"  "+
                    rs.getInt(3) +"  ");
        }


        pst.close();
        conn.close();

    }
}
