fifopage replacement
import java.util.Scanner;
class fpr
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
		for(int i=0;i<page;i++ )
		{
			size[i]=scan.nextInt();
			
		}
		for(int i=0;i<frame;i++)
		{
			frames[i]=-1;
		}
		int hit=0;
		int num=0;
		boolean check=true;
		for(int i=0;i<page;i++)
		{
			check=true;
			int p=size[i];
			for(int j=0;j<frame;j++)
			{
				if(frames[j]==p)
				{
					check=false;
					hit++;
					break;
				}
			}
			if(num==frame)
			 num=0;
		 
			 if(check)
			 {
			 frames[num]=p;
			 num++;
			 }
			 
			 
		}
		System.out.println("number of hits"+hit);
	}	
}