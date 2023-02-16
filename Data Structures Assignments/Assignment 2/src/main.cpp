/*!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

merge_Two_apartments fonksiyonu DEV sunucusunda çalışmıyor. Doğru apartmanı bulmasına rağmen if'e girmiyor 

ve segmentation fault döndürüyor. Kendi bilgisayarımda output alabiliyorken DEV'de kodu bir türlü çalıştıramadım.

Merge apartments fonksiyonunda hatanın sebebinden daha detaylı bahsettim.

!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! */


#include "network.h"
#include "string"

int main(int argc,char*argv[])
{
    Street street;
    string arg1 = argv[1];
    string arg2 = argv[2];
    street.main_func(street,arg1.c_str(),arg2.c_str());
    return 0;
}