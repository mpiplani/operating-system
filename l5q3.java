import java.util.ArrayList;
import java.util.Scanner;
public class nextfit 
{
public static void main(String[] args) 
{
int i;
int j;
int flag=0;     //flag =0  memory not allocated, flag =1 memory not allocated
Scanner scan = new Scanner(System.in);
System.out.println("Enter the no. of processes:");
int n = scan.nextInt();
int r[] = new int[n];
System.out.println("Enter no. of Blocks:");
int l = scan.nextInt();
ArrayList<Integer> list1 = new ArrayList<Integer>();   //Memory map 
ArrayList<Integer> occupy = new ArrayList<Integer>();    //signify whether place is occupied or not, occuppy=1,unoccupied =0
System.out.println("Enter requirement of each process");
for(i=0;i<n;i++)
{
r[i]=scan.nextInt();
}
System.out.println("Enter Size of each block");
for(i=0;i<l;i++)
{
 j = scan.nextInt(); 
 list1.add(j);
 occupy.add(0);
}
int t,k,h,re;   //re - checks that whole map is travelled
h=0;      // signifies last allocated place
for(j=0;j<n;j++)
{
   flag=0;
    i=(h%list1.size());      //gives next position to start in memory map 
    re=1;
   System.out.println("NOW MEMORY MAP  HAS:");
   for(k=0;k<list1.size();k++)
   {
       System.out.print(list1.get(k));
       System.out.println("  " +occupy.get(k));
   }
while(i<list1.size()&&re<=list1.size())         //itterate until whole map is travelled
{  i++;   
   if(j==0)
   i=0;
   re++;
   if(i>=list1.size())
      i=i%list1.size();
     t=list1.get(i);
    
    
     if(r[j]<list1.get(i)&&occupy.get(i)==0)      // fragment with size less than required by 
    {
        occupy.remove(i);                          //updates occupy 
        occupy.add(i,0);
        list1.remove(i);
        list1.add(i,t-r[j]);                     //fragments memory into 2 parts - 1.occupied 2.leftover
        list1.add(i,r[j]);
        occupy.add(i,1);
        h=i;                                     //store next position to store
        flag =1;                                    //memory allocated
        break;
    }
    if(r[j]==list1.get(i)&&occupy.get(i)==0)
    {
        occupy.remove(i);                       //updates occupy 
        occupy.add(i,1); 
        h=i;
        flag=1;                                 //memory allocated
        break;
    }
   
}
if(flag==0)                                   //if no memory allocated
System.out.println("PROCESS"+j+" COULD NOT BE ALLOCATED MEMORY");
}
System.out.println(" MEMORY MAP FINALLY HAS :");
   for(k=0;k<list1.size();k++)
   {
       System.out.print(list1.get(k));         //display each block
       System.out.println("  " +occupy.get(k)); //occupied=1,unoccupied=0
   }
    
}
}
