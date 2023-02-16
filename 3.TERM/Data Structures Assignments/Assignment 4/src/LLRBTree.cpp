#include "Trees.H"
#include <queue>
#include <fstream>

LLRBTree::LLRBTree()
{
  root = NULL;
}

bool LLRBTree::IsRed(LLRBTreeNode *node)
{
  return node != NULL && node->color == Color::RED;
}

LLRBTreeNode *LLRBTree::RotateLeft(LLRBTreeNode *node)
{
  LLRBTreeNode *new_root = node->right;
  node->right = new_root->left;
  new_root->left = node;
  new_root->color = node->color;
  node->color = Color::RED;
  return new_root;
}

LLRBTreeNode *LLRBTree::RotateRight(LLRBTreeNode *node)
{
  LLRBTreeNode *new_root = node->left;
  node->left = new_root->right;
  new_root->right = node;
  new_root->color = node->color;
  node->color = Color::RED;
  return new_root;
}

void LLRBTree::SwitchColors(LLRBTreeNode *node)
{
  node->color = Color::RED;
  node->left->color = Color::BLACK;
  node->right->color = Color::BLACK;
}

LLRBTreeNode *LLRBTree::InsertPriv(LLRBTreeNode *node, itemInfo val)
{
  if (node == NULL)
  {
    return new LLRBTreeNode(val, Color::RED);
  }

  // Finding location of new node.
  if (val.name < node->val.name)
  {
    node->left = InsertPriv(node->left, val);
  }
  else if (val.name > node->val.name)
  {
    node->right = InsertPriv(node->right, val);
  }


  // REBALANCING

  if (IsRed(node->right) && !IsRed(node->left)) // right-red , left-black, needs left rotation because there can not be red line for right child.
  {
    node = RotateLeft(node);
  }
  if (IsRed(node->left) && IsRed(node->left->left)) // left-red, left-left-red , needs right rotation because there can not be 2 red lines in order.
  {
    node = RotateRight(node);
  }
  if (IsRed(node->left) && IsRed(node->right)) // Both child are red.
  {
    SwitchColors(node);
  }
  return node;
}



LLRBTreeNode *LLRBTree::MoveRedLeft(LLRBTreeNode *node)
{
  SwitchColors(node);
  if (IsRed(node->right->left))
  {
    node->right = RotateRight(node->right);
    node = RotateLeft(node);
    SwitchColors(node);
  }
  return node;
}

LLRBTreeNode *LLRBTree::MoveRedRight(LLRBTreeNode *node)
{
  SwitchColors(node);
  if (IsRed(node->left->left))
  {
    node = RotateRight(node);
    SwitchColors(node);
  }
  return node;
}

LLRBTreeNode*  LLRBTree::Fixup(LLRBTreeNode *node){
  if (IsRed(node->right))
    {
      node = RotateLeft(node);
    }
    if (IsRed(node->left) && IsRed(node->left->left))
    {
      node = RotateRight(node);
    }
    if (IsRed(node->left) && IsRed(node->right))
    {
      SwitchColors(node);
    }
    return node;
}

LLRBTreeNode* LLRBTree::Deletemin(LLRBTreeNode *node)
{
  if (node->left == NULL)
  {
    delete node;
    return NULL;
  }
  if (!IsRed(node->left) && !IsRed(node->left->left))
  {
    node = MoveRedLeft(node);
  }
  node->left = Deletemin(node->left);
  return Fixup(node);
}

LLRBTreeNode* LLRBTree::DeletePriv(LLRBTreeNode *node,string val)
{
  if (val < node->val.name)
    {
      if (!IsRed(node->left) && !IsRed(node->left->left))
      {
        node = MoveRedLeft(node);
      }
      node->left = DeletePriv(node->left, val);
    }
    else
    {
      if (IsRed(node->left))
      {
        node = RotateRight(node);
      }
      if (val == node->val.name && node->right == NULL)
      {
        delete node;
        return NULL;
      }
      if (!IsRed(node->right) && !IsRed(node->right->left))
      {
        node = MoveRedRight(node);
      }
      if (val == node->val.name)
      {
        LLRBTreeNode *temp = node->right;
        while (temp->left != NULL)
        {
          temp = temp->left;
        }
        node->val = temp->val;
        node->right = Deletemin(node->right);
      }
      else
      {
        node->right = DeletePriv(node->right, val);
      }
    }
    return Fixup(node);
}

LLRBTreeNode *LLRBTree::findPriv(LLRBTreeNode *root, string item_name)
{
  if (root == NULL || root->val.name == item_name)
  {
    return root;
  }

  if (item_name < root->val.name)
  {
    return findPriv(root->left, item_name);
  }
  return findPriv(root->right, item_name);
}



void LLRBTree::Insert(itemInfo val)
{
  root = InsertPriv(root, val);
  root->color = Color::BLACK;
}

void LLRBTree::Delete(string val)
{
  if (root == NULL)
  {
    return;
  }
  if (!IsRed(root->left) && !IsRed(root->right))
  {
    root->color = Color::RED;
  }
  root = DeletePriv(root, val);
  if (root != NULL)
  {
    root->color = Color::BLACK;
  }
}

void LLRBTree::UpdateRBT(string item_name, float val)
{
  LLRBTreeNode *node = findPriv(root, item_name);
  node->val.price = val;
}

bool LLRBTree::findPublic(string item_name)
{
  LLRBTreeNode *node = findPriv(root, item_name);
  if (node == NULL)
  {
    return false;
  }
  else
  {
    return true;
  }
}

void LLRBTree::printAllRBT(ofstream &out1, ofstream &out2)
{
  if (root == NULL)
  {
    out2 << "{}"
         << "\n";

    return;
  }

  out2 << "\n"
       << "\t";

  queue<LLRBTreeNode *> nodes;
  nodes.push(root);
  nodes.push(NULL); // To understand where level ends.
  while (!nodes.empty())
  {

    LLRBTreeNode *temp = nodes.front();
    nodes.pop();

    if (temp == NULL)
    { // We understand that we are at the end of the level.
      if (nodes.empty())
      {
        break; // Loop ends if queue is empty after deletion of null.
      }

      out2 << "\n"
           << "\t";

      nodes.push(NULL);
    }

    else
    {

      out2 << "\"" << temp->val.name << "\""
           << ":"
           << "\"" << temp->val.price << "\"";

      if (nodes.front() != NULL)
      { // If front of queue is NULL, we can understand that we are at the end of level.

        out2 << ",";
      }

      if (temp->left != NULL)
      {
        nodes.push(temp->left);
      }

      if (temp->right != NULL)
      {
        nodes.push(temp->right);
      }
    }
  }
  out2 << "\n";
}

void LLRBTree::printPublic(bool NodeFounded, string item_name, ofstream &out2)
{
  LLRBTreeNode *node = findPriv(root, item_name);
  if (NodeFounded)
  {
    out2 << "\t"
         << "\"" << node->val.name << "\""
         << ":"
         << "\"" << node->val.price << "\""
         << "\n";
    return;
  }

  out2 << "{}"
       << "\n";
}


void LLRBTree::DestroyTreePriv(LLRBTreeNode *root){
    if(root == NULL){
        return;
    }

    DestroyTreePriv(root->left);
    DestroyTreePriv(root->right);
    delete root;
}

void LLRBTree::DestroyTree(){
    DestroyTreePriv(root);
}
