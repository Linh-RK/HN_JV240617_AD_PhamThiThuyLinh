package ra.controller;

import ra.mode.Singer;
import ra.mode.Song;
import ra.service.SingerService;

import java.util.Scanner;

import static ra.run.MusicManagement.inputChoice;

public class SingerController {
    public void singerManagement( Scanner scanner, Song[] arraySong, Singer[] arraySinger, int currentSongIndex, int currentSingerIndex) {
        boolean flag = true;
        int choice;
        do {
            System.out.println("********************************SINGER-MANAGEMENT********************************");
            System.out.println("*                                                                               *");
            System.out.println("*       1.Nhập vào số lượng ca sĩ cần thêm và nhập thông tin cần thêm mới       *");
            System.out.println("*       2.Hiển thị danh sách tất cả ca sĩ đã lưu trữ                            *");
            System.out.println("*       3.Thay đổi thông tin ca sĩ theo mã id                                   *");
            System.out.println("*       4.Xóa ca sĩ theo mã id                                                  *");
            System.out.println("*       5.Thoát                                                                 *");
            System.out.println("*                                                                               *");
            System.out.println("*********************************************************************************");
            choice = inputChoice(scanner);
            switch (choice) {
                case 1:
                    SingerService.addNewSinger(scanner, Song.arraySong, Singer.arraySinger, Song.currentSongIndex,Singer.currentSingerIndex);
                    break;
                case 2:
                    SingerService.showAllSinger(scanner, Song.arraySong, Singer.arraySinger, Song.currentSongIndex,Singer.currentSingerIndex);
                    break;
                case 3:
                    SingerService.updateSingerInfoById(scanner, Song.arraySong, Singer.arraySinger, Song.currentSongIndex,Singer.currentSingerIndex);
                    break;
                case 4:
                    SingerService.deleteSingerById(scanner, Song.arraySong, Singer.arraySinger, Song.currentSongIndex,Singer.currentSingerIndex);
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
