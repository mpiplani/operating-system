#include<stdio.h>
#include<pthread.h>
pthread_mutex_t waiter;
void* dining()
{
	pthread_mutex_lock(&waiter);
	lock_fork_right();
	lock_fork_left();
	pthread_mutex_unlock(&waiter);
	printf("Eating now\n");
	unlock_left();
	unlock_right();
}
