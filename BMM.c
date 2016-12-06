#include<stdio.h>
#include<pthread.h>

pthread_t th[4];
int **result;
int **a,**b;
int r1,c1,r2,c2;
void* block_mm()
{
	int i,j,k;
	if(th[0]==pthread_self())
	{
		for(i=0;i<r1/2;i++)
		{
			for(j=0;j<c2/2;j++)
			{
				for(k=0;k<r2;k++)
				{
					result[i][j]+=a[i][k]*b[k][j];
				}
			}
		}
	}
	if(th[1]==pthread_self())
        {
                for(i=r1/2;i<r1;i++)
                {
                        for(j=0;j<c2/2;j++)
                        {
                                for(k=0;k<r2;k++)
                                {
                                        result[i][j]+=a[i][k]*b[k][j];
                                }
                        }
                }
        }
	if(th[2]==pthread_self())
        {
                for(i=r1/2;i<r1;i++)
                {
                        for(j=c2/2;j<c2;j++)
                        {
                                for(k=0;k<r2;k++)
                                {
                                        result[i][j]+=a[i][k]*b[k][j];
                                }
                        }
                }
        }
	if(th[3]==pthread_self())
        {
                for(i=0;i<r1/2;i++)
                {
                        for(j=c2/2;j<c2;j++)
                        {
                                for(k=0;k<r2;k++)
                                {
                                        result[i][j]+=a[i][k]*b[k][j];
                                }
                        }
                }
        }
}

void main()
{
	int i,j;
	printf("Enter number of rows and columns for first matrix\n");
	scanf("%d%d",&r1,&c1);
	printf("Enter number of rows and columns for second matrix\n");
	scanf("%d%d",&r2,&c2);
	if(c1!=r2)
	{
		printf("Multiplication not possible");
		exit(0);
	}
	a=(int **)malloc(r1*sizeof(int));
	for(i=0;i<r1;i++)
	{
		a[i]=(int *)malloc(c1*sizeof(int));
	}
	b=(int **)malloc(r2*sizeof(int));
	for(i=0;i<r2;i++)
	{
		b[i]=(int *)malloc(c2*sizeof(int)); 
	}
	result=(int **)malloc(r1*sizeof(int));
	for(i=0;i<r1;i++)
	{
		result[i]=(int *)malloc(c2*sizeof(int));
	}
	printf("Enter elements of first array");
	for(i=0;i<r1;i++)
		for(j=0;j<c1;j++)
			scanf("%d",&a[i][j]);
	printf("Enter elements of second array");
	for(i=0;i<r2;i++)
		for(j=0;j<c2;j++)
			scanf("%d",&b[i][j]);
	for(i=0;i<4;i++)
		pthread_create(&th[i],NULL,*block_mm,NULL);
	for(i=0;i<4;i++)
		pthread_join(th[i],NULL);
	for(i=0;i<r1;i++)
	{
		for(j=0;j<c2;j++)
			printf("%d\t",result[i][j]);
		printf("\n");
	}
}
