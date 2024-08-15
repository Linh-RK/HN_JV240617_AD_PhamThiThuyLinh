package ra.controller;

import ra.mode.Singer;
import ra.mode.Song;
import ra.service.SongService;
import java.util.Scanner;

import static ra.run.MusicManagement.inputChoice;

public class SongController {
    public void songManagement(Scanner scanner, Song[] arraySong, Singer[] arraySinger, int currentSongIndex, int currentSingerIndex) {
        boolean flag = true;
        int choice;
        do {
            System.out.println("*********************************SONG-MANAGEMENT*********************************");
            System.out.println("*                                                                               *");
            System.out.println("*       1.Nhập vào số lượng bài hát cần thêm và nhập thông tin cần thêm mới     *");
            System.out.println("*       2.Hiển thị danh sách tất cả bài hát đã lưu trữ                          *");
            System.out.println("*       3.Thay đổi thông tin bài hát theo mã id                                 *");
            System.out.println("*       4.Xóa bài hát theo mã id                                                *");
            System.out.println("*       5.Thoát                                                                 *");
            System.out.println("*                                                                               *");
            System.out.println("*********************************************************************************");
            choice = inputChoice(scanner);
            switch (choice) {
                case 1:
                    SongService.addNewSong(scanner, Song.arraySong, Singer.arraySinger, Song.currentSongIndex, Singer.currentSingerIndex);
                    break;
                case 2:
                    SongService.showAllSong(scanner, Song.arraySong, Singer.arraySinger, Song.currentSongIndex, Singer.currentSingerIndex);
                    break;
                case 3:
                    SongService.updateSongInfo(scanner, Song.arraySong, Singer.arraySinger, Song.currentSongIndex, Singer.currentSingerIndex);
                    break;
                case 4:
                    SongService.deleteSong(scanner, Song.arraySong, Singer.arraySinger, Song.currentSongIndex, Singer.currentSingerIndex);
                    break;
                case 5:
                    flag = false;
                    break;
                default:
                    System.out.println("Please enter a choice between 1 and 5: ");
            }
        }while (flag) ;
    }
}
