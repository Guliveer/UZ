#include <stdio.h>
#include <stdlib.h>

struct BST {
    int key;
    struct BST *pParent, *pLeft, *pRight;
};

void printBSTAsc(const struct BST* pNode) {
    if (pNode == NULL) return;
    printBSTAsc(pNode->pLeft);

    printf("%d ", pNode->key);

    printBSTAsc(pNode->pRight);
}

void printBSTDesc(const struct BST* pNode) {
    if (pNode == NULL) return;
    printBSTDesc(pNode->pRight);

    printf("%d ", pNode->key);

    printBSTDesc(pNode->pLeft);
}

int main() {
    /* Drzewo (przykład):
     *      5
     *     / \
     *    1   10
    */

    struct BST* pNode = malloc(sizeof(struct BST));
    pNode->key = 5;
    pNode->pParent = NULL;

    pNode->pLeft = malloc(sizeof(struct BST));
    pNode->pLeft->key = 1;
    pNode->pLeft->pParent = pNode;
    pNode->pLeft->pLeft = NULL;
    pNode->pLeft->pRight = NULL;

    pNode->pRight = malloc(sizeof(struct BST));
    pNode->pRight->key = 10;
    pNode->pRight->pParent = pNode;
    pNode->pRight->pLeft = NULL;
    pNode->pRight->pRight = NULL;

    printBSTAsc(pNode);

    free(pNode->pLeft);
    free(pNode->pRight);
    free(pNode);
}

