#include<stdio.h>
#include<semaphore.h>
#include<pthread.h>

sem_t serviceQueue;
sem_t resource;
sem_t readValueAccess;
int readValue;
pthread_t *reader,*writer;

void* Reader()
{
	sem_wait(&serviceQueue);
	sem_wait(&readValueAccess);
	if(readValue==0)
		sem_wait(&resource);
	readValue++;
	sem_post(&serviceQueue);
	sem_post(&readValueAccess);
	printf("I am reading\n");
	sem_wait(&readValueAccess);
	readValue--;
	if(readValue==0)
		sem_post(&resource);
	sem_post(&readValueAccess);	
}

void* Writer()
{
	sem_wait(&serviceQueue);
	sem_wait(&resource);
	printf("I am writing\n");
	sem_post(&serviceQueue);
	sem_post(&resource);
}

int main()
{
	int i,num_reader=5,num_writer=7;
	printf("Enter number of readers\n");
	scanf("%d",&num_reader);
	printf("Enter number of writers\n");
	scanf("%d",&num_writer);
	reader=(pthread_t *)malloc(num_reader*sizeof(pthread_t));
	writer=(pthread_t *)malloc(num_writer*sizeof(pthread_t));
	readValue=0;
	sem_init(&serviceQueue,0,1);
	sem_init(&resource,0,1);
	sem_init(&readValueAccess,0,1);
	for(i=0;i<num_reader;i++)
		pthread_create(&reader[i],NULL,Reader,NULL);
	for(i=0;i<num_writer;i++)
		pthread_create(&writer[i],NULL,Writer,NULL);
	for(i=0;i<num_writer;i++)
		pthread_join(writer[i],NULL);
	for(i=0;i<num_reader;i++)
		pthread_join(reader[i],NULL);
	sem_close(&serviceQueue);
	sem_close(&resource);
	sem_close(&readValueAccess);
}
