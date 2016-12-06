import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

class FIFO
{
    static String currPage;     //Current requested Page
    static String victimPage;   //Page to be replaced
    static List<String> requestList=new ArrayList<>();      //List of requests by a process
    static int num_request;     //number of requests
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
        int index;
        int count=0;
        /*
        This loop replaces pages as per FIFO algorithm
        */
        while(!requestList.isEmpty())
        {
            currPage=requestList.remove(0);
            if(!frame.contains(currPage))
            {
                victimPage=(String)pagingQueue.poll();
                pagingQueue.offer(currPage);
                index=frame.indexOf(victimPage);
                frame.set(index, currPage);
                System.out.println("Miss");
                count++;
            }
            System.out.println(frame);
        }
        System.out.println("Miss ratio= "+(size+count)+"/"+num_request);
    }
}
