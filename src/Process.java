public class Process {

    private String name;
    private int arrivalTime;
    private int burstTime;
    private int priority;
    private String queueName;
    int wait;
    int finish;

    public Process() { }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getBurstTime() { return burstTime; }

    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }

    public String getQueueName() { return queueName; }

    public void setQueueName(String queueName) { this.queueName = queueName; }

    public int getPriority() { return priority; }

    public void setPriority(int priority) { this.priority = priority; }
}
