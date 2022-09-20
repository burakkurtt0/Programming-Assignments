import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Properties;


import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class HUcinema extends Application{


    public static Database createDatabase(String file) {
        
        
        Database dtb = new Database("assets/data/"+file);
        
        return dtb;
    }

    Database database;

    static String title;
    static Integer  max_error;
    static Integer discount_percentage;
    static Integer block_time;
    final Integer width = 400;
    final Integer height = 400;

    public Stage Cinema ;
    public void start(Stage primaryStage) {
        try{
        database = createDatabase("backup.dat");
        Cinema = primaryStage;
        getproperties("properties.dat");
        primaryStage.getIcons().add(new Image("logo.png"));
        primaryStage.setTitle(title);
        
        
        primaryStage.setScene(LogInScene());

        primaryStage.show();}
        catch (Exception E){
            E.printStackTrace();
        }
    }


    public Scene LogInScene(){
        
        Button Signup = new Button("SIGN UP");
        Signup.setOnAction(e->{
            Cinema.setScene(SignUpScene());
        });
        Button Login = new Button("LOG IN");


        

        

        Pane Rootpane = new Pane();
        Pane layoutText = new Pane();
        GridPane layout = new GridPane();
        GridPane layoutButton = new GridPane();
        layout.setPadding(new Insets(200, 70, 30, 100));
        layoutButton.setPadding(new Insets(280, 70, 0, 100));

        Text txt = new Text(width/8,height/5,"Welcome to the HUCS Cinema Reservation System! \nPlease enter your credentials below and click LOGIN.\nYou can create a new account by clicking SIGN UP button.");
        layoutText.getChildren().add(txt);
        Text username= new Text( "Username:  ");
        Text password = new Text("Password: ");
        TextField username_login = new TextField();
        PasswordField password_login = new PasswordField();

        layout.setVgap(width/40);
        layoutButton.setVgap(width/40);
        layoutButton.setHgap(height/10);

        layout.add(username,1,0);
        layout.add(username_login,2,0);

        layout.add(password,1,1);
        layout.add(password_login,2,1);    

       layoutButton.add(Signup, 0, 0);
       layoutButton.add(Login, 3, 0);

       File audio = new File("assets/effects/error.mp3");
       Media error = new Media(audio.toURI().toString());
       MediaPlayer errorsound = new MediaPlayer(error);
        Text warningText = new Text();
        Login.setOnAction(e->{
            try{layoutText.getChildren().remove(warningText);}
            catch(Exception notinserted){}
            ArrayList<User> userlist  = database.getUserList();
           
            warningText.setX(125);
            warningText.setY(350);
            errorsound.stop();
            boolean wronginput = true;

                for(User usr : userlist){
                    if(username_login.getText().equals(usr.getName()) && password_login.getText().equals(usr.getPassword())){
                        wronginput = false;
                        Cinema.setScene(WelcomeScene(usr));
                        
            }}
            if (wronginput){
            warningText.setText("ERROR: There is no such a credential!");
            errorsound.play();
            try{layoutText.getChildren().add(warningText);}
            catch(Exception f){
                f.printStackTrace();
            }}

           
        });



        layoutText.getChildren().addAll(layoutButton);
       Rootpane.getChildren().addAll(layoutText,layout);

       return new Scene(Rootpane,width,height);
    }


    public Scene SignUpScene(){
        Pane rootpane = new Pane();
        Pane txtpane = new Pane();
        GridPane inputPane = new GridPane();
        GridPane buttonPane = new GridPane();
     
        inputPane.setPadding(new Insets(150, 70, 30, 100));
        buttonPane.setPadding(new Insets(250, 70, 0, 100));

        Button Signup_in_signup = new Button("SIGN UP");
        
        Button Login_in_signup = new Button("LOG IN");

        Text txt = new Text(width/8, height/5, "Welcome to the HUCS Cinema Reservation System! \nFill the form below to create a new account.\nYou can go to Log In page by clicking LOG IN Button.");
        txtpane.getChildren().add(txt);
        


        Text username= new Text( "Username:  ");
        Text password = new Text("Password: ");
        Text passwordagain = new Text("Password: ");

        TextField username_signup = new TextField();
        PasswordField password_signup = new PasswordField();
        PasswordField password_signup_again = new PasswordField();

        inputPane.setVgap(width/40);
        buttonPane.setVgap(width/40);
        buttonPane.setHgap(height/10);


        inputPane.add(username,1,0);
        inputPane.add(username_signup,2,0);

        inputPane.add(password,1,1);
        inputPane.add(password_signup,2,1);

        inputPane.add(passwordagain, 1, 2);
        inputPane.add(password_signup_again, 2, 2);

        buttonPane.add(Login_in_signup, 0, 4);
        buttonPane.add(Signup_in_signup, 3, 4);
        
        File audio = new File("assets/effects/error.mp3");
        Media error = new Media(audio.toURI().toString());
        MediaPlayer errorsound = new MediaPlayer(error);
        





        


        //Action methods for buttons
        Login_in_signup.setOnAction(e->{
            Cinema.setScene(LogInScene());
        });

        Text warningText = new Text();
        
        
        Signup_in_signup.setOnAction(e->{
            
            warningText.setX(125);
            warningText.setY(350);
            errorsound.stop();
            

            ArrayList<User> userlist  = database.getUserList();
            boolean sameId = false;
                for(User usr : userlist){
                if(usr.getName().equals(username_signup.getText())){
                    sameId = true;
            }}
        


            

            if(username_signup.getText().length() == 0){
                
                warningText.setText("ERROR: Username cannot be empty!");
                errorsound.play();
                
            }

            else if(sameId){
                    warningText.setText("ERROR: This username already exists!");
                    errorsound.play();
                    
            }


            else if(password_signup.getText().length()==0 ){
                warningText.setText("ERROR: Password cannot be empty!");
                errorsound.play();
                
            }
        

            else if (!(password_signup.getText().equals(password_signup_again.getText()))){
                warningText.setText("ERROR: Passwords do not match!");
                errorsound.play();
                
            }
            
            else{
                    
                    warningText.setX(10);
                    warningText.setText("SUCCESS: You have successfully registered with your new credentials ");
                    userlist.add(new User(username_signup.getText(),password_signup.getText(),false,false));
                    database.setUserList(userlist);
                    try{
                        FileWriter out = new FileWriter("assets/data/backup.dat",true);
                        out.write("user\t" + username_signup.getText()+"\t"+hashPassword(password_signup.getText())+"\t"+"false\t"+"false\n");
                        out.close();
                    }
                    catch(Exception d){

                    }
                    
                         
                    
                }
            
            
            try{
            txtpane.getChildren().addAll(warningText);}
            
            catch (Exception E){}
            

        });



        
        
        txtpane.getChildren().addAll(buttonPane);
        rootpane.getChildren().addAll(txtpane,inputPane);
        
        return new Scene(rootpane,width,height);

    }

    public Scene WelcomeScene(User user){
        Pane Rootpane = new Pane();
        GridPane filmlist_and_OK = new GridPane();
        Text mainText = new Text();
        GridPane gridlogout = new GridPane();
        HBox adminlayout;    


        mainText.setX(70);
        mainText.setY(130);





        ArrayList<Film> filmArray = database.getFilmList();
        ComboBox<String> filmList = new ComboBox<String>();
        for (Film film : filmArray){
          
            filmList.getItems().add(film.getName());
        }
        filmList.getSelectionModel().selectFirst();

       

        if(user.isAdmin()){
            
            if(user.isClubmember()){
                mainText.setText("Welcome "+user.getName()+" (Admin - Club Member)!\nYou can either select film below or do edits.");
            }
            else{
                mainText.setText("Welcome "+user.getName()+" (Admin)!\nYou can either select film below or do edits.");
            }

            adminlayout = new HBox();
            adminlayout.setSpacing(15);
            Button addFilm = new Button("Add Film");
            Button removeFilm = new Button("Remove Film");
            Button edit_users = new Button("Edit Users");
            adminlayout.getChildren().addAll(addFilm,removeFilm,edit_users);
            filmlist_and_OK.add(adminlayout, 0, 3);

            addFilm.setOnAction(e->{
                Cinema.setScene(addFilmScene(user));
            });

            removeFilm.setOnAction(e->{
                Cinema.setScene(removeFilmScene(user));
            });

            edit_users.setOnAction(e->{
                Cinema.setScene(Edit_User_Scene(user));
            });


            }

        else{
            if(user.isClubmember()){
                mainText.setText("Welcome "+user.getName()+" (Club Member)!\nSelect a film and then click OK to continue.");
                
            }

            else{
                mainText.setText("Welcome "+user.getName()+"!\nSelect a film and then click OK to continue.");
            }

            

        }
        Button OK = new Button("OK");
        OK.setOnAction(e->{
            Film SelectedFilm = null;
            for(Film film : database.getFilmList()){
                if (film.getName().equals(filmList.getValue())){
                    SelectedFilm = film;
                }
            }

            Cinema.setScene(FilmScene(user, SelectedFilm));


        });

        Button Log_out = new Button("LOG OUT");
        gridlogout.getChildren().add(Log_out);
        gridlogout.setPadding(new Insets(300, 0, 30, 310));
        Log_out.setOnAction(e->{
            Cinema.setScene(LogInScene());
        });

        filmlist_and_OK.setPadding(new Insets(100, 0, 70, 50));
        filmlist_and_OK.setHgap(20);
        filmlist_and_OK.setVgap(20);
        filmlist_and_OK.add(filmList, 0, 2);
        filmlist_and_OK.add(OK, 1, 2);
        filmlist_and_OK.add(mainText, 0, 0);
    
        
        
        

       
        Rootpane.getChildren().addAll(gridlogout,filmlist_and_OK);
        

        return new Scene(Rootpane,width,height);

    }




    // Sıkıntılı.
    public Scene Edit_User_Scene(User user){
        TableView<User> userTable = new TableView<>();
        TableColumn<User,String> name = new TableColumn<User,String>("Username");
        name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        TableColumn<User,Boolean> club = new TableColumn<User,Boolean>("Club Member");
        club.setCellValueFactory(new PropertyValueFactory<>("Clubmember"));
        
        TableColumn<User,Boolean> admin = new TableColumn<User,Boolean>("Admin");
        admin.setCellValueFactory(new PropertyValueFactory<>("Admin"));
        userTable.setEditable(true);
        
        Button Back = new Button("BACK");
        Back.setOnAction(e->{
            Cinema.setScene(WelcomeScene(user));
        });
        

        userTable.setMaxHeight(300);
        userTable.setPrefWidth(350);
        userTable.getColumns().add(name);
        userTable.getColumns().add(club);
        userTable.getColumns().add(admin);
        

        userTable.getItems().add(database.getUserList().get(0));

        

        StackPane table_layout = new StackPane();
        table_layout.setPadding(new Insets(20, 0, 300, 25));
        table_layout.getChildren().add(userTable);

        
        Pane root = new Pane();
        root.getChildren().addAll(table_layout,Back);

        return new Scene(root,width,height);


    }




    public Scene removeFilmScene(User user){
        Pane root = new Pane();
        
        GridPane buttonpane = new GridPane();
        Text mainText = new Text("Select the film that you desire to remove and then click OK.");
        mainText.setX(40);
        mainText.setY(130);

        Button Back = new Button("BACK");
        Button OK = new Button("OK");

        


        ComboBox<String> filmlist = new ComboBox<>();
        for (Film film : database.getFilmList()){
            filmlist.getItems().addAll(film.getName());
        }
        filmlist.getSelectionModel().selectFirst();
        filmlist.setLayoutX(60);
        filmlist.setLayoutY(150);

        buttonpane.add(Back, 0, 1);
        buttonpane.add(OK, 1, 1);
        buttonpane.setHgap(150);
        buttonpane.setPadding(new Insets(250, 50, 0, 100));
        

        Back.setOnAction(e->{
            Cinema.setScene(WelcomeScene(user));
        });

        OK.setOnAction(e->{
            ArrayList<Film> filmArray = database.getFilmList();
            String filmname=filmlist.getValue();
            Film matchedfilm = null;
            for (Film film : filmArray){
                if (filmname.equals(film.getName())){
                    matchedfilm = film;
                }
            }

            database.getFilmList().remove(matchedfilm);
            filmlist.getItems().remove(matchedfilm.getName());


        });

        root.getChildren().addAll(buttonpane,mainText,filmlist);


        return new Scene(root,width,height);
    }

    



    public Scene addFilmScene(User user){
        Pane root = new Pane();
        VBox texts = new VBox();
        VBox fields = new VBox();
        GridPane grid = new GridPane();
        GridPane buttons = new GridPane();
        
        Pane textPane = new Pane();

        Text mainText = new Text("Please give name, relative path of the trailer and duration of the film.");
        mainText.setX(10);
        mainText.setY(80);
        
        Text name = new Text("Name:");
        Text trailer = new Text("Trailer (Path):");
        Text duration = new Text("Duration (m):");
        TextField namefield = new TextField();
        TextField trailerfield = new TextField();
        TextField durationfield = new TextField();
        Button Back = new Button("BACK");
        Button OK = new Button("OK");

        
        texts.getChildren().addAll(name,trailer,duration);
        texts.setSpacing(19);
        fields.setSpacing(7);
        fields.getChildren().addAll(namefield,trailerfield,durationfield);
        grid.add(texts, 1, 0);
        grid.add(fields, 2, 0);
        grid.setLayoutX(75);
        grid.setLayoutY(150);
        grid.setHgap(20);
        
        buttons.add(Back, 0, 2);
        buttons.add(OK,2,2);
        buttons.setLayoutX(75);
        buttons.setLayoutY(300);
        buttons.setHgap(100);

        File audio = new File("assets/effects/error.mp3");
        Media error = new Media(audio.toURI().toString());
        MediaPlayer errorsound = new MediaPlayer(error);

        Back.setOnAction(e->{
            Cinema.setScene(WelcomeScene(user));
        });
        Text WarningText = new Text();
        OK.setOnAction(e->{

            ArrayList<Film> filmlist = database.getFilmList();
            errorsound.stop();
            WarningText.setX(120);
            WarningText.setY(350);
            if (namefield.getText().length() == 0){
                WarningText.setText("ERROR: Film name could not be empty!");
                errorsound.play();
            }

            else if (trailerfield.getText().length() == 0){
                WarningText.setText("ERROR: Trailer path could not be empty!");
                errorsound.play();
            }
            else if (durationfield.getText().length() == 0 ){
                WarningText.setText("ERROR: Duration could not be empty!");
                errorsound.play();
            }
            
            else{
            try{
            Integer durationTime = Integer.parseInt(durationfield.getText());
            
            if(durationTime  <= 0){
                WarningText.setText("ERROR: Duration has to be a positive integer!");
                errorsound.play();
            }
            else{ // IF other things are OK.
                boolean diffname = true;
                for (Film film : filmlist){
                    
                    if (film.getName().equals(namefield.getText())){
                        diffname =false;
                        WarningText.setText("ERROR: System has a film with same name!");
                        errorsound.play();
                    }}
                if (diffname){
                File trailerpath = new File("assets/trailers/"+trailerfield.getText());

                if (!(trailerpath.exists())){
                    WarningText.setText("ERROR: There is no such a trailer.");
                    errorsound.play();
                }
                else{
                    
                    database.getFilmList().add(new Film(namefield.getText(), trailerfield.getText(), Integer.parseInt(durationfield.getText())));
                    WarningText.setText("SUCCESS: Film added successfully!");
                    try{
                        FileWriter out = new FileWriter("assets/data/backup.dat",true);
                        out.write("film\t"+namefield.getText()+"\t"+trailerfield.getText()+"\t"+durationfield.getText()+"\n");
                        out.close(); }
                    catch(IOException ioexcept){}
                   }
                }
               }  
           }        
            catch (Exception d ){
                d.printStackTrace();
                WarningText.setText("ERROR: Duration has to be a positive integer!");
                errorsound.play();}
              }  
                
            try{textPane.getChildren().add(WarningText);}
            catch (Exception p){}
            
        });
            

        



        textPane.getChildren().add(mainText);
        root.getChildren().addAll(textPane,grid,buttons);
        return new Scene(root,width,height);
    }




    public Scene FilmScene(User user ,Film film){
        Pane root = new Pane();
        VBox playButtons = new VBox();
        HBox OtherButtons = new HBox();
        GridPane mediaPlane = new GridPane();
        playButtons.setLayoutX(350*2); playButtons.setLayoutY(90);
        playButtons.setSpacing(10*2);

        OtherButtons.setLayoutX(90*2); OtherButtons.setLayoutY(400);
        

        Button play_pause = new Button("  >  ");
        Button backward_5s = new Button("<<");
        Button forward_5s = new Button(">>");
        Button replay = new Button("|<<");
        Button Back = new Button("BACK");
        Button OK = new Button("OK");



        ComboBox<String> HallList = new ComboBox<>();
        film.FindHalls("assets/data/backup.dat");
        for(Hall hall: film.getHallList()){
            HallList.getItems().add(hall.getHallname());
        }
        HallList.getSelectionModel().selectFirst();


        Text mainText = new Text(film.getName() +" ("+film.getDuration()+" minutes)");
        mainText.setX(50*5); mainText.setY(40);


        Slider VolumeSlider = new Slider();
        VolumeSlider.setOrientation(Orientation.VERTICAL);
        File path = new File("assets/trailers/"+film.getPath());
        Media media = new Media(path.toURI().toString());
        MediaPlayer trailer = new MediaPlayer(media);
        MediaView movieTrailer = new MediaView(trailer);
        
        movieTrailer.setX(30);
        movieTrailer.setY(50);
        
        
        movieTrailer.setFitHeight(500);
        movieTrailer.setFitWidth(600);
       
        

        VolumeSlider.setValue(trailer.getVolume() *100);
        VolumeSlider.valueProperty().addListener(new InvalidationListener() {

            @Override
            public void invalidated(javafx.beans.Observable observable) {
                trailer.setVolume(VolumeSlider.getValue()/100);
                
            }
            
            
        });



        Back.setOnAction(e->{
            trailer.stop();
            Cinema.setScene(WelcomeScene(user));
        });

        OK.setOnAction(e->{
            String hallname = HallList.getValue();
            Hall selectedHall = null;
            for (Hall hall : film.getHallList()){
                if ( hall.getHallname().equals(hallname)){
                    selectedHall = hall;
                }
            }

            Cinema.setScene(HallScreen(user, film , selectedHall));

        });

        play_pause.setOnAction(e->{
            if (play_pause.getText().equals("  >  ")){
                play_pause.setText("  ||  ");
                trailer.play();
            }
            else{
                play_pause.setText("  >  ");
                trailer.pause();
            }
        });

        backward_5s.setOnAction(e->{
            if (trailer.getCurrentTime().lessThan(new Duration(5000))){
               
                trailer.stop();
                trailer.play();
            }
            else{trailer.seek(trailer.getCurrentTime().subtract(new Duration(5000)));}
            
        });

        forward_5s.setOnAction(e->{
            if(trailer.getCurrentTime().greaterThan(trailer.getStopTime().subtract(new Duration(5000)))){
                trailer.seek(trailer.getStopTime());
                
            }
            else{trailer.seek(trailer.getCurrentTime().add(new Duration(5000)));}
            
        });

        replay.setOnAction(e->{
            trailer.stop();
            trailer.play();
            if (play_pause.getText().equals("  >  ")){
                play_pause.setText("  ||  ");
            }
        });

        if(user.isAdmin()){
            Button AddHall = new Button("Add Hall");
            Button RemoveHall = new Button("Remove Hall");

            AddHall.setOnAction(e->{
                Cinema.setScene(AddHall(user, film));
            });

            RemoveHall.setOnAction(e->{
                Cinema.setScene(RemoveHall(user, film));
            });

            OtherButtons.getChildren().addAll(Back,AddHall,RemoveHall,HallList,OK);
            OtherButtons.setSpacing(20);

        }

        else{
            OtherButtons.getChildren().addAll(Back,HallList,OK);
            OtherButtons.setSpacing(50);
        }



        mediaPlane.getChildren().add(movieTrailer);
        playButtons.getChildren().addAll(play_pause,backward_5s,forward_5s,replay,VolumeSlider);
        

        root.getChildren().addAll(movieTrailer,playButtons,OtherButtons,mainText);

        return new Scene(root,width*2,height+150);
    }



    public Scene AddHall(User user, Film film){
        Pane root = new Pane();

        Text mainText = new Text(film.getName()+" ("+film.getDuration()+" minutes)");
        mainText.setX(50);
        mainText.setY(50);
        GridPane firstgrid = new GridPane();
        firstgrid.setPadding(new Insets(100, 50, 150, 100));
        firstgrid.setHgap(50); firstgrid.setVgap(20);
        
        HBox lower_buttons = new HBox();
        lower_buttons.setLayoutX(100);
        lower_buttons.setLayoutY(300);
        lower_buttons.setSpacing(100);

        Button OK = new Button("OK");
        Button BACK = new Button("BACK");
        Text row = new Text("Row:");
        Text column = new Text("Column:");
        ComboBox<Integer> rows = new ComboBox<>();
        ComboBox<Integer> columns = new ComboBox<>();

        for (int i=3;i<=10;i++){
            rows.getItems().add(i);
            columns.getItems().add(i);
        }

        rows.getSelectionModel().selectFirst();
        columns.getSelectionModel().selectFirst();


        Text name = new Text("Name:");
        Text price = new Text("Price:");
        TextField nameField = new TextField();
        TextField priceField = new TextField();
        Text errorText = new Text();




        firstgrid.add(row, 0, 0);
        firstgrid.add(column, 0, 1);
        firstgrid.add(rows, 1, 0);
        firstgrid.add(columns,1,1);
        firstgrid.add(name, 0, 2);
        firstgrid.add(price, 0, 3);
        firstgrid.add(nameField, 1, 2);
        firstgrid.add(priceField, 1, 3);

        File audio = new File("assets/effects/error.mp3");
        Media error = new Media(audio.toURI().toString());
        MediaPlayer errorsound = new MediaPlayer(error);

        BACK.setOnAction(e->{
            Cinema.setScene(FilmScene(user, film));
        });
        OK.setOnAction(e->{
            try{root.getChildren().removeAll(errorText);}
            catch(Exception notInserted){}
            
            errorText.setText("");
            errorsound.stop();
            errorText.setX(120); errorText.setY(350);
            if (nameField.getText().length() == 0){
                errorText.setText("ERROR: Hall name could not be empty!");
                errorsound.play();
            }
            else if (priceField.getText().length() == 0){
                
                errorText.setText("ERROR: Price could not be empty!");
                errorsound.play();
            }

            else{
                
                try{
                    Integer intPrice = Integer.parseInt(priceField.getText());

                    for (Hall hall : database.getHallList()){
                        if (nameField.getText().equals(hall.getHallname())){
                            errorText.setText("ERROR: Hall name must be unique!");
                            errorsound.play();
                            throw new Exception();
                        }
                    }
                    FileWriter out = new FileWriter("assets/data/backup.dat",true);
                    out.write("hall\t"+film.getName()+"\t"+nameField.getText()+"\t"+intPrice+"\t"+rows.getValue()+"\t"+columns.getValue()+"\n");
                    film.getHallList().add(new Hall(film.getName(), nameField.getText(), intPrice, rows.getValue(), columns.getValue()));
                    database.getHallList().add(new Hall(film.getName(), nameField.getText(), intPrice, rows.getValue(), columns.getValue()));
                    out.close();
                    errorText.setText("SUCCESS: Hall successfully created!");
                }
                catch(NumberFormatException notInteger){
                    errorText.setText("ERROR: Price value should be an integer!");
                    errorsound.play();

                }
                catch(Exception el){}
                
            }
                try{root.getChildren().add(errorText);}
                catch(Exception asd){}

        });


        lower_buttons.getChildren().addAll(BACK,OK);

        root.getChildren().addAll(mainText,firstgrid,lower_buttons);
        return new Scene(root,width,height);
    }


    public Scene RemoveHall(User user , Film film){
        Pane root = new Pane();
        Text mainText = new Text("Select the hall that you desire to remove from "+film.getName()+" and then click OK.");
        mainText.setX(50); mainText.setY(50);
        ComboBox<String> FilmList = new ComboBox<>();

        FilmList.setLayoutX(150); FilmList.setLayoutY(200);

        for (Hall hall : film.getHallList()){
            FilmList.getItems().add(hall.getHallname());
        }
        FilmList.getSelectionModel().selectFirst();

        Button OK = new Button("OK");
        Button BACK = new Button("BACK");
        BACK.setOnAction(e->{
            Cinema.setScene(FilmScene(user, film));
        });

        OK.setOnAction(e->{
            Hall RemovingHall = null;

            for (Hall hall : film.getHallList()){
                if (hall.getHallname().equals(FilmList.getValue())){
                    RemovingHall = hall;
                    
                    
                }
            }
            FilmList.getItems().remove(RemovingHall.getHallname());
            film.getHallList().remove(RemovingHall);
            database.getHallList().remove(RemovingHall);

        });

        HBox buttons = new HBox();
        buttons.getChildren().addAll(BACK,OK);
        buttons.setSpacing(150);
        buttons.setLayoutX(10);
        buttons.setLayoutY(300);

        root.getChildren().addAll(mainText,FilmList,buttons);
        return new Scene(root,width,height);
    }


    public Scene HallScreen(User user , Film film,Hall hall){
        Pane root = new Pane();
    
        Button BACK = new Button();
        BACK.setOnAction(e->{
            Cinema.setScene(FilmScene(user, film));
        });
        GridPane seats = new GridPane();
        seats.setLayoutX(100);
        seats.setLayoutY(60);
        seats.setVgap(8);
        seats.setHgap(8);
        seats.setMaxSize(400, 600);
        
       
      
        
        Text MainText = new Text(film.getName() +" ("+film.getDuration()+" Minutes) Hall: "+hall.getHallname());
        MainText.setX(250); MainText.setY(30);
        Image emptySeat = new Image("assets/icons/empty_seat.png");
        Image reserved_seat = new Image("assets/icons/reserved_seat.png");
        ArrayList<ArrayList<Button>> ButtonList = new ArrayList<>();
        try{
        for (int i =0 ; i< hall.getRow();i++){
            for(int j =0;j<hall.getColumn();j++){
                Button seat = new Button();
                
                ImageView seatview = new ImageView(emptySeat);
                seatview.setFitHeight(40);
                seatview.setFitWidth(40);
                seat.setGraphic(seatview);
                
                seats.add(seat, j, i);
                ButtonList.get(i).add(seat);
            }
        }}
        catch(IndexOutOfBoundsException e){}

        root.getChildren().addAll(MainText,seats,BACK);
        return new Scene(root,width*2,height+400);
    }











    public static void getproperties(String filename)throws IOException{
        FileReader properties = new FileReader("assets/data/"+filename);
        Properties p = new Properties();
        p.load(properties);

        title = p.getProperty("title");
        
        max_error = Integer.parseInt(p.getProperty("maximum-error-without-getting-blocked"));
        discount_percentage = Integer.parseInt(p.getProperty("discount-percentage"));
        block_time = Integer.parseInt(p.getProperty("block-time"));
        
    }


    private static String hashPassword(String password){
        byte[] bytesOfPassword = password.getBytes(StandardCharsets.UTF_8);
        byte[] md5Digest = new byte[0];
        try{
            md5Digest = MessageDigest.getInstance("MD5").digest(bytesOfPassword);

        }
        catch(NoSuchAlgorithmException e){return null;}
        return Base64.getEncoder().encodeToString(md5Digest);
    }

    
}



    