import java.util.concurrent.Semaphore;

public class Scheduler implements Runnable
{
    private final Queue PROC = new Queue();
    private Thread me;
    public final Semaphore CPU = new Semaphore(1);

    public Scheduler(){ this.me = new Thread(this, "SCHEDULER");}

    @Override
    public void run()
    {
        while(!this.PROC.isEmpty())
        {
            Flow flow = this.PROC.getHead().getFlow();

            if (flow.state == States.FINISHED) PROC.remove();
            else
            {
                flow.state = States.RUNNING;
                flow.run();
                PROC.pass();
            }
        }
    }

    public void add(Flow f)
    {
        if(this.PROC.isEmpty())
        {
            this.PROC.add(f);
            f.state = States.READY;
            me.start();
        }
        else
        {
            this.PROC.add(f);
            f.state = States.READY;
        }
    }


    public void interrupt()
    {
        me.interrupt();
    }
}
