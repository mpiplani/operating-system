Code:
#include <pthread.h>

#include <stdio.h>

int a[10];

int b[10];

int c[10];

int m;

void* multi(void* p)

{
	
   int i,j,l,g;

   g=*((int *)p);
	
   for(i=g*m/3;i<(g+1)*m/3;i++)
	
     {
        
	
        c[i]=a[i]*b[i]+c[i];
			
	
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
     
scanf("%d",&m);

     int sum=0;
      
for(j=0;j<m;j++)
	
       {
	
          scanf("%d",&a[j]);

        }
        
for(j=0;j<m;j++)
	
           {
	
              scanf("%d",&b[j]);
	
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
	
	
                 sum=sum+c[i];
	
               }

           printf(sum);
    
}

