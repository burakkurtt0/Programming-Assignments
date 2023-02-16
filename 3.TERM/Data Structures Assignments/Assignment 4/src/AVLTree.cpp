#include "Trees.H"
#include <fstream>
#include <queue>

int AVLTree::GetHeight(AVLTreeNode *node)
{
    if (node == NULL)
    {
        return 0;
    }
    return node->height;
}

int AVLTree::GetBalance(AVLTreeNode *node)
{
    return GetHeight(node->right) - GetHeight(node->left);
}

void AVLTree::UpdateHeight(AVLTreeNode *node)
{
    node->height = 1 + max(GetHeight(node->left), GetHeight(node->right));
}

AVLTreeNode *AVLTree::RotateRight(AVLTreeNode *node)
{
    AVLTreeNode *new_root = node->left;
    node->left = new_root->right;
    new_root->right = node;
    UpdateHeight(node);
    UpdateHeight(new_root);
    return new_root;
}

AVLTreeNode *AVLTree::RotateLeft(AVLTreeNode *node)
{
    AVLTreeNode *new_root = node->right;
    node->right = new_root->left;
    new_root->left = node;
    UpdateHeight(node);
    UpdateHeight(new_root);
    return new_root;
}

AVLTreeNode *AVLTree::InsertPriv(AVLTreeNode *node, itemInfo val)
{
    if (node == NULL)
    {
        return new AVLTreeNode(val);
    }
    if (val.name < node->val.name)
    {
        node->left = InsertPriv(node->left, val);
    }
    else
    {
        node->right = InsertPriv(node->right, val);
    }
    UpdateHeight(node);
    int balance = GetBalance(node);
    if (balance > 1 && val.name > node->right->val.name)
    {
        // Right-Right case
        return RotateLeft(node);
    }
    if (balance < -1 && val.name < node->left->val.name)
    {
        // Left-Left case
        return RotateRight(node);
    }
    if (balance > 1 && val.name < node->right->val.name)
    {
        // Right-Left case
        node->right = RotateRight(node->right);
        return RotateLeft(node);
    }
    if (balance < -1 && val.name > node->left->val.name)
    {
        // Left-Right case
        node->left = RotateLeft(node->left);
        return RotateRight(node);
    }
    return node;
}

AVLTreeNode* AVLTree::findPriv(AVLTreeNode *root,string item_name)
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

AVLTreeNode *AVLTree::DeletePriv(AVLTreeNode *node, string name)
{
    if (node == NULL)
    {
        return NULL;
    }
    if (name < node->val.name)
    {
        node->left = DeletePriv(node->left, name);
    }
    else if (name > node->val.name)
    {
        node->right = DeletePriv(node->right, name);
    }
    else
    {
        // Found the node to delete
        if (node->left == NULL || node->right == NULL)
        {
            // Node has at most one child
            AVLTreeNode *temp = node->left ? node->left : node->right;
            if (temp == NULL)
            {
                // Node has no children
                temp = node;
                node = NULL;
            }
            else
            {
                // Node has one child
                *node = *temp;
            }
            delete temp;
        }
        else
        {
            // Node has two children
            AVLTreeNode *temp = node->right;
            while (temp->left != NULL)
            {
                temp = temp->left;
            }
            node->val = temp->val;
            node->right = DeletePriv(node->right, temp->val.name);
        }
    }
    if (node == NULL)
    {
        return node;
    }
    UpdateHeight(node);
    int balance = GetBalance(node);
    if (balance > 1 && GetBalance(node->right) >= 0)
    {
        // Right-Right case
        return RotateLeft(node);
    }
    if (balance > 1 && GetBalance(node->right) < 0)
    {
        // Right-Left case
        node->right = RotateRight(node->right);
        return RotateLeft(node);
    }
    if (balance < -1 && GetBalance(node->left) <= 0)
    {
        // Left-Left case
        return RotateRight(node);
    }
    if (balance < -1 && GetBalance(node->left) > 0)
    {
        // Left-Right case
        node->left = RotateLeft(node->left);
        return RotateRight(node);
    }
    return node;
}



AVLTree::AVLTree()
{
    root = NULL;
}

void AVLTree::Insert(itemInfo val)
{
    root = InsertPriv(root, val);
}

void AVLTree::Delete(string val)
{
    root = DeletePriv(root, val);
}

void AVLTree::printAllAVL(ofstream &out1, ofstream &out2)
{
    if (root == NULL)
    {
        out1 << "{}"
             << "\n";

        return;
    }
    out1 << "\n"
         << "\t";

    queue<AVLTreeNode *> nodes;
    nodes.push(root);
    nodes.push(NULL); // To understand where level ends.
    while (!nodes.empty())
    {

        AVLTreeNode *temp = nodes.front();
        nodes.pop();

        if (temp == NULL)
        { // We understand that we are at the end of the level.
            if (nodes.empty())
            {
                break; // Loop ends if queue is empty after deletion of null.
            }
            out1 << "\n"
                 << "\t";

            if (nodes.empty())
            {
                break; // Loop ends if queue is empty after deletion of null.
            }

            nodes.push(NULL);
        }

        else
        {
            out1 << "\"" << temp->val.name << "\""
                 << ":"
                 << "\"" << temp->val.price << "\"";

            if (nodes.front() != NULL)
            { // If front of queue is NULL, we can understand that we are at the end of level.

                out1 << ",";
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
    out1 << "\n";
}

void AVLTree::printPublic(bool NodeFounded, string item_name, ofstream &out1)
{
    AVLTreeNode *node = findPriv(root, item_name);
    if (NodeFounded)
    {
        out1 << "\t"
             << "\"" << node->val.name << "\""
             << ":"
             << "\"" << node->val.price << "\""
             << "\n";
        return;
    }

    out1 << "{}"
         << "\n";
}

bool AVLTree::findPublic(string item_name)
{
    AVLTreeNode *node = findPriv(root, item_name);
    if (node == NULL)
    {
        return false;
    }
    else
    {
        return true;
    }
}

void AVLTree::UpdateAVL(string item_name, float new_val)
{
    AVLTreeNode *Node = findPriv(root, item_name);
    Node->val.price = new_val;
}


void AVLTree::DestroyTreePriv(AVLTreeNode *root){
    if(root == NULL){
        return;
    }

    DestroyTreePriv(root->left);
    DestroyTreePriv(root->right);
    delete root;
}

void AVLTree::DestroyTree(){
    DestroyTreePriv(root);
}
