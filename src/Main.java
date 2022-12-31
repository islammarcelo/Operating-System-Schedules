import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        boolean x = false;
        int chose,nProcesses,bTime,aTime,priority;
        String pName,qName;
        Process process;
        Queue<Process> processes ;
        ArrayList<Process> p;
        Scanner in = new Scanner(System.in);
        do {
            System.out.println("-------------------------------Chose Schedule-----------------------------");
            System.out.println("1- Preemptive Shortest- Job  First (SJF) Scheduling  with context switching ");
            System.out.println("2- Round Robin (RR) with context switching");
            System.out.println("3- Preemptive  Priority Scheduling (Provide a solution to avoid starvation problem)");
            System.out.println("4- Multi level Scheduling ");
            System.out.println("5- Exit ");

            System.out.println("Enter Your chose");
            chose = in.nextInt();
            switch (chose){

                case 1:
                    System.out.println("Enter Number Of Processes");
                    nProcesses = in.nextInt();
                    processes = new LinkedList<>();
                    p = new ArrayList<>();
                    for (int i = 1; i <= nProcesses; i++){
                        process = new Process();
                        process.setName("P"+i);
                        System.out.println("Enter burst Time Of " + process.getName());
                        bTime = in.nextInt();
                        process.setBurstTime(bTime);
                        System.out.println("Enter arrive Time Of " + process.getName());
                        aTime = in.nextInt();
                        process.setArrivalTime(aTime);
                        processes.add(process);
                        p.add(process);

                    }
                    SJF sjf = new SJF();
                    sjf.processQueue = processes;
                    sjf.shortestJobFirstScheduling();
                    sjf.print();
                    sjf.waitingTimeForEachProcess(p);
                    sjf.turnaroundTimeForEachProcess(p);
                    x = true;
                    break;
                case 2:
                    System.out.println("Enter Q");
                    int q = in.nextInt();
                    System.out.println("Enter Number Of Processes");
                    nProcesses = in.nextInt();
                    processes = new LinkedList<>();
                    p = new ArrayList<>();
                    for (int i = 1; i <= nProcesses; i++) {
                        process = new Process();
                        process.setName("P" + i);
                        System.out.println("Enter burst Time Of " + process.getName());
                        bTime = in.nextInt();
                        process.setBurstTime(bTime);
                        System.out.println("Enter arrive Time Of " + process.getName());
                        aTime = in.nextInt();
                        process.setArrivalTime(aTime);
                        processes.add(process);
                        p.add(process);
                    }
                    RR rr = new RR();
                    rr.processQueue = processes;
                    rr.roundRobinSchedule(q);
                    rr.print();
                    rr.waitingTimeForEachProcess(p);
                    rr.turnaroundTimeForEachProcess(p);
                    x = true;
                    break;
                case 3:
                    System.out.println("Enter Number Of Processes");
                    nProcesses = in.nextInt();
                    processes = new LinkedList<>();
                    p = new ArrayList<>();
                    for (int i = 1; i <= nProcesses; i++) {
                        process = new Process();
                        process.setName("P" + i);
                        System.out.println("Enter burst Time Of " + process.getName());
                        bTime = in.nextInt();
                        process.setBurstTime(bTime);
                        System.out.println("Enter arrive Time Of " + process.getName());
                        aTime = in.nextInt();
                        process.setArrivalTime(aTime);
                        System.out.println("Enter Priority Of " + process.getName());
                        priority = in.nextInt();
                        process.setPriority(priority);
                        processes.add(process);
                        p.add(process);
                    }
                    Priority priorityC = new Priority();
                    priorityC.processQueue = processes;
                    priorityC.priorityScheduling();
                    priorityC.print();
                    priorityC.waitingTimeForEachProcess(p);
                    priorityC.turnaroundTimeForEachProcess(p);
                    x = true;
                    break;

                case 4:
                    System.out.println("Enter Number Of Processes");
                    nProcesses = in.nextInt();
                    System.out.println("Enter Q");
                    int qq = in.nextInt();
                    processes = new LinkedList<>();
                    p = new ArrayList<>();
                    for (int i = 1; i <= nProcesses; i++) {
                        process = new Process();
                        process.setName("P" + i);
                        System.out.println("Enter burst Time Of " + process.getName());
                        bTime = in.nextInt();
                        process.setBurstTime(bTime);
                        System.out.println("Enter arrive Time Of " + process.getName());
                        aTime = in.nextInt();
                        process.setArrivalTime(aTime);
                        System.out.println("Enter Queue Name Of " + process.getName());
                        qName = in.next();
                        process.setQueueName(qName);
                        processes.add(process);
                        p.add(process);
                    }
                    MultiScheduling multiScheduling = new MultiScheduling();
                    multiScheduling.allProcess = processes;
                    multiScheduling.q = qq;
                    multiScheduling.multiScheduling();
                    multiScheduling.print();
                    multiScheduling.waitingTimeForEachProcess(p);
                    multiScheduling.turnaroundTimeForEachProcess(p);
                    x = true;
                    break;

                case 5:
                    break;
            }

        }while(x);
    }
}
