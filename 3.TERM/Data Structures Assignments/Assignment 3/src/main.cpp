#include <iostream>
#include <string>
#include <vector>
#include <fstream>
#include <sstream>
#include <list>
#include <iomanip>
#include <math.h>

using namespace std;
template <typename T>
struct Node  // GENERİC node structure for keeping Baristas , Cashiers and orders in queue.
{
    T item;
    Node *next = NULL;
};

struct Time  // Order structure keeps all the information about orders.
{
    double arrival;
    double orderTime;
    double brewTime;
    double price;
    double FinishTime;
};

class FileOps //Class for reading input and splits lines to Time structures.
{
public:
    static Time splitLine(string line)
    {
        vector<double> times;
        string str;
        stringstream s(line);
        while (getline(s, str, ' '))
        {
            stringstream l(str);
            double temp;
            l >> temp;
            times.push_back(temp);
        }

        Time time;
        time.arrival = times[0];
        time.brewTime = times[2];
        time.orderTime = times[1];
        time.price = times[3];
        time.FinishTime = 0;
        return time;
    }

    static vector<string> readFile(string filename)
    {
        ifstream file(filename.c_str());
        vector<string> lines;
        string str;
        while (getline(file, str))
        {
            lines.push_back(str);
        }
        file.close();
        return lines;
    }
};

template <typename T>
class queue // GENERIC queue class contains size , enqueue , dequeue and Isempty operatıons.
{
public:
    Node<T> *first;
    Node<T> *last;
    int size;

    queue()
    {
        first = last = NULL;
        size = 0;
    }

    void setSize(int siz)
    {
        size = siz;
    }

    int Size()
    {
        return size;
    }

    bool IsEmpty()
    {
        if (first == NULL)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    void enqueue(T &item)
    {
        Node<T> *temp = new Node<T>;
        temp->item = item;
        temp->next = NULL;

        if (first == NULL)
        {
            first = temp;
            last = temp;
        }

        else
        {
            last->next = temp;
            last = temp;
        }
        size++;
    }
    T dequeue()
    {
        if (first == NULL)
        {
            T item;
            return item;
        }
        Node<T> *temp = first;
        T data = temp->item;
        if (first == last)
        {
            first = last = NULL;
        }
        else
        {
            first = first->next;
            delete temp;
        }
        return data;
    }
};

struct Cashier // Cashier structure. Also holds order variable which it's working on it.
{
    int ID;
    bool busy;
    double busy_time;
    double total_busy_time;
    Time order;
};

struct Barista // Same with cashier structure.
{
    int ID;
    bool busy;
    double busy_time;
    double total_busy_time;
    Time order;
};

class model_F  // MODEL 1 
{
    double total_time; 
    

    // Number of cashiers,baristas and orders.
    int n_cashier;
    int n_barista;   
    int n_order;


    
    queue<Time> orderQueue; // Keeps all orders in input.
    queue<Time> ActiveOrders; // When cashier takes order but not prepared by barista, these orders get in ActiveOrders.
    queue<Time> completedOrders; // When baristas finished order, order get in this queue.

    queue<Cashier> cashiers; 
    queue<Barista> baristas;

public:
    model_F(int cas, int n_order)
    {
        n_cashier = cas;
        n_barista = cas / 3;
        n_order = n_order;
        total_time = 0;


        // Initialization of cashiers and baristas.
        for (int i = 0; i < n_cashier; i++)
        {
            Cashier *cas = new Cashier;
            cas->ID = i + 1;
            cas->busy = false;
            cas->busy_time = 0;
            cas->total_busy_time = 0;
            cashiers.enqueue(*cas);
            if (i < n_barista)
            {
                Barista *bar = new Barista;
                bar->ID = i + 1;
                bar->busy = false;
                bar->busy_time = 0;
                bar->total_busy_time = 0;
                baristas.enqueue(*bar);
            }
        }
    }

    // Puts all orders into orderQueue from input file.
    void getAllOrders(vector<string> orders)
    {
        for (int i = 0; i < orders.size(); i++)
        {
            Time time = FileOps::splitLine(orders[i]);
            orderQueue.enqueue(time);
        }
    }


    // Returns most expensive order from ActiveOrders queue.
    Node<Time> *RetrieveMostExpensive(queue<Time> orders)
    {
        double max = 0;
        Node<Time> *MaxPriceOrder;
        Node<Time> *temp = orders.first;
        while (temp != NULL)
        {
            if (temp->item.price > max)
            {
                max = temp->item.price;
                MaxPriceOrder = temp;
            }
            temp = temp->next;
        }
        
        return MaxPriceOrder;
    }


