package ra.mode;

import java.util.Scanner;

public class Singer {
    public static Singer[] arraySinger = new Singer[100];
    public static int currentSingerIndex = -1;
    private int singerId;
    private String singerName;
    private int age;
    private String nationality;
    private boolean gender;
    private String genre;

    public Singer() {}

    public Singer(int singerId, String singerName, int age, String nationality, boolean gender, String genre) {

        this.singerId = singerId;
        this.singerName = singerName;
        this.age = age;
        this.nationality = nationality;
        this.gender = gender;
        this.genre = genre;
    }

    public int getSingerId() {
        return singerId;
    }

    public void setSingerId(int singerId) {
        this.singerId = singerId;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
    public void inputData(Scanner scanner,Singer[] arraySinger, int currentSingerIndex){
        this.singerId = autoInputSingerId(scanner, arraySinger, currentSingerIndex);
        System.out.println("Nhập tên ca sĩ");
        this.singerName = inputString(scanner, arraySinger, currentSingerIndex);
        this.age = inputAge(scanner, arraySinger, currentSingerIndex);
        System.out.println("Nhập quốc tịch");
        this.nationality = inputString(scanner, arraySinger, currentSingerIndex);
        this.gender = inputGender(scanner, arraySinger, currentSingerIndex);
        System.out.println("Nhập thể loại");
        this.genre = inputString(scanner, arraySinger, currentSingerIndex);
//    singerId – mã ca sĩ – int (Tự động tăng)
//▪ singerName – tên ca sĩ – String (Không được để trống)
//▪ age – ttuổi – int (Phải lớn hơn 0)
//▪ nationality – quốc tịch – String (không được để trống)
//▪ gender - giới tính - boolean
//▪ genre - thể loại - String (Không được để trống)
    }
    private boolean inputGender(Scanner scanner, Singer[] arraySinger, int currentSingerIndex) {
        System.out.println("Nhập giới tính ca sĩ (true: nam ca sĩ, false là nữ ca sĩ)");
        do {
            String gender = scanner.nextLine();
            if((gender.equals("true") || gender.equals("false"))) {
                return Boolean.parseBoolean(gender);
            } else
                System.out.println("Giới tính cần nhập true: nam ca sĩ, false là nữ ca sĩ");
        }while(true);
    }

    private int inputAge(Scanner scanner, Singer[] arraySinger, int currentSingerIndex) {
        System.out.println("Nhập tuổi ca sĩ");
        String age;
        do {
            age = scanner.nextLine();
            if(age.isEmpty()){
                System.out.println("Tuổi ca sĩ không được để trống, vui lòng nhập dữ liệu !");
            } else
                try{
                    int singerAge = Integer.parseInt(age);
                    if(singerAge <1) {
                        System.out.println("Tuổi phải là số nguyên dương ! Vui lòng nhập lại");
                    }
                    else {
                        return singerAge;
                    }
                }
                catch (Exception e){
                    System.err.println("Tuổi phải là số nguyên dương ! Vui lòng nhập lại");
                }
        }while(true);
    }

    private String inputString(Scanner scanner, Singer[] arraySinger, int currentSingerIndex) {
        String string ="";
       do {
           string = scanner.nextLine();
           if(string.isEmpty()){
               System.out.println("Không được để trống dữ liệu, vui lòng nhập dữ liệu !");
           } else
               return string;
       }while(true);
    }

    private int autoInputSingerId(Scanner scanner, Singer[] arraySinger, int currentSingerIndex) {
        if(currentSingerIndex == -1){
            return 1;
        }else{
            return arraySinger[currentSingerIndex].getSingerId() + 1;
        }
    }

    public void displayData(){
        System.out.println("--------------------------------------------------------------------------");
        System.out.printf("| %-5s | %-10s | %-5s | %-15s | %-10s | %-10s |\n",this.singerId,this.singerName,this.age,this.nationality,this.gender ? "Nam ca sĩ" : "Nữ ca sĩ",this.genre);
    }

    public void inputDataUpdate(Scanner scanner, Singer[] arraySinger, int currentSingerIndex) {
        System.out.println("Nhập tên ca sĩ");
        this.singerName = inputString(scanner, arraySinger, currentSingerIndex);
        this.age = inputAge(scanner, arraySinger, currentSingerIndex);
        System.out.println("Nhập quốc tịch");
        this.nationality = inputString(scanner, arraySinger, currentSingerIndex);
        this.gender = inputGender(scanner, arraySinger, currentSingerIndex);
        System.out.println("Nhập thể loại");
        this.genre = inputString(scanner, arraySinger, currentSingerIndex);
    }
}
