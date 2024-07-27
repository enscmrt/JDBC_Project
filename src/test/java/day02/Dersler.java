package day02;

public class Dersler {
    private int id;

    public Dersler() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDers_isim(String ders_isim) {
        this.ders_isim = ders_isim;
    }

    public void setEgitim_suresi(int egitim_suresi) {
        this.egitim_suresi = egitim_suresi;
    }

    public String getDers_isim() {
        return ders_isim;
    }

    public int getEgitim_suresi() {
        return egitim_suresi;
    }

    public Dersler(int id, String ders_isim, int egitim_suresi) {
        this.id = id;
        this.ders_isim = ders_isim;
        this.egitim_suresi = egitim_suresi;
    }

    private String ders_isim;
    private int egitim_suresi;
}