    // Removes selected order from selected queue.
    void DeleteSelectedOrder(queue<Time> &orders, Node<Time> *remove)
    {

        Node<Time> *temp = orders.first;
        if (temp == remove)
        {
            orders.first = temp->next;
            temp->next = NULL;
            // delete temp; 
            orders.setSize(orders.Size() - 1);
            return;
        }
        while (temp->next != NULL)
        {
            if (temp->next == remove)
            {
                temp->next = remove->next;
                remove->next = NULL;
                if (remove == orders.last)
                {
                    orders.last = temp;
                }
                // delete remove; 
                break;
            }
            temp = temp->next;
        }

        orders.setSize(orders.Size() - 1);
    }


    // Find mininum working time from all of employees. It can be arrival of any customer, end of taking order or end of brew.
    double FindMinTime()
    {
        Node<Cashier> *tempC = cashiers.first;
        double min = 9999;
        Node<Barista> *tempB = baristas.first;
        for (int i = 0; i < cashiers.Size(); i++)
        {
            if (tempC->item.busy)
            {
                if (tempC->item.busy_time < min)
                {
                    min = tempC->item.busy_time;
                }
            }

            tempC = tempC->next;
        }

        for (int i = 0; i < baristas.Size(); i++)
        {
            if (tempB->item.busy)
            {
                if (tempB->item.busy_time < min)
                {
                    min = tempB->item.busy_time;
                }
            }

            tempB = tempB->next;
        }

        Node<Time> *order = orderQueue.first;
        for (int i = 0; i < orderQueue.Size(); i++)
        {
            if (order != NULL)
            {
                if (total_time < order->item.arrival)
                {
                    if (order->item.arrival - total_time < min)
                    {
                        min = order->item.arrival - total_time;
                    }
                }

                order = order->next;
            }
        }

        return min;
    }

    void giveFirstWorkToCashier(queue<Time> &orderQueue){
        Node<Cashier> *tempC = cashiers.first;
        Time order = orderQueue.dequeue();
        tempC->item.busy_time = order.orderTime + order.arrival;
        tempC->item.total_busy_time += order.orderTime;
        tempC->item.busy = true;
        tempC->item.order = order;
    }
    
    // Main function to keep things work. For given time(MinWorkTime) all employee's work time is updated.
    // If anyone's work is done, that employee can take another order or go idle depends on situation. 
    void update(double time, queue<Time> &completedOr)
    {

        total_time += time;

        Node<Cashier> *tempC = cashiers.first;
        Node<Barista> *tempB = baristas.first;
        for (int i = 0; i < cashiers.Size(); i++)
        {
            if (tempC->item.busy)
            {
                tempC->item.busy_time -= time;
                if (tempC->item.busy_time <= 0)
                {

                    ActiveOrders.enqueue(tempC->item.order);
                    if (!orderQueue.IsEmpty())
                    {
                        if (orderQueue.first->item.arrival <= total_time)
                        {
                            Time order = orderQueue.dequeue();

                            tempC->item.total_busy_time += order.orderTime;
                            tempC->item.order = order;
                            tempC->item.busy_time += order.orderTime;
                        }
                        else
                        {
                            tempC->item.busy = false;
                            tempC->item.busy_time = 0;
                        }
                    }
                    else
                    {
                        tempC->item.busy = false;
                        tempC->item.busy_time = 0;
                    }
                }
            }
            else
            {
                if (!orderQueue.IsEmpty())
                {
                    if (orderQueue.first->item.arrival <= total_time)
                    {
                        Time order = orderQueue.dequeue();

                        tempC->item.busy = true;
                        tempC->item.total_busy_time += order.orderTime;
                        tempC->item.order = order;
                        tempC->item.busy_time += order.orderTime;
                    }
                }
            }
            tempC = tempC->next;
        }

        for (int i = 0; i < baristas.Size(); i++)
        {

            if (tempB->item.busy)
            {
                tempB->item.busy_time -= time;
                if (tempB->item.busy_time <= 0)
                {
                    tempB->item.order.FinishTime = total_time;

                    completedOr.enqueue(tempB->item.order);

                    if (!ActiveOrders.IsEmpty())
                    {

                        Node<Time> *orderNode = RetrieveMostExpensive(ActiveOrders);
                        tempB->item.total_busy_time += orderNode->item.brewTime;
                        tempB->item.busy_time += orderNode->item.brewTime;
                        tempB->item.order = orderNode->item;
                        DeleteSelectedOrder(ActiveOrders, orderNode);
                    }
                    else
                    {
                        tempB->item.busy = false;
                        tempB->item.busy_time = 0;
                    }
                }
            }
            else
            {
                if (!ActiveOrders.IsEmpty())
                {
                    Node<Time> *orderNode = RetrieveMostExpensive(ActiveOrders);

                    tempB->item.busy = true;
                    tempB->item.total_busy_time += orderNode->item.brewTime;
                    tempB->item.busy_time += orderNode->item.brewTime;
                    tempB->item.order = orderNode->item;
                    DeleteSelectedOrder(ActiveOrders, orderNode);
                }
            }
            tempB = tempB->next;
        }
    }

