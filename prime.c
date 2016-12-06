#include<stdio.h>
#include<pthread.h>
int i,j,c,k,n,l;
int arr2[100];
void* mult(void* x)
{
	int count=0;
	
	l = *((int *)x);
 for(c=l*n/3;c<(l+1)*n/3;c++)
 {   
  for(k=1;k<(l+1)*n/3;k++)
  {
	 for(i=1;i<=k;i++)
	 {
	   if(k%i==0)
	   {
	   count++;
	   }
	 }
  
	 if(count==2)
	 { 
     for(j=0;j<100;j++)
	  {arr2[j]=k;}
	 }
  }	 
 }
}
main()
{
pthread_t th1;
pthread_t th2;
pthread_t th3;
int a=0;
int b=1;
int c=2;
int* ar;
printf("Enter the last number");
scanf("%d",&n);
int arr[n];
for(j=0;j<n;j++){
	arr[j]=j+1;
}
ar = &a;
 pthread_create(&th1,NULL,mult,(void *)ar);

 ar = &b;
 pthread_create(&th2,NULL,mult,(void *)ar);

 ar = &c;
 pthread_create(&th3,NULL,mult,(void *)ar);
 pthread_join(th1,NULL);
 pthread_join(th2,NULL);
 pthread_join(th3,NULL);
 printf("Prime numbers are:");
 for(j=0;j<n;j++)
 {
	 printf("%d",arr2[j]);
 }
}
