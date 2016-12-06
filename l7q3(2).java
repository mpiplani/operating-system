//least recently used
import java.util.Scanner;
class lru
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
		for(int i=0;i<page;i++ )
		{
			size[i]=scan.nextInt();
			
		}
		for(int i=0;i<frame;i++)
		{
			frames[i]=-1;
			recent[i]=-1;
		}
		int miss=0;
		int num=0;
		int j=0;
		int max;
		boolean check=false;
		for(int i=0;i<page;i++)
		{
			for(j=0;j<frame;j++)
			 recent[j]++;
			 for(j=0;j<frame;j++)
			 {
				if(size[i]==frames[j])
				
				{
					check=true;
					recent[j]=0;
				}
			 }
			 if(check==false)
			 {
				max=0;
				if(recent[j]>recent[max])
				max=j;
				frames[max]=size[i];
				recent[max]=0;
				miss++;
			}
				check=false;
			 }
			 System.out.println("number of hits"+(page-miss));
		}
	}