    // rounds float value into n decimal places.
    double round2(double val, int places)
    {

        long temp = (long)pow(10, places);
        val = val * temp;
        long tmp = round(val);

        return (double)tmp / temp;
    }


    // Checking for max order queue for barista.
    int checkBaristaWait(int max)
    {
        if (ActiveOrders.Size() > max)
        {
            max = ActiveOrders.Size();
        }
        return max;
    }


    void UnitUtilization(vector<double> &stats)
    {
        Node<Cashier> *tempC = cashiers.first;
        Node<Barista> *tempB = baristas.first;

        while (tempC != NULL)
        {
            double valC = tempC->item.total_busy_time / total_time;
            stats.push_back(round2(valC, 2));

            tempC = tempC->next;
        }

        while (tempB != NULL)
        {
            double valB = tempB->item.total_busy_time / total_time;
            stats.push_back(round2(valB, 2));
            tempB = tempB->next;
        }
    }


    // Finds order which has minimum arrival time and push it into stats vector and removes that order from queue. Then repeats it until there is no order in queue. 
    void TurnAroundCalculator(vector<double> &stats)
    {
        if (completedOrders.first == NULL)
        {
            return;
        }
        Node<Time> *temp = completedOrders.first;
        Node<Time> *minNode = NULL;
        double arrival = 9999;
        while (temp != NULL)
        {
            if (temp->item.arrival < arrival)
            {
                minNode = temp;
                arrival = temp->item.arrival;
            }
            temp = temp->next;
        }

        stats.push_back(round2(minNode->item.FinishTime - minNode->item.arrival, 2));
        DeleteSelectedOrder(completedOrders, minNode);
        TurnAroundCalculator(stats);
    }


    // This function is called once in main(). Contains all operations above.
    void mainFunc(vector<string> lines, string outputname)
    {
        vector<double> stats;
        getAllOrders(lines);
        int size = orderQueue.Size();
        giveFirstWorkToCashier(orderQueue);

        int max_barista_queue = 0;
        double min = FindMinTime();

        while (completedOrders.Size() != size)
        {

            max_barista_queue = checkBaristaWait(max_barista_queue);

            update(min, completedOrders);
            min = FindMinTime();
        }

    
        UnitUtilization(stats);
        TurnAroundCalculator(stats);

        ofstream out(outputname.c_str(),ios::app);

        out << setprecision(2) << fixed << total_time <<"\n";
        out << -1 << "\n"; // For max cashier queue.
        out << max_barista_queue << "\n";

        for (int i = 0; i < stats.size(); i++)
        {
            out << setprecision(2);
            out << fixed;
            out << stats[i] << "\n";
        }
        out << "\n";
        out.close();
    }
};

class Model_S  // MOSTLY SIMILAR WITH MODEL_F, THERE ARE SMALL CHANGES IN SOME FUNCTIONS.
{
    double total_time;

    int n_cashier;
    int n_barista;
    int n_order;

    queue<Time> orderQueue;
    queue<Time> completedOrders;
    vector<queue<Time> > ActiveOrderGroups; // Every 3C 1B group has it's own ActiveorderQueue.
    queue<Cashier> cashiers;
    queue<Barista> baristas;

public:
    Model_S(int cas, int n_order)
    {
        n_cashier = cas;
        n_barista = cas / 3;
        n_order = n_order;
        total_time = 0;

        for (int i = 0; i < n_cashier; i++)
        {
            Cashier *cas = new Cashier;
            cas->ID = i + 1;
            cas->busy = false;
            cas->busy_time = 0;
            cas->total_busy_time = 0;
            cashiers.enqueue(*cas);
            if (i < n_barista)
            {
                Barista *bar = new Barista;
                bar->ID = i + 1;
                bar->busy = false;
                bar->busy_time = 0;
                bar->total_busy_time = 0;
                baristas.enqueue(*bar);
            }
        }

        for (int i = 0; i < n_cashier / 3; i++)
        {
            queue<Time> q;
            ActiveOrderGroups.push_back(q);
        }
    }

