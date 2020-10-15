import static java.lang.Thread.sleep;

public class ProcessImp extends Flow
{
    int parent_id = 0;

    public ProcessImp(int id, int time_execution)
    {
        super(id, time_execution, Types.PROCESS);
        this.me = new Thread(this, Types.PROCESS + " " + id);
    }

    public ProcessImp(int id, ProcessImp parent)
    {
        super(id, parent.time_execution, Types.PROCESS);
        this.parent_id = parent.id;
        this.me = new Thread(this, Types.PROCESS + " " + id);
    }

    public void call()
    {

    }

    public ProcessImp fork(int id)
    {
        return new ProcessImp(id, this);
    }


}
