import java.util.*;
public class Memory
{
    public static void bestfit(ArrayList<String> segment,ArrayList<String> process_list)
    {
        ArrayList<String> temp_segment=segment;
        firstfit(temp_segment,process_list);
    }
    public static void worstfit(ArrayList<String> segment,ArrayList<String> process_list)
    {
        ArrayList<String> temp_segment=segment;
        Collections.reverse(temp_segment);
        firstfit(temp_segment,process_list);
    }
    public static void nextfit(ArrayList<String> segment,ArrayList<String> process_list)
    {
        ArrayList<String> temp_segment=segment;
        ArrayList<String> temp_process=process_list;
        String curr_process,curr_segment;
        int index=0;
        int flag=0;
        while(!process_list.isEmpty())
        {
            flag=0;
            index=index%temp_segment.size();
            curr_process=temp_process.get(0);
            while(index<temp_segment.size())
            {
                curr_segment=temp_segment.get(index);
                try
                {
                    if(Integer.parseInt(curr_segment)>Integer.parseInt(curr_process))
                    {
                        temp_segment.remove(index);
                        temp_segment.add(index,curr_process+"MB");
                        temp_segment.add(index+1,Integer.toString((Integer.parseInt(curr_segment)-Integer.parseInt(curr_process))));
                        temp_process.remove(0);
                        flag=1;
                        break;
                    }
                    else if(Integer.parseInt(curr_segment)==Integer.parseInt(curr_process))
                    {
                        temp_segment.remove(index);
                        temp_segment.add(index,curr_process+"MB");
                        temp_process.remove(0);
                        flag=1;
                        index=index+2;
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
                String removed=temp_process.remove(0);
                System.out.println("This process can't be added. Too large memory requirement. Size is "+removed);
            }
        }
        display(temp_segment);   
    }
    public static void firstfit(ArrayList<String> segment,ArrayList<String> process_list)
    {
        ArrayList<String> temp_segment=segment;
        ArrayList<String> temp_process=process_list;
        String curr_process,curr_segment;
        int index=0;
        int flag=0;
        while(!process_list.isEmpty())
        {
            flag=0;
            index=0;
            curr_process=temp_process.get(0);
            while(index<temp_segment.size())
            {
                curr_segment=temp_segment.get(index);
                try
                {
                    if(Integer.parseInt(curr_segment)>Integer.parseInt(curr_process))
                    {
                        temp_segment.remove(index);
                        temp_segment.add(index,curr_process+"MB");
                        temp_segment.add(index+1,Integer.toString((Integer.parseInt(curr_segment)-Integer.parseInt(curr_process))));
                        temp_process.remove(0);
                        flag=1;
                        break;
                    }
                    else if(Integer.parseInt(curr_segment)==Integer.parseInt(curr_process))
                    {
                        temp_segment.remove(index);
                        temp_segment.add(index,curr_process+"MB");
                        temp_process.remove(0);
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
                String removed=temp_process.remove(0);
                System.out.println("This process can't be added. Too large memory requirement. Size is "+removed);
            }
        }
        display(temp_segment);
    }
    public static void display(ArrayList<String> temp_segment)
    {
        System.out.println(temp_segment);
    }
    public static void main(String args[])
    {
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
            process_list.add(Integer.toString(sc.nextInt()));
        System.out.println("First Fit");
        firstfit(new ArrayList<>(segment),new ArrayList<>(process_list));
        System.out.println("Next Fit");
        nextfit(new ArrayList<>(segment),new ArrayList<>(process_list));
        ArrayList<Integer> temp_segment=new ArrayList<>();
        for(int i=0;i<segment.size();i++)
            temp_segment.add(Integer.parseInt(segment.get(i)));
        Collections.sort(temp_segment);
        System.out.println("Sorted Memory Map");
        System.out.println(temp_segment);
        ArrayList<String> temp_segment2=segment;
        segment=new ArrayList<>();
        System.out.println("Best Fit");
        for(int i=0;i<temp_segment.size();i++)
            segment.add(Integer.toString(temp_segment.get(i)));
        bestfit(new ArrayList<>(segment),new ArrayList<>(process_list));
        worstfit(new ArrayList<>(segment),new ArrayList<>(process_list));
    }
}
