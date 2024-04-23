import java.io.*;
import java.util.Scanner;
public class ReadData {
    public static int[][] GetData(String fileName) {
        try{

        Scanner sc = new Scanner(new File(fileName));
        sc.nextLine();
        int[] arr_500 = new int[500];
        int[] arr_1k = new int[1000];
        int[] arr_2k = new int[2000];
        int[] arr_4k = new int[4000];
        int[] arr_8k = new int[8000];
        int[] arr_16k = new int[16000];
        int[] arr_32k = new int[32000];
        int[] arr_64k = new int[64000];
        int[] arr_128k = new int[128000];
        int[] arr_250k = new int[250000];
        
        int i = 0;
        while(sc.hasNext()){

            String[] line = sc.nextLine().split(",");
            int number = Integer.parseInt(line[6]);
            if (i < 500){ arr_500[i] = number;}
            if (i < 1000){ arr_1k[i] = number;}
            if (i < 2000){ arr_2k[i] = number;}
            if (i < 4000){ arr_4k[i] = number;}
            if (i < 8000){ arr_8k[i] = number;}
            if (i < 16000){ arr_16k[i] = number;}
            if (i < 32000){ arr_32k[i] = number;}
            if (i < 64000){ arr_64k[i] = number;}
            if (i < 128000){ arr_128k[i] = number;}
            if (i < 250000){ arr_250k[i] = number;}
            i++;
            if(i==25000){
                break;
            }
            
        }
        sc.close();
        int[][] arrays = {arr_500, arr_1k, arr_2k,arr_4k,arr_8k,arr_16k,arr_32k,arr_64k,arr_128k,arr_250k};
        return arrays;}
        catch(Exception e){
            int[][] arrays = new int[1][];
            System.out.println("Wrong file input");
            return arrays;
        }

    }
}
