import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class Main{
public static void main(String[] args) throws IOException{
    
    Stack stack = new Stack();
    Queue que = new Queue();
    
    
    
    
    FileReader in = new FileReader(args[0]);
    
    Scanner sc = new Scanner(in);

    while(sc.hasNextLine()){
        String line = sc.nextLine();
        if (line.startsWith("e")){
            String[] lineArray = line.split(" ");
            String operation = lineArray[0];
            Integer number = Integer.parseInt(lineArray[1]);
            switch(operation){
                case "enqueFront":
                que.enqueueFront(number);
                que.print();
                break;

                case "enqueBack":
                que.enqueueBack(number);
                que.print();
                break;

                case "enqueMiddle":
                que.enqueueMiddle(number);
                que.print();
                break;
            }   
        }

        else if (line.startsWith("d")){
            switch(line){
                case "dequeFront":
                que.dequeueFront();
                que.print();
                 break;

                case "dequeMiddle":
                que.dequeueMiddle();  que.print(); break;

                case "dequeBack":
                que.dequeueBack();  que.print(); break;
            }
            
        }




        else{
            Integer number = Integer.parseInt(line);
            while (number>=8){
                int rem = number%8;
                number = number/8;
                stack.push(rem);
            }
            stack.push(number);
            stack.print();
            
            
        }
    }
    sc.close();
    

    

   

}
}