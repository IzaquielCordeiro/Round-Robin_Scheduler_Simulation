import static java.lang.Thread.sleep;

public abstract class Flow implements Runnable
{
    protected int id;
    public enum States {RUNNING, BLOCKED, READY, CREATED};
    public enum Types {PROCESS, THREAD};
    protected Types type;
    protected States state;
    protected int time_execution;

    public Flow(int id, int time_execution, Types t)
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

    public void run(long next_time_slice_interrupt){
        if (this.state.equals(States.READY))
        {
            this.state = States.RUNNING;
            while(System.currentTimeMillis()<next_time_slice_interrupt)
            {
                try
                {
                    sleep(300);
                    System.out.println(type + " " + id + " " + this.state);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }
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

    public States getState(){return this.state;}
}
