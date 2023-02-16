webstore:main
	g++ -std=c++11 main.o mainTree.o AVLTree.o LLRBTree.o -o webstore
main:mainTree
	g++ -std=c++11 -c main.cpp -o main.o
mainTree:AVLTree
	g++ -std=c++11 -c mainTree.cpp -o mainTree.o
AVLTree:LLRBTree
	g++ -std=c++11 -c AVLTree.cpp -o AVLTree.o
LLRBTree:
	g++ -std=c++11 -c LLRBTree.cpp -o LLRBTree.o