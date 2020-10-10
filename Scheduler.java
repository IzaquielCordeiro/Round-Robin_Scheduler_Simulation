import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import static java.lang.Thread.sleep;

public class Scheduler
{
    public static int N_PROCESS = 6;
    public static int N_THREADS= 0;
    public static int EXECUTION_TIME = 200;
    public static int PROCESS_TIME = 1000;
    public static int THREAD_TIME = 500;
    private static Queue<Flow> FLOWS = new Queue(N_PROCESS+N_THREADS);

    public static void main(String[] args) throws InterruptedException
    {
        for (int i=1; i<=N_PROCESS; i++){FLOWS.add(new Process(i, PROCESS_TIME));}
        //for (int i=0; i<=(N_THREADS); i++){FLOWS.set(i, new Thread(1, 1000));}

        while(true)
        {
            long sys_start_time = System.currentTimeMillis();
            long next_time_slice_interrupt = sys_start_time+EXECUTION_TIME;

            while(System.currentTimeMillis()<next_time_slice_interrupt)
            {
                FLOWS.getHead().setReady();
                FLOWS.getHead().run(next_time_slice_interrupt);
            }
            FLOWS.getHead().block();
            FLOWS.next();

        }

    }

}
