#include<stdio.h>
#include<pthread.h>
int m1[6][6];
int m2[6][6];
int result[6][6];
struct array
{
	int a[3][3];
	int b[3][3];
	int c[3][3];
	int d[3][3];
	int answer[3][3];
}pa[4];
void *mult(void *para)
{
	struct array*q=(struct array*)para;
	int i;
	int j;
	int k;
	for( i=0;i<3;i++)
	{
		for(j=0;j<3;j++)
		{
			for( k=0;k<3;k++)
			{
				q->answer[i][j]=q->a[i][k]*q->b[k][j]+q->c[i][k]*q->d[k][j]+q->answer[i][j];
			}
		}
	}
}
void main()
{
	printf("enter first matrix");
		int x,y,i,j,l;
	for(x=0;x<6;x++)
	{
		for( y=0;y<6;y++)
		{
			scanf("%d",&m1[x][y]);
		}
        }
	
	printf("enter second matrix");
	for(x=0;x<6;x++)
	{
		for( y=0;y<6;y++)
		{
			scanf("%d",&m2[x][y]);
		}
	}
	//giving structures matrices
        for( i=0;i<3;i++)
	{
		for(j=0;j<3;j++)
		{
			pa[0].a[i][j]=m1[i][j];//a
			pa[0].b[i][j]=m2[i][j];//e
			pa[0].c[i][j]=m1[i][j+3];//b
			pa[0].d[i][j]=m2[i+3][j];//g

			pa[1].a[i][j]=m1[i][j];//a
			pa[1].b[i][j]=m2[i][j+3];//f
			pa[1].c[i][j]=m2[i][j];//b
			pa[1].d[i][j]=m2[i+3][j+3];//h

			pa[2].a[i][j]=m1[i+3][j];//c
			pa[2].b[i][j]=m2[i][j];//e
			pa[2].c[i][j]=m1[i+3][j+3];//d
			pa[2].d[i][j]=m2[i+3][j];//g

			pa[3].a[i][j]=m1[i+3][j];//c
			pa[3].b[i][j]=m2[i][j+3];//f
			pa[3].c[i][j]=m1[i+3][j+3];//d
			pa[3].d[i][j]=m2[i+3][j+3];//h
		}
	}



	pthread_t th[4];
	
	for(l=0;l<4;l++)
	{
		pthread_create(&th[l],NULL,mult,&pa[l]);
	}
	for(l=0;l<4;l++)
	{
		pthread_join(th[l],NULL);
	}
        for(i=0;i<3;i++)
	{
		for( j=0;j<3;j++)
		{
			result[i][j]=pa[0].answer[i][j];
			result[i][j+3]=pa[1].answer[i][j];
			result[i+3][j]=pa[2].answer[i][j];
			result[i+3][j+3]=pa[3].answer[i][j];
		}
	}
         for(i=0;i<6;i++)
	{
		for( j=0;j<6;j++)
		{
			printf("%d",result[i][j]);
		}
		printf("\n");
	}
}
