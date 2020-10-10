public class Queue<Flow>
{
    private int head = 0;
    private int tail = -1;
    private Flow[] flows;

    public Queue(int lenght){
        this.flows = (Flow[]) new Object[lenght];
    }

    public Flow add(Flow f)
    {
        this.flows[++tail] = f;
        return get(tail);
    }

    public Flow get(int i)
    {
        return this.flows[i];
    }

    public Flow getHead()
    {
        return this.flows[head];
    }

    public Flow next()
    {
        this.tail = this.head;
        this.head = (this.head+1)%flows.length;
        return get(head);
    }

}
