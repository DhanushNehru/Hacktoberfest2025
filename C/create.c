//Display a Linked List
#include <stdio.h>
#include <stdlib.h>
struct Node //self referential structure
{
int data;
struct Node *next;
}*head=NULL;
	void create(int B[],int n)
	{
	int i;
	struct Node *t,*last;
	head=(struct Node *)malloc(sizeof(struct Node));
	head->data=B[0];
	head->next=NULL;
	last=head;
	for(i=1;i<n;i++)
	{
	t=(struct Node*)malloc(sizeof(struct Node));
	t->data=B[i];
	t->next=NULL;
	last->next=t;
	last=t;
	}
	}
	void Display(struct Node *p)
	{
	while(p!=NULL)
	{
	printf("%d ",p->data);
	p=p->next; //null
	}
	}
	
int main()
{
int A[]={3,5,7,10,25,8,32,2};
create(A,8); //call
Display(head);
return 0;
}
