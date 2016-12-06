#include<stdio.h>
 #include<pthread.h>
 #include<semaphore.h>

sem_t read;
sem_t data;
int readCount=0;

void *Reader(void *arg);
void *Writer(void *arg);

int main()
	{
		int i=0;
		sem_init(&read,0,1);
		sem_init(&data,0,1);

		pthread_t Readers_th[2],Writer_th[2];
		for(i=0;i<2;i++)
		{
			pthread_create(&Readers_th[i],NULL,Reader,(void *)i);
		}
		for(i=0;i<2;i++)
		{
			pthread_create(&Writer_th[i],NULL,Writer,(void *)i);
		}
		for(i=0;i<2;i++)
		{
			pthread_join(Writer_th[i],NULL);
		}

		for(i=0;i<2;i++)
		{
			pthread_join(Readers_th[i],NULL);
		}
		sem_destroy(&data);
		sem_destroy(&read);
		return 0;
 }

void * Writer(void *arg)
 {
 int temp=(int)arg;
 printf(“\nWriter %d is trying to enter”,temp);
 sem_wait(&data);
 printf(“\nWriter %d is writting into the database”,temp);
 printf(“\nWriter %d is leaving the database”);
sem_post(&data);
 }

void *Reader(void *arg)
 {
 int temp=(int)arg;
 printf(“\nReader %d is trying to enter ”,temp);
 sem_wait(&read);//locks read
 readCount++;
 if(readCount==1)
 {
	sem_wait(&data);//for writers//locks data
	printf(“\nReader %d is reading the database”,temp);
 }
 sem_post(&read);//unlock read
 sem_wait(&read);//lock read
 readCount--;
if(readCount==0)
 {
 printf(“\nReader %d is leaving the database”,temp);
 sem_post(&data);
 }
 sem_post(&read);//unlock for writer

}