import java.util.ArrayList;
import java.util.Scanner;

import java.io.*;


public class Database {

    private ArrayList<User> UserList ;
    private ArrayList<Film> FilmList ;
    private ArrayList<Hall> HallList ;
    private ArrayList<Seat> SeatList ;


    public Database(){
        User admin = new User("admin", "password" , true ,true);
        UserList = new ArrayList<>();
        UserList.add(admin);

        FilmList = new ArrayList<>();
        HallList = new ArrayList<>();
        SeatList = new ArrayList<>();
    }
    

    public Database(String filename){
        UserList = new ArrayList<User>();
        FilmList = new ArrayList<Film>();
        HallList = new ArrayList<Hall>();
        SeatList = new ArrayList<Seat>();


        try{
        FileReader in = new FileReader(filename);    
        Scanner sc = new Scanner(in);
        while (sc.hasNextLine()){
            String line = sc.nextLine();
            ArrayList<String> LineArray = new ArrayList<>();
            for (String word : line.split("\t")){
                LineArray.add(word);
            }



            if(LineArray.get(0).equals("user")){               
                UserList.add(new User(LineArray.get(1), LineArray.get(2), Boolean.parseBoolean(LineArray.get(3)), Boolean.parseBoolean(LineArray.get(4))));
                
            }


            else if (LineArray.get(0).equals("film")){
                FilmList.add(new Film(LineArray.get(1),LineArray.get(2),Integer.parseInt(LineArray.get(3))));
            }

            else if (LineArray.get(0).equals("hall")){
                HallList.add(new Hall(LineArray.get(1),LineArray.get(2),Integer.parseInt(LineArray.get(3)),Integer.parseInt(LineArray.get(4)),Integer.parseInt(LineArray.get(5))));
            }

            
        }
        sc.close();

        FileWriter out = new FileWriter(filename,true);
        out.write("\n");
        out.close();
    }
    catch (Exception e ){
        e.printStackTrace();
    }}


    public ArrayList<User> getUserList() {
        return UserList;
    }
    public void setUserList(ArrayList<User> userList) {
        UserList = userList;
    }
    public ArrayList<Film> getFilmList() {
        return FilmList;
    }
    public void setFilmList(ArrayList<Film> filmList) {
        FilmList = filmList;
    }
    public ArrayList<Hall> getHallList() {
        return HallList;
    }
    public void setHallList(ArrayList<Hall> hallList) {
        HallList = hallList;
    }
    public ArrayList<Seat> getSeatList() {
        return SeatList;
    }
    public void setSeatList(ArrayList<Seat> seatList) {
        SeatList = seatList;
    }


    
}




class User{
    private String Name;
    private String Password;
    private boolean Clubmember;
    private boolean Admin;

    public User(String Name, String password,boolean clubmember , boolean admin){
        this.Name = Name;
        this.Password = password;
        this.Clubmember = clubmember;
        this.Admin = admin;
    }

    public String getName() {
        return Name;
    }
    public void setName(String name) {
        this.Name = name;
    }
    public String getPassword() {
        return Password;
    }
    public void setPassword(String password) {
        this.Password = password;
    }
    public boolean isClubmember() {
        return Clubmember;
    }
    public void setClubmember(boolean clubmember) {
        this.Clubmember = clubmember;
    }
    public boolean isAdmin() {
        return Admin;
    }
    public void setAdmin(boolean admin) {
        this.Admin = admin;
    }


    
}

class Hall{
    private String filmname;
    private String hallname;
    private Integer price;
    private Integer row;
    private Integer column;
    public Hall(String filmname, String hallname, Integer price, Integer row, Integer column) {
        this.filmname = filmname;
        this.hallname = hallname;
        this.price = price;
        this.row = row;
        this.column = column;
    }
    public String getFilmname() {
        return filmname;
    }
    public void setFilmname(String filmname) {
        this.filmname = filmname;
    }
    public String getHallname() {
        return hallname;
    }
    public void setHallname(String hallname) {
        this.hallname = hallname;
    }
    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }
    public Integer getRow() {
        return row;
    }
    public void setRow(Integer row) {
        this.row = row;
    }
    public Integer getColumn() {
        return column;
    }
    public void setColumn(Integer column) {
        this.column = column;
    }
    

}

class Seat{

}

class Film{
    private ArrayList<Hall> HallList = new ArrayList<>();
    private String name;
    private String path;
    private Integer duration;
    public Film(String name, String path, Integer duration){
        this.name = name;
        this.path = path;
        this.duration = duration;

    }

    public ArrayList<Hall> FindHalls(String Filename){
        try{
            Scanner sc = new Scanner(new FileReader(Filename));
            while (sc.hasNextLine()){
                String[] lineArray = sc.nextLine().split("\t");
                
                if (lineArray[0].startsWith("hall")){
                    
                    if (lineArray[1].equals(this.getName())){
                        if (this.getHallList().size() == 0){
                            
                            
                            this.getHallList().add(new Hall(lineArray[1], lineArray[2], Integer.parseInt(lineArray[3]), Integer.parseInt(lineArray[4]), Integer.parseInt(lineArray[5])));
                        }
                        else{
                            Boolean diffHall = true;
                            for (Hall hall : this.getHallList()){
                                if (hall.getHallname().equals(lineArray[2])){
                                    diffHall = false;
                                }}

                            if (diffHall){
                                this.getHallList().add(new Hall(lineArray[1], lineArray[2], Integer.parseInt(lineArray[3]), Integer.parseInt(lineArray[4]), Integer.parseInt(lineArray[5])));
                            }
                                
                                
                            }
                        
                        

                    }

                }
                
        }}
        catch(Exception e){
            
        }
        return this.getHallList();
       
    }
    
    public ArrayList<Hall> getHallList() {
        return HallList;
    }

    public void setHallList(ArrayList<Hall> hallList) {
        HallList = hallList;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }
    public Integer getDuration() {
        return duration;
    }
    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
