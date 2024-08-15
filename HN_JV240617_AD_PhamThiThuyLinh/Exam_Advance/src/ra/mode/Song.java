package ra.mode;

import ra.service.SingerService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;

import static ra.mode.Singer.arraySinger;
import static ra.mode.Singer.currentSingerIndex;

public class Song {
// songId–mãbàihát –String(Có4kýtựvàbắtđầubằngkítựS,khôngtrùnglặp)
//▪ songName – tên bài hát – String (Không được để trống)
//▪ descriptions – mô tả bài hát – String
//▪ singer - ca sĩ - Singer (không được null)
//▪ songWriter - người sáng tác- String (không được để trống)
//▪ createdDate- ngày tạo - Date (mặc định là thời gian hệ thống)
//▪ songStatus- trạng thái - boolean
    public static Song[] arraySong = new Song[100];
    public static int currentSongIndex = -1;
    private String songId;
    private String songName;
    private String descriptions;
    private Singer singer;
    private String songWriter;
//    private LocalDate createdDate;
    private LocalDate createdDate;
    private boolean songStatus;
    public Song() {}

    public Song(String songId, String songName, String descriptions, Singer singer, String songWriter, LocalDate createdDate, boolean songStatus) {
        this.songId = songId;
        this.songName = songName;
        this.descriptions = descriptions;
        this.singer = singer;
        this.songWriter = songWriter;
        this.createdDate = createdDate;
        this.songStatus = songStatus;
    }

    public String getSongId() {
        return songId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public Singer getSinger() {
        return singer;
    }

    public void setSinger(Singer singer) {
        this.singer = singer;
    }

    public String getSongWriter() {
        return songWriter;
    }

    public void setSongWriter(String songWriter) {
        this.songWriter = songWriter;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public boolean isSongStatus() {
        return songStatus;
    }

    public void setSongStatus(boolean songStatus) {
        this.songStatus = songStatus;
    }
    public void inputData(Scanner scanner, Song[] arraySong, int currentSongIndex, Singer[] arraySinger,int currentSingerIndex ){
        this.songId = inputSongId(scanner, arraySong,currentSongIndex);
        System.out.println("Nhập tên bài hát:");
        this.songName = inputString(scanner, arraySong,currentSongIndex);
        System.out.println("Nhập mô tả");
        this.descriptions = inputString(scanner, arraySong,currentSongIndex);
        this.singer = inputSinger(scanner, arraySong, currentSongIndex, arraySinger, currentSingerIndex);
        System.out.println("Nhập tên nhạc sĩ");
        this.songWriter = inputString(scanner, arraySong,currentSongIndex);
//        System.out.println("Nhập ngày tạo:");
        this.createdDate = java.time.LocalDate.now();
        this.songStatus = inputSongStatus(scanner, arraySong,currentSongIndex);
    }

    private Singer inputSinger(Scanner scanner, Song[] arraySong, int currentSongIndex, Singer[] arraySinger, int currentSingerIndex) {
        Singer singer = new Singer();
        System.out.println("Nhập tên ca sĩ:");
        SingerService.showAllSinger( scanner, Song.arraySong, Singer.arraySinger,Song.currentSongIndex, Singer.currentSingerIndex);
        String singerName ="";
        do {
            singerName = scanner.nextLine();
            if (!isExistedSingerName(singerName, Singer.arraySinger, Singer.currentSingerIndex)) {
                System.out.println("Tên ca sĩ không có trong list, vui lòng nhập lại hoặc thêm tên ca sĩ trước !");
            } else {
                for (int i = 0; i <= Singer.currentSingerIndex; i++) {
                    if (Singer.arraySinger[i].getSingerName().equalsIgnoreCase(singerName)) {
                        singer = Singer.arraySinger[i];
                        return singer;
                    }
                }
            }

        }while (true);
    }

    private boolean isExistedSingerName(String singerName, Singer[] arraySinger, int currentSingerIndex) {
        if(Singer.currentSingerIndex ==-1) {
            return false;
        } else{
            for (int i = 0; i <= Singer.currentSingerIndex; i++) {
                if(Singer.arraySinger[i].getSingerName().equalsIgnoreCase(singerName)){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean inputSongStatus(Scanner scanner, Song[] arraySong, int currentSongIndex) {
        System.out.println("Nhập trạng thái bài hát (true hoặc false):");
        do {
            String gender = scanner.nextLine();
            if((gender.equals("true") || gender.equals("false"))) {
                return Boolean.parseBoolean(gender);
            } else
                System.out.println("Trạng thái bài hát cần nhập true hoặc false:");
        }while(true);
    }
    private String inputString(Scanner scanner, Song[] arraySong, int currentSongIndex) {
        String string = "";
        do {
            string = scanner.nextLine();
            if(string.isEmpty()){
                System.out.println("Không được để trống dữ liệu, vui lòng nhập dữ liệu !");
            } else
                return string;
        }while(true);
    }

    private String inputSongId(Scanner scanner, Song[] arraySong, int currentSongIndex) {
        System.out.println("Nhập ID bài hát theo form Sxxx:");
        Pattern pattern = Pattern.compile("^S\\w{3}");
        String songId = "";
        do {
            songId = scanner.nextLine();
            if(!pattern.matcher(songId).find()) {
                System.out.println("ID bài hát không đúng format (Sxxx)!");
            }else if(isExistedSongId(songId, arraySong, currentSongIndex))
                System.out.println("ID bài hát đã tồn tại ! Vui lòng nhập lại.");
            else
                return songId;
        }while (true);
    }

    private boolean isExistedSongId(String songId, Song[] arraySong, int currentSongIndex){
        if(currentSongIndex == -1)
            return false;
        else {
            for(int i = 0; i < currentSongIndex+1; i++) {
                if(arraySong[i].getSongId().equalsIgnoreCase(songId))
                    return true;
            }
        }
        return false;
    }

    public void displayData(){
        System.out.println("----------------------------------------------------------------------------------------------");
        System.out.printf("| %-5s | %-10s | %-15s | %-10s | %-10s | %-12s | %-10s |\n",this.songId,this.songName,this.descriptions,this.singer.getSingerName(),this.songWriter,this.createdDate,this.songStatus);
    }

    public void inputDataUpdate(Scanner scanner, Song[] arraySong, int currentSongIndex) {
        System.out.println("Nhập tên bài hát:");
        this.songName = inputString(scanner, arraySong,currentSongIndex);
        System.out.println("Nhập mô tả");
        this.descriptions = inputString(scanner, arraySong,currentSongIndex);
        this.singer = inputSinger(scanner, arraySong, currentSongIndex, arraySinger, currentSingerIndex);
        System.out.println("Nhập tên nhạc sĩ");
        this.songWriter = inputString(scanner, arraySong,currentSongIndex);
        this.songStatus = inputSongStatus(scanner, arraySong,currentSongIndex);
    }
}
