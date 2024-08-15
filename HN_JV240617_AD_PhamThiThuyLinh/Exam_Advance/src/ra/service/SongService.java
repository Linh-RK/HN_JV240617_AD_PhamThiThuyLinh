package ra.service;

import ra.mode.Singer;
import ra.mode.Song;

import java.util.Scanner;

import static ra.service.SingerService.*;

public class SongService {
    public static void addNewSong(Scanner scanner, Song[] arraySong, Singer[] arraySinger, int currentSongIndex, int currentSingerIndex) {
        String  replace = "bài hát";
        int number = inputNumberAdd(scanner, replace);
        for (int i = 1; i <= number; i++) {
            Song song = new Song();
            song.inputData( scanner, Song.arraySong, Song.currentSongIndex, Singer.arraySinger,Singer.currentSingerIndex);
            currentSongIndex ++;
            Song.arraySong[currentSongIndex] = song;
            Song.currentSongIndex = currentSongIndex;
        }
        showAllSong(scanner, Song.arraySong, Singer.arraySinger, Song.currentSongIndex, Singer.currentSingerIndex);
    }

    public static void showAllSong(Scanner scanner, Song[] arraySong, Singer[] arraySinger, int currentSongIndex, int currentSingerIndex) {
        if (Song.currentSongIndex == -1) {
            System.out.println("Danh sách bài hát trống !");
        }else {
            System.out.println("Danh sách bài hát :");
            System.out.println("----------------------------------------------------------------------------------------------");
            System.out.printf("| %-5s | %-10s | %-15s | %-10s | %-10s | %-12s | %-10s |\n","ID","Name","Descriptions","Singer","Writer","Created Date","Status");
            for (int i = 0; i <= Song.currentSongIndex; i++) {
                Song.arraySong[i].displayData();
            }
            System.out.println("----------------------------------------------------------------------------------------------");
        }
    }

    public static void updateSongInfo(Scanner scanner, Song[] arraySong, Singer[] arraySinger, int currentSongIndex, int currentSingerIndex) {
        String idUpdate="" ;
        while (true){
            System.out.println("Nhập ID bài hát cần update thông tin: ");
            idUpdate = scanner.nextLine();
             if(idUpdate.isEmpty()){
                 System.out.println("Không dược để trống dữ liệu. Vui lòng nhập lại!");
             } else
                 break;
        }
        int index = indexUpdate(idUpdate, arraySong, currentSongIndex);
        if (index < 0) {
        System.out.println("ID bài hát không tồn tại !");
        } else {
//            Song song = new Song();
//            song.setSongId(idUpdate);
//            song.inputDataUpdate(scanner, arraySong, currentSongIndex);
//            for (int i = 0; i <= currentSongIndex; i++) {
//                if (arraySong[i].getSongId().equalsIgnoreCase(idUpdate)) {
//                    arraySong[i]= song;
//                }
//            }
            Song.arraySong[index].inputDataUpdate(scanner,Song.arraySong, Song.currentSongIndex);
            Singer.arraySinger = arraySinger;
            System.out.println("Thông tin bài hát đã được update");
            showAllSong(scanner, Song.arraySong, Singer.arraySinger, Song.currentSongIndex, Singer.currentSingerIndex);
        }
    }