    void getAllOrders(vector<string> orders)
    {
        for (int i = 0; i < orders.size(); i++)
        {
            Time time = FileOps::splitLine(orders[i]);
            orderQueue.enqueue(time);
        }
    }

    Node<Time> *RetrieveMostExpensive(queue<Time> orders)
    {
        double max = 0;
        Node<Time> *MaxPriceOrder;
        Node<Time> *temp = orders.first;
        while (temp != NULL)
        {
            if (temp->item.price > max)
            {
                max = temp->item.price;
                MaxPriceOrder = temp;
            }
            temp = temp->next;
        }
       
        return MaxPriceOrder;
    }

    void DeleteSelectedOrder(queue<Time> &orders, Node<Time> *remove)
    {

        Node<Time> *temp = orders.first;
        if (temp == remove)
        {
            orders.first = temp->next;
            temp->next = NULL;

            orders.setSize(orders.Size() - 1);
            return;
        }
        while (temp->next != NULL)
        {
            if (temp->next == remove)
            {
                temp->next = remove->next;
                remove->next = NULL;
                if (remove == orders.last)
                {
                    orders.last = temp;
                }

                break;
            }
            temp = temp->next;
        }

        orders.setSize(orders.Size() - 1);
    }

    double FindMinTime()
    {   
        
        Node<Cashier> *tempC = cashiers.first;
        double min = 9999;
        Node<Barista> *tempB = baristas.first;
        for (int i = 0; i < cashiers.Size(); i++)
        {
            if (tempC->item.busy)
            {
                if (tempC->item.busy_time < min)
                {
                    min = tempC->item.busy_time;
                }
            }

            tempC = tempC->next;
        }

        for (int i = 0; i < baristas.Size(); i++)
        {
            if (tempB->item.busy)
            {
                if (tempB->item.busy_time < min)
                {
                    min = tempB->item.busy_time;
                }
            }

            tempB = tempB->next;
        }
        
        Node<Time> *order = orderQueue.first;
        for (int i = 0; i < orderQueue.Size(); i++)
        {   
            if(order!=NULL){
            if (total_time < order->item.arrival)
            {
                if (order->item.arrival - total_time < min)
                {
                    min = order->item.arrival - total_time;
                }
            }
            order = order->next;
        }}

        return min;
    }
    void giveFirstWorkToCashier(queue<Time> &orderQueue){
        Node<Cashier> *tempC = cashiers.first;
        Time order = orderQueue.dequeue();
        tempC->item.busy_time = order.orderTime + order.arrival;
        tempC->item.total_busy_time += order.orderTime;
        tempC->item.busy = true;
        tempC->item.order = order;
    }


    double round2(double val, int places)
    {

        long temp = (long)pow(10, places);
        val = val * temp;
        long tmp = round(val);

        return (double)tmp / temp;
    }

    vector<int> checkBaristaWait(vector<int> &sizes)
    {
        for (int i = 0; i < baristas.Size(); i++)
        {
            if (ActiveOrderGroups[i].Size() > sizes[i])
            {
                sizes[i] = ActiveOrderGroups[i].Size();
            }
        }

        return sizes;
    }

    void UnitUtilization(vector<double> &stats)
    {
        Node<Cashier> *tempC = cashiers.first;
        Node<Barista> *tempB = baristas.first;

        while (tempC != NULL)
        {
            double valC = tempC->item.total_busy_time / total_time;
            stats.push_back(round2(valC, 2));

            tempC = tempC->next;
        }

        while (tempB != NULL)
        {
            double valB = tempB->item.total_busy_time / total_time;
            stats.push_back(round2(valB, 2));
            tempB = tempB->next;
        }
    }

    void TurnAroundCalculator(vector<double> &stats)
    {
        if (completedOrders.first == NULL)
        {
            return;
        }
        Node<Time> *temp = completedOrders.first;
        Node<Time> *minNode = NULL;
        double arrival = 9999;
        while (temp != NULL)
        {
            if (temp->item.arrival < arrival)
            {
                minNode = temp;
                arrival = temp->item.arrival;
            }
            temp = temp->next;
        }

        stats.push_back(round2(minNode->item.FinishTime - minNode->item.arrival, 2));
        DeleteSelectedOrder(completedOrders, minNode);
        TurnAroundCalculator(stats);
    }

