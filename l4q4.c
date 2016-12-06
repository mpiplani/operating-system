#include<stdio.h>
#include<pthread.h>
#include<semaphore.h>

sem_t sem;
pthread_mutex_t mutex;
pthread_t *student;

int capacity;
void* mess()
{
	int i=0,j=0;
	sem_wait(&sem);
	while(j<100000000)
	{
		while(i<100000000)
		{
			i++;
		}
		j++;
	}
	printf("Inside Critical Section\n");
	sem_post(&sem);
	printf("Outside Critical Section\n");
}
int main()
{
	int num,i;
	printf("Enter the capacity\n");
	scanf("%d",&capacity);
	printf("Enter number of students\n");
	scanf("%d",&num);
	student=(pthread_t *)malloc(sizeof(pthread_t)*num);
	sem_init(&sem,0,capacity);
	pthread_mutex_init(&mutex,NULL);
	for(i=0;i<num;i++)
		pthread_create(&student[i],NULL,*mess,NULL);
	for(i=0;i<num;i++)
		pthread_join(student[i],NULL);
	sem_close(&sem);
}


