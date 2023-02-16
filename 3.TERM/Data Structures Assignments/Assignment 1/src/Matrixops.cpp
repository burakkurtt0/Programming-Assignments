#include <iostream>
#include <fstream>
#include <string>
#include <sstream>
#include <cstdlib>
#include "Matrixheader.h"

using namespace std;



    //Sıkıntılı (KEY MATRIX VE SIZE AYNI OLMADIĞINDA SIKINTI ÇIKIYOR BUYUK IHTIMAL SORUN BENIM INPUTLARIMDA)
     int Matrixops::DotProduct(int **&p, int **&key, int size)
    {
        
        int sum = 0;
        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                
                int k = p[i][j] * key[i][j];
                sum += k;
            }
        }
        
        return sum;
    }

     void Matrixops::createKeyorMap(string filename, int **&mat)
    {

        
        ifstream file;
        file.open(filename.c_str());
        string line;
        
        linecol lc;
        lc = getLineandColumn(filename);

        // Declaration of key or map Matrix (Depends on filename argument).

        mat = new int *[lc.line];
        for (int i = 0; i < lc.line; i++)
        {
            mat[i] = new int[lc.column];
        }

        int i = 0;
        int j;
        char sep = ' ';
        string str;
        while (getline(file, line))
        {
            stringstream ss(line);
            j = 0;
            while (getline(ss, str, sep))
            {
                stringstream s(str);
                int value;
                s >> value;
                mat[i][j] = value;
                j++;
            }
            i++;
        }

      
    }

     linecol Matrixops::getLineandColumn(string filename)
    {
        ifstream file;
        
        file.open(filename.c_str());
        string line;
        
        string str;
        char sep = ' ';
        int line_num = 0;
        int column_num = 0;
        
        while (getline(file, line))
        {
           
            line_num++;
            stringstream ss(line);
            while (getline(ss, str, sep))
            {
                column_num++;
            }
        }

        column_num = column_num / line_num;
        linecol lc;
        lc.line = line_num;
        lc.column = column_num;
        
        file.close();
        return lc;
    }

  void Matrixops::createProjectionMatrix(int **&p, int **&map, int size)
    {
        
        p = new int *[size];
        for (int i = 0; i < size; i++)
        {
            p[i] = new int[size];
        }

        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                p[i][j] = map[i][j];
            }
        }
       
    }
    void Matrixops::updateProjection(int **&map, int **&p, int size, int x = 0, int y = 0)
    {
        
        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                p[i][j] = map[i + x][j + y];
            }
        }
    }


    /*
    DefineInput Matrixops::DefineSizes(string inp1, string inp2){
        DefineInput df;
        char sep = 'x';
        string str;
        string sizes[2];
        stringstream ss(inp1);
        int i =0;
        while(getline(ss,str,sep)){
            sizes[i] = str;
            i++;
        }
        stringstream k (inp2);
        stringstream m1(sizes[0]);
        stringstream m2(sizes[1]);

        int s1,s2,sk;
        m1 >> s1;
        m2 >> s2;
        k >> sk;
        df.m_size1 = s1;
        df.m_size2 = s2;
        df.k_size = sk;

        return df;

    }*/


