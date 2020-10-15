import java.util.concurrent.Callable;

import static java.lang.Thread.sleep;

public abstract class Flow implements Runnable
{
    protected int id;
    protected Types type;
    protected States state;
    protected long time_execution;
    protected Thread me;
    private long START_TIME;

    public Flow(int id, long time_execution, Types t)
    {
        this.id = id;
        this.state = States.CREATED;
        this.time_execution = time_execution;
        this.type = t;
        this.START_TIME = System.currentTimeMillis();
    }

    @Override
    public void run()
    {
        try
        {
            System.out.println(this);
            sleep(time_execution);
            this.state = States.FINISHED;
        } catch (InterruptedException e)
        {
            this.time_execution -= System.currentTimeMillis() - START_TIME;
            this.state = States.READY;
            System.err.println("INTERRUPTED");
        }

        if(time_execution <= 0)
        {
            this.state = States.FINISHED;
            this.me.interrupt();
            System.out.println(this);
        }

    }

    public void finish()
    {
        this.state = States.FINISHED;
        System.err.println(this);
    }

    public States getState(){return this.state;}

    public ThreadImp createThread(int id, Callable func)
    {
        return new ThreadImp(id, this.id, func);
    }

    public void join(ThreadImp t)
    {
        System.out.println(this);

        try
        {
            t.mutex.acquire();
            t.mutex.release();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public String toString()
    {
        return this.type + " " + this.id + " " + this.state;
    }
}
