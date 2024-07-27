package Jdbc_codeChallenge.telRehberi;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBase {//DB action alan JDBC meth class
    Connection connection = null;//sql query db'ye gondermek icin DB baglantisi ya[acak obj
    Statement statement = null;//sql query'lerinin result'ini return edecek obj
    PreparedStatement pStatement = null;//parametreli statement obj

    private void dBaseConnect() {//mySql connection
        String url = "jdbc:mysql://localhost:3306/";//mysql baglanti adresi
        String userName = "root";//mysql baglanti username
        String password = "123456";//mysql baglanti password
        try {
            connection = DriverManager.getConnection(url, userName, password);//mysql baglanacak obj parametreleri girilerek baglanti yapildi
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    private void useTelRehberi() {
        String sql_query = "use telefonrehberi";
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql_query);
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    private void dBClosed() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void createDBase() {//schema(DB) ve table olusturan meth
        dBaseConnect();
        try {
            String sql_query = "create database if not exists telefonrehberi";//mysql'deki db create icin sql query
            statement = connection.createStatement();//mysql baglantisindaki ilgili db icin statement atandi
            statement.executeUpdate(sql_query);//db create eden sql query calistirildi->simsek
            useTelRehberi();//mysql db'ye baglanan meth
            sql_query = "create table if not exists tel_nolar" +//ilgili db'ye table create edecek query
                    "(id int not null primary key auto_increment," +
                    "isim varchar(50)," +
                    "tel varchar(20));";
            statement.executeUpdate(sql_query);//db create eden sql query calistirildi->s
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            dBClosed();//db kapatan meth
        }
    }

    public List<Kayit> listData() {
        List<Kayit> liste = new ArrayList<>();//select'ten gelecek record'larin atanacagi bos liste
        createDBase();
        dBaseConnect();
        useTelRehberi();
        String sql_query = "select * from tel_nolar";
        try {
            ResultSet resultSet = statement.executeQuery(sql_query);
            while (resultSet.next()) {//iteratot loop ile datalar listelendi
                Kayit kayit = new Kayit();//kayit classindan obj create edildi
                kayit.setId(resultSet.getInt(1));//iterator ile db'den gelen result recordlar obj ile ilgili ins variableye atandi
                kayit.setIsim(resultSet.getString(2));//iterator ile db'den gelen result recordlar obj ile ilgili ins variableye atandi
                kayit.setTel(resultSet.getString(3));//iterator ile db'den gelen result recordlar obj ile ilgili ins variableye atandi
                liste.add(kayit);//iterator'dan gelen result recordlar obj liste eklendi
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            dBClosed();
        }
        return liste;
    }

    public void addData(Kayit kayit) {//table'a record insertion(giriş ekleme)
        createDBase();
        dBaseConnect();
        useTelRehberi();
        String sql_query="insert into tel_nolar(isim,tel) values(?,?);";//mysql'de ilgili table'a ve column'a record girişi yapıldı
        try {
            pStatement=connection.prepareStatement(sql_query);
            pStatement.setString(1,kayit.getIsim());//table'da isim column'a kayıt obj'den isim variable record olarak insert edildi
            pStatement.setString(2,kayit.getTel());//table'da isim column'a kayıt obj'den tel variable record olarak insert edildi
            pStatement.executeUpdate();//db'de ilgili table ve column'a record için query execute edıldı->şimşek
        }catch (SQLException e){
            System.out.println(e.toString());
        }finally {
            dBClosed();
        }
    }

    public void updateData(Kayit kayit){
        createDBase();
        dBaseConnect();
        useTelRehberi();
        String sql_query="update tel_nolar set isim= ?, tel= ? where id= ?;";//mysql'de ilgili table'a ve column'a record update icin sql query tanimlandi
        try {
            pStatement=connection.prepareStatement(sql_query);
            pStatement.setString(1, kayit.getIsim());//table'da isim column'a kayıt obj'den isim variable record olarak update edildi
            pStatement.setString(2, kayit.getTel());//table'da isim column'a kayıt obj'den tel variable record olarak update edildi
            pStatement.setInt(3, kayit.getId());//table'da isim column'a kayıt obj'den id variable record olarak update edildi
            pStatement.executeUpdate();//db'de ilgili table ve column'a update için query execute edıldı->şimşek
        }catch (SQLException e){
            System.out.println(e.toString());
        }finally {
            dBClosed();
        }
    }

    public void deleteData(int id){
        createDBase();
        dBaseConnect();
        useTelRehberi();
        String sql_query="delete from tel_nolar where id= ?;";//mysql'de ilgili table'a ve column'a record delete icin sql query tanimlandi
        try {
            pStatement= connection.prepareStatement(sql_query);
            pStatement.setInt(1,id);//table'a delete edilecek query için id set edildi
            pStatement.executeUpdate();//db'de ilgili table ve column'a silmek için query execute edıldı->şimşek
        }catch (SQLException e){
            System.out.println(e.toString());
        }finally {
            dBClosed();
        }
    }
}
