0#include <pthread.h>
#include <stdio.h>
int a[500][500];
int b[500][500];
int c[500][500];
int m,n,k;
void* multi(void* p)
{
	int i,j,l,g;
	 g=*((int *)p);
	for(i=g*m/3;i<(g+1)*m/3;i++)
	{
        	for( j=0;j<n;j++)
		{
			for( l=0;l<k;l++)
			{
			c[i][j]=a[i][l]*b[l][j]+c[i][j];
			}
		}
	}
}
void main()
{
int t=0;
int u=1;
int v=2;
int i;
int j;
int *arr;
	printf("enter the values of m,n,k");
        scanf("%d %d %d",&m,&n,&k);
	for( i=0;i<m;i++)
	{
	for(j=0;j<n;j++)
	{
	scanf("%d",&a[i][j]);
	}
	}

	for( i=0;i<n;i++)
	{
	for( j=0;j<k;j++)
	{
	scanf("%d",&b[i][j]);
	}
	}
arr=&t;
	pthread_t p1,p2,p3;
	pthread_create(&p1,NULL,multi,(void *)arr);
	
arr=&u;
	pthread_create(&p2,NULL,multi,(void *)arr);
	
arr=&v;
	pthread_create(&p3,NULL,multi,(void *)arr);
       pthread_join(p1,NULL);
        pthread_join(p2,NULL);
	pthread_join(p3,NULL);
for(i=0;i<m;i++)
	{
	for( j=0;j<k;j++)
	{
	printf("%d",c[i][j]);
	}
printf("\n");
	}
}
