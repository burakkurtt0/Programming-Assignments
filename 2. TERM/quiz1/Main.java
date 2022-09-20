import java.io.*;
import java.util.Scanner;
public class Main {
    public static void main(String[] args)throws IOException{

        FileInputStream in = null;
        FileWriter out = null;

        in = new FileInputStream(args[0]);
        out = new FileWriter(args[1]);

        Scanner sc = new Scanner(in);
        
        String number_String = "";
        boolean armstrongbool = false;
        boolean ascending_order = false;
        boolean descending_order= false;

        while (sc.hasNextLine()){
            String line = sc.nextLine();

            if (line.equals("Calculating Armstrong numbers")){
                armstrongbool = true;   
                out.write(line + "\n");
                continue;
            }

            if (armstrongbool){
                operations.CalculateArmstrongNumbers(Integer.parseInt(line),out);
                armstrongbool = false;
                continue;
            }

            if (line.equals("Ascending order sorting")){
                ascending_order = true;
                number_String = "";
                out.write(line + "\n");
                continue;
            }


            if (line.equals("-1")){
                ascending_order = false;
                descending_order = false;
                continue;
            }

            if (ascending_order){
                number_String += line;
                operations.AscendingOrder(number_String,out);
                number_String += " "; 
                out.write("\n");
                continue;
            }

            if (line.equals("Descending order sorting")){
                descending_order=true;
                number_String = "";   
                out.write(line +"\n");
                continue;
            }
            
            if (descending_order){
                number_String +=line;
                operations.DescendingOrder(number_String,out);
                number_String += " ";
                out.write("\n");
                continue;
            }

            if (line.equals("Exit")){
                out.write("Terminated..");
                out.close();
                sc.close();
                break;
                
            }
        }
        
        }
        
    }

