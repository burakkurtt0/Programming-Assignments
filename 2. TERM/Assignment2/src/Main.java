import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
public class Main {
    public static void main(String[] args)throws IOException {
        
        LinkedList<Square> list = Board.createBoardList(); // Squares on the board are created in random order
        Square[] board = new Square[40];
        board = Board.createBoard(board, list); // Squares are listed in the right order in array (i. index ----->  i+1. square)
 
        GamePlayer p1 = new GamePlayer("Player 1");
        GamePlayer p2 = new GamePlayer("Player 2");
        Banker bank = new Banker("Banker");

        GamePlayer[] PlayerList = {p1,p2};
        FileWriter out = new FileWriter("output.txt");
        FileReader in = new FileReader("command1.txt");
        Scanner scn = new Scanner(in);
        boolean Gameover = false;
        
        int jailcount1 = 0; // If player in jail, it reperesents which turn(not game turn) they will be free. (1 for player1 , 2 for player2)
        int jailcount2 = 0;
        int j1 =0 ; // Counting how many turns are passed in jail
        int j2 =0;
        
        while (scn.hasNextLine()){

            String line = scn.nextLine();
            if (!line.startsWith("s")){

            String[] commandline = line.split(";");
            int Dice = Integer.parseInt(commandline[1]);
            int playerNumber = (Integer.parseInt(commandline[0].substring(commandline[0].length()-1)));
            if (playerNumber ==1){
                int i = 0 ; //Representing index number for PlayerList (GamePlayer [])

            if (PlayerList[i].isJailStatus()){

                if (PlayerList[i].getTurncount()>=jailcount1){ //Last turn in jail
                    PlayerList[i].setJailStatus(false);
                    PlayerList[i].setTurncount(PlayerList[i].getTurncount()+1);
                    out.write(PlayerList[playerNumber].getName() + "    "+Dice + " "+PlayerList[i].getPosition()+"  " +(int)PlayerList[0].getMoney()+"   " +(int)PlayerList[1].getMoney() + "   "+   PlayerList[i].getName() + "  "+"in"+"   "+ "jail"+"   "+"(count="+j1+")"+"\n");
                    
                    j1 =0 ;
                    continue;
                }
                else{
                    PlayerList[i].setTurncount(PlayerList[i].getTurncount()+1);
                    out.write(PlayerList[i].getName() + "    "+Dice + " "+PlayerList[i].getPosition()+"  " +(int)PlayerList[0].getMoney()+"   " +(int)PlayerList[1].getMoney() + "   "+   PlayerList[i].getName() + "  "+"in"+"   "+ "jail"+"   "+"(count="+j1+")"+"\n");
                    j1++;
                    
                    continue;
                }
            }
            int player_pos = PlayerList[i].getPosition();
            player_pos += Dice;

            if (player_pos >40) {
                player_pos = player_pos%40;
                Player.TradeMoney(bank, PlayerList[i], 200);
            }
            PlayerList[i].setPosition(player_pos);
            
            jailcount1 = GameMethods.TakeAction(PlayerList[i], PlayerList[1], bank, board, player_pos, Dice,out,PlayerList); // If player goes jail , TakeAction returns how many turn player stay in jail ,otherwise returns 0.(except bankrupt situations)

            if (jailcount1 == 100 || PlayerList[i].getMoney()<=0){// Takeaction returns 100 if player goes bankrupt
                 Gameover = true;
                GameMethods.Show(PlayerList, board, bank, out,PlayerList[1]);
                break;
            } 

            if (jailcount1 != 0){
                j1=1;
            }}
            
    else{ 
    // same commands, just for other player
    int i = 1;
    if (PlayerList[i].isJailStatus()){

        if (PlayerList[i].getTurncount()>=jailcount2){
            PlayerList[i].setJailStatus(false);
            PlayerList[i].setTurncount(PlayerList[i].getTurncount()+1);
            out.write(PlayerList[i].getName() + "    "+Dice + " "+PlayerList[i].getPosition()+"  " +(int)PlayerList[0].getMoney()+"   " +(int)PlayerList[1].getMoney() + "   "+   PlayerList[i].getName() + "  "+"in"+"   "+ "jail"+"   "+"(count="+j2+")"+"\n");
            j2 =0 ;
            continue;
        }
        else{
            PlayerList[i].setTurncount(PlayerList[i].getTurncount()+1);
            out.write(PlayerList[i].getName() + "    "+Dice + " "+PlayerList[i].getPosition()+"  " +(int)PlayerList[0].getMoney()+"   " +(int)PlayerList[1].getMoney() + "   "+   PlayerList[i].getName() + "  "+"in"+"   "+ "jail"+"   "+"(count="+j2+")"+"\n");
            j2++;
            continue;
        }
    }
    int player_pos = PlayerList[i].getPosition();
    player_pos += Dice;

    if (player_pos >40) {
        player_pos = player_pos%40;
        Player.TradeMoney(bank, PlayerList[i], 200);
    }
    PlayerList[i].setPosition(player_pos);
    jailcount2 = GameMethods.TakeAction(PlayerList[i], PlayerList[0], bank, board, player_pos, Dice,out,PlayerList);

    if (jailcount2 == 100 || PlayerList[i].getMoney()<=0){
        Gameover = true;
        GameMethods.Show(PlayerList, board, bank, out,PlayerList[0]);
        break;
    } 

    if (jailcount2 != 0){
        j2=1;
    }
}}
//Show() command
else{GameMethods.Show(PlayerList, board, bank, out);}

        }//End of While

        if (!Gameover){
        GameMethods.Show(PlayerList, board, bank, out);
    }
        scn.close();
        out.close();
    }}
    

