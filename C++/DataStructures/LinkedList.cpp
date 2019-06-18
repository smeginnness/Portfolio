#include <iostream>

class LinkedListNode{
	public:
	LinkedListNode* nextPtr;
	int data;
	
	LinkedListNode(int d){
		data = d;
		nextPtr = NULL;
	}
	
	int getData(){
		return data;
	}
	LinkedListNode* getNextPtr(){
		return nextPtr;
	}
};

class LinkedList {
private:
	LinkedListNode* head;

public:


	void addBack(int data) {
		LinkedListNode toAdd(data);
		if (head == NULL) {
			head = &toAdd;
		}
		else {
			LinkedListNode* temp = head;
			while (temp->nextPtr != NULL) {
				temp = temp->nextPtr;
			}
			temp->nextPtr = &toAdd;
		}
	}

	void addFront(int data) {
		LinkedListNode toAdd(data);
		if (head == NULL) {
			head = &toAdd;
		}
		else {
			toAdd.nextPtr = head;
			head = &toAdd;
		}
	}

	int remove(int search) { //removes an element from the list and returns the index
		if (head == NULL) {
			return -1; //error value saying that list is empty
		}
		LinkedListNode* temp = head;
		int location = 1;
		while (temp->nextPtr != NULL) {
			if (temp->data == search) {
				delete temp;
				return location;
			}
			location++;
		}
		return -2;//error value for "item not found"
	}
};