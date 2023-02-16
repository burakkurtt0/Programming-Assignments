#include <fstream>
#include <string>
using namespace std;


// Linecol is only created for returning 2 int values from getLineandColumn func.
struct linecol{
    int line,column;
};


enum Movement
{
    FOUND = 0,
    UP = 1,
    DOWN = 2,
    RIGHT = 3,
    LEFT = 4
};

class Matrixops{
    public:
   
    static int DotProduct(int**&p , int **&key, int size); // Calculates dot product of two matrix and return the value of product.
    static void createKeyorMap(string filename, int **&mat); // For the given file, creates key or map matrix.
    static linecol getLineandColumn(string filename); // As stated above, depending on the file name , it returns size of key or map matrix.
    static void createProjectionMatrix(int**&p,int** &map,int size); // Creates a projection matrix which follows same position with key matrix on map matrix. In beginning, it starts at
    // left top corner same with key matrix.
    static void updateProjection(int**&map,int**&p,int size,int x, int y ); // It ıs used in recursive move function. It moves through map matrix depending on the value of dot product.

};

class Moveops{
    public:
    static Movement FindMove(int sum); // It returns move way as enum.
    static void ReverseMove(Movement &m); 
    static void LookForBoundaries(int **&key, int **&p, Movement &m, int size); // If key matrix tries to reach out of map matrix, this function is called, then movement will be reversed.
    static void Move(int **&key, int **&map, int **p, int size, int x , int y,string out); // Recursive function which contains most of the functions in it. First of all, DotProduct is called
    // then way of move is calculated. After checking movement is valid (It will not reach out the bounds of map) , then updateProjection is called and projection is moved.
};













