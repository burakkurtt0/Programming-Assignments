import java.util.*;

public abstract class MatchMethods {
    
    


    public static Shapes FindShape (List<ArrayList<String>> map, int c1 , int c2)throws NotMatchException {
        
        String shape = map.get(c1).get(c2);
        Shapes  s = null;

        switch(shape){
            case "D" : s = new D(shape); break;
            case "T" : s = new T(shape); break;
            case "S" : s = new S(shape); break;
            case "W" : s = new W(shape); break;
            case "/" : s = new Slash(shape); break;
            case "+" : s = new Plus(shape); break;
            case "-" : s = new Minus(shape); break;
            case "\\": s = new ReverseSlash(shape); break;
            case "|" : s = new Line(shape); break;
            //New shape can be added.
        }

        if (s != null){
            return s;
        }
        else{
            throw new NotMatchException();
        }
        }
    



    

    public static int DeleteHorizontal(List<ArrayList<String>> map, int c1, int c2, Shapes  s, Player player) throws NotMatchException{
    int deleteDirection = 9999;
    Shapes  s1 = null;
    Shapes  s2 = null;
    deleteDirection=lookHorizontal(map, c1, c2, s.getShape());
    

            switch(deleteDirection){
                case 4:
                s1 = FindShape(map, c1, c2-1);
                s2 = FindShape(map, c1, c2-2);
                map.get(c1).set(c2, " ");
                map.get(c1).set(c2-1, " ");
                map.get(c1).set(c2-2," ");
                player.setTemp_score(s.getScore()+ s1.getScore() + s2.getScore());
                player.setScore(player.getScore() + player.getTemp_score());
                return 1;
                

                case 6:
                s1 = FindShape(map, c1, c2+1);
                s2 = FindShape(map, c1, c2+2);
                map.get(c1).set(c2, " ");
                map.get(c1).set(c2+1, " ");
                map.get(c1).set(c2+2," ");
                player.setTemp_score(s.getScore()+ s1.getScore() + s2.getScore());
                player.setScore(player.getScore() + player.getTemp_score());
                return 1;
            }
            
            return 9999;
    }

    public static int DeleteVertical(List<ArrayList<String>> map, int c1, int c2, Shapes  s, Player player) throws NotMatchException{
        
        int deleteDirection = 9999;
        Shapes  s1 = null;
        Shapes  s2 = null;
        deleteDirection = lookVertical(map, c1, c2, s.getShape());
      
            switch(deleteDirection){
                case 2:
                s1 = FindShape(map, c1-1, c2);
                s2 = FindShape(map, c1-2, c2); 
                map.get(c1).set(c2, " ");
                map.get(c1-1).set(c2, " ");
                map.get(c1-2).set(c2," ");
                player.setTemp_score(s.getScore()+ s1.getScore() + s2.getScore());
                player.setScore(player.getScore() + player.getTemp_score());
                return 2;
                

                case 8:
                s1 = FindShape(map, c1+1, c2);
                s2 = FindShape(map, c1+2, c2);
                map.get(c1).set(c2, " ");
                map.get(c1+1).set(c2, " ");
                map.get(c1+2).set(c2," ");
                player.setTemp_score(s.getScore()+ s1.getScore() + s2.getScore());
                player.setScore(player.getScore() + player.getTemp_score());
                return 2;

            }
            return 9999;
    }

