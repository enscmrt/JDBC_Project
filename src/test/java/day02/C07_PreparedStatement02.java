package day02;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class C07_PreparedStatement02 {
    /*
   Cok miktarda kayit eklemek icin PreparedStatement metodu kullanilabilir.
   PreparedStatement hem hizli hem de daha guvenli (SQL injection saldirilari icin) bir yontemdir.
   ResultSet tum sonucları bellekte tutuyor.. banka hesapları vs hassas datalar için  ama guvenli degil..
   bu yuzden database in guvenligi acisindan PreparedStatement tercih edilir..
   Bunun icin; Data insert'e uygun bir POJO(Plain Old Java Object) class'i obj ile datalar db'ye insert edilir.

   PreparedStatement : Statement interface'ini extend eder.
   Statement ile olusturdugumuz sorgu icin Db'de bellekte sorgunun bir örnegi olusturulur.
   Sorgu her calistirildiginda yeni bir örnegi olusturulur.
   PreparedStatement : Birden fazla kez calistirilabilen parametrelendirilmis SQL sorgularini temsil eder.
   PreparedStatement ile sorgu olusturudumuzda, bu sorgunun örnegi DB'de bellekte tutulur,
   sorgu her çalıştırıldığında aynı önceki örneği kullanılır.
*/
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/javaCan";      // format kullanılan DBMS göre değişir, bu MySql için kullanılılan format
        String user = "root";
        String password = "123456";

        // 1- Create Connection
        Connection conn = DriverManager.getConnection(url, user, password);

        // 2- Create Statement
        Statement statement = conn.createStatement();  //query calistirmak icin

        // Task-> Dersler adında bir tablo  create edip (id int, ders_isim varchar(15), egitim_suresi int)
        // tabloya 6 ders girişi yapın
        //statement.execute("create table dersler (id int, ders_isim varchar(15), egitim_suresi int)");
        statement.execute("create table dersler (id int, ders_isim varchar(15), egitim_suresi int)");
        // Ders class'dan obj depolayan bos list tanımlandı.
        List<Dersler> derslerList = new ArrayList<>();
        derslerList.add(new Dersler(101,"Java",12));
        derslerList.add(new Dersler(102,"JavaScript",12));
        derslerList.add(new Dersler(103,"Html",11));
        derslerList.add(new Dersler(104,"Selenium",16));
        derslerList.add(new Dersler(105,"Jenkins",7));
        derslerList.add(new Dersler(106,"Sql",10));

        System.out.println("derslerList = " + derslerList);
        System.out.println("derslerList.get(1).getDers_isim() = " + derslerList.get(1).getDers_isim());

        PreparedStatement table =conn.prepareStatement("insert into dersler values (?,?,?)");

        for (Dersler ders:derslerList){
            table.setInt(1,ders.getId());
            table.setString(2,ders.getDers_isim());
            table.setInt(3,ders.getEgitim_suresi());
            table.addBatch();
        }
        table.executeBatch();


        //table statement'e girilen obj value'ları mavi zemin(query group) yapıldı

        //table statement'e girilen obj value'ları mavi zemin(query group) şimşeklendi(group execute)

        conn.close();
        System.out.println("agam bu output okuduysan code cincix  :)  DEWAMKEEE....");
    }
}
