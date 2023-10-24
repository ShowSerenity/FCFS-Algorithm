import java.util.Scanner;

class Process {
    String name;
    int arrivalTime;
    int burstTime;

    public Process(String name, int arrivalTime, int burstTime) {
        this.name = name;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n;
        System.out.print("Enter the number of processes: ");
        n = scanner.nextInt();

        Process[] processes = new Process[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Enter the name of process " + (i + 1) + ": ");
            String name = scanner.next();
            System.out.print("Enter the arrival time of process " + (i + 1) + ": ");
            int arrivalTime = scanner.nextInt();
            System.out.print("Enter the burst time of process " + (i + 1) + ": ");
            int burstTime = scanner.nextInt();
            processes[i] = new Process(name, arrivalTime, burstTime);
        }

        // Sort processes by arrival time
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (processes[j].arrivalTime > processes[j + 1].arrivalTime) {
                    Process temp = processes[j];
                    processes[j] = processes[j + 1];
                    processes[j + 1] = temp;
                }
            }
        }

        int currentTime = 0;
        double totalWaitingTime = 0;
        System.out.println("Process\tArrival Time\tBurst Time\tWaiting Time\tTurnaround Time");
        for (Process process : processes) {
            if (currentTime < process.arrivalTime) {
                currentTime = process.arrivalTime;
            }
            int waitingTime = currentTime - process.arrivalTime;
            totalWaitingTime += waitingTime;
            int turnaroundTime = waitingTime + process.burstTime;
            System.out.println(process.name + "\t" + process.arrivalTime + "\t\t" + process.burstTime + "\t\t"
                    + waitingTime + "\t\t" + turnaroundTime);
            currentTime += process.burstTime;
        }

        double averageWaitingTime = totalWaitingTime / n;
        System.out.println("Average Waiting Time: " + averageWaitingTime);
        scanner.close();
    }
}
