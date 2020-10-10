public class Process extends Flow
{
    public Process(int id, int time_execution)
    {
        super(id, time_execution, Types.PROCESS);
        new Thread((Runnable)this, "PROCESS " + id).start();
    }
}
