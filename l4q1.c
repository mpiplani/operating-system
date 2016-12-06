#include<stdio.h>
#include<pthread.h>
#include<stdlib.h>
#include<stdbool.h>

int BUFFER_MAX=10;
int counter=0;
int turn;	// turn = 0 will be turn of producer and turn = 1 will be turn of consumer 
bool flag[2];	//flag[0] will be a flag for producer and flag[1] will be flag for consumer
int buffer[10];
void* producer()
{
	int i=0;
	int k=0;
	while(k<20)
	{
		while(counter==BUFFER_MAX);
		buffer[i]=10*i;
		i=(i+1)%BUFFER_MAX;
		turn=1;
		flag[0]=true;
		while(flag[1]&&turn==1);	//Wait
		counter++;
		printf("Producer counter is %d\n",counter);
		flag[0]=false;
		k++;
	}
}
void* consumer()
{
	int i=0;
	int k=0,itemProcessed=0;
	while(k<20)
	{
		while(counter==0);
		itemProcessed = buffer[counter-1];
		flag[1]=true;
		turn=0;
		while(flag[0]&&turn==0);	//Wait
		counter--;	// critical section
		printf("Consumer counter is %d\n",counter);
		flag[1]=false;
		k++;
	}
}
int main()
{
	pthread_t Producer,Consumer;
	pthread_create(&Producer,NULL,producer,NULL);
	pthread_create(&Consumer,NULL,consumer,NULL);
	pthread_join(Producer,NULL);
	pthread_join(Consumer,NULL);
	return 1;
}
