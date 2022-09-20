
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Queue {
    private Node firstNode;
    private Node lastNode;

    Queue(){
        firstNode = null;
        lastNode = null;
        firstNode = lastNode;
    }

    public int Size(){
        int size = 1;
        Node current = firstNode;
        while (current != lastNode){
            
            size++;
            current = current.nextNode;
        }
        return size;
    }

    public void enqueueBack(int data){

        if (firstNode == null){
            firstNode = lastNode = new Node(data);
        }

        else{
            
            lastNode = lastNode.nextNode= new Node(data);
            
        }
    }

    public void enqueueFront(int data){

        if (firstNode == null){
            firstNode = new Node(data);
            lastNode = firstNode;
            
        }

        else{
            firstNode = new Node(data,firstNode);
            

        }
    }

    public void enqueueMiddle(int data){
        int half_size = Size()/2;
        int i = 1;
        Node current = firstNode;
        ArrayList<Integer> firstelements = new ArrayList<>();
        if (half_size%2 ==0){
            while(i<=half_size){
                
                firstelements.add(current.data);
                current = current.nextNode;
                i++;
            }
            firstNode = current = new Node(data, current);
            
            for (int j = firstelements.size()-1;j>=0;j--){
                
                enqueueFront(firstelements.get(j));
            }
        }

        else if (half_size%2 !=0){
            while(i<=half_size){
                
                firstelements.add(current.data);
                current = current.nextNode;
                i++;
            }
            firstNode = current = new Node(data, current);
            
            for (int j = firstelements.size()-1;j>=0;j--){
                
                enqueueFront(firstelements.get(j));
            }
        }

    }


    public int dequeueBack(){
        if (firstNode == null){
            return -1;
        }

        else if (Size() == 1){
            
            Integer data = firstNode.data;
            firstNode = lastNode = null;
            return data;
        }
        else{
            int removeitem = lastNode.data;

            Node current = firstNode;
            while (current.nextNode!= lastNode){
                current = current.nextNode;
            }

            lastNode = current;
            current.nextNode = null;


            return removeitem;

        }
        
    } 

    public int dequeueMiddle(){
        
        if (firstNode ==null){
            return -1;
        }
        else{

            if (Size()>3){
                
            int half_size = Size()/2;
            int i = 1;
            Node current = firstNode;
            ArrayList<Integer> firstelements = new ArrayList<>();
        if (half_size%2 ==0){
            while(i<=half_size){
                
                firstelements.add(current.data);
                current = current.nextNode;
                i++;
            }
            
            int removeitem = current.data;
            firstNode = current.nextNode;
            
            for (int j = firstelements.size()-1;j>=0;j--){
                
                enqueueFront(firstelements.get(j));}
            
            return removeitem;
        }

        else if (half_size%2 !=0){
            while(i<half_size){
                
                firstelements.add(current.data);
                current = current.nextNode;
                i++;
            }
            int removeitem = current.data;
            firstNode = current.nextNode;
            
            for (int j = firstelements.size()-1;j>=0;j--){
                
                enqueueFront(firstelements.get(j));
            }
            return removeitem;
        }
        else{
            return 0;
        }

    }
    else{
       
        int data = firstNode.data;
        
        if (Size() == 3){
            Node current = firstNode.nextNode;

            dequeueFront();
            dequeueFront();
            enqueueFront(data);
            return current.data;

        }
        else if (Size() ==2){
            dequeueFront();
            return firstNode.data;
        }
        else{
            dequeueFront();
            return firstNode.data;
        }
    }
}
        
    }


    public int dequeueFront(){
        if (firstNode ==null){
            return -1;
        }
        else{
            int removeitem = firstNode.data;

            firstNode = firstNode.nextNode;

            return removeitem;
        }

        
    }



    public void print()throws IOException{
        FileWriter out = new FileWriter("output.txt",true);
        if (firstNode == null){
            out.write("[] \n");
        }
        else{
        Node current = firstNode;
        out.write("[");
        while(current!=lastNode.nextNode){
            if (current == lastNode){
                out.write(current.data.toString());
            }
            else{
                out.write(current.data.toString() + ",");}
            current = current.nextNode;
        }
        out.write("]\n");
       
    } out.close();
}
}
