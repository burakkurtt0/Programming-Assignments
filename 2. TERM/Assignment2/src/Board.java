import java.util.LinkedList;

public class Board {
    public static LinkedList<Square> createBoardList(){


        
        Square freeparking = new FreeParking();
        Square go = new Go();
        Square goJail = new GoJail();
        Square incomeTax = new IncomeTax();
        Square jail = new Jail();
        Square SuperTax = new SuperTax();
        Square com1 = new CommunityCards(3);
        Square com2 = new CommunityCards(18);
        Square com3 = new CommunityCards(34);
        Square chance1 = new Chance(8);
        Square chance2 = new Chance(23);
        Square chance3 = new Chance(37);

        PropertyJsonReader propertyreader = new PropertyJsonReader();
        LinkedList<Square> landsList = propertyreader.getSquares();
        landsList.add(freeparking);
        landsList.add(goJail);
        landsList.add(incomeTax);
        landsList.add(jail);
        landsList.add(go);
        landsList.add(SuperTax);
        landsList.add(com1);
        landsList.add(com2);
        landsList.add(com3);
        landsList.add(chance1);
        landsList.add(chance2);
        landsList.add(chance3);

        return landsList;
        }
        
        public static Square[] createBoard(Square[] SqrArray,LinkedList<Square> list){
            for (int i = 1; i<list.size()+1;i++){
                for (Square sqr : list){

                    if (i == sqr.getId()){
                        SqrArray[i-1] = sqr;
                    }
                }
            }

            return SqrArray;
        }

        
    
}
