
/*!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

merge_Two_apartments fonksiyonu DEV sunucusunda çalışmıyor. Doğru apartmanı bulmasına rağmen if'e girmiyor 

ve segmentation fault döndürüyor. Kendi bilgisayarımda output alabiliyorken DEV'de kodu bir türlü çalıştıramadım.

Merge apartments fonksiyonunda hatanın sebebinden daha detaylı bahsettim.

!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! */
#include <iostream>
#include <vector>
#include <sstream>
#include <fstream>
#include "network.h"

using namespace std;


int Street::CalculateCurrentBand(Apartment &ap)
{
    FlatN *curr = ap.flat->head;
    if (curr == NULL)
    {
        return 0;
    }

    int curr_Band = 0;
    while (curr != NULL)
    {
        curr_Band += curr->bandwidth;
        curr = curr->next;
    }

    return curr_Band;
}

void Street::add_apartment(Street &street, int indx, int max_band, string apart_name)
{

    Apartment *apartNode = new Apartment();
    apartNode->name = apart_name;
    apartNode->flat = new Flat();
    apartNode->max_bandwidth = max_band;

    if (street.head == NULL)
    {

        street.head = apartNode;
        street.tail = apartNode;
        street.head->next = street.tail;
        street.tail->next = street.head;
    }

    else
    {

        int i = 0;
        Apartment *curr = street.head;
        while (i < indx - 1)
        {

            curr = curr->next;
            i++;
        }

        if (indx == 0)
        {
            apartNode->next = curr;
            street.tail->next = apartNode;
            street.head = apartNode;
        }

        else if (curr->next == street.head)
        {
            curr->next = apartNode;
            apartNode->next = street.head;
            street.tail = apartNode;
        }

        else
        {

            apartNode->next = curr->next;
            curr->next = apartNode;
        }
    }
    street.size++;
}

void Street::add_flat(Street &street, string apt_name, int indx, int flat_id, int bandwidth)
{
    Apartment *apt = street.head;
    if (apt->name == apt_name)
    {
    }
    else
    {
        apt = apt->next;
        while (apt != street.head)
        {
            if (apt->name == apt_name)
            {
                break;
            }
            apt = apt->next;
        }
    }

    FlatN *newFlat = new FlatN;
    int curr_Band = Street::CalculateCurrentBand(*apt);

    newFlat->isEmpty = false;
    newFlat->ID = flat_id;
    if ((apt->max_bandwidth - curr_Band) == 0)
    {
        newFlat->isEmpty = true;
        newFlat->bandwidth = 0;
    }

    else if (apt->max_bandwidth - curr_Band < bandwidth)
    {
        newFlat->bandwidth = apt->max_bandwidth - curr_Band;
    }
    else
    {
        newFlat->bandwidth = bandwidth;
    }

    if (apt->flat->head == NULL)
    {

        apt->flat->head = newFlat;
        apt->flat->tail = newFlat;
        return;
    }

    else if (indx == 0)
    {
        newFlat->next = apt->flat->head;
        apt->flat->head->prev = newFlat;
        apt->flat->head = newFlat;
        return;
    }

    else
    {

        int i = 0;
        FlatN *curr = apt->flat->head;

        while (i < indx - 1)
        {
            curr = curr->next;
            i++;
        }

        if (curr == apt->flat->tail)
        {

            curr->next = newFlat;
            newFlat->prev = curr;
            apt->flat->tail = newFlat;
            return;
        }

        else
        {
            FlatN *temp = curr->next;
            newFlat->next = temp;
            temp->prev = newFlat;
            curr->next = newFlat;
            newFlat->prev = curr;
            return;
        }
    }
}