    public static int DeleteDiagonal(List<ArrayList<String>> map, int c1, int c2, Shapes  s, Player player) throws NotMatchException{
        int deleteDirection = 9999;
        Shapes  s1 = null;
        Shapes  s2 = null;

        deleteDirection = lookDiagonal(map, c1, c2, s.getShape());
            
            switch(deleteDirection){
                case 1:
                s1 = FindShape(map, c1-1, c2-1);
                s2 = FindShape(map, c1-2, c2-2);
                map.get(c1).set(c2, " ");
                map.get(c1-1).set(c2-1, " ");
                map.get(c1-2).set(c2-2, " ");
                player.setTemp_score(s.getScore()+ s1.getScore() + s2.getScore());
                player.setScore(player.getScore() + player.getTemp_score());
                return 3;

                case 9:
                s1 = FindShape(map, c1+1, c2+1);
                s2 = FindShape(map, c1+2, c2+2);
                map.get(c1).set(c2, " ");
                map.get(c1+1).set(c2+1, " ");
                map.get(c1+2).set(c2+2, " ");
                player.setTemp_score(s.getScore()+ s1.getScore() + s2.getScore());
                player.setScore(player.getScore() + player.getTemp_score());
                return 3;
                
                case 3:
                s1 = FindShape(map, c1-1, c2+1);
                s2 = FindShape(map, c1-2, c2+2);
                map.get(c1).set(c2, " ");
                map.get(c1-1).set(c2+1, " ");
                map.get(c1-2).set(c2+2, " ");
                player.setTemp_score(s.getScore()+ s1.getScore() + s2.getScore());
                player.setScore(player.getScore() + player.getTemp_score());
                return 3;

                case 7:
                s1 = FindShape(map, c1+1, c2-1);
                s2 = FindShape(map, c1+2, c2-2);
                map.get(c1).set(c2, " ");
                map.get(c1+1).set(c2-1, " ");
                map.get(c1+2).set(c2-2, " ");
                player.setTemp_score(s.getScore()+ s1.getScore() + s2.getScore());
                player.setScore(player.getScore() + player.getTemp_score());
                return 3;
            }
            return 9999;
    }

    public static void DeleteJewels(List<ArrayList<String>> map, int c1, int c2,  Player player) throws NotMatchException{
        Shapes s = null;
        try{
            s = FindShape(map, c1, c2);
        }
        catch (IndexOutOfBoundsException e){
            throw new NotMatchException();
        }
    
        
    if (s.getMatchingDirection().equals("Horizontal")){
         DeleteHorizontal(map, c1, c2, s, player);
        
    }

    else if(s.getMatchingDirection().equals("Vertical")){
        DeleteVertical(map, c1, c2, s, player);
        
    }

    else if(s.getMatchingDirection().equals("Diagonal")){
        DeleteDiagonal(map, c1, c2, s, player);
        
    }

    else if (s.getMatchingDirection().equals("Anywhere")){{
    }
        int del = 9999;
        del = DeleteVertical(map, c1, c2, s, player);
        
        if (del != 2){
            del = DeleteHorizontal(map, c1, c2, s, player);
            if (del!= 1){
                
                DeleteDiagonal(map, c1, c2, s, player);
               
            }
        }
        
    }
    

    else if (s.getMatchingDirection().equals("Right Diagonal")){
         DeleteDiagonal(map, c1, c2, s, player);
        
    }

    else if (s.getMatchingDirection().equals("Horizontal and Vertical")){
        int del = 9999;
        del = DeleteHorizontal(map, c1, c2, s, player);
        
        if (del!=1){
            DeleteVertical(map, c1, c2, s, player);
            
        }
        
    }


    else if (s.getMatchingDirection().equals("Left diagonal")){
        DeleteDiagonal(map, c1, c2, s, player);
        
    }

    }
        

        

        
        


      



