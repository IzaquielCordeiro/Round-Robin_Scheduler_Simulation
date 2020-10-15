import static java.lang.Thread.sleep;

public class PIC implements Runnable
{
    private final Scheduler scheduler;
    private final long TIME;

    public PIC(long time, Scheduler scheduler)
    {
        this.TIME = time;
        this.scheduler = scheduler;
        new Thread((Runnable)this, "PIC").start();
    }

    @Override
    public void run()
    {
        while(true)
        {
            try
            {
                sleep(TIME);
                scheduler.interrupt();
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}
