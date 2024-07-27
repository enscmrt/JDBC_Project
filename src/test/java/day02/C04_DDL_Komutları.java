package day02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class C04_DDL_Komutları {
    /*
A) CREATE TABLE, DROP TABLE, ALTER TABLE gibi DDL ifadeleri için sonuç kümesi (ResultSet) döndürmeyen
   yöntemler kullanılmalıdır. JDBC bu amaçla 2 alternatif sunar:
    1) execute() yöntemi
    2) executeUpdate() yöntemi.

B)  - execute() yöntemi herhangi bir SQL ifadesi ile kullanılabilen genel bir komuttur.
    - execute() yöntemi bir Boolean değer döndürür.
    - DDL ve DML işlemleri için false dönerken, "select" gibi  (ResultSet üreten) işlemler için true döner.
    - Kullanılacak SQL ifadesinin türü belli olmadığında özellikle tercih edilir.

C) - executeUpdate() yöntemi INSERT, UPDATE gibi DML işlemleri için yaygın olarak kullanılır.
   - İşlemden etkilenen satır sayısını döndürür.
   - INSERT, UPDATE, DELETE gibi komutlar kullanıldığında:
           executeUpdate() yöntemi, veritabanında yapılan değişikliklerden etkilenen satırların sayısını döndürür.
           Bu sayı, başarılı bir şekilde eklenen, güncellenen veya silinen satırların sayısını temsil eder.
   - Ayrıca DDL işlemleri için de kullanılabilir ve bu durumda 0 döner.
           CREATE TABLE, DROP TABLE, ALTER TABLE gibi komutlar kullanıldığında
           veritabanı yapısını değiştirir ve genellikle satır sayısını etkilemez

 execute() herhangi bir SQL komutu ile kullanılabilir .......  DDL ---> False    DML----> True
 executeUpdate()  DDL ----> 0           DML -----> İşlemden etkilenen satır sayısını döndürür
*/
    public static void main(String[] args) throws SQLException {
        // 1- Create Connection
        // 2- Create Statement/Query
        // 3- Execute Statement/Query
        // 4- Close Connection
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaCan", "root", "123456"); //database baglantisi icin
        Statement st = connection.createStatement();//sql sorgu(query) calistirmak (execute etmek) icin
        System.out.println("   ***   task01   ***   ");
        // Task01-> markalar adında bir tablo oluşturunuz. marka_id int, marka_isim VARCHAR(15), calisan_sayisi int
        //1.yontem : execute () methodu ile
        boolean isCreated=st.execute("Create table markalar (marka_id int, marka_isim VARCHAR(15), calisan_sayisi int)");
        System.out.println("Tablo oluştu-->"+isCreated);
        //2.yontem : executeUpdate() methodu ile
       //int etkilenenSatirSayisi= st.executeUpdate("Create table markalar1 (marka_id int, marka_isim VARCHAR(15), calisan_sayisi int)");
        //System.out.println("Yeni tablo oluştu etkilenen satır sayısı"+etkilenenSatirSayisi);

        // Task03-> markalar tablosuna yeni bir sutun {sube_sayisi int} ekleyiniz.
        System.out.println("   ***   task03   ***   ");
        System.out.println("yeni sütun eklendi"+st.execute("alter table markalar add column sube_sayisi int"));
        // Task04-> markalar tablosuna yeni bir sutun {sube_sayisi2 int} ekleyiniz, ancak bu sutunun yeri marka_id den sonra olsun
        System.out.println("   ***   task04   ***   ");
        System.out.println("marka_id sonrasına yeni sutun eklendi -->" + st.execute("alter table markalar add column sube_sayisi2 int after marka_id  "));
        System.out.println("   ***   task5   ***   ");
        // Task05-> markalar tablosunun adini  brands olarak degistiriniz
        //  System.out.println("markalar tablosunun adini  brands olarak degistirildi -> "+statement.execute("alter table markalar rename to brands"));
        System.out.println("   ***   task06   ***   ");
        // Task06-> markalar tablosunda marka_isim sutununu isim olarak degistiriniz
        // System.out.println(" marka_isim sutununu isim olarak degistirildi -> "+statement.execute("alter table brands  rename  column marka_isim to isim"));

        System.out.println("   ***   task07   ***   ");
        // Task07-> markalar tablosunda marka_isim sutununun data type ini char(20) olarak degistiriniz
        //System.out.println("isim sutununun data type ini char(20) olarak degistirildi ->" + statement.execute("alter table brands modify isim char(20)"));
        // Task02->  markalar tablosunu siliniz
//        System.out.println("   ***   task02   ***   ");
//        boolean q2= st.execute("drop table markalar");
//        System.out.println("Oluşturduuğum tablo silindi"+q2);
        st.close();
        connection.close();
    }
}
