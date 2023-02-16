#include "Matrixheader.h"
#include <iostream>
#include <fstream>
#include <sstream>



using namespace std;
int main(int argc,char *argv[])
{
    
    int size;
    string str = argv[2];
    stringstream ss(str);
    ss >> size;

    
    int **map;
    int **key;
    int **projection;
    
   

    string mapFile = argv[3];
    string keyFile = argv[4];
    const string outputName = argv[5];
    

    
    Matrixops::createKeyorMap(mapFile, map);
    Matrixops::createKeyorMap(keyFile, key);
    Matrixops::createProjectionMatrix(projection, map, size);
    Moveops::Move(key,map,projection,size,0,0,outputName);
    
    
   

 

    delete projection;
    delete map;
    delete key;
    return 0;
}