
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Mapusage{
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        Map<Integer , Integer> Maps=new  HashMap<>();
      System.out.println("Enter the Roll and Marks of the Studet: ");
      for(Map.Entry<Integer, Integer> i : Maps.entrySet()){
        int roll=sc.nextInt();
        int Marks=sc.nextInt();
        Maps.put(roll,Marks);
      }
        // int rollno=102;
        // if(Maps.containsKey(rollno)){
        //     System.out.println("marks of roll " + rollno + " : " + Maps.get(rollno));
        // }
        // else{
        //     System.out.println("Student not found");
        // }
        // Maps.remove(106); 
        // int highestMarks=Integer.MIN_VALUE;
        // int highRoll=-1;
        // System.out.println("print all the student marks: ");
        // int countPass=0;
        // int countFail=0;
    //     for(Map.Entry<Integer ,Integer> i : Maps.entrySet()){
    //             //  System.out.println("Roll: " + i.getKey() + ", Marks : " + i.getValue());
    //             // if(i.getValue() > highestMarks){
    //             //    highestMarks=i.getValue();
    //             //    highRoll=i.getKey();
                    
    //             // }
    //             if(i.getValue() >=40){
    //                 countPass++;
    //             }
    //             else{
    //                 countFail++;
    //             }

    //     }
    //     System.out.println("The  number of the students Pass  are :" + countPass +"\n The no.of Students Fail are : " + countFail);

    }
}