Street Street::remove_apartment(Street &street, string apartName, string filename)
{
    
    int indx = Street::FindApartment(street, apartName);

    if (indx == 0)
    {

        Apartment *apart = street.head;
        street.tail->next = apart->next;
        street.head = apart->next;
        delete apart;
        street.size--;
        return street;
    }

    Apartment *curr = street.head;
    int i = 0;
    Apartment *apt;
    while (i < indx - 1)
    {
        curr = curr->next;
        i++;
    }

    if (curr->next == street.tail)
    {
        //Apartment *apt = street.tail;
        apt = street.tail;
        curr->next = street.head;
        street.tail = curr;
        
    }
    else
    {
        //Apartment *apt = curr->next;
        apt = curr->next;
        curr->next = apt->next;
       
    }

    // Delete apt
    FlatN * curflat = apt->flat->head;
    while(curflat!=NULL){
        FlatN* temp = curflat;
        curflat = curflat->next;
        delete temp;
    }
    delete apt->flat;
    delete apt;
    


    if (street.head == NULL)
    {
        street.size--;
        ofstream out(filename.c_str(),ios::app);
        out << "There is no apartment. \n";
        out.close();
    }
    else
    {
        street.size--;
        return street;
    }
}

void Street::make_flat_empty(Street &street, string apt_name, int id)
{
    Apartment *apt = street.head;

    if (apt->name == apt_name)
    {
        FlatN *curr = apt->flat->head;
        while (curr != NULL)
        {
            if (curr->ID == id)
            {
                curr->isEmpty = true;
                curr->bandwidth = 0;
                break;
            }
            curr = curr->next;
        }
    }

    apt = apt->next;
    while (apt != street.head)
    {
        if (apt->name == apt_name)
        {

            FlatN *curr = apt->flat->head;
            while (curr != NULL)
            {
                if (curr->ID == id)
                {
                    curr->isEmpty = true;
                    curr->bandwidth = 0;
                    break;
                }
                curr = curr->next;
            }
            break;
        }

        apt = apt->next;
    }
}

int Street::find_sum_of_max_bandwidth(Street &street, string output)
{

    ofstream out(output.c_str(), ios::app);

    out << "\n";

    if (street.head == NULL)
    {
        out << "sum of bandwidth: " << 0 << "\n";
        return 0;
    }

    int total_band = 0;
    Apartment *apt = street.head;
    total_band += street.head->max_bandwidth;
    apt = apt->next;
    while (apt != street.head)
    {
        total_band += apt->max_bandwidth;
        apt = apt->next;
    }
    out << "sum of bandwidth: " << total_band << "\n"
        << "\n";

    out.close();
    return total_band;
}

Street Street::merge_two_apartments(Street &street, string name1, string name2,string filename)
{
    // HATALI (DEV SUNUCUSUNDA)

    Apartment *apt1 ;
    Apartment *apt2 ;
    Apartment *curr = street.head; // street.head apartman listesindeki ilk apartmanı döndürüyor. DEV sunucusunda bazen burası bile null olarak gözükebiliyor.
    // Apartment *curr = street.head satırını diğer fonksiyonlarda defalarca kullanmama rağmen DEV'de sorunsuz çalışıyorlar, sadece bu fonksiyonda sıkıntı yaratıyor.
    int i = 0;

    while (i < street.size)
    {

        // Burada sırasıyla apartmanları dönüp isimleri fonksiyon argümanlarıyla uyuşuyor mu diye kontrol ettim. Tüm listeyi dönmesine rağmen ilk if'e girip doğru apartmanı buluyor
        // fakat ikinci apartmanın isminde bir apartman olmasına rağmen else if kısmına hiçbir zaman girmiyor. Bu yüzden apt2 her zaman null olarak kalıyor. Alttaki if'e girmeye çalışırken
        // de segmentation fault döndürüyor. Dediğim gibi kod bilgisayarımda sorunsuz çalışırken bu hatayı sadece DEV sunucusunda alıyorum.
        if (curr->name == name1)
        {
            apt1 = curr;
        }
        else if (curr->name == name2)
        {
            apt2 = curr;
        }
        i++;
        curr = curr->next;
    }

    if (apt2->flat->head == NULL)
    {
        apt1->max_bandwidth += apt2->max_bandwidth;
        street = Street::remove_apartment(street, apt2->name,filename);
        return street;
    }
    else if (apt1->flat->head == NULL)
    {
        apt1->flat->head = apt2->flat->head;
    }

    else
    {
        apt1->flat->tail->next = apt2->flat->head;
    }
    apt1->max_bandwidth += apt2->max_bandwidth;

    street = Street::remove_apartment(street, apt2->name,filename);

    return street;
}

