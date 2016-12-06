import java.util.Scanner;
import java.util.*;
class firstfitc1
{
	public static void main(String args[])
	{
	  System.out.println("enter the number of processes");
	  Scanner scan=new Scanner(System.in);
	  int p=scan.nextInt();
	  int space[]=new int[p];
	  	  int f[]=new int[p];
			int sub=0;
			
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
		int sum=0;
		ArrayList<Integer> l=new ArrayList<Integer>();
		for(int j=0;j<p;j++)
		{
			System.out.println(f[j]);
		}
		for(int j=0;j<p;j++)
		{
			if(f[j]==m)
			{
				
			
		
		
				System.out.println("trying the first memory contraction algorithm");
				System.out.println("memory map is");
				
				//int z=0;
				for(int i=0;i<m;i++)
				{
					//if(i<=m-2 &&memoryleft[i]!=0)
					//{
					if(memoryleft[i]==0)
						System.out.print("allocated "+memoryallocated[i]);
				}
				for(int i=0;i<m;i++)
				{
					 if(memoryleft[i]!=0)
						{
						
								sum=sum+memoryleft[i];
								//i++;
						}
						
				}
				//System.out.println(sum);
					l.add(sum);
				break;
				}
			}
			
					/*else
					{
						l.add(memoryleft[i]);
						
					}*/
					
		
				
			
		
		for(int ab=0;ab<l.size();ab++)
				{
					System.out.println(l.get(ab)+ "space left");
				
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
						sub=l.get(i)-space[j];
						//System.out.println(sub);
						l.remove(i);
						//for(int y=0;y<l.size();y++)
						//System.out.println(l.get(y));
						w=i;
						l.add(i,sub);
						//for(int y=0;y<l.size();y++)
							//System.out.println(l.get(y));
						break;
					}
					else
					{
						w++;
					}
				}
				if(w==l.size())
					System.out.println("process "+j +" cannot be allocated");
			}
		}
	  
	}
}