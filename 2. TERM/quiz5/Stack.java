import java.io.FileWriter;
import java.io.IOException;

public class Stack {
    private Node firstNode;
    private Node lastNode;

    public Stack(){
        firstNode = lastNode = null;
    }

    public boolean isEmpty(){
        return firstNode == null;
    }

    public void push(int data){
        if (isEmpty()){
            firstNode = lastNode = new Node(data);
        }

        else{
            lastNode.nextNode = new Node(data);
            lastNode = lastNode.nextNode;

        }
    }


    public void pop(){
        if (firstNode == lastNode){
            firstNode = lastNode = null;
        }

        else{
            Node current = firstNode;
            while(current.nextNode != lastNode){
                current = current.nextNode;
            }
            lastNode = current;
            current.nextNode= null;
        }
    }

    public Integer Top(){
        Integer data = lastNode.data;
        return data;
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

    public boolean isFull(){
        return  (Size() == 50) ? true : false;
    }

    public void print() throws IOException{
        FileWriter out = new FileWriter("output.txt",true);
        while (firstNode != null){
            out.write(Top().toString());
            pop();
            
        }
        out.write("\n");
        out.close();
        
        
}}
