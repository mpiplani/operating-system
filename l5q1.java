import java.util.*;
class banker
{
	public static void main(String[] args)
	{
		Scanner scan=new Scanner(System.in);
		int avail[]=new int[4];
		int max[][]=new int[4][4];
		int allocate[][]=new int[4][4];
		int alloc[]={0,0,0,0};
		int need[][]=new int[4][4];
		int running[]=new int[4];
		int exec,count=0;
		int safe=0;
		for(int i=0;i<4;i++)
		{
			running[i]=1;
			count++;
		}
		System.out.println("enter the total resources available");
		for(int i=0;i<4;i++)
		{
			avail[i]=scan.nextInt();
		}
		System.out.println("enter the allocated matrix resources");
		for(int i=0;i<4;i++)
		{
			for(int j=0;j<4;j++)
			{
				allocate[i][j]=scan.nextInt();
			}
		}
		System.out.println("enter the maximum matrix");
		for(int i=0;i<4;i++)
		{
			for(int j=0;j<4;j++)
			{
				max[i][j]=scan.nextInt();
			}
		}
		for(int i=0;i<4;i++)
		{
			for(int j=0;j<4;j++)
			{
				alloc[j]=alloc[j] + allocate[i][j];
			}
		}
		for(int i=0;i<4;i++)
		{
			need[i]=avail[i] - alloc[i];
		}
		while(count!=0)
		{
			safe=0;
			for(int i=0;i<4;i++)
			{
				if(running[i])
				{
					exec=1;
					for(int j=0;j<4;j++)
					{
						if((avail[i][j] - allocate[i][j])>need[j])
						{
							exec=0;
							break;
						}
					}
					if(exec==0)
					{
						running[i]=0;
						count--;
						safe=1;
						for(int j=0;j<4;j++)
						{
							need[j]=need[j]+allocate[i][j];
						}
						break;
					}
				}
			}
			if(safe!=0)
			{
				System.out.println("unsafe state");
				break;
			}
			if(safe==0)
			{
				System.out.println("safe state");
			}
		}
	}
}