#include <iostream>
#include "Matrixheader.h"
using namespace std;

Movement Moveops::FindMove(int sum)
{
    int m_number = sum % 5;
    if (m_number < 0)
    {
        m_number += 5;
    }
    Movement m;

    if (m_number == 0)
    {
        m = FOUND;
    }
    else if (m_number == 1)
    {
        m = UP;
    }
    else if (m_number == 2)
    {
        m = DOWN;
    }
    else if (m_number == 3)
    {
        m = RIGHT;
    }
    else if (m_number == 4)
    {
        m = LEFT;
    }
    return m;
}

void Moveops::ReverseMove(Movement &m)
{
    if (m == 1)
    {
        m = DOWN;
    }
    else if (m == 2)
    {
        m = UP;
    }

    else if (m == 3)
    {
        m = LEFT;
    }
    else if (m == 4)
    {
        m = RIGHT;
    }
}

void Moveops::Move(int **&key, int **&map, int **p, int size, int x , int y ,string out)
{
    
    
    ofstream output;
    output.open(out.c_str(),ios::app);
    //output.open(out);
    int sum = Matrixops::DotProduct(p, key, size);

    Movement m;
    m = FindMove(sum);
    LookForBoundaries(key, p, m, size);
    if (m == 0)
    {
        output << x + size / 2 << "," << y + size / 2 << ":" << sum << "\n";
        //cout << x + size / 2 << "," << y + size / 2 << ":" << sum << endl;
        output << "Treasure Found !!" << "\n";
        output.close();
    }

    else if (m == 1)
    {
        Matrixops::updateProjection(map, p, size, x - size, y);
        output<<x + size / 2 << "," << y + size / 2 << ":" << sum << "\n";
        //cout << x + size / 2 << "," << y + size / 2 << ":" << sum << endl;
        output.close();
        Move(key, map, p, size, x - size, y,out);
    }

    else if (m == 2)
    {
        Matrixops::updateProjection(map, p, size, x + size, y);
        output <<x + size / 2 << "," << y + size / 2 << ":" << sum << "\n";
        //cout << x + size / 2 << "," << y + size / 2 << ":" << sum << endl;
        output.close();
        Move(key, map, p, size, x + size, y,out);
    }

    else if (m == 3)
    {
        Matrixops::updateProjection(map, p, size, x, y + size);
        output<<x + size / 2 << "," << y + size / 2 << ":" << sum << "\n";
        //cout << x + size / 2 << "," << y + size / 2 << ":" << sum << endl;
        output.close();
        Move(key, map, p, size, x, y + size,out);
    }
    else if (m == 4)
    {
        Matrixops::updateProjection(map, p, size, x, y - size);
        output <<x + size / 2 << "," << y + size / 2 << ":" << sum << "\n";
        //cout << x + size / 2 << "," << y + size / 2 << ":" << sum << endl;
        output.close();
        Move(key, map, p, size, x, y + size,out);
    }
}

void Moveops::LookForBoundaries(int **&key, int **&p, Movement &m, int size)
{
    int count = 0;
    if (m == 1)
    {

        for (int i = 0; i < size; i++)
        {
            if (p[0][i] == 0)
            {
                count++;
            }
        }

        if (count == size)
        {
            ReverseMove(m);
        }
    }

    else if (m == 2)
    {
        for (int i = 0; i < size; i++)
        {
            if (p[size - 1][i] == 0)
            {
                count++;
            }
        }
        if (count == size)
        {
            ReverseMove(m);
        }
    }

    else if (m == 3)
    {
        for (int i = 0; i < size; i++)
        {
            if (p[i][size - 1] == 0)
            {
                count++;
            }
        }
        if (count == size)
        {
            ReverseMove(m);
        }
    }

    else if (m == 4)
    {
        for (int i = 0; i < size; i++)
        {
            if (p[i][0] == 0)
            {
                count++;
            }
        }
        if (count == size)
        {
            ReverseMove(m);
        }
    }
}
