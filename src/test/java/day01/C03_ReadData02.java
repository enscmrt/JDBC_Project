package day01;

import java.sql.*;

public class C03_ReadData02 {

    public static void main(String[] args) throws SQLException {
        String url="jdbc:mysql://localhost:3306/javaCan";      // format kullanılan DBMS göre değişir, bu MySql için kullanılılan format
        String user="root";
        String password="123456";


        // 1- Create Connection
        Connection conn = DriverManager.getConnection(url,user,password);

        // 2- Create Statement/Query
        Statement st = conn.createStatement();

        //       TASKLER
        // Task01-> personel table'daki record'ları listeleyen code yazınız
        System.out.println("****** Task01 ******");
        String q1 = "SELECT * FROM personel;";
        ResultSet rs1 = st.executeQuery(q1);
        while (rs1.next()){
            System.out.printf("%-6d  %-10s %-10s %-10s %-10s %-6d\n",rs1.getInt(1),
                    rs1.getString(2),
                    rs1.getString(3),
                    rs1.getInt(4),
                    rs1.getInt(5),
                    rs1.getInt(6)
            );
        }
        // Task02-> personel table'daki maası 3000 den yuksek olan record'ları listeleyen code yazınız
        System.out.println("****** Task02 ******");

        String q2 = "select * from personel where maas > 3000";
        ResultSet rs2 = st.executeQuery(q2);
        while(rs2.next()){
            System.out.printf("%-6d  %-10s %-10s %-10s %-10s %-6d\n",rs2.getInt(1),
                    rs2.getString(2),
                    rs2.getString(3),
                    rs2.getInt(4),
                    rs2.getInt(5),
                    rs2.getInt(6)
            );
        }
        // Task03-> personel table'daki persone_id değeri 7521 olan record'ları listeleyen code yazınız

        // Task04-> personel table'daki bolumu_id si 10 ve  30  olan record'ları listeleyen code yazınız

        // Task05-> personel table'daki ismin 3. harfi h   olan record'ları listeleyen code  yazınız.
        System.out.println("****** Task05 ******");
        String q5 = "select * from personel where personel_isim like '__h%'";
        ResultSet rs5 = st.executeQuery(q5);
        while (rs5.next()){
            System.out.printf("%-6d  %-10s %-10s %-10s %-10s %-6d\n",rs5.getInt(1),
                    rs5.getString(2),
                    rs5.getString(3),
                    rs5.getInt(4),
                    rs5.getInt(5),
                    rs5.getInt(6)
            );
        }


        // 5- Close Connection
        st.close();
        conn.close();
    }
}