FlatN *Street::removeFlat(Street &street, Flat *flat, FlatN *removedflat)
{
    Apartment *apt = street.head;
    int i = 0;
    while (i < street.size)
    {
        if (apt->flat == flat)
        {
            break;
        }
        apt = apt->next;
    }

    if (removedflat->ID == flat->head->ID)
    {

        flat->head = removedflat->next;
    }
    else if (removedflat == flat->tail)
    {

        flat->tail = removedflat->prev;
        flat->tail->next = NULL;
    }
    else
    {

        removedflat->prev->next = removedflat->next;
        removedflat->next->prev = removedflat->prev;
    }
    removedflat->next = NULL;
    removedflat->prev = NULL;
    apt->max_bandwidth = apt->max_bandwidth - removedflat->bandwidth;
    return removedflat;
}

void Street::relocate_flats_to_same_apartments(Street &street, string apt_Name, int id, vector<int> id_list)
{
    int band_width_to_Add = 0;
    int i = 0;
    int j = 0;
    Apartment *apt = street.head;
    Flat *relocateFlat = new Flat();
    while (i < street.size)
    {
        FlatN *relocate;
        FlatN *tempFlat = apt->flat->head;

        while (tempFlat != NULL)
        {
            if (tempFlat->ID == id_list[j])
            {
                tempFlat = Street::removeFlat(street, apt->flat, tempFlat);
                band_width_to_Add += tempFlat->bandwidth;

                if (j == 0)
                {
                    relocateFlat->head = tempFlat;
                }
                else
                {
                    FlatN *temp_relocateFlat = relocateFlat->head;
                    while (temp_relocateFlat->next != NULL)
                    {
                        temp_relocateFlat = temp_relocateFlat->next;
                    }
                    temp_relocateFlat->next = tempFlat;
                    tempFlat->prev = temp_relocateFlat;
                    relocateFlat->tail = tempFlat;
                }

                j++;
            }

            tempFlat = tempFlat->next;
        }
        apt = apt->next;
        i++;
    }

    apt = street.head;
    i = 0;
    FlatN *flat_to_relocate;
    while (i < street.size)
    {

        if (apt->name == apt_Name)
        {
            FlatN *temp = apt->flat->head;
            while (temp != NULL)
            {
                if (temp->ID == id)
                {
                    flat_to_relocate = temp;
                    break;
                }
                temp = temp->next;
            }
            break;
        }

        i++;
        apt = apt->next;
    }

    if (flat_to_relocate == apt->flat->head)
    {

        relocateFlat->tail->next = flat_to_relocate;
        flat_to_relocate->prev = relocateFlat->tail;
        apt->flat->head = relocateFlat->head;
    }
    else
    {

        FlatN *temp = flat_to_relocate->prev;
        temp->next = relocateFlat->head;
        relocateFlat->head->prev = temp;
        flat_to_relocate->prev = relocateFlat->tail;
        relocateFlat->tail->next = flat_to_relocate;
    }

    apt->max_bandwidth += band_width_to_Add;
}

void Street::list_apartments(Street street, string output)
{
    ofstream out(output.c_str(), ios::app);

    Apartment *apt = street.head;
    int i = 0;
    while (i < street.size)
    {
        out << apt->name << "(" << apt->max_bandwidth << ")"
            << "   ";

        FlatN *curr = apt->flat->head;
        while (curr != NULL)
        {
            out << "Flat" << curr->ID << "(" << curr->bandwidth << ")"
                << "    ";
            curr = curr->next;
        }
        out << "\n";
        apt = apt->next;
        i++;
    }

    out.close();
}

// Additional functions for finding correct place from String arguments.
int Street::FindApartment(Street street, string ApartName)
{
    Apartment *apt = street.head->next;
    int index = 1;
    if (street.head->name == ApartName)
    {
        return 0;
    }

    while (apt != street.head)
    {
        if (apt->name == ApartName)
        {
            return index;
        }
        index++;
        apt = apt->next;
    }
}

