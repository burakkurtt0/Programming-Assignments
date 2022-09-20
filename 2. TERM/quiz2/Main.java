import java.io.*;
import java.util.*;
import java.util.Collections;



public class Main{
    public static void main(String[] args)throws IOException{

        
        

        FileInputStream in = new FileInputStream(args[0]);
        FileInputStream in2 = new FileInputStream(args[0]);
        FileWriter basketball = new FileWriter("Basketball.txt");
        FileWriter Handball = new FileWriter("Handball.txt");
        FileWriter Volleyball = new FileWriter("Volleyball.txt");
        FileWriter Football = new FileWriter("Football.txt");

        Teams TeamList =  new Teams();
        Team_stats stats = new Team_stats();

        TeamList.DivideTeams(in);

        
        
        
        
        
        
        Scanner sc = new Scanner(in2);
        

        while (sc.hasNextLine()){
            
            String[] line =  sc.nextLine().split("\t");
            
            String[] score  = line[3].split(":");
            int score1 = Integer.parseInt(score[0]);
            int score2 = Integer.parseInt(score[1]);
            
            if (line[0].equals("F")){
                
                int i = -1 ;
                int j = -1 ;
                while (i<3){
                    i++;
                    if (line[1].equals(TeamList.f_teams[i])){
                        while (j<3){
                            j++;
                            if (line[2].equals(TeamList.f_teams[j])){
                                stats.Score_list_f[i] += score1;
                                stats.Negative_score_f[i] += score2;
                                stats.Score_list_f[j] += score2;
                                stats.Negative_score_f[j] += score1;


                                if (score1>score2){
                                    stats.Matches_won_f[i]+=1;
                                    stats.RankingScore_f[i]+=3;

                                    stats.Matches_lost_f[j]+=1;}

                                else if (score2>score1){
                                    stats.Matches_won_f[j]+=1;
                                    stats.RankingScore_f[j]+=3;
                                    stats.Matches_lost_f[i]+=1;
                                }
                                else{
                                    stats.Matches_draw_f[i]+=1;
                                    stats.Matches_draw_f[j]+=1;
                                    stats.RankingScore_f[i]+=1;
                                    stats.RankingScore_f[j]+=1;
                                }
                                

                                }
                            }
                        }

                    }
                    
                }


                
                if (line[0].equals("V")){
                    
                    int i = -1 ;
                    int j = -1 ;
                while (i<3){
                    i++;
                    if (line[1].equals(TeamList.v_teams[i])){
                        while (j<3){
                            j++;
                            if (line[2].equals(TeamList.v_teams[j]) ){
                                stats.Score_list_v[i] += score1;
                                stats.Negative_score_v[i] += score2;
                                stats.Score_list_v[j] += score2;
                                stats.Negative_score_v[j] += score1;

                                if ((score1==3 && score2==1) || (score1 == 3 && score2==0)){
                                    stats.Matches_won_v[i] +=1;
                                    stats.RankingScore_v[i] +=3;
                                    stats.Matches_lost_v[j] +=1;

                                }
                                else if (score1==3 && score2==2){
                                    stats.Matches_won_v[i] +=1;
                                    stats.RankingScore_v[i] +=2;
                                    stats.Matches_lost_v[j] +=1;
                                    stats.RankingScore_v[j] +=1;

                                }
                                else if (score2==3 && score1==2){
                                    stats.Matches_won_v[j] +=1;
                                    stats.RankingScore_v[j] +=2;
                                    stats.Matches_lost_v[i] +=1;
                                    stats.RankingScore_v[i] +=1;

                                }
                                if ((score2==3 && score1==1) || (score2 == 3 && score1==0)){
                                    stats.Matches_won_v[j] +=1;
                                    stats.RankingScore_v[j] +=3;
                                    stats.Matches_lost_v[i] +=1;

                                }
                            }
                        }
                    }    
                }}   

                    
                    


                

                if (line[0].equals("H")){
                    
                   
                    int k = -1 ;
                    int l = -1 ;
                while (k<3){
                    k++;
                    if (line[1].equals(TeamList.h_teams[k]) ){
                        while (l<3){
                            l++;
                            if (line[2].equals(TeamList.h_teams[l]) ){
                                stats.Score_list_h[k] += score1;
                                stats.Negative_score_h[k] += score2;
                                stats.Score_list_h[l] += score2;
                                stats.Negative_score_h[l] += score1;

                                if (score1>score2){
                                    stats.Matches_won_h[k]+=1;
                                    stats.RankingScore_h[k] +=2;
                                    stats.Matches_lost_h[l]+=1;}

                                else if (score2>score1){
                                    stats.Matches_won_h[l]+=1;
                                    stats.Matches_lost_h[k]+=1;
                                    stats.RankingScore_h[l]+=2;
                                }
                                else{
                                    stats.Matches_draw_h[k]+=1;
                                    stats.Matches_draw_h[l]+=1;
                                    stats.RankingScore_h[k]+=1;
                                    stats.RankingScore_h[l]+=1;
                                }

                                }
                            }
                        }

                    }
                   


                }
                if (line[0].equals("B")){
                    
                    int y = -1 ;
                    int z = -1 ;
                    while (y<3){
                        y++;
                        if (line[1].equals(TeamList.b_teams[y]) ){
                            while (z<3){
                                z++;
                                if (line[2].equals(TeamList.b_teams[z]) ){
                                    stats.Score_list_b[y] += score1;
                                    stats.Negative_score_b[y] += score2;
                                    stats.Score_list_b[z] += score2;
                                    stats.Negative_score_b[z] += score1;
    
                                    if (score1>score2){
                                        stats.Matches_won_b[y]+=1;
                                        stats.RankingScore_b[y]+=2;
                                        stats.Matches_lost_b[z]+=1;
                                        stats.RankingScore_b[z]+=1;}
    
                                    else if (score2>score1){
                                        stats.Matches_won_b[z]+=1;
                                        stats.RankingScore_b[y]+=1;
                                        stats.RankingScore_b[z]+=2;
                                        stats.Matches_lost_b[y]+=1;
                                    }
                                    else{
                                        stats.Matches_draw_b[y]+=1;
                                        stats.Matches_draw_b[z]+=1;
                                        stats.RankingScore_b[z]+=2;
                                        stats.RankingScore_b[y]+=1;
                                    }
    
                                    }
                                }
                            }
    
                        }
                        
    
                    }
                    
                
            
           

        
            }
            sc.close();

           
            
            Integer[] rankscore_f = new Integer[stats.RankingScore_f.length];
            for (int i =0 ; i<4;i++){
                rankscore_f[i] = stats.RankingScore_f[i];
            }
            Arrays.sort(rankscore_f,Collections.reverseOrder());

           
           
            Integer[] number_listf = new Integer[4];


            for (int i = 0 ; i<4 ; i++){
                
                
                for (int j = 0 ; j<4 ; j++){
                    
                    if (rankscore_f[i] == stats.RankingScore_f[j]){
                       
                        number_listf[i] = j;
                        
                        
                    }
                    
                }
            }

            Integer[] rankscore_b = new Integer[stats.RankingScore_b.length];
            for (int i =0 ; i<4;i++){
                rankscore_b[i] = stats.RankingScore_b[i];
            }
            Arrays.sort(rankscore_b,Collections.reverseOrder());

           
           
            Integer[] number_listb = new Integer[4];


            for (int i = 0 ; i<4 ; i++){
                
                
                for (int j = 0 ; j<4 ; j++){
                    
                    if (rankscore_b[i] == stats.RankingScore_b[j]){
                       
                        number_listb[i] = j;
                        
                        
                    }
                    
                }
            }


            Integer[] rankscore_v = new Integer[stats.RankingScore_v.length];
            for (int i =0 ; i<4;i++){
                rankscore_v[i] = stats.RankingScore_v[i];
            }
            Arrays.sort(rankscore_v,Collections.reverseOrder());

           
           
            Integer[] number_listv = new Integer[4];
            

            for (int i = 0 ; i<4 ; i++){
                
                
                for (int j = 0 ; j<4 ; j++){
                    
                    if (rankscore_v[i] == stats.RankingScore_v[j]){

                        if (i == 1 ){
                            number_listv[i]= 0;
                        }

                        else if (i == 2){
                            number_listv[i] = 2;
                        }
                        else{
                            number_listv[i] = j;  
                        }
                        
                        
                        
                        
                        
                    }
                    
                }
            }


            System.out.println(stats.RankingScore_h[0]);
            System.out.println(stats.RankingScore_h[1]);
            System.out.println(stats.RankingScore_h[2]);
            System.out.println(stats.RankingScore_h[3]);
            Integer[] rankscore_h = new Integer[stats.RankingScore_h.length];
            for (int i =0 ; i<4;i++){
                rankscore_h[i] = stats.RankingScore_h[i];
            }
            Arrays.sort(rankscore_h,Collections.reverseOrder());

           
           
            Integer[] number_listh = new Integer[4];


            for (int i = 0 ; i<4 ; i++){
                
                
                for (int j = 0 ; j<4 ; j++){
                    
                    if (rankscore_h[i] == stats.RankingScore_h[j]){
                       
                        number_listh[i] = j;
                        
                        
                    }
                    
                }
            }

    
        
            for (int i = 0 ; i<4 ; i++){
                int nm = number_listf[i];
                Football.write(i+1+"."+"    "+TeamList.f_teams[nm]+"   "+(stats.Matches_won_f[nm]+stats.Matches_draw_f[nm]+stats.Matches_lost_f[nm])+"  "+stats.Matches_won_f[nm]+"   "+
                stats.Matches_draw_f[nm]+"  " + stats.Matches_lost_f[nm]+"  " +stats.Score_list_f[nm]+":"+stats.Negative_score_f[nm]+"  "+stats.RankingScore_f[nm]+"\n" );
            }
            Football.close();

            for (int i = 0 ; i<4 ; i++){
                int nm = number_listb[i];
                basketball.write(i+1+"."+"    "+TeamList.b_teams[nm]+"   "+(stats.Matches_won_b[nm]+stats.Matches_draw_b[nm]+stats.Matches_lost_b[nm])+"  "+stats.Matches_won_b[nm]+"   "+
                stats.Matches_draw_b[nm]+"  " + stats.Matches_lost_b[nm]+"  " +stats.Score_list_b[nm]+":"+stats.Negative_score_b[nm]+"  "+stats.RankingScore_b[nm]+"\n" );
            }
            basketball.close();

            for (int i = 0 ; i<4 ; i++){
                int nm = number_listv[i];
                Volleyball.write(i+1+"."+"    "+TeamList.v_teams[nm]+"   "+(stats.Matches_won_v[nm]+stats.Matches_draw_v[nm]+stats.Matches_lost_v[nm])+"  "+stats.Matches_won_v[nm]+"   "+
                stats.Matches_draw_v[nm]+"  " + stats.Matches_lost_v[nm]+"  " +stats.Score_list_v[nm]+":"+stats.Negative_score_v[nm]+"  "+stats.RankingScore_v[nm]+"\n" );
            }
            Volleyball.close();
            
            for (int i = 0 ; i<4 ; i++){
                int nm = number_listh[i];
                Handball.write(i+1+"."+"    "+TeamList.h_teams[nm]+"   "+(stats.Matches_won_h[nm]+stats.Matches_draw_f[nm]+stats.Matches_lost_h[nm])+"  "+stats.Matches_won_h[nm]+"   "+
                stats.Matches_draw_h[nm]+"  " + stats.Matches_lost_h[nm]+"  " +stats.Score_list_h[nm]+":"+stats.Negative_score_h[nm]+"  "+stats.RankingScore_h[nm]+"\n" );
            }
            Handball.close();
        }}




    

