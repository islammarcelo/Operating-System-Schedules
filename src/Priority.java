import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Priority extends Common {

    private Process getHighPriority(){

        Process process = new Process();
        ArrayList<Process> processArrayList = new ArrayList<>();

        for (Process p : processQueue){
            if(p.getArrivalTime() < startTime)
                processArrayList.add(p);
        }
        int priority = 1000;
        for (Process p : processArrayList){
            if (p.getPriority() < priority) {
                priority = p.getPriority();
                process = p;
            }
        }
        //After Get The Small Time Remove Process From Process Queue
        processQueue.remove(process);
        return process;
    }

    private void updateQueueProcess(Process process){

        //For Delete Process has Burst Time equal Zero
        Queue<Process> q = new LinkedList<>();
        for (Process p : processQueue){
            if(p.getBurstTime() == 0)
                q.add(p);
        }
        for (Process p : q){
            processQueue.remove(p);
        }
    }

    public void priorityScheduling(){

        Process process = new Process();
        process = getHighPriority();
        int arrival;
        int starvation = 0;

        while (!processQueue.isEmpty()){

            int time = process.getBurstTime();
            Process p = new Process();
            p.setName(process.getName());
            p.setArrivalTime(process.getArrivalTime());

            innerloop:
            for (int i = 0; i < time; i++){
                process.setBurstTime(process.getBurstTime()-1);
                processQueue.add(process);
                startTime++;
                updateQueueProcess(process);
                process = getHighPriority();
                starvation++; //For starvation problem
                if (process.getName().equals(p.getName()))
                    continue;
                else {
                    int f = getFinishTime(p);
                    arrival = (f == -1) ? p.getArrivalTime() : f;
                    int t =  (executionProcesses.isEmpty()) ? 1 : executionProcesses.get(executionProcesses.size() - 1).finish;
                    p.wait = t - arrival;
                    p.finish = startTime-1;
                    executionProcesses.add(p);

                    break innerloop;
                }

            }
            if(starvation >= 3 && processQueue.size() != 0){
                increasePriority();
                starvation = 0;
            }
        }

        //For Last process because at last process outer from while loop as the process queue is empty.
        int f = getFinishTime(process);
        arrival = (f == -1) ? process.getArrivalTime() : f;
        process.wait = startTime - arrival;
        process.finish = startTime + process.getBurstTime();
        executionProcesses.add(process);



    }

    //For Starvation
    private void increasePriority() {
        Process process = new Process();
        process = processQueue.peek();
        for (Process p : processQueue){
            if(process.getPriority() > p.getPriority())
                process = p;
        }
        processQueue.remove(process);
        process.setPriority(process.getPriority()-1);
        processQueue.add(process);
    }

}
