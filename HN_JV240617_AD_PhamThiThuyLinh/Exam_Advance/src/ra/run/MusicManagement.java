package ra.run;

import ra.controller.SingerController;
import ra.controller.SongController;
import ra.mode.Singer;
import ra.mode.Song;
import ra.service.SingerService;
import ra.service.SongService;

import java.util.Scanner;

import static ra.mode.Singer.arraySinger;
import static ra.mode.Singer.currentSingerIndex;
import static ra.mode.Song.arraySong;
import static ra.mode.Song.currentSongIndex;

public class MusicManagement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        SingerController singerController = new SingerController();
        SongController songController = new SongController();
        do {
            System.out.println("********************************MUSIC-MANAGEMENT*********************************");
            System.out.println("*                                                                               *");
            System.out.println("*                            1. Quản lý ca sĩ                                   *");
            System.out.println("*                            2. Quản lý bài hát                                 *");
            System.out.println("*                            3. Tìm kiếm bài hát                                *");
            System.out.println("*                            4. Thoát                                           *");
            System.out.println("*                                                                               *");
            System.out.println("*********************************************************************************");
            choice = inputChoice(scanner);
            switch (choice) {
                case 1:
                    singerController.singerManagement(scanner,arraySong,arraySinger, currentSongIndex, currentSingerIndex);
                    break;
                case 2:
                    songController.songManagement(scanner,arraySong,arraySinger, currentSongIndex, currentSingerIndex );
                    break;
                case 3:
                    searchManagement(scanner,arraySong,arraySinger, currentSongIndex, currentSingerIndex);
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please enter a choice between 1 and 3: ");
            }
        }while(true);
    }

    private static void searchManagement(Scanner scanner, Song[] arraySong, Singer[] arraySinger, int currentSongIndex, int currentSingerIndex) {
        boolean flag = true;
        int choice;
        do {
            System.out.println("********************************SEARCH-MANAGEMENT********************************");
            System.out.println("*                                                                               *");
            System.out.println("*       1.Tìm kiếm bài hát theo tên ca sĩ hoặc thể loại                         *");
            System.out.println("*       2.Tìm kiếm ca sĩ theo tên hoặc thể loại                                 *");
            System.out.println("*       3.Hiển thị danh sách bài hát theo thứ tự tên tăng dần                   *");
            System.out.println("*       4.Hiển thị 10 bài hát được đăng mới nhất                                *");
            System.out.println("*       5.Thoát                                                                 *");
            System.out.println("*                                                                               *");
            System.out.println("*********************************************************************************");
            choice = inputChoice(scanner);
            switch (choice) {
                case 1:
                    SongService.searchSong(scanner, Song.arraySong, Singer.arraySinger, Song.currentSongIndex,Singer.currentSingerIndex);
                    break;
                case 2:
                    SingerService.searchSinger(scanner, Song.arraySong, Singer.arraySinger, Song.currentSongIndex,Singer.currentSingerIndex);
                    break;
                case 3:
                    SongService.sortSong(scanner, Song.arraySong, Singer.arraySinger, Song.currentSongIndex,Singer.currentSingerIndex);
                    break;
                case 4:
                    SongService.tenNewestSong(scanner, Song.arraySong, Singer.arraySinger, Song.currentSongIndex,Singer.currentSingerIndex);
                    break;
                case 5:
                    flag = false;
                    break;
                default:
                    System.out.println("Please enter a choice between 1 and 5: ");
            }
        }while (flag) ;
    }

    public static int inputChoice(Scanner scanner) {
        System.out.println("Please enter your choice: ");
        do {
            try{
                return Integer.parseInt(scanner.nextLine());
            }
            catch(Exception e) {
                System.out.println("Choice must be an positive integer: ");
            }
        }while(true);
    }
}
