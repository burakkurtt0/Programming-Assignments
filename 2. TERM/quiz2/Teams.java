
import java.io.FileInputStream;
import java.util.*;
public class Teams {

    Set<String> F_teams = new HashSet<String>();
    Set<String> V_teams = new HashSet<String>();
    Set<String> H_teams = new HashSet<String>();
    Set<String> B_teams = new HashSet<String>();

    String[] f_teams = new String[4];
    String[] v_teams = new String[4];
    String[] h_teams = new String[4];
    String[] b_teams = new String[4];

    public void DivideTeams(FileInputStream in){
        Scanner scanner = new Scanner(in);

        while (scanner.hasNextLine()){
            
            String line = scanner.nextLine();
            String[] lineArray = line.split("\t");

            if (lineArray[0].equals("F")){
                F_teams.add(lineArray[1]);
                F_teams.add(lineArray[2]);
            }
            
            else if (lineArray[0].equals("V")){
                V_teams.add(lineArray[1]);
                V_teams.add(lineArray[2]);
            }

            else if (lineArray[0].equals("H")){
                H_teams.add(lineArray[1]);
                H_teams.add(lineArray[2]);
            }

            else if (lineArray[0].equals("B")){
                B_teams.add(lineArray[1]);
                B_teams.add(lineArray[2]);
            }
        

        
        }


        int i =0 ;
        for (String s : F_teams){
            f_teams[i] = s;
            i++;
        }
        int j =0 ;
        for (String s : V_teams){
            v_teams[j] = s;
            j++;
        }
        int k =0 ;
        for (String s : H_teams){
            h_teams[k] = s;
            k++;
        }
        int l =0 ;
        for (String s : B_teams){
            b_teams[l] = s;
            l++;
        }
        scanner.close();
    }
}



    