    void update(double time)
    {

        total_time += time;
        
        int j = 0;  // J variable states which 3C 1B group are we in.
        Node<Cashier> *tempC = cashiers.first;
        Node<Barista> *tempB = baristas.first;

        
        while (j != n_cashier / 3)
        {
            

            for (int i = 0; i < 3; i++)
            {
                if (tempC->item.busy)
                {
                    tempC->item.busy_time -= time;
                    if (tempC->item.busy_time <= 0)
                    {

                        ActiveOrderGroups[j].enqueue(tempC->item.order);
                        if (!orderQueue.IsEmpty())
                        {
                            if (orderQueue.first->item.arrival <= total_time)
                            {

                                Time order = orderQueue.dequeue();

                                tempC->item.total_busy_time += order.orderTime;
                                tempC->item.order = order;
                                tempC->item.busy_time += order.orderTime;
                            }
                            else
                            {
                                tempC->item.busy = false;
                                tempC->item.busy_time = 0;
                            }
                        }
                        else
                        {
                            tempC->item.busy = false;
                            tempC->item.busy_time = 0;
                        }
                    }
                }
                else
                {
                    if (!orderQueue.IsEmpty())
                    {
                        if (orderQueue.first->item.arrival <= total_time)
                        {

                            Time order = orderQueue.dequeue();
                           
                            tempC->item.busy = true;
                            tempC->item.total_busy_time += order.orderTime;
                            tempC->item.order = order;
                            tempC->item.busy_time += order.orderTime;
                        }
                    }
                }
                tempC = tempC->next;
            }

            if (tempB->item.busy)
            {
                tempB->item.busy_time -= time;
                if (tempB->item.busy_time <= 0)
                {
                    
                    tempB->item.order.FinishTime = total_time;
                    completedOrders.enqueue(tempB->item.order);
                    if (!ActiveOrderGroups[j].IsEmpty())
                    {
                        Node<Time> *orderNode = RetrieveMostExpensive(ActiveOrderGroups[j]);
                        tempB->item.total_busy_time += orderNode->item.brewTime;
                        tempB->item.busy_time += orderNode->item.brewTime;
                        tempB->item.order = orderNode->item;
                        DeleteSelectedOrder(ActiveOrderGroups[j], orderNode);
                    }
                    else
                    {
                        tempB->item.busy = false;
                        tempB->item.busy_time = 0;
                    }
                }
            }
            else
            {
                if (!ActiveOrderGroups[j].IsEmpty())
                {
                    Node<Time> *orderNode = RetrieveMostExpensive(ActiveOrderGroups[j]);

                    tempB->item.busy = true;
                    tempB->item.total_busy_time += orderNode->item.brewTime;
                    tempB->item.busy_time += orderNode->item.brewTime;
                    tempB->item.order = orderNode->item;
                    DeleteSelectedOrder(ActiveOrderGroups[j], orderNode);
                }
            }

            tempB = tempB->next;

            j++;
        }
    }

    void mainFunc(vector<string> lines, string outputname)
    {   
        
        vector<double> stats;
        vector<int> maxBaristaSizes;
        for (int i = 0; i < baristas.Size(); i++)
        {
            maxBaristaSizes.push_back(0); // Initialize it with zeros.
        }

        
        getAllOrders(lines);
        int size = orderQueue.Size();
        giveFirstWorkToCashier(orderQueue);
       
        int max_barista_queue = 0;
       
        double min = FindMinTime();
       
        while (completedOrders.Size() != size)
        {
            
            maxBaristaSizes = checkBaristaWait(maxBaristaSizes);
            update(min);
            min = FindMinTime();
        }

      

        

        UnitUtilization(stats);
        TurnAroundCalculator(stats);
        ofstream out(outputname.c_str(), ios::app);
       
        out << setprecision(2) << fixed << total_time << "\n";
        out << -1 << "\n"; // Max cashier queue.

        for (int i = 0; i < maxBaristaSizes.size(); i++)
        {
            out << maxBaristaSizes[i] << "\n";
        }

    
        for (int i = 0; i < stats.size(); i++)
        {
            out << setprecision(2) << fixed << stats[i] << "\n";
        }
        out.close();
    }
};

int main(int argc, char *argv[])
{
    vector<string> lines = FileOps::readFile(argv[1]);
    int N_cas;
    int N_Order;
    stringstream ncas(lines[0]);
    stringstream nord(lines[1]);
    ncas >> N_cas;
    nord >> N_Order;

    lines.erase(lines.begin());
    lines.erase(lines.begin());
    // Only orders remain after erase operation


    vector<string> linesS = lines;
    model_F modelF(N_cas, N_Order);
    Model_S modelS(N_cas, N_Order);
    modelF.mainFunc(lines, argv[2]);
    modelS.mainFunc(linesS, argv[2]);
}
