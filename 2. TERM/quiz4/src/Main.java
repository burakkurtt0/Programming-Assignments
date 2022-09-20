import java.io.IOException;
import java.util.*;
import java.io.*;
public class Main{

 


    public static void StoreHashMap(String file) throws IOException{
        FileReader in = new FileReader(file);
        Scanner scn = new Scanner(in);
        FileWriter out = new FileWriter("contactsHashMap.txt");
        
        HashMap<String , Contact> contactMap = new HashMap<>();

        while (scn.hasNextLine()){
            Contact cnt = new Contact();
            String line = scn.nextLine();
            String[] lineArray = line.split(" ");
            cnt.setPhoneNumber(lineArray[0]);
            cnt.setFirstName(lineArray[1]);
            cnt.setLastName(lineArray[2]);
            cnt.setSocialSecurityNumber(lineArray[3]);
            contactMap.put(cnt.getPhoneNumber(), cnt);
        }
        scn.close();

        for (String number: contactMap.keySet()){
            out.write(contactMap.get(number).getPhoneNumber()+" "+contactMap.get(number).getFirstName()+" "+contactMap.get(number).getLastName()+" "+contactMap.get(number).getSocialSecurityNumber()+"\n");
        }
        out.close();
    }


    public static void StoreTreeSet(String file) throws IOException{
        FileReader in = new FileReader(file);
        Scanner scn = new Scanner(in);
        FileWriter out = new FileWriter("contactsTreeSet.txt");
        
        Set<Contact> contactSet = new HashSet<>();
        while (scn.hasNextLine()){
            Contact cnt = new Contact();
            String line = scn.nextLine();
            String[] lineArray = line.split(" ");
            cnt.setPhoneNumber(lineArray[0]);
            cnt.setFirstName(lineArray[1]);
            cnt.setLastName(lineArray[2]);
            cnt.setSocialSecurityNumber(lineArray[3]);
            contactSet.add(cnt);
        }
        scn.close();
        Set<Contact> contactTreeSet = new TreeSet<>(new Comparator<Contact>() {
            public int compare (Contact c1, Contact c2){
                return c1.getPhoneNumber().compareTo(c2.getPhoneNumber());
            }
            
        });
        contactTreeSet.addAll(contactSet);
        
        Iterator<Contact> cntiterator= contactTreeSet.iterator();
        while (cntiterator.hasNext()){
            Contact cnt = cntiterator.next();
            out.write(cnt.getPhoneNumber()+" "+cnt.getFirstName()+" "+cnt.getLastName()+" "+cnt.getSocialSecurityNumber()+"\n");
            
        }
        out.close();
    }


    public static void StoreTreeSetOrderByLastName(String file) throws IOException{ 
        FileReader in = new FileReader(file);
        Scanner scn = new Scanner(in);
        FileWriter out = new FileWriter("contactsTreeSetOrderByLastName.txt");
        
        Set<Contact> contactSet = new HashSet<>();
        while (scn.hasNextLine()){
            Contact cnt = new Contact();
            String line = scn.nextLine();
            String[] lineArray = line.split(" ");
            cnt.setPhoneNumber(lineArray[0]);
            cnt.setFirstName(lineArray[1]);
            cnt.setLastName(lineArray[2]);
            cnt.setSocialSecurityNumber(lineArray[3]);
            contactSet.add(cnt);
        }
        scn.close();
        Set<Contact> contactTreeSet = new TreeSet<>(new LastNameComparator());
        contactTreeSet.addAll(contactSet);
        
        Iterator<Contact> cntiterator= contactTreeSet.iterator();
        while (cntiterator.hasNext()){
            Contact cnt = cntiterator.next();
            out.write(cnt.getPhoneNumber()+" "+cnt.getFirstName()+" "+cnt.getLastName()+" "+cnt.getSocialSecurityNumber()+"\n");
            
        }
        out.close();
    }
    

    public static void StoreArrayList(String file ) throws IOException{
        FileReader in = new FileReader(file);
        Scanner scn = new Scanner(in);
        FileWriter out = new FileWriter("contactsArrayList.txt");
        FileWriter out2 = new FileWriter("contactsArrayListOrderByLastName.txt");
        ArrayList<Contact> contactArray = new ArrayList<Contact>();
        while (scn.hasNextLine()){
            Contact cnt = new Contact();
            String line = scn.nextLine();
            String[] lineArray = line.split(" ");
            cnt.setPhoneNumber(lineArray[0]);
            cnt.setFirstName(lineArray[1]);
            cnt.setLastName(lineArray[2]);
            cnt.setSocialSecurityNumber(lineArray[3]);
            contactArray.add(cnt);
        }
        scn.close();

        Iterator<Contact> cntiterator = contactArray.iterator();
        while (cntiterator.hasNext()){
            Contact cnt = cntiterator.next();
            out.write(cnt.getPhoneNumber()+" "+cnt.getFirstName()+" "+cnt.getLastName()+" "+cnt.getSocialSecurityNumber()+"\n");
            
        }

        Collections.sort(contactArray , new LastNameComparator());

        Iterator<Contact> cntiteratorr = contactArray.iterator();
        while (cntiteratorr.hasNext()){
            Contact cnt = cntiteratorr.next();
            out2.write(cnt.getPhoneNumber()+" "+cnt.getFirstName()+" "+cnt.getLastName()+" "+cnt.getSocialSecurityNumber()+"\n");
            
        }


        out.close();
        out2.close();

    }


    public static void StoreHashSet(String file)throws IOException{
        FileReader in = new FileReader(file);
        Scanner scn = new Scanner(in);
        FileWriter out = new FileWriter("contactsHashSet.txt");
        HashSet<Contact> contactSet = new HashSet<>();
        while (scn.hasNextLine()){
            Contact cnt = new Contact();
            String line = scn.nextLine();
            String[] lineArray = line.split(" ");
            cnt.setPhoneNumber(lineArray[0]);
            cnt.setFirstName(lineArray[1]);
            cnt.setLastName(lineArray[2]);
            cnt.setSocialSecurityNumber(lineArray[3]);
            contactSet.add(cnt);
        }
        scn.close();

        Iterator<Contact> itr = contactSet.iterator();

        while (itr.hasNext()){
            Contact cnt = itr.next();
            out.write(cnt.getPhoneNumber()+" "+cnt.getFirstName()+" "+cnt.getLastName()+" "+cnt.getSocialSecurityNumber()+"\n");
        }
        out.close();
    }




public static void main(String[] args) throws IOException{

    StoreArrayList(args[0]);
    StoreHashSet(args[0]);
    StoreTreeSetOrderByLastName(args[0]);
    StoreHashMap(args[0]);
    StoreTreeSet(args[0]);

    

    




    

    }
}