import java.util.Scanner;
import java.util.*;
class firstfitc
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
		ArrayList<Integer> l=new ArrayList<Integer>();
		int sum=0;
		int sub=0;
		int r=0;
		for(int j=0;j<p;j++)
		{
			if(f[j]==m)
			{
				r=1;
				break;
			}
		}
		if(r==1)
		{
				System.out.println("trying the first memory contraction algorithm");
				System.out.println("memory map is");
				for(int i=0;i<m;i++)
				
					//System.out.println(memoryleft[i]);
				int z=0;
				for(int i=0;i<m;i++)
				{
					//System.out.println(memoryleft[i]);
					//System.out.println(m);
					//System.out.println(i);
					if(i<=m-2 && memoryleft[i]!=0)
					{
					z=i+1;
					if(memoryleft[i]!=0)
					{
						sum=memoryleft[i];
						while( z<m && memoryleft[z]!=0  )
						{
							sum=sum+memoryleft[z];
							z++;
							i++;
						}
						l.add(sum);
						
					}
					}
					else
					{
						l.add(memoryleft[i]);
						
					}
					
				}
				for(int i=0;i<l.size();i++)
				{
					System.out.println(l.get(i)+ "space left");
				
				}
			}
		
		for(int j=0;j<p;j++)
		{
			if(f[j]==m)
			{
				int w=0;
				for(int i=0;i<l.size();i++)
				{
				if(space[j]<=l.get(i))
				{
					System.out.println("process "+j +"now can be allocated"+i);
					w=i;
				
					sub=l.get(i)-space[j];
						
						l.remove(i);
						
				
						l.add(i,sub);
							break;
				}
				else
					w++;
				}
				if(w==l.size())
					System.out.println("process "+j +" cannot be allocated");
			}
		}
	  
	}
}