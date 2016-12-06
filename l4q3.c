#include<stdio.h>
 #include<pthread.h>
 #include<semaphore.h>
 sem_t a;
 void func(void *b)
 {
	int l=(int)b;
	int value;
	sem_wait(&a);
	printf("process locked %d",l);
	sem_getvalue(&a,&value);
	printf("value of semaphore %d",value);
	sem_post(&a);
	pthread_exit(0);
 }
 void main()
 {
	pthread_t th[4];
	int i;
	sem_init(&a,0,4);
		for(i=0;i<3;i++)
		{
			pthread_create(&th[i],NULL,func,(void *)i);
		}
		
		for(i=0;i<3;i++)
		{
			pthread_join(th[i],NULL);
		}
}