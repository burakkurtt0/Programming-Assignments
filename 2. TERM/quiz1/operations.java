import java.util.Arrays;
import java.util.Collections;
import java.io.*;

public class operations {

    public static void CalculateArmstrongNumbers(int max_number, FileWriter out) throws IOException{
        for (int i = 0;i<max_number;i++){
            String strnumber;
            strnumber = String.valueOf(i);
            if (strnumber.length()==1){continue;}
            int armstrong = 0;
            for (int j = 0 ; j<strnumber.length() ; j++ ){
                armstrong += Math.pow(Integer.parseInt(strnumber.substring(j,j+1)),strnumber.length());

            }
            if (armstrong == i){
                
                out.write(armstrong + " ");
            }
        }
        out.write(" ");
        out.write("\n");
    }


    public static void AscendingOrder(String  numbers,FileWriter out)throws IOException{
        String [] numberarray = numbers.split(" ");
        
        
        Integer [] intNumberArray = new Integer[numberarray.length];
        int i =0;
        for (String number:numberarray){
            int tempnumber= Integer.parseInt(number);
            intNumberArray[i] = tempnumber;
            i++;
        }

        Arrays.sort(intNumberArray);
        for (i = 0 ; i<intNumberArray.length;i++){
            out.write(intNumberArray[i]+" ");
            
        }
    }

    public static void DescendingOrder(String  numbers, FileWriter out) throws IOException
    {
        String [] numberarray = numbers.split(" ");
            
            
        Integer [] intNumberArray = new Integer[numberarray.length];
        int i =0;
        for (String number:numberarray){
            int tempnumber= Integer.parseInt(number);
            intNumberArray[i] = tempnumber;
            i++;
        }
    
        Arrays.sort(intNumberArray,Collections.reverseOrder());
        for (i = 0 ; i<intNumberArray.length;i++){
            
            out.write(intNumberArray[i] + " ");
        
    }
}}