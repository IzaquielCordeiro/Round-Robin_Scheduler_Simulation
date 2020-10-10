public class Scheduler
{
    public static int N_PROCESS = 4;
    public static int[] PROCESSES_TIME = {3000, 9000, 4500, 6000};
    public static int N_THREADS= 0;
    public static int EXECUTION_TIME = 2100;
    public static int PROCESS_TIME = 2000;
    public static int THREAD_TIME = 10000;
    private static Queue<Flow> FLOWS = new Queue(N_PROCESS+N_THREADS);

    private static Flow ExecutingFlow;

    public static void main(String[] args) throws InterruptedException
    {
        for (int i=1; i<=N_PROCESS; i++){FLOWS.add(new Process(i, PROCESSES_TIME[i-1]));}
        //for (int i=0; i<=(N_THREADS); i++){FLOWS.set(i, new Thread(1, 1000));}

        while(true)
        {
            if(!FLOWS.getHead().getState().equals(Flow.States.FINISHED))
            {
                long sys_start_time = System.currentTimeMillis();
                long next_time_slice_interrupt = sys_start_time + EXECUTION_TIME;

                ExecutingFlow = FLOWS.getHead();
                ExecutingFlow.setReady();
                ExecutingFlow.run(next_time_slice_interrupt);
            }
            FLOWS.next();
        }

    }

}
