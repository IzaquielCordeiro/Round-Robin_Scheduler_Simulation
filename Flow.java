import static java.lang.Thread.sleep;

public abstract class Flow implements Runnable
{
    protected int id;
    public enum States {RUNNING, BLOCKED, READY, CREATED, FINISHED};
    public enum Types {PROCESS, THREAD};
    protected Types type;
    protected States state;
    protected long time_execution;

    public Flow(int id, long time_execution, Types t)
    {
        this.id = id;
        this.state = States.CREATED;
        this.time_execution = time_execution;
        this.type = t;
        System.out.println(type + " " + id + " " + this.state);
    }

    @Override
    public void run()
    {
        this.state = States.READY;
    }

    public void run(long next_time_slice_interrupt)
    {
        long start = System.currentTimeMillis();
        if (this.state.equals(States.READY))
        {
            this.state = States.RUNNING;
            System.out.println(type + " " + id + " " + this.state);
            while(this.state.equals(States.RUNNING)) work(start, next_time_slice_interrupt);
        }
    }

    protected void work(long from, long until)
    {
        long now = from;
        while(now < until)
        {
            this.time_execution -= now-from;
            from = now;
            if (this.time_execution <= 0)
            {
                this.finish();
                return;
            }
            else now = System.currentTimeMillis();
        }
        this.block();
    }

    public void finish()
    {
        this.state = States.FINISHED;
        System.err.println(type + " " + id + " " + this.state);
    }

    public void setReady()
    {
        this.state = States.READY;
        System.out.println(type + " " + id + " " + this.state);
    }

    public void block()
    {
        this.state = States.BLOCKED;
        System.out.println(type + " " + id + " " + this.state);
    }

    public void fisinh()
    {
        this.state = States.FINISHED;
        System.out.println(type + " " + id + " " + this.state);
    }

    public States getState(){return this.state;}
}
