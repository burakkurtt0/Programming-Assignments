#include "Trees.H"
#include <fstream>
#include <sstream>
#include <queue>

MainTree::MainTree(){
     root = NULL;
}
void MainTree::InsertPriv(mainNode *root,Category cat){
    mainNode* temp = Search(root,cat.name);
    

    if(temp!=NULL){ // IF CATEGORY EXISTS.
     
      temp->AVL->Insert(cat.item);
      temp->RBTree->Insert(cat.item);
    }




    else{ // IF CATEGORY IS NOT INSERTED YET.
    if (root->category.name > cat.name)
    {
      if (root->left == NULL)
      {
        mainNode *temp = new mainNode;
        temp->AVL->Insert(cat.item);
        temp->RBTree->Insert(cat.item);
        temp->category = cat;
        root->left = temp;
      }
      else
      {
        InsertPriv(root->left,cat);
      }
    }
    else
    {
      if (root->right == NULL)
      {
        mainNode *temp = new mainNode;
        temp->AVL->Insert(cat.item);
        temp->RBTree->Insert(cat.item);
        temp->category = cat;
        root->right = temp;
      }
      else
      {
        InsertPriv(root->right,cat);
      }
    }
    }
}


mainNode* MainTree::Search(mainNode *root,string data){
    if (root == NULL || root->category.name == data)
    {
      
      return root;
    }

 
      // Checking place of item in tree.
      if (data < root->category.name)
      {
         return Search(root->left, data);
      }
      
      return  Search(root->right, data);
}

void MainTree::findAVL(mainNode *root,string cat_name, string item_name ,ofstream &out1,ofstream &out2){
    mainNode* node = Search(root,cat_name);
    if(node == NULL){
      out1 << "{}" << "\n";
      return ;
    }
    bool nodefound = node->AVL->findPublic(item_name);
    if(nodefound){
      
      out1 << "{" << "\n";
      out1 << "\"" << node->category.name << "\"" << ":" << "\n";
      node->AVL->printPublic(nodefound,item_name,out1);
      
      out1 << "}" << "\n";
      return;
    }

    out1  << "{}" << "\n";
    
}

void MainTree::findLLRBT(mainNode *root , string cat_name , string item_name,ofstream &out1,ofstream &out2){
    mainNode* node = Search(root,cat_name);
    if(node == NULL){
      out2 << "{}" << "\n";
      return ;
    }
    bool nodefound = node->RBTree->findPublic(item_name);
    if(nodefound){
      
      out2 << "{" << "\n";
      out2 << "\"" << node->category.name << "\"" << ":" << "\n";
      
      node->RBTree->printPublic(nodefound,item_name,out2);
    
      out2 << "}" << "\n";
      return;
    }
    out2  << "{}" << "\n";
}


void MainTree::InsertPublic(Category data){
    if (root != NULL)
    {
     
      this->InsertPriv(root,data);
      
    }
    else
    {
     
      mainNode *temp = new mainNode;
      temp->category = data;
      temp->AVL->Insert(data.item);
      temp->RBTree->Insert(data.item);
      root = temp;
    }
}


void MainTree::printALLItems(ofstream &out1,ofstream &out2){
    if(root == NULL){
      out1 << "{}" << "\n";
      out2 << "{}" << "\n";
      
      
      return;
    }

    //Searching and printing in level-order.
    queue<mainNode*> nodes;
    nodes.push(root);

    out1 << "{" << "\n";
    out2 << "{" << "\n";
    
    mainNode* temp = root;

    while(!nodes.empty()){
      out1 <<  "\""<<temp->category.name << "\"" << ":"  ;
      out2 <<  "\""<<temp->category.name << "\"" << ":"  ;
      
      temp->AVL->printAllAVL(out1,out2); // BURAYI DA DÜZENLE.
      temp->RBTree->printAllRBT(out1,out2);  // BURAYI DA DÜZENLE.

      if(temp->left != NULL){
        nodes.push(temp->left);
      }
      if(temp->right!= NULL){
        nodes.push(temp->right);
      }

      nodes.pop();
      temp = nodes.front();
    }

    out1 << "}" << "\n";
    out2 << "}" << "\n";
    
}



mainNode* MainTree::searchPublic(string itemname){
    return Search(root, itemname);
}

