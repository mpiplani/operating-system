#include<stdio.h>
#include<pthread.h>
#include<semaphore.h>
/*I HAVE USED SEMAPHORE OF SIZE =1 INSTEAD OF INBUILT MUTEX TO USE FUNCTION sem_getvalue() 
-to find out if any other philosopher is not using the same chopstick .*/

sem_t c[5]; // 5 semaphores  of SIZE= 1 and hence MUTEX 
pthread_mutex_t lock;   //global mutex
void* func(void *y)
{
	int valuel;
	int valuer;
	int l = (int)y;         // philosophers no.
 	int r;                  //left chopstick = pholisopher no.
	sem_getvalue(&c[l], &valuel);  //semaphore value of left chopstick
	 r = (l+1)%5;
	sem_getvalue(&c[r], &valuer);   //semaphore value of right chopstick
	 
       printf("\nThe value of left  is %d", valuel);
       printf("\nThe value of right is %d", valuer);
      pthread_mutex_lock(&lock);
       if(valuel>0&&valuer>0)          //value of semaphore greater than ZERO Critical section can be accesssed by a process
       {
 	   sem_wait(&c[l]);                ///both chopsticks available ,philosopher locks both
	   sem_wait(&c[r]);
	   }
	pthread_mutex_unlock(&lock);             // global mutex unlocked
	//CRITICAL SECTION 

	printf(" \n PHILOSOPHER %d IS EATING WITH FORK %d and  %d  ",l,l,r);

	//CRITICAL SECTION OVER
     sem_post(&c[l]);                  //both chopsticks are released by philosopher 
     sem_post(&c[r]);
     printf("\n RELEASED RESOURCE: %d : %d",l,r);
     printf("\n Philosopher %d is thinking",l);

}

int main()
{
    int i = 0;
    int j=0;
    pthread_t th[5];                                            // philosophers
    pthread_mutex_init(&lock, NULL);
    for(i=0;i<=4;i++)
    sem_init(&c[i],0,1);                                       //SEMAPHORE OF SIZE =1 SO MUTEX 
while(j!=-1)
{ 
   for(i=0;i<=4;i++)
    {
	
        pthread_create(&(th[i]), NULL, func, (void*)i);         //calls function 
        
    }
  for(i=0;i<=4;i++)
  {
  	pthread_join(th[i], NULL);
  }
    
}
    return 0;
}
