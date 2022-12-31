import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class MultiScheduling {
    public ArrayList<Process> executionProcesses = new ArrayList<>();
    public Queue<Process> processQueue = new LinkedList<>();
    public Queue<Process> allProcess = new LinkedList<>();
    public ArrayList<Process> FCFS = new ArrayList<>();
    public Queue<Process> waitQueue = new LinkedList<>();
    public int startTime = 0;
    public int q = 2;

    private void roundRobinSchedule(int q) {

        Process process = new Process();
        buildWaitQueue();
        int arrival;


        while (!waitQueue.isEmpty()) {

            process = waitQueue.remove();
            Process p = new Process();
            int f = getFinishTime(process);
            arrival = (f == -1) ? process.getArrivalTime() : f;

            if (process.getBurstTime() < q) {

                p.setName(process.getName());
                p.wait = startTime - arrival;
                p.finish = startTime + process.getBurstTime();
                startTime += process.getBurstTime();
                executionProcesses.add(p);


            }
            else if(processQueue.isEmpty() && waitQueue.isEmpty()){
                p.setName(process.getName());
                p.wait = executionProcesses.get(executionProcesses.size() - 1).finish - arrival;
                p.finish = startTime + process.getBurstTime();
                executionProcesses.add(p);
                startTime += process.getBurstTime();
            }
            else {
                p.setName(process.getName());
                p.wait = startTime - arrival;
                p.finish = startTime + q;
                startTime += q;
                executionProcesses.add(p);
                process.setBurstTime(process.getBurstTime() - q);
                if (!processQueue.isEmpty()) {
                    buildWaitQueue();
                }
                if (process.getBurstTime() != 0) {
                    waitQueue.add(process);
                }
            }
        }


    }

    private void buildWaitQueue() {

        for (Process process : processQueue) {
            if (process.getArrivalTime() <= startTime && process.getQueueName().equals("1")) {
                waitQueue.add(process);
            }

        }

        for (Process process : waitQueue) {
            processQueue.remove(process);
        }

    }

    public int getFinishTime(Process process){

        int f = -1;

        for (Process i : executionProcesses){
            if (process.getName().equals(i.getName()))
                f = i.finish;
        }

        return f;
    }

    public void multiScheduling(){
        buildQueues();
        int sumBurstTime = sumTime();
        roundRobinSchedule(q);
        while (startTime < sumBurstTime){
            if (waitQueue.isEmpty())
                fcfsSchedul();
//            else if (processQueue.isEmpty() && waitQueue.isEmpty())
//                break;
        }
    }

    private void fcfsSchedul() {


        int arrival;

        //int time = FCFS.get(0).getBurstTime();
        while (!FCFS.isEmpty()){
            boolean flag = false;
            int time = FCFS.get(0).getBurstTime();
            Process p = new Process();
            p.setName(FCFS.get(0).getName());
            p.setArrivalTime(FCFS.get(0).getArrivalTime());

            innerloop:
            for (int i = 0; i < time; i++){
                startTime++;
                FCFS.get(0).setBurstTime(FCFS.get(0).getBurstTime()-1);
                if (!processQueue.isEmpty() && processQueue.peek().getArrivalTime() == startTime){
                    int f = getFinishTime(p);
                    arrival = (f == -1) ? p.getArrivalTime() : f;
                    int t =  (executionProcesses.isEmpty()) ? 1 : executionProcesses.get(executionProcesses.size() - 1).finish;
                    p.wait = t - arrival;
                    p.finish = startTime;
                    executionProcesses.add(p);
                    flag = true;
                    roundRobinSchedule(q);

                    break innerloop;
                }
            }
            if (!flag){
                int f = getFinishTime(p);
                arrival = (f == -1) ? p.getArrivalTime() : f;
                int t =  (executionProcesses.isEmpty()) ? 1 : executionProcesses.get(executionProcesses.size() - 1).finish;
                p.wait = t - arrival;
                p.finish = startTime;
                executionProcesses.add(p);
            }
            updateFCFS(); //update FCFSArrayList
        }

    }

    //To delete First element if burst time equal 0
    private void updateFCFS() {
        if (FCFS.get(0).getBurstTime() == 0)
            FCFS.remove(0);
    }

    private int sumTime() {
        int sum = 0;
        for (Process p : allProcess)
            sum += p.getBurstTime();
        return sum;
    }

    private void buildQueues (){
        for (Process p : allProcess){
            if(p.getQueueName().equals("1"))
                processQueue.add(p);
            else FCFS.add(p);
        }
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
        System.out.println("Average Waiting = " + ave/processes.size()+1);
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
        System.out.println("Average Turnaround = " + ave/processes.size()+1);

    }

    public void print(){
        System.out.println("Execution Of Processes");
        for (Process p : executionProcesses)
            System.out.print(p.getName()+" ");

        System.out.println();
    }
}