int Street::FindIndexForAddApartment(Street street, string location)
{ // Needs BeforeX or head etc..
    if (location == "head")
    {

        return 0;
    }
    else
    {
        stringstream ss(location);
        string s;
        int i = 0;
        vector<string> locationvec;
        while (getline(ss, s, '_'))
        {
            locationvec.push_back(s);
        }

        int indx = Street::FindApartment(street, locationvec[1]);

        if (locationvec[0] == "before")
        {

            return indx;
        }
        else if (locationvec[0] == "after")
        {
            return indx + 1;
        }
    }
}

vector<int> Street::GetListRelocate(string line)
{

    vector<int> idList;
    vector<string> splitList;
    stringstream ss(line);
    string str;
    while (getline(ss, str, '['))
    {
        splitList.push_back(str);
    }

    string lastStr = splitList[splitList.size() - 1];
    splitList.erase(splitList.end() - 1);
    stringstream sss(lastStr);
    string strr;
    while (getline(sss, strr, ']'))
    {
        splitList.push_back(strr);
    }

    string listwithout_Brackets = splitList[1];

    vector<string> vectorofId;
    stringstream sstream(listwithout_Brackets);
    string strig;
    while (getline(sstream, strig, ','))
    {
        vectorofId.push_back(strig);
    }

    for (int i = 0; i < vectorofId.size(); i++)
    {

        stringstream s(vectorofId[i]);
        int j;
        s >> j;
        idList.push_back(j);
    }

    return idList;
}

vector<int> Street::PrepareAdd_Flat_Arguments(vector<string> line)
{
    vector<int> numeric_Values;
    stringstream indx(line[2]);
    stringstream id(line[3]);
    stringstream bandwidth(line[4]);

    int index, idd, bandwidthh;

    indx >> index;
    id >> idd;
    bandwidth >> bandwidthh;
    numeric_Values.push_back(index);
    numeric_Values.push_back(idd);
    numeric_Values.push_back(bandwidthh);

    return numeric_Values;
}
vector<string> Street::splitLine(string line)
{
    vector<string> linesplitted;
    stringstream ss(line);
    string str;
    while (getline(ss, str, '\t'))
    {
        linesplitted.push_back(str);
    }

    return linesplitted;
}

void Street::main_func(Street street, string filename, string outputname)
{
    ifstream file(filename.c_str());
    string line;
    while (getline(file, line))
    {
        vector<string> linevec = splitLine(line);

        if (linevec[0] == "add_apartment")
        {
            int index = street.FindIndexForAddApartment(street, linevec[2]);
            int bandwidth;
            stringstream bandwidthh(linevec[3]);
            bandwidthh >> bandwidth;
            add_apartment(street, index, bandwidth, linevec[1]);
        }
        else if (linevec[0] == "add_flat")
        {
            vector<int> flat_inf = PrepareAdd_Flat_Arguments(linevec);
            add_flat(street, linevec[1], flat_inf[0], flat_inf[2], flat_inf[1]);
        }
        else if (linevec[0] == "remove_apartment")
        {
            remove_apartment(street, linevec[1],outputname);
        }
        else if (linevec[0] == "merge_two_apartments")
        {
            // HATALI
            merge_two_apartments(street, linevec[1], linevec[2],outputname);
        }
        else if (linevec[0] == "find_sum_of_max_bandwidths")
        {
            find_sum_of_max_bandwidth(street, outputname);
        }
        else if (linevec[0] == "list_apartments")
        {
            list_apartments(street, outputname);
        }
        else if (linevec[0] == "make_flat_empty")
        {
            stringstream idd(linevec[2]);
            int id;
            idd >> id;
            make_flat_empty(street, linevec[1], id);
        }
        else if (linevec[0] == "relocate_flats_to_same_apartment")
        {
            ofstream output(outputname.c_str(), ios::app);
            stringstream idd(linevec[2]);
            int id;
            idd >> id;
            vector<int> vec;
            vec = GetListRelocate(linevec[3]);
            relocate_flats_to_same_apartments(street, linevec[1], id, vec);
            output.close();
        }
    }
    file.close();
}
