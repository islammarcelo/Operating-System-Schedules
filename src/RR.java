import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class RR extends Common {

    public Queue<Process> waitQueue = new LinkedList<>();

    public void roundRobinSchedule(int q) {

        Process process = new Process();
        buildWaitQueue();
        int arrival;


        while (!waitQueue.isEmpty()) {

            process = waitQueue.remove();
            Process p = new Process();
            int f = getFinishTime(process);
            arrival = (f == -1) ? p.getArrivalTime() : f;

            if (process.getBurstTime() < q) {

                p.setName(process.getName());
                p.wait = startTime - arrival;
                p.finish = startTime + process.getBurstTime();
                startTime += process.getBurstTime() + 1;
                executionProcesses.add(p);
                Process context = new Process();
                context.setName("c");
                executionProcesses.add(context);


            } else {
                p.setName(process.getName());
                p.wait = startTime - arrival;
                p.finish = startTime + q;
                startTime += q + 1; // +1 34an el context
                executionProcesses.add(p);
                Process context = new Process();
                context.setName("c");
                executionProcesses.add(context);
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
            if (process.getArrivalTime() < startTime) {
                waitQueue.add(process);
            }
        }

        for (Process process : waitQueue) {
            processQueue.remove(process);
        }

    }






}
