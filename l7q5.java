//optimal page replacement
import java.util.Scanner;
class opr
{
	public static void main(String args[])
	{
		Scanner scan=new Scanner(System.in);
		System.out.println("enter number of frames");
		int frame=scan.nextInt();
		System.out.println("enter number of pages");
		int page=scan.nextInt();
		int size[]=new int[page];
		System.out.println("enter size of pages");
		int frames[]=new int[frame];
		int recent[]=new int[frame];
		int flag[]=new int[frame];
		for(int i=0;i<page;i++ )
		{
			size[i]=scan.nextInt();
			
		}
		for(int i=0;i<frame;i++)
		{
			frames[i]=-1;
			
		}
		int miss=0;
		int num=0;
		int j=0;
		int max=0;
		boolean check=false;
		for(int i=0;i<page;i++)
		{
			for(j=0;j<frame;j++)
			{
				flag[j]=0;
				recent[j]=page;
			}
			for(int k=i+1;k<page;k++)
			{
				for(j=0;j<frame;j++)
				if(size[k]==frames[j] && flag[j]==0)
				{
					recent[j]=k;
					flag[j]=1;
				}
			}	
			for(j=0;j<frame;j++)
			{
				if(size[i]==frames[j])
				check=true;
				if(check==false)
				{
					max=0;
					for(j=0;j<frame;j++)
					{
						if(recent[j]>recent[max])
						max=j;
						if(frames[j]<0)
						{
							max=j;
							break;
						}
					}
					frames[max]=size[i];
					miss++;
				}
			}
		}
		System.out.println("number of hits"+(page-miss));
		}
		}
		