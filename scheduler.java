package ass1;

import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;

class student{
    int token;
    String name;
    float cgpa;

    public student(String name, float cgpa, int token) {
        this.name = name;
        this.cgpa = cgpa;
        this.token = token;
    }
}
class schedulerCriteria implements Comparator<student>{
    @Override
    public int compare(student student1, student student2){
       
        if(Float.compare(student1.cgpa,student2.cgpa)==0){
            if(student1.name.equals(student2.name)){
                    return Integer.compare(student2.token, student1.token);
            }
            else {
                return student1.name.compareTo(student2.name);
            }
        }
        else{
            return Float.compare(student2.cgpa, student1.cgpa);
        }
    }
}
public class scheduler {
    
    static Scanner input;
    static int noOfEvents, token, eventNo;
    static String name, eventType;
    static float cgpa;
    static PriorityQueue <student> pQueue;
    static student enteringStudent;
    
    static void readEvent(){
        
        eventType = input.next();
        if(eventType.equals("ENTER")==true){
            
            name = input.next();
            cgpa = input.nextFloat();
            token = input.nextInt();
            enteringStudent = new student(name, cgpa, token);
            
            pQueue.add(enteringStudent);
        }
        else if(eventType.equals("SERVED")==true){
                pQueue.poll();
        }
        else{
            System.out.println("Erroneous format in Event no " + eventNo);
        }
    }
    public static void main(String[] args) {
        
        input = new Scanner(System.in); 
        noOfEvents = input.nextInt();
    
        if(noOfEvents>=0){
            
            pQueue = new PriorityQueue<student>(100,new schedulerCriteria());
            
            for(eventNo=1;eventNo<=noOfEvents;++eventNo){
                readEvent();
            }
           
            if(pQueue.isEmpty()==true){
                System.out.println("EMPTY");
            }
            else{
                while(pQueue.isEmpty()!=true){
                    System.out.println(pQueue.poll().name);
                }
            }
        }
        else{
            System.out.println("Invalid no of events");
        }
    }
}
