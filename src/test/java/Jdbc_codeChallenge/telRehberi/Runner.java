package Jdbc_codeChallenge.telRehberi;

import java.util.Scanner;

public class Runner {
    static Kayit kayitObj;
    static DataBase dataBaseMeth = new DataBase();
    static Scanner scan = new Scanner(System.in);
    static Scanner scanLN = new Scanner(System.in);

    public static void main(String[] args) {
        menu();
    }

    private static void menu() {
        System.out.println("-------------------------");
        System.out.println("1 - List Records\n2 - Delete Records\n3 - Update Recod\n4 - Add new Record\nX - Exit\n" +
                "-------------------------\nSeçiminiz : ");
        String secim = scan.next();
        switch (secim) {
            case "1":
                listRecords();
                break;
            case "2":
                deleteRecords();
                break;
            case "3":
                updateRecords();
                break;
            case "4":
                addNewRecords();
                break;
            case "X":
                System.out.println("Sistemden cikis yapilmistir...");
                break;

        }
    }

    private static void addNewRecords() {
        System.out.println("-----------------\nRecord Ekleme : ");
        kayitObj=new Kayit();
        System.out.println("Isim : ");
        String isim=scanLN.nextLine();
        System.out.println("Tel : ");
        String tel=scanLN.nextLine();
        kayitObj.setIsim(isim);
        kayitObj.setTel(tel);
        dataBaseMeth.addData(kayitObj);
        System.out.println("Kayit islemi basariyla gerceklesti");
        menu();
    }

    private static void updateRecords() {
        System.out.println("---------------\nUpdate edilecek kaydin ID'sini giriniz : ");
        kayitObj=new Kayit();
        int updateID=scan.nextInt();
        boolean flag=false;
        for (int i = 0; i < dataBaseMeth.listData().size(); i++) {
            if(updateID==dataBaseMeth.listData().get(i).getId()){
                System.out.printf("%5d%20s%15s%n", dataBaseMeth.listData().get(i).getId(), dataBaseMeth.listData().get(i).getIsim(), dataBaseMeth.listData().get(i).getTel());
                System.out.print("kayıt yenilemeyi onaylıyor musunuz (E:evet-H:Hayir) :");
                String onay=scan.next();
                if (onay.equalsIgnoreCase("E")){
                    System.out.println("Guncellemek istemedigin alana X gir");
                    System.out.println("Yeni Isim : ");
                    String yeniIsim=scanLN.nextLine();
                    System.out.println("Yeni tel : ");
                    String yeniTel=scanLN.nextLine();
                    if (yeniIsim.equalsIgnoreCase("x")){
                        yeniIsim=dataBaseMeth.listData().get(i).getIsim();
                        kayitObj.setIsim(yeniIsim);
                    }else {
                        kayitObj.setIsim(yeniIsim);
                    }

                    if (yeniTel.equalsIgnoreCase("x")){
                        yeniTel=dataBaseMeth.listData().get(i).getTel();
                        kayitObj.setTel(yeniTel);
                    }else {
                        kayitObj.setTel(yeniTel);
                    }
                    kayitObj.setId(updateID);
                    dataBaseMeth.updateData(kayitObj);
                    System.out.println("Update basariyla gerceklesti :)");
                    flag=true;
                    break;
                } else if (onay.equalsIgnoreCase("H")) {
                    System.out.println("Update islemi iptal edildi");
                    flag=true;
                    break;
                }else {
                    System.out.println("Hatali giris...");
                    i--;
                }
            }
        }
        if (!flag){
            System.out.println("Update ID bulunamadi");
        }
        menu();
    }

    private static void deleteRecords() {
        System.out.println("----------------\nSilinecek kaydin id'sini giriniz : ");
        int silinecekId=scan.nextInt();
        boolean flag=false;
        for (int i = 0; i < dataBaseMeth.listData().size(); i++) {
            if (silinecekId==dataBaseMeth.listData().get(i).getId()){
                System.out.printf("%5d%20s%15s%n", dataBaseMeth.listData().get(i).getId(), dataBaseMeth.listData().get(i).getIsim(), dataBaseMeth.listData().get(i).getTel());
                System.out.println("Kaydi silmeyi onayliyor musun? (E:evet-H:Hayir)");
                String onay=scan.next();
                if (onay.equalsIgnoreCase("E")){
                    dataBaseMeth.deleteData(silinecekId);
                    System.out.println("Silme islemi basariyla gerceklesti :)");
                    flag=true;
                    break;
                }else if (onay.equalsIgnoreCase("H")){
                    System.out.println("Silme islemi iptal eildi");
                    flag=true;
                    break;
                }else {
                    System.out.println("Hatali giris");
                    i--;
                }
            }
        }
        if (!flag){
            System.out.println("Silinecek ID bulumadi");
        }
        menu();
    }

    private static void listRecords() {
        System.out.printf("%4S%20S%17S\n", "id", "isim-soyisim", "telefon no");
        System.out.printf("%4S%20S%17S\n", "--", "------------", "------------");
        for (int i = 0; i < dataBaseMeth.listData().size(); i++) {
            System.out.printf("%4S%20S%17S\n",dataBaseMeth.listData().get(i).getId(),
                    dataBaseMeth.listData().get(i).getIsim(),
                    dataBaseMeth.listData().get(i).getTel());
        }
        if (dataBaseMeth.listData().isEmpty()){
            System.out.println("Listelenecek kayit bulunamadi");
        }
        menu();
    }
}