    public static int lookVertical(List<ArrayList<String>> map, int c1 , int c2 , String shape) throws NotMatchException{
        Shapes  s = FindShape(map, c1, c2);

        try{
            Shapes s1 = FindShape(map, c1-1, c2);
            Shapes s2 = FindShape(map, c1-2, c2);


            if (s.isMath()){
                if ((s1.isMath()) && (s2.isMath())){
                    return 2;
                }
            }


             if (s.getMatchingDirection().equals("Anywhere")){
                if (s1.getMatchingDirection().equals("Vertical") && s2.getMatchingDirection().equals("Vertical")){
                    return 2;
                }
                else if (s1.getMatchingDirection().equals("Anywhere")){
                    return 2;
                }

                else if (s1.getMatchingDirection().equals("Vertical")&& s2.getMatchingDirection().equals("Anywhere")){
                    return 2;
                }
                
            }
            
            
            
            else if ((s1.getShape().equals(shape)) && (s2.getShape().equals(shape))){

                return 2;
            }
        }

        catch (IndexOutOfBoundsException e){
            
        }


        try{
           
            Shapes s1 = FindShape(map, c1+1, c2);
            Shapes s2 = FindShape(map, c1+2, c2);
            if (s.isMath()){
                if ((s1.isMath()) && (s2.isMath())){
                    return 8;
                }
            }

             if (s.getMatchingDirection().equals("Anywhere")){
                if (s1.getMatchingDirection().equals("Vertical") && s2.getMatchingDirection().equals("Vertical")){
                    return 8;
                }
                else if (s1.getMatchingDirection().equals("Anywhere") ){
                    return 8;
                }

                else if (s1.getMatchingDirection().equals("Vertical")&& s2.getMatchingDirection().equals("Anywhere")){
                    return 8;
                }
                
            }

            else if ((s1.getShape().equals(shape)) && (s2.getShape().equals(shape))){
                
                return 8;
            }
        }
        catch (IndexOutOfBoundsException e){
            
        }
        return 9999;
    }



   
    public static int lookHorizontal(List<ArrayList<String>> map, int c1 , int c2 , String shape) throws NotMatchException{
        Shapes  s = FindShape(map, c1, c2);
        
        try{
            Shapes s1 = FindShape(map, c1, c2-1);
            Shapes s2 = FindShape(map, c1, c2-2);
            

            if (s.isMath()){
               
                if ((s1.isMath()) && (s2.isMath())){
                    return 4;
                }
            }

            if (s.getMatchingDirection().equals("Anywhere")){
                
                
                if (s1.getMatchingDirection().equals("Horizontal") && s2.getMatchingDirection().equals("Horizontal")){
                    return 4;
                }
                else if (s1.getMatchingDirection().equals("Anywhere")){
                    return 4;
                }

                else if (s1.getMatchingDirection().equals("Horizontal")&& s2.getMatchingDirection().equals("Anywhere")){
                    return 4;
                }
                
            }

            else if ((s1.getShape().equals(shape)) && (s2.getShape().equals(shape))){
                

                return 4;
            }
        }

        catch (IndexOutOfBoundsException e){
            
        }


        try{
            Shapes s1 = FindShape(map, c1, c2+1);
            Shapes s2 = FindShape(map, c1, c2+2);

            if (s.isMath()){
                if ((s1.isMath() ) && (s2.isMath())){
                    return 6;
                }
            }
             if (s.getMatchingDirection().equals("Anywhere")){
                if (s1.getMatchingDirection().equals("Horizontal") && s2.getMatchingDirection().equals("Horizontal")){
                    return 6;
                }
                else if (s1.getMatchingDirection().equals("Anywhere") ){
                    return 6;
                }

                else if (s1.getMatchingDirection().equals("Horizontal")&& s2.getMatchingDirection().equals("Anywhere")){
                    return 6;
                }
                
                
            }

            else if ((s1.getShape().equals(shape)) && (s2.getShape().equals(shape))){
                
                return 6;
            }
        }
        catch (IndexOutOfBoundsException e){
            
        }
        return 9999;
    }



