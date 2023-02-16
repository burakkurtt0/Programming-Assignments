#include "Trees.H"

int main(int argc, char* argv[]){

    string inp = argv[1];
    string out1 = argv[2];
    string out2 = argv[3];
    MainTree *tree = new MainTree();
    FileReading_and_Main::main_Func(inp,out1,out2,tree);

    return 0;
}