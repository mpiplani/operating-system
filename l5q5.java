import java.util.Scanner;
import java.util.*;
class worstfit1
{
	public static void main(String args[])
	{
	  System.out.println("enter the number of processes");
	  Scanner scan=new Scanner(System.in);
	  int p=scan.nextInt();
	  int space[]=new int[p];
	  int check=0;
	  	  int processunallocated[]=new int[p];
	  System.out.println("enter space required by various processes");
	  for(int i=0;i<p;i++)
	  {
		space[i]=scan.nextInt();
		
	  }
	  System.out.println("enter the number of memory segments");
	  int m=scan.nextInt();
	  int memoryallocated[]=new int[m];
	  int memoryleft[]=new int[m];
	  int memoryallocated1[]=new int[m];
	  int memoryleft1[]=new int[m];
	  int index[]=new int[m];
	  
	   int memory[]=new int[m];
	   int sortm[]=new int[m];
	   int sortm1[]=new int[m];
	    int sortm2[]=new int[m];
	 int f[]=new int[m];
		   int f1[]=new int[m];
	   for(int i=0;i<m;i++)
	  {
		memory[i]=scan.nextInt();
		sortm2[i]=memory[i];
		f[i]=0;
		f1[i]=0;
	  }
	   for(int i = 0; i < p; i++)
		 {
			 processunallocated[i] = 1;
		 }
	  Arrays.sort(sortm2);
	  for(int i=0;i<m;i++)
	  {
		sortm[m-i-1]=sortm2[i];
	  }
	  for(int i = 0; i < m; i++)
		 {
		 sortm1[i]=sortm[i];
		 }
	  for(int i = 0; i < m; i++)
		 {
			 for(int j = 0; j < m; j++)
			 {
				 if(sortm1[i] == memory[j])
				 {
					 index[i] = j;
					 memory[j] = 0;
					 break;
				 }
			 }
		 }
	  
	  
	  
	  for(int i=0;i<p;i++ )
	  {
		for(int j=0;j<m;j++)
		{
			
		if(space[i]<=sortm[j])
			{
				memoryallocated[j]=space[i];
				memoryleft[j]=sortm[j]-memoryallocated[j];
				f[j]=i;
				check=1;
				sortm[j]=sortm[j]-memoryallocated[j];
				break;
			}

			
		}
		if(check == 1)
					 processunallocated[i] = 0;
			 check = 0;
	  }
	 for(int i = 0; i <m; i++)
		 {
			 memoryallocated1[index[i]] = memoryallocated[i];
			 memoryleft1[index[i]] = memoryleft[i];
			 f1[index[i]] = f[i];
		 }
  
  
  
  
  
  
		for(int j=0;j<m;j++)
		{
		System.out.println("memory segment"+j+"allocatedmemory="+ memoryallocated1[j]+" left"+memoryleft1[j]);
		}
		for(int j=0;j<m;j++)
		{
		if(memoryallocated1[j]==0)
			System.out.println("segment"+j+"not allocated");
		else
		System.out.println("segment"+j+"allocated in"+f1[j]);
	
		
		}
		for(int i = 0; i < p; i++)
		 {
			 if(processunallocated[i] == 1)
				 System.out.println("The process "+i+" has not been allocated any memory.");
		 }
	}
	  
	
}