    public static int lookDiagonal(List<ArrayList<String>> map, int c1 , int c2 , String shape)throws NotMatchException{
        Shapes s = FindShape(map, c1, c2);
        try{
            Shapes s1 = FindShape(map, c1-1, c2-1);
            Shapes s2 = FindShape(map, c1-2, c2-2);

            if (s.isMath() && s.getMatchingDirection().equals("Left Diagonal")){
                if ((s1.isMath()) && (s2.isMath())){
                    return 1;
                }
            }

             if (s.getMatchingDirection().equals("Anywhere")){
                if (s1.getMatchingDirection().equals("Diagonal") && s2.getMatchingDirection().equals("Diagonal")){
                    return 1;
                }
                else if (s1.getMatchingDirection().equals("Anywhere")){
                    return 1;
                }

                else if (s1.getMatchingDirection().equals("Diagonal")&& s2.getMatchingDirection().equals("Anywhere")){
                    return 1;
                }
                
            }
            else if ((s1.getShape().equals(shape)) && (s2.getShape().equals(shape))){

                return 1;
            }
        }

        catch (IndexOutOfBoundsException e){
            
        }


        try{
            Shapes s1 = FindShape(map, c1+1, c2+1);
            Shapes s2 = FindShape(map, c1+2, c2+2);
            
            
            if (s.isMath() && s.getMatchingDirection().equals("Left Diagonal")){
                if ((s1.isMath() ) && (s2.isMath())){
                    return 9;
                }
            }
             if (s.getMatchingDirection().equals("Anywhere")){
                if (s1.getMatchingDirection().equals("Diagonal") && s2.getMatchingDirection().equals("Diagonal")){
                    return 9;
                }
                else if (s1.getMatchingDirection().equals("Anywhere")){
                    return 9;
                }

                else if (s1.getMatchingDirection().equals("Diagonal")&& s2.getMatchingDirection().equals("Anywhere")){
                    return 9;
                }
                
            }
            else if ((s1.getShape().equals(shape)) && (s2.getShape().equals(shape))){
                
                return 9;
            }
        }
        catch (IndexOutOfBoundsException e){
            
        }


        try{
            Shapes s1 = FindShape(map, c1-1, c2+1);
            Shapes s2 = FindShape(map, c1-2, c2+2);
            
            if (s.isMath() && s.getMatchingDirection().equals("Right Diagonal")){
                if ((s1.isMath() ) && (s2.isMath() )){
                    return 3;
                }
            }

             if (s.getMatchingDirection().equals("Anywhere")){
                if (s1.getMatchingDirection().equals("Diagonal") && s2.getMatchingDirection().equals("Diagonal")){
                    return 3;
                }
                else if (s1.getMatchingDirection().equals("Anywhere") ){
                    return 3;
                }

                else if (s1.getMatchingDirection().equals("Diagonal")&& s2.getMatchingDirection().equals("Anywhere")){
                    return 3;
                }
               
            }
            if ((s1.getShape().equals(shape)) && (s2.getShape().equals(shape))){
                
                return 3;
            }
        }
        catch (IndexOutOfBoundsException e){
            
        }

        try{
            Shapes s1 = FindShape(map, c1+1, c2-1);
            Shapes s2 = FindShape(map, c1+2, c2-2);
            
            if (s.isMath() && s.getMatchingDirection().equals("Right Diagonal")){
                if ((s1.isMath() ) && (s2.isMath())){
                    return 7;
                }
            }
             if (s.getMatchingDirection().equals("Anywhere")){
                if (s1.getMatchingDirection().equals("Diagonal") && s2.getMatchingDirection().equals("Diagonal")){
                    return 7;
                }
                else if (s1.getMatchingDirection().equals("Anywhere") ){
                    return 7;
                }

                else if (s1.getMatchingDirection().equals("Diagonal")&& s2.getMatchingDirection().equals("Anywhere")){
                    return 7;
                }
                
            }
            if ((s1.getShape().equals(shape)) && (s2.getShape().equals(shape))){
                
                return 7;
            }
        }
        catch(IndexOutOfBoundsException e ){
            
        }
        return 9999;
    }








public static void DropElements(List<ArrayList<String>> map){
    boolean needDrop = true;
    while (needDrop){
    needDrop = false;
    

    for (int i = map.size()-1 ; i>=0;i--){
        for (int j = 0; j<map.get(0).size() ; j++){
            if (i==0){
                continue;
            }

            else{
                try{
                    if (map.get(i).get(j).equals(" ") && !(map.get(i-1).get(j).equals(" "))){
                        needDrop = true;
                        map.get(i).set(j, map.get(i-1).get(j));
                        map.get(i-1).set(j, " "); 
                    }
                }
                catch (IndexOutOfBoundsException e){
                    continue;
                }
            }
        }
    }
}
}
}

class NotMatchException extends Exception{
    NotMatchException(){}
}