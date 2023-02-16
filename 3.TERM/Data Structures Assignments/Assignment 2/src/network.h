/*!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

merge_Two_apartments fonksiyonu DEV sunucusunda çalışmıyor. Doğru apartmanı bulmasına rağmen if'e girmiyor 

ve segmentation fault döndürüyor. Kendi bilgisayarımda output alabiliyorken DEV'de kodu bir türlü çalıştıramadım.

Merge apartments fonksiyonunda hatanın sebebinden daha detaylı bahsettim.

!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! */


#include <iostream>
#include <vector>
#include <sstream>
#include <fstream>
using namespace std;

struct FlatN
{
    FlatN *prev = nullptr;
    FlatN *next = nullptr;
    int bandwidth;
    int ID;
    bool isEmpty = false;
};


class Flat
{
public:
    FlatN *head = nullptr;
    FlatN *tail = nullptr;

public:
    Flat()
    {
        head = tail = nullptr;
    }
};

struct Apartment
{

    Apartment *next = nullptr;
    string name;
    int max_bandwidth;
    Flat *flat = nullptr;
};

class Street{
    public:
    int size;
    Apartment* head;
    Apartment* tail;
    Street(){
        size = 0;
        head = tail = NULL;
    }
    int CalculateCurrentBand(Apartment &ap); 
    void add_apartment(Street &street, int indx, int max_band, string apart_name); 
    void add_flat(Street &street, string apt_name, int indx, int flat_id, int bandwidth); 
    Street remove_apartment(Street &street, string apartName,string filename); 
    void make_flat_empty(Street &street, string apt_name, int id); 
    int find_sum_of_max_bandwidth(Street &street,string output); 
    Street merge_two_apartments(Street &street, string name1, string name2,string filename); 
    FlatN *removeFlat(Street &street, Flat *flat, FlatN *removedflat); // Removes flat from given apartment. Only sets its next and prev pointers to null and not deletes its pointer.
    //  This function is only used in relocate_flats function so I just moved it to another apartment without deleting any pointer.

    void relocate_flats_to_same_apartments(Street &street, string apt_Name, int id, vector<int> id_list);
    void list_apartments(Street street,string output);
    
    
    int FindApartment(Street street, string ApartName); // Finds apartment's index number from its name. I put that in a function because it is used a lot.
    
    
    // Functions related with input processing.    
    int FindIndexForAddApartment(Street street, string location); // Location value keeps "head" , "before" and "after" values which are used in add_apartmen line. Finds index of new apartment to add.
    static vector<int> GetListRelocate(string line); // Transforms string list "[1,2,3,4]" to int vector.
    static vector<int> PrepareAdd_Flat_Arguments(vector<string> line); // returns id,bandwidth and index of new flat in int vector.
    static vector<string> splitLine(string line); //Splits the given lane (with \t character) and puts all elements in string vector.
    void main_func(Street street, string filename, string outputname);
};