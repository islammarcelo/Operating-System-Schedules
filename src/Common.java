import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Common {
    public ArrayList<Process> executionProcesses = new ArrayList<>();
    public Queue<Process> processQueue = new LinkedList<>();
    public int startTime = 1;

    public int getFinishTime(Process process){

        int f = -1;

        for (Process i : executionProcesses){
            if (process.getName().equals(i.getName()))
                f = i.finish;
        }

        return f;
    }

    public void waitingTimeForEachProcess(ArrayList<Process> processes){
        int sum = 0;
        double ave = 0.0;
        for(int i = 0; i < processes.size(); i++){
            for (int j = 0; j < executionProcesses.size();  j++){
                if(executionProcesses.get(j).getName().equals(processes.get(i).getName()))
                    sum+= executionProcesses.get(j).wait;
            }
            ave+=sum;
            System.out.print(processes.get(i).getName()+" = "+sum);
            System.out.print(" ");
            sum = 0;
        }
        System.out.println();
        System.out.println("Average = " + ave/processes.size()+1);
    }

    public void turnaroundTimeForEachProcess (ArrayList<Process> processes){
        int tTime = 0;
        double ave = 0.0;
        for(int i = 0; i < processes.size(); i++){
            for (int j = 0; j < executionProcesses.size();  j++){
                if(executionProcesses.get(j).getName().equals(processes.get(i).getName()))
                    tTime = executionProcesses.get(j).finish;
            }

            ave+=tTime-processes.get(i).getArrivalTime();
            System.out.print(processes.get(i).getName()+" = "+(tTime-processes.get(i).getArrivalTime()));
            System.out.print(" ");
        }
        System.out.println();
        System.out.println("Average = " + ave/processes.size()+1);

    }

    public void print(){
       System.out.println("Execution Of Processes");
       for (Process p : executionProcesses)
           System.out.print(p.getName()+" ");

       System.out.println();
    }
}