void MainTree::FindandPrintItemFunction(string cat_name,string item_name , ofstream &out1,ofstream &out2){
    findAVL(root,cat_name,item_name,out1,out2);
    findLLRBT(root,cat_name,item_name,out1,out2);
}

void MainTree::UpdateData(string cat_name , string item_name,float val){
    mainNode* node = Search(root,cat_name);
    node->AVL->UpdateAVL(item_name,val);
    node->RBTree->UpdateRBT(item_name,val);
}

void MainTree::RemoveItem(string cat_name , string item_name){
    mainNode *node = Search(root,cat_name);
    node->AVL->Delete(item_name);
    node->RBTree->Delete(item_name);
}

void MainTree::PrintCategory(string cat_name , ofstream &out1,ofstream &out2){
    mainNode* node = Search(root,cat_name);
    
    out1 << "{\n";
    out2 << "{\n";

    out1 << "\"" << cat_name << "\"" << ":";
    out2 << "\"" << cat_name << "\"" << ":";
    
    node->AVL->printAllAVL(out1,out2);
    node->RBTree->printAllRBT(out1,out2); 


    out1 << "}\n";
    out2 << "}\n";
    
}


void MainTree::DestroyTreePriv(mainNode *root){
  if(root == NULL){
    return;
  }

  DestroyTreePriv(root->left);
  DestroyTreePriv(root->right);
  root->AVL->DestroyTree();
  root->RBTree->DestroyTree();
  delete root;
}

void MainTree::DestroyTree(){
  DestroyTreePriv(root);
}


vector<string> FileReading_and_Main::ReadFile(string filenametxt){
    
    vector<string> lines;
    ifstream ifstr;
    ifstr.open(filenametxt.c_str());
    string line;

    while (getline(ifstr, line))
    {
      lines.push_back(line);
    }

    return lines;
}

void FileReading_and_Main::main_Func(string input,string output1,string output2, MainTree* &tree){
    vector<string> lines = ReadFile(input);
    
    ofstream AVL_out(output1);
    ofstream RBT_out(output2);

    for(int i = 0 ; i < lines.size();i++){
      string line = lines[i];
     
      vector<string> linesplit;
      string word;
      stringstream ss(line);
      while(getline(ss,word,'\t')){
        linesplit.push_back(word);
      }

    

      if(linesplit[0] == "insert"){
        
        int price; 
        stringstream s(linesplit[3]);
        s >> price;
        Category cat;
        itemInfo item;
        cat.name = linesplit[1];
        item.name = linesplit[2];
        item.price = price;
        cat.item = item;
        tree->InsertPublic(cat);
        
      }

      if(linesplit[0] == "printAllItems"){
        AVL_out <<"command:printALLItems" << "\n" ;
        RBT_out <<"command:printALLItems" << "\n" ;
       
        tree->printALLItems( AVL_out,  RBT_out);
      }

      else if(linesplit[0] == "printItem"){
        AVL_out << "command:printItem\t" << linesplit[1] << "\t" << linesplit[2]<<"\n";
        RBT_out << "command:printItem\t" << linesplit[1] << "\t" << linesplit[2]<<"\n";
     
        tree->FindandPrintItemFunction(linesplit[1],linesplit[2],AVL_out,RBT_out);
      }

      else if(linesplit[0] == "updateData"){
        
        stringstream ss(linesplit[3]);
        float x;
        ss >> x;
        tree->UpdateData(linesplit[1],linesplit[2],x);
        
      }

      else if(linesplit[0] == "remove"){
        
        tree->RemoveItem(linesplit[1],linesplit[2]);
        
      }

      else if(linesplit[0] == "printAllItemsInCategory"){
        AVL_out << "command:printAllItemsInCategory\t" << linesplit[1] << "\n";
        RBT_out << "command:printAllItemsInCategory\t" << linesplit[1] << "\n";
       
       
        tree->PrintCategory(linesplit[1],AVL_out,RBT_out);
        
      }

      else if(linesplit[0] == "find"){
        AVL_out << "command:find\t" << linesplit[1] << "\t" << linesplit[2] << "\n";
        RBT_out << "command:find\t" << linesplit[1] << "\t" << linesplit[2] << "\n";
       
        tree->FindandPrintItemFunction(linesplit[1],linesplit[2],AVL_out,RBT_out);
      }

    }

    AVL_out.close();
    RBT_out.close();
    tree->DestroyTree();

  
}











