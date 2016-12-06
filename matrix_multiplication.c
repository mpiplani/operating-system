#include<pthread.h>
#include<stdio.h>

int **result;
int **a;
int **b;
pthread_t th[3];
int r1,c1,r2,c2;
void* matrix_multiplication(void *d)
{
	int i,j,k;
	if(th[0]==pthread_self())
	{
		for(i=0;i<r1/3;i++)
		{
			for(j=0;j<c2;j++)
			{
				for(k=0;k<r2;k++)
				{
					result[i][j] += a[i][k]*b[k][j];
				}
			}
		}
	}
	else if(th[1]==pthread_self())
	{
		i=r1/3;
                while(i<2*r1/3)
                {
                        for(j=0;j<c2;j++)
                        {
                                for(k=0;k<r2;k++)
                                {
                                        result[i][j]+=a[i][k]*b[k][j];
                                }
                        }
			i++;
                }
	}
	else if(th[2]==pthread_self())
	{
                for(i=2*r1/3;i<r1;i++)
                {
                        for(j=0;j<c2;j++)
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
	printf("\nEnter row and column number\n");
	scanf("%d\n%d",&r1,&c1);
	a=(int **)malloc(r1*sizeof(int));
	for(i=0;i<r1;i++)
	{
		a[i]=(int *)malloc(c1*sizeof(int));
	}
	printf("\nEnter the values\n");
	for(i=0;i<r1;i++)
	{
		for(j=0;j<c1;j++)
		{
			printf("Enter value of a[%d][%d] ",i,j);
			scanf("%d",&a[i][j]);
		}
	}
	printf("Enter row and column number\n");
        scanf("%d%d",&r2,&c2);
        b=(int **)malloc(r2*sizeof(int));
        for(i=0;i<r2;i++)
        {
                b[i]=(int *)malloc(c2*sizeof(int));
        }
        printf("\nEnter the values\n");
        for(i=0;i<r2;i++)
        {
  	      for(j=0;j<c2;j++)
	      {
		       printf("Enter value of b[%d][%d] ",i,j);
                       scanf("%d",&b[i][j]);
 	      }
	}
	result=(int **)malloc(r1*sizeof(int));
	for(i=0;i<r1;i++)
	{
		result[i]=(int *)malloc(c2*sizeof(int));
	}
	if(r2!=c1)
	{
		printf("Multiplication not possible. Program is terminating\n");
		exit(0);
	}
	for(i=0;i<3;i++)
	{
		pthread_create(&th[i],NULL,matrix_multiplication,NULL);
	}
	for(i=0;i<3;i++)
	{
		pthread_join(th[i],NULL);
	}
	for(i=0;i<r1;i++)
	{
		for(j=0;j<c2;j++)
		{
			printf("%d\t",result[i][j]);
		}
		printf("\n");
	}
}
