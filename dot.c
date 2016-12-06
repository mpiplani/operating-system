#include <pthread.h>
#include <stdio.h>
int n;
int sum=0;
void* mult(void* x)
{
 int s;
 int* l = (int* )x;
 for(s=0;s<n;s++)
 {
  sum = sum + l[s]*l[n+s];
 }
}

main()
{
int i=0,j=0,k=0;
pthread_t th;
printf("enter the value of n\n");
scanf("%d",&n);
int a[2*n];
printf("enter the vector a\n");
for(i=0;i<n;i++)
{
 scanf("%d",&a[i]);
}

printf("enter the vector b\n");
for(j=n;j<2*n;j++)
{
 scanf("%d",&a[j]);
}

 pthread_create(&th,NULL,mult,(void*)a);
 pthread_join(th,NULL);

 printf("%d\n",sum);
}
 
