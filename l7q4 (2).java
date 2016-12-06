import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;


public class Optimal 
{
    static String currPage;     //Current requested Page
    static String victimPage;   //Page to be replaced
    static List<String> requestList=new ArrayList<>();      //List of requests by a process
    static int num_request;     //Number of requests
    static Queue pagingQueue=new LinkedList<>();    //Queue storing pages in order of their arrival time   
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter number of frames");
        int size=sc.nextInt();
        List<String> frame=new ArrayList(size);
        String input;
        System.out.println("Enter process requirement. Enter 'Exit' to stop");
        while(true)
        {
            input=sc.next();
            if(input.equalsIgnoreCase("Exit"))
                break;
            requestList.add(input);
        }
        num_request=requestList.size();
        for(int i=0;i<size;i++)
            requestList.add("0");
        System.out.println(requestList);
        /*
        This loop ensures that no empty frame is there.
        */
        for(int i=0;i<size;i++)
        {
            currPage=requestList.remove(0);
            if(!frame.contains(currPage))
            {
                frame.add(currPage);
                pagingQueue.offer(frame.get(i));
            }
            System.out.println(frame);
        }
        /*
        This loop ensures that Optimal algorithm is performed.
        The unusual add statements on line 31 & 32 are justified here,
        When I am creating sublist, I am taking 0 to size-1 elements, but
        it will raise ArrayOutOfBoundException when less than size-1 elements
        in requestList are remaining.
        Rest of algorithm is standard
        */
        int index,count=0;
        while(!requestList.isEmpty())
        {
            currPage=requestList.remove(0);
            if(!frame.contains(currPage))
            {
                victimPage=frame.get(0);
                for(int i=0;i<size;i++)
                {
                    
                    if(!requestList.subList(0,2).contains(frame.get(i)))
                    {
                        victimPage=frame.get(i);
                    }
                }
                System.out.println("Miss");
                frame.set(frame.indexOf(victimPage), currPage);
            }
            System.out.println(frame);
        }
    }
}