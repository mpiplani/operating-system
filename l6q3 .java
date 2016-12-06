import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
    Input Format:-  Set of integers is given as input.
                    Each process is assigned a process name.
                    Process name for a given process is given by "Process"+entry number.
                    Suppose 1,2,3 are given as input, then "Process 0" is name of 1
                    "Process 1" is name of 2 and "Process 2" is name of 3.
    Output Format:- First, output of first fit is shown, then output of adjacent
                    compaction is shown and finally output of other compaction( I don't
                    know the name) is shown.
                    Assume memory map is 2,2,2,2,2 and process requirement is 1,2,3,4,5
                    then, O/P of firstfit algorithm will be 
                    1MB Process 0
                    1
                    2MB Process 1
                    2
                    2
                    2
                    
    Format Explanation:-   If request of a process is success, then "MB" and process ID is appended
                           to it. If memory segment/fragment is too small to hold a process, then
                           nothing is appended to it. Same covention is followed for other O/P.
*/
public class Compaction
{
   static int count=0;
   static ArrayList<String> unallocated_process;        //stores the list of unallocated processes .
   static ArrayList<String> process_name;               //stores the name of processes.
   //This algo is copied from my previous submission
   public static void firstfit(ArrayList<String> temp_segment,ArrayList<String> temp_process)
    {
        unallocated_process=new ArrayList<>();
        String curr_process,curr_segment;
        int index=0;
        int flag=0;
        int count=0;
        while(!temp_process.isEmpty())
        {
            flag=0;
            index=0;
            /*
            At end of each iteration, process at 0 index is removed, hence current process
            always exists on 0th position
            */
            curr_process=temp_process.get(0);
            
            /*
            This loop is searches first memory which fits for current process.
            */
            while(index<temp_segment.size())
            {
                curr_segment=temp_segment.get(index);
                try
                {
                    if(Integer.parseInt(curr_segment)>Integer.parseInt(curr_process))
                    {
                        temp_segment.remove(index);
                        temp_segment.add(index,curr_process+"MB "+process_name.get(0));
                        temp_segment.add(index+1,Integer.toString((Integer.parseInt(curr_segment)-Integer.parseInt(curr_process))));
                        temp_process.remove(0);
                        process_name.remove(0);
                        flag=1;
                        break;
                    }
                    else if(Integer.parseInt(curr_segment)==Integer.parseInt(curr_process))
                    {
                        temp_segment.remove(index);
                        temp_segment.add(index,curr_process+"MB "+process_name.get(0));
                        temp_process.remove(0);
                        process_name.remove(0);
                        flag=1;
                        break;
                    }
                }
                catch(Exception E)
                {
                    
                    index++;
                    continue;
                }
                index++;
            }
            if(flag==0)
            {
                String unallocated=temp_process.remove(0);
                unallocated_process.add(unallocated);
                process_name.add(process_name.remove(0));
            }
        }
        display(temp_segment);
    }
    public static void display(ArrayList<String> temp_segment)
    {
        for(int i=0;i<temp_segment.size();i++)
        {
            System.out.println(temp_segment.get(i));
        }
    }

    public static void doAdjacentCompaction(ArrayList<String> segment)
    {
        Integer temp;
        int num;
        for(int i=0;i<segment.size();i++)
        {
            /*
            This block of code is main logic here.
            For understanding this, it is required that you know
            O/P format commented above
            
            Integer.parseInt(value) will generate a error if a non integer value is 
            attempted to convert into a integer.
            
            O/P of firstfit will attach a string literal to each allocated segment,
            hence, when they are parsed, they will throw a error, catch block will continue the
            execution.
            
            For compaction, atleast two adjacent unallocated segments/fragments are required
            Also, my firstfit algorithm leaves unallocated segments as they were i.e they can be parsed.
            So, they are parsed and added in list untill a exception occures. Ultimately, the O/P
            will be adjacent compaction.
            */
            try
            {
                for(;;)
                {
                    temp=Integer.parseInt(segment.get(i))+Integer.parseInt(segment.get(i+1));
                    segment.remove(i);
                    segment.remove(i);
                    segment.add(i,Integer.toString(temp));
                }
            }
            catch(Exception E)
            {
                continue;
            }
        }
        display(segment);
        System.out.println("FirstFIt after adjacent compaction");
        firstfit(segment,new ArrayList<>(unallocated_process));
    }
    
    /*
    A logic similar to function above is used here.
    If a segment is fragmented/unallocated, it is summed up and
    stored in a variable
    else
    An exeception will be raised if we try to parse a non integer value.
    So, whenever an exception will occur, we would add the process to our original
    memory map. At last, sum would be parsed and added to List<String> segment;
    */
    public static void doOtherCompaction(ArrayList<String> segment)
    {
        List<String> temp_segment=segment;
        segment=new ArrayList<>(segment.size());
        int temp_sum=0;
        int i=0;
        while(!temp_segment.isEmpty())
        {
            try
            { 
                temp_sum+=Integer.parseInt(temp_segment.get(0));
                temp_segment.remove(0);
            }
            catch(Exception E)
            {
                segment.add(temp_segment.remove(0));
            }
        }
        segment.add(Integer.toString(temp_sum));
        display(segment);
        System.out.println("FirstFit after compaction");
        firstfit(segment,unallocated_process);
    }
    
    public static void main(String args[])
    {
        process_name=new ArrayList<>();
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter number of segments.");
        int t=sc.nextInt();
        ArrayList<String> segment=new ArrayList<>(t);
        System.out.println("Enter size of segments.");
        for(int i=0;i<t;i++)
            segment.add(Integer.toString(sc.nextInt()));
        System.out.println("Enter number of process.");
        int n=sc.nextInt();
        ArrayList<String> process_list=new ArrayList<>(n);
        System.out.println("Enter processes requirements.");
        for(int i=0;i<n;i++)
        {
            process_name.add("Process "+i);
            process_list.add(Integer.toString(sc.nextInt()));
        }
        System.out.println("First Fit");
        System.out.println("Before Comapction. First fit algorithm.");
        firstfit(segment,new ArrayList<>(process_list));
        System.out.println("After adjacent Compaction.");
        doAdjacentCompaction(new ArrayList<>(segment));
        System.out.println("After other compaction");
        doOtherCompaction(new ArrayList<>(segment));
    }
}