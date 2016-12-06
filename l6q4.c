/* C program for Merge Sort */
#include<stdlib.h>
#include<stdio.h>
#include<pthread.h> 
#include<semaphore.h>
// Merges two subarrays of arr[].
// First subarray is arr[l..m]
// Second subarray is arr[m+1..r]

/*
Semaphore is used to limit number of current thread usage to 4.
*/
pthread_mutex_t thread_lock;
sem_t thread_count;
int *arr;
struct node
{
    int l;
    int r;
};

void merge(int low, int high)
{
        int mid = (low+high)/2;
        int left = low;
        int right = mid+1;

        int b[high-low+1];
        int i, cur = 0;

        while(left <= mid && right <= high)
	{
                if (arr[left] > arr[right])
                        b[cur++] = arr[right++];
                else
                        b[cur++] = arr[left++];
        }

        while(left <= mid)
	     b[cur++] = arr[left++];
        while(right <= high) 
	     b[cur++] = arr[right++];
        for (i = 0; i < (high-low+1) ; i++) 
	     arr[low+i] = b[i];
}
 
/*

Since only 4 threads at Max are allowed at a given instant.
I have used semaphores and a global mutex

Once a thread locks a mutex, it decrements semaphore by 2.
Then, it creates two child threads and increments semaphore by 2.
This ensures that a array has been divided into two sub arrays successfully.

*/
void* mergeSort(void *a)
{
    pthread_t left_thread,right_thread;
    struct node *temp=(struct node *)a;
    struct node left;
    struct node right;
    if (temp->l < temp->r)
    {
      int m = (temp->l+temp->r)/2;
	left.l=temp->l;
	left.r=m;
	right.l=m+1;
	right.r=temp->r;
	pthread_mutex_lock(&thread_lock);
	sem_wait(&thread_count);
	sem_wait(&thread_count);
	pthread_create(&left_thread,NULL,mergeSort,&left);
	pthread_create(&right_thread,NULL,mergeSort,&right);
	sem_post(&thread_count);
	sem_post(&thread_count);
	pthread_mutex_unlock(&thread_lock);
	pthread_join(left_thread,NULL);
	pthread_join(right_thread,NULL);
        merge(temp->l, temp->r);
    }
}
 
void printArray(int A[], int size)
{
    int i;
    for (i=0; i < size; i++)
        printf("%d ", A[i]);
    printf("\n");
}
 
int main()
{
    pthread_mutex_init(&thread_lock,0);
    sem_init(&thread_count,0,4);
    struct node main;
    pthread_t *main_thread;
    main_thread=(pthread_t *)malloc(sizeof(pthread_t));
    int arr_size,i=0;
    int *a;
    printf("Enter size of array\n");
    scanf("%d",&arr_size);
    a=(int *)malloc(sizeof(int)*arr_size);
    printf("Enter elements of array\n");
    for(i=0;i<arr_size;i++)
    {
        scanf("%d",(a+i));        
    }
    arr=a;
    printf("Given array is \n");
    printArray(arr, arr_size);
    main.l=0;
    main.r=arr_size-1;
    pthread_create(&main_thread,NULL,mergeSort,&main);
    pthread_join(main_thread,NULL);
    printf("\nSorted array is \n");
    printArray(arr, arr_size);
    return 0;
}
