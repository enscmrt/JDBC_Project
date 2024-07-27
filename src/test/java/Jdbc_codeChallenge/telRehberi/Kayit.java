package Jdbc_codeChallenge.telRehberi;

public class Kayit {
    private int id;//db icin table'a ilgili column object olusturuldu
    private String isim;//db icin table'a ilgili column object olusturuldu
    private String tel;//db icin table'a ilgili column object olusturuldu

    public Kayit(int id, String isim, String tel) {
        this.id = id;
        this.isim = isim;
        this.tel = tel;
    }

    public Kayit() {
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return  "id=" + id +
                ", isim='" + isim + '\'' +
                ", tel='" + tel + '\'';
    }
}
