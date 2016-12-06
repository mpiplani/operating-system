import java.util.Scanner;
class firstfit
{
	public static void main(String args[])
	{
	  System.out.println("enter the number of processes");
	  Scanner scan=new Scanner(System.in);
	  int p=scan.nextInt();
	  int space[]=new int[p];
	  	  int f[]=new int[p];

	  System.out.println("enter space required by various processes");
	  for(int i=0;i<p;i++)
	  {
		space[i]=scan.nextInt();
		f[i]=0;
	  }
	  System.out.println("enter the number of memory segments");
	  int m=scan.nextInt();
	  int memoryallocated[]=new int[m];
	  int memoryleft[]=new int[m];
	   int memory[]=new int[m];
	   for(int i=0;i<m;i++)
	  {
		memory[i]=scan.nextInt();
		memoryleft[i]=memory[i];
	  }
	  for(int i=0;i<p;i++)
	  {
		for(int j=0;j<m;j++)
		{
			if(space[i]<=memory[j])
			{
				memoryallocated[j]=space[i];
				memoryleft[j]=memory[j]-memoryallocated[j];
				f[i]=j;
				memory[j]=memory[j]-memoryallocated[j];
				break;
			}
			else
			{
			f[i]++;
			}
			
			
		}
	  }
		for(int j=0;j<m;j++)
		{
		System.out.println("memory segment"+j+"allocatedmemory="+ memoryallocated[j]+" left"+memoryleft[j]);
		}
		for(int j=0;j<p;j++)
		{
		if(f[j]<m)
		System.out.println("process"+j+"allocated in"+f[j]);
		else
		System.out.println("process"+j+"not allocated");
		}
		for(int j=0;j<p;j++)
		{
			if(f[j]==m)
			{
				System.out.println("trying the first memory contraction algorithm");
				System.out.println("memory map is");
				for(int i=0;i<m;i++)
				{
					for(int k=0;k<m;k++)
					{
						if(memoryleft[k]!=0 && memoryleft[k+1]!=0 )
						{
							memoryleft[k]=memoryleft[k+1];
							memoryleft[k+1]=0;
						}
					}
				}
			}
		}
	  
	}
}