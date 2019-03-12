/*
Problem Statement received via Internshala Chat (11/03/19 @1601 hrs) :

    In this problem, there are types of events :  ENTER (a student enters the queue) or SERVED.
    A unique token is assigned to any student entering the queue.
    The queue serves the students based on the following criteria:
        The student having the highest Cumulative Grade Point Average (CGPA) is served first.
        Any students having the same CGPA will be served by name in ascending case-sensitive alphabetical order. 
        Any students having the same CGPA and name will be served in ascending token order. 
    Given a sequence of events, print the names of students who are yet to be served(based on above criteria).
    If the queue is empty, print EMPTY.
    Input Format
        The first line of input contains an integer, denoting the total number of events. 
        Each of the subsequent lines will be of the following two forms: 
            ENTER name CGPA token - The student to be inserted into the priority queue. 
            SERVED - The highest priority student in the queue was served.
        Constraints where where each token i is a unique integer. 
    Output Format
        Print the names (based on the criteria) of the students who are not served at all after executing all events; 
        if every student in the queue was served, then print EMPTY.
    Sample Input
        12
        ENTER John 3.75 50 
        ENTER Mark 3.8 24
        ENTER Shafaet 3.7 35 
        SERVED
        SERVED
        ENTER Samiha 3.85 36 
        SERVED 
        ENTER Ashley 3.9 42 
        ENTER Maria 3.6 46 
        ENTER Anik 3.95 49 
        ENTER Dan 3.95 50 
        SERVED 
    Sample Output
        Dan 
        Ashley
        Shafaet
        Maria 
*/

package ass1;

import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Scanner;

class student{

    int token;
    String name;
    float cgpa;

    public student(String name, float cgpa, int token){
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
                return student1.name.compareTo(student2.name);                  //lexicographic ordering as in Unicode, case sensitive
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
        try{
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
        catch(Exception e){
            System.out.println("Exception Handled : "+ e.toString());
        }
    }
    
    public static void main(String[] args){
        try{
            input = new Scanner(System.in); 
            noOfEvents = input.nextInt();
            if(noOfEvents>=0){
                pQueue = new PriorityQueue<>(100,new schedulerCriteria());
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
        catch(Exception e){
            System.out.println("Exception Handled : "+ e.toString());
        }
    }
    
}