    private static int indexUpdate(String idUpdate, Song[] arraySong, int currentSongIndex) {
        if(currentSongIndex == -1)
            return -1;
        else {
            for (int i = 0; i <= currentSongIndex; i++) {
                if (arraySong[i].getSongId().equalsIgnoreCase(idUpdate)) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static void deleteSong(Scanner scanner, Song[] arraySong, Singer[] arraySinger, int currentSongIndex, int currentSingerIndex) {
        System.out.println("Nhập ID bài hát bạn muốn xoá: ");
        String idDelete = scanner.nextLine();
        if (!isExistedSongId(idDelete, arraySong, currentSongIndex)) {
            System.out.println("ID bài hát không tồn tại");
        } else {
            for (int i = 0; i <= currentSongIndex; i++) {
                if(arraySong[i].getSongId().equalsIgnoreCase(idDelete)) {
                    for (int j = i; j <= currentSongIndex; j++) {
                        arraySong[j]= arraySong[j+1];
                    }
                    currentSongIndex --;
                    Song.currentSongIndex = currentSongIndex;
                    Song.arraySong = arraySong;
                    break;
                }
            }
            System.out.println("Xoá thành công");
            showAllSong(scanner, Song.arraySong, Singer.arraySinger, Song.currentSongIndex, Singer.currentSingerIndex);
        }
    }
    private static boolean isExistedSongId(String idUpdate, Song[] arraySong, int currentSongIndex) {
        if(currentSongIndex == -1)
            return false;
        else {
            for (int i = 0; i <= currentSongIndex; i++) {
                if (arraySong[i].getSongId().equalsIgnoreCase(idUpdate)) {
                    return true;
                }
            }
        }
        return false;
    }
    public static void searchSong(Scanner scanner, Song[] arraySong, Singer[] arraySinger, int currentSongIndex, int currentSingerIndex) {
        System.out.println("Nhập từ khoá tìm kiếm bài hát theo tên ca sĩ hoặc thể loại:");
        String search = scanner.nextLine();
        Song[] result = new Song[100];
        int currentResult = -1;

        for (int i = 0; i <= currentSongIndex; i++) {
            if(arraySong[i].getSinger().getSingerName().equalsIgnoreCase(search) || arraySong[i].getSinger().getGenre().equalsIgnoreCase(search) ) {
                    currentResult ++;
                    result[currentResult] = arraySong[i];
            }
        }
        if(currentResult == -1){
            System.out.println("Không có kết quả phù hợp !");
        } else {
            System.out.println("----------------------------------------------------------------------------------------------");
            System.out.printf("| %-5s | %-10s | %-15s | %-10s | %-10s | %-12s | %-10s |\n","ID","Name","Descriptions","Singer","Writer","Created Date","Status");
            for (int i = 0; i <= currentResult; i++) {
                result[i].displayData();
            }
            System.out.println("----------------------------------------------------------------------------------------------");
        }
    }

    public static void sortSong(Scanner scanner, Song[] arraySong, Singer[] arraySinger, int currentSongIndex, int currentSingerIndex) {
    for (int i = 0; i < currentSongIndex; i++) {
        for (int j = i+1; j <= currentSongIndex; j++) {
            if(arraySong[i].getSongName().compareTo(arraySong[j].getSongName()) > 0) {
                Song temp = arraySong[i];
                arraySong[i] = arraySong[j];
                arraySong[j] = temp;
            }
        }
    }
        showAllSong(scanner, arraySong, arraySinger, currentSongIndex, currentSingerIndex);
    }

    public static void tenNewestSong(Scanner scanner, Song[] arraySong, Singer[] arraySinger, int currentSongIndex, int currentSingerIndex) {
        if(currentSongIndex == -1)
            System.out.println("Danh sách bài hát trống !");
        else {
//            sort
            for (int i = 0; i <= currentSongIndex; i++) {
                for (int j = i + 1; j <= currentSongIndex; j++) {
                    if (arraySong[i].getCreatedDate().isBefore(arraySong[j].getCreatedDate())) {
                        Song temp = arraySong[i];
                        arraySong[i] = arraySong[j];
                        arraySong[j] = temp;
                    }
                }

            }
            System.out.println("----------------------------------------------------------------------------------------------");
            System.out.printf("| %-5s | %-10s | %-15s | %-10s | %-10s | %-12s | %-10s |\n","ID","Name","Descriptions","Singer","Writer","Created Date","Status");
            for (int i = 0; i < 10; i++) {
                if(arraySong[i] != null )
                    arraySong[i].displayData();
            }
            System.out.println("----------------------------------------------------------------------------------------------");
        }
    }
}
