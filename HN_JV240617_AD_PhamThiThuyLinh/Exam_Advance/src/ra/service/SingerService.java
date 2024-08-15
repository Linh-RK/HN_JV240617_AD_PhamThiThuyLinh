package ra.service;

import ra.mode.Singer;
import ra.mode.Song;

import java.sql.SQLOutput;
import java.util.Scanner;

public class SingerService {
    public static void addNewSinger(Scanner scanner, Song[] arraySong, Singer[] arraySinger, int currentSongIndex, int currentSingerIndex) {
        String  replace = "ca sĩ";
        int number = inputNumberAdd(scanner, replace);
        for (int i = 1; i <= number; i++) {
            Singer singer = new Singer();
            singer.inputData(scanner, arraySinger, currentSingerIndex);
            arraySinger[currentSingerIndex + 1] = singer;
            currentSingerIndex ++;
            Singer.currentSingerIndex = currentSingerIndex;
        }
        Singer.arraySinger = arraySinger;
        showAllSinger(scanner,Song.arraySong, Singer.arraySinger, Song.currentSongIndex, Singer.currentSingerIndex);
    }

    public static int inputNumberAdd(Scanner scanner, String replace) {
        System.out.println("Nhập số "+ replace +" bạn muốn thêm: ");
        do {
            try{
                int number = Integer.parseInt(scanner.nextLine());
                if (number < 1) {
                    System.out.println("Số "+ replace +" cần thêm phải là số nguyên dương: ");
                } else
                    return number;
            }
            catch(Exception e){
                System.out.println("Số "+ replace + " cần thêm phải là số nguyên dương: ");
            }
        }while (true);
    }

    public static void showAllSinger(Scanner scanner, Song[] arraySong, Singer[] arraySinger, int currentSongIndex, int currentSingerIndex) {
        if (currentSingerIndex == -1) {
            System.out.println("Danh sách ca sĩ trống !");
            return;
        }else {
            System.out.println("Danh sách ca sĩ :");
            System.out.println("--------------------------------------------------------------------------");
            System.out.printf("| %-5s | %-10s | %-5s | %-15s | %-10s | %-10s |\n","ID","Name","Age","Nationality","Gender" ,"Genre");
            for (int i = 0; i <= Singer.currentSingerIndex; i++) {
//                Singer.arraySinger[i].displayData();
                if(Singer.arraySinger[i] != null)
                    Singer.arraySinger[i].displayData();
            }
            System.out.println("--------------------------------------------------------------------------");
        }
    }

    public static void updateSingerInfoById(Scanner scanner, Song[] arraySong, Singer[] arraySinger, int currentSongIndex, int currentSingerIndex) {
        System.out.println("Nhập ID ca sĩ cần update thông tin: ");
        int idUpdate = inputId(scanner);
        if (!isExistedSingerId(idUpdate, arraySinger, currentSingerIndex)) {
            System.out.println("ID ca sĩ không tồn tại !");
        } else {
            Singer singer = new Singer();
            singer.setSingerId(idUpdate);
            singer.inputDataUpdate(scanner, arraySinger, currentSingerIndex);
            for (int i = 0; i <= currentSingerIndex; i++) {
                if (arraySinger[i].getSingerId() == idUpdate) {
                    arraySinger[i]= singer;
                }
            }
            Singer.arraySinger = arraySinger;
            System.out.println("Thông tin ca sĩ đã được update");
            showAllSinger(scanner, Song.arraySong, Singer.arraySinger, Song.currentSongIndex, Singer.currentSingerIndex);
        }
    }

    private static boolean isExistedSingerId(int idUpdate, Singer[] arraySinger, int currentSingerIndex) {
        if(currentSingerIndex == -1)
            return false;
        else {
            for (int i = 0; i <= currentSingerIndex; i++) {
                if (arraySinger[i].getSingerId() == idUpdate) {
                    return true;
                }
            }
        }
        return false;
    }

    public static int inputId(Scanner scanner) {
        do {
            try{
                int idUpdate = Integer.parseInt(scanner.nextLine());
                if (idUpdate < 1) {
                    System.out.println("ID ca sĩ phải là số nguyên dương !");
                }else
                    return idUpdate;
            }
            catch (Exception e) {
                System.out.println("ID ca sĩ phải là số nguyên dương !");
            }
        }while (true);
    }

    public static void deleteSingerById(Scanner scanner, Song[] arraySong, Singer[] arraySinger, int currentSongIndex, int currentSingerIndex) {
        System.out.println("Nhập ID ca sĩ bạn muốn xoá: ");
        int idDelete = inputId(scanner);
        if (!isExistedSingerId(idDelete, Singer.arraySinger, Singer.currentSingerIndex)) {
            System.out.println("ID ca sĩ không tồn tại");
        } else {
            if(singerHasSong(idDelete,Song.arraySong, Singer.arraySinger, Song.currentSongIndex, Singer.currentSingerIndex)){
                System.out.println("Ca sĩ có bài hát trong danh sách nên không thể xoá");
            }
            else{
                for (int i = 0; i <= Singer.currentSingerIndex; i++) {
                    if(Singer.arraySinger[i].getSingerId() == idDelete) {
                        for (int j = i; j <= Singer.currentSingerIndex; j++) {
                            Singer.arraySinger[j]= Singer.arraySinger[j+1];
                        }
                        Singer.arraySinger = arraySinger;
                        Singer.currentSingerIndex --;
                        break;
                    }
                }
                System.out.println("Xoá thành công");
                showAllSinger(scanner, Song.arraySong, Singer.arraySinger, Song.currentSongIndex, Singer.currentSingerIndex);
            }

        }
    }

    private static boolean singerHasSong(int idDelete, Song[] arraySong, Singer[] arraySinger, int currentSongIndex, int currentSingerIndex) {
        if(currentSongIndex == -1)
            return false;
        else {
            for (int i = 0; i <= currentSongIndex; i++) {
                if(arraySong[i].getSinger().getSingerId() == idDelete) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void searchSinger(Scanner scanner, Song[] arraySong, Singer[] arraySinger, int currentSongIndex, int currentSingerIndex) {
        System.out.println("Nhập từ khoá tìm kiếm ca sĩ theo tên hoặc thể loại:");
        String searchSinger = scanner.nextLine();
        System.out.println("--------------------------------------------------------------------------");
        System.out.printf("| %-5s | %-10s | %-5s | %-15s | %-10s | %-10s |\n","ID","Name","Age","Nationality","Gender" ,"Genre");
        for (int i = 0; i <= Singer.currentSingerIndex; i++) {
            if (Singer.arraySinger[i].getSingerName().equalsIgnoreCase(searchSinger) || Singer.arraySinger[i].getGenre().equalsIgnoreCase(searchSinger)) {
                Singer.arraySinger[i].displayData();
            }
        }
        System.out.println("--------------------------------------------------------------------------");
    }
}
