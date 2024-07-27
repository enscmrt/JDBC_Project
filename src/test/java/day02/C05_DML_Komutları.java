package day02;

import java.sql.*;

public class C05_DML_Komutları {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaCAN", "root", "123456"); //database baglantisi icin
        Statement st = connection.createStatement();//sql sorgu(query) calistirmak (execute etmek) icin
        //st.execute("Create table calisanlar (isim varchar(20), sehir VARCHAR(15), maas int) ");

        System.out.println("   ***   task01   ***   ");
        // Task01-> Calisanlar tablosuna yeni bir kayit (('Yasin Bey' ,'Antalya', 33000)  ekleyelip kaydi teyit icin sorgulayınız.
        //st.executeUpdate("insert into calisanlar values ('Yasin Bey' ,'Antalya', 33000)");




        // Task02-> Calisanlar tablosuna birden fazla yeni kayıt ekleyelim.
        System.out.println("   ***   task02   ***   ");

        // 1.YONTEM
        // Ayri ayri sorgular ile veritabanina tekrar tekrar ulasmak islemlerin yavas yapilmasina yol acar.
        // 10000 tane veri kaydi yapildigi dusunuldugunde  bu kotu bir yaklasimdir.
        String [] insertQueries = {
                "insert into calisanlar values('Gulhan Bey' ,'Hatay', 41000)",
                "insert into calisanlar values('Gulay Hanım' ,'Rize', 51000)",
                "insert into calisanlar values('Gulsun Hanım' ,'Manisa', 21000)",
                "insert into calisanlar values('Gul Hanım' ,'Burasa', 43000)"};
        int recordSayisi =0;
        for (String query:insertQueries){
            recordSayisi += st.executeUpdate(query);
        }
        System.out.println("recordSayisi = " + recordSayisi);

        // 2.YONTEM --> addBatch ve excuteBatch() metotlari ile
        // addBatch() -> metodu ile SQL ifadeleri gruplandirilabilir ve exucuteBatch()  metodu ile veritabanina bir kere gonderilebilir.
        // ***!!!!**** excuteBatch() metodu bir int [] dizi dondurur ve  Bu dizi her bir ifade sonucunda etkilenen satir sayisini return eder.
        String [] insertQueries1 = {
                "insert into calisanlar values('Ahmet Bey' ,'Adana', 41000)",
                "insert into calisanlar values('Gulnur Hanım' ,'Bolu', 51000)",
                "insert into calisanlar values('Gulsum Hanım' ,'Tokat', 21000)",
                "insert into calisanlar values('Gulizar Hanım' ,'Bursa', 43000)"
        };
        for (String query : insertQueries1){
            st.addBatch(query);//insertQuery1  array deki her bir eleman(sql query) batch'e(workbanch'deki seçili->query mavi zemin) eklendi
        }
        int [] satir = st.executeBatch();
        System.out.println(satir[2]);
        System.out.println(satir.length);

        // table son hali intelij de gorelim...
        ResultSet rs = st.executeQuery("select * from calisanlar");
        while (rs.next()) {
            System.out.println(rs.getString(1)+" "+
                    rs.getString(2)+" "+
                    rs.getInt(3));
        };

        connection.close();
        st.close();
    }
}
