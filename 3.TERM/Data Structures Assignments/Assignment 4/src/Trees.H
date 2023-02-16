#include <iostream>
#include <vector>
using namespace std;


struct itemInfo // Items in the secondary trees ( AVL and Red-Black Tree)

{
  string name;
  float price;
};


struct Category{ // Categories in the first tree (Binary Search Tree)
  string name;
  itemInfo item;
};

enum class Color
{
  RED,
  BLACK
};

class AVLTreeNode
{
public:
  itemInfo val;
  int height; // Height of the nodes are kept to balance tree after insertion and deletion.
  AVLTreeNode *left;
  AVLTreeNode *right;

  AVLTreeNode(itemInfo val) : val(val), height(1), left(NULL), right(NULL) {
     // New node's height is 1 because it has no child when it is created.
  }
};

class AVLTree{
  private:
  AVLTreeNode *root;
  int GetHeight(AVLTreeNode *node); // Returns height of node.
  int GetBalance(AVLTreeNode *node); // Returns Balance factor of node, obtained by (Height of left-child) - (Height of right-child). It is used to detect are there any unbalanced nodes in tree.
  void UpdateHeight(AVLTreeNode *node); // After rebalancing, node's height is updated. It takes max height of left or right child and add 1 to that result.

  // Rotate functions are used to rebalance tree again after insertion or deletion.
  AVLTreeNode* RotateRight(AVLTreeNode *node);
  AVLTreeNode* RotateLeft(AVLTreeNode *node);

  // Private functions are needed because we need to pass root to that functions. When we declared them as public, we can not access to root and can not call function.
  AVLTreeNode* InsertPriv(AVLTreeNode *node, itemInfo val); 
  AVLTreeNode* DeletePriv(AVLTreeNode *node, string name); 


  AVLTreeNode* findPriv(AVLTreeNode *root, string item_name);  // Finds node which has given item in it. If node is not found, it returns null.
  void DestroyTreePriv(AVLTreeNode* root);

  public:
  AVLTree(); // Simple constructor, sets root as null.

  // Public insert and delete functions, they use private insertPriv and DeletePriv functions.
  void Insert(itemInfo val); 
  void Delete(string val);

  void printAllAVL(ofstream &out1, ofstream &out2); // It prints all nodes in the AVLTree in level-order. 
  void printPublic(bool NodeFounded,string item_name , ofstream &out1); // Prints given item's information. (Used for printItem function).
  bool findPublic(string item_name); // Works same way with private/public insert function, it uses private find function to find node, if it is found it returns true,false otherwise.
  void UpdateAVL(string item_name , float new_val); //Updates information of given item.
  void DestroyTree();
};





class LLRBTreeNode
{
public:
  itemInfo val;
  Color color; // Each node has Color from enumerate (RED or BLACK)
  LLRBTreeNode *left;
  LLRBTreeNode *right;

  LLRBTreeNode(itemInfo val, Color color) : val(val), color(color), left(NULL), right(NULL) {
    
  }
};

class LLRBTree{
  private:
  LLRBTreeNode *root;
  bool IsRed(LLRBTreeNode *node); // Checks if given node is red.

  // Rotation functions for rebalancing tree. Similar as AVL, only color of node changes.
  LLRBTreeNode* RotateLeft(LLRBTreeNode *node);
  LLRBTreeNode* RotateRight(LLRBTreeNode *node);

  
  void SwitchColors(LLRBTreeNode *node); // If given node's 2 child is red, childs become black and node become red.

  // Private functions are used again. 
  LLRBTreeNode* InsertPriv(LLRBTreeNode *node,itemInfo val);
  LLRBTreeNode* DeletePriv(LLRBTreeNode *node, string val);

  // Used in delete functions.
  LLRBTreeNode* MoveRedLeft(LLRBTreeNode *node);
  LLRBTreeNode* MoveRedRight(LLRBTreeNode *node);
  LLRBTreeNode* Deletemin(LLRBTreeNode *node);


  LLRBTreeNode* Fixup(LLRBTreeNode *node); //Checks red-black balance after rotating nodes and fix them if necessary.
  LLRBTreeNode* findPriv(LLRBTreeNode *root,string item_name); // Find given item. (Same as AVL function)
  void DestroyTreePriv(LLRBTreeNode *root);

  public:
  LLRBTree(); // sets root as null.
  void Insert(itemInfo val); 
  void Delete(string val);
  void UpdateRBT(string item_name , float val); // Updates information of given item.
  bool findPublic(string item_name); // Uses findPriv function to find given item, returns true if node exists.
  void printAllRBT(ofstream &out1 , ofstream &out2); // Print all node information in tree.
  void printPublic(bool NodeFounded , string item_name, ofstream &out2); // Used in PrintItem function, prints specific item.
  void DestroyTree();
};




struct mainNode
{
  // Each node contains AVL and RBTree.
  Category category;
  AVLTree *AVL = new AVLTree();
  LLRBTree *RBTree = new LLRBTree();
  mainNode *left = NULL;
  mainNode *right = NULL;

};

class MainTree{
  private:
  mainNode *root;
  void InsertPriv(mainNode *root,Category cat); // Finds new node's location, if category exists then adds item to that category, if catgeory did not exist, then it creates that category and add item in it.  
  mainNode* Search(mainNode *root, string data); // Search for given node.
  void findAVL(mainNode *root,string cat_name, string item_name ,ofstream &out1,ofstream &out2); // Used in printItem function.
  void findLLRBT(mainNode *root , string cat_name , string item_name,ofstream &out1,ofstream &out2); // Used in printItem function.
  void DestroyTreePriv(mainNode *root);


  public:
  MainTree();
  void InsertPublic(Category data);
  
  void printALLItems(ofstream &out1,ofstream &out2); // Prints all items and writing files to items for all trees.
  
  mainNode* searchPublic(string itemname);
  void FindandPrintItemFunction(string cat_name,string item_name, ofstream &out1,ofstream &out2);
  void UpdateData(string cat_name , string item_name,float val);
  void RemoveItem(string cat_name, string item_name);
  void PrintCategory(string cat_name,ofstream &out1 , ofstream &out2);
  void DestroyTree();
};



class FileReading_and_Main{ // Extra class for file reading operations and main function. (implemented in mainTree.cpp file)
  public:
  static vector<string> ReadFile(string filenametxt);
  static void main_Func(string input,string output1,string output2, MainTree* &tree);
};

