
import java.util.HashMap;
import java.util.Map;


public class Mapusage{
    public static void main(String[] args) {
        Map<Integer , Integer> Maps=new  HashMap<>();
        Maps.put(101,39);
        Maps.put(102,98);
        Maps.put(103,34);
        Maps.put(104,56);
        Maps.put(105,87);
        Maps.put(106,87);
        int rollno=102;
        if(Maps.containsKey(rollno)){
            System.out.println("marks of roll " + rollno + " : " + Maps.get(rollno));
        }
        else{
            System.out.println("Student not found");
        }
        Maps.remove(106); 
        System.out.println("print all the student marks: ");
        System.out.println();
        for(Map.Entry<Integer ,Integer> i : Maps.entrySet()){
                 System.out.println("Roll: " + i.getKey() + ", Marks : " + i.getValue());
        }
    }
}