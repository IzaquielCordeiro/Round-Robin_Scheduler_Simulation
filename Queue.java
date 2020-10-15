public class Queue
{
    private Node head;
    private Node tail;

    public Node add(Flow f)
    {
        Node node = new Node(f);
        if(isEmpty())
        {
            this.head = node;
            this.tail = this.head;
            node.setNext(this.head);
            node.setPrev(this.tail);
        }
        else
        {
            node.setNext(this.head);
            node.setPrev(this.tail);
            this.tail.setNext(node);
            this.head.setPrev(node);
            this.tail = node;
        }

        return node;
    }

    public Node remove()
    {
        Node node = null;
        if(this.isEmpty() || this.head.equals(this.tail))
        {
            this.head = node;
            this.tail = node;
        }
        else
        {
            node = this.head;
            node.getPrev().setNext(node.getNext());
            node.getNext().setPrev(node.getPrev());
            this.tail = node.getPrev();
            this.head = node.getNext();
        }

        return node;
    }

    public Node pass()
    {
        if(this.head.getNext() != null)
        {
            this.tail = head;
            this.head = this.head.getNext();
            return this.head;
        }
        return this.head;
    }

    public Node getHead()
    {
        return this.head;
    }

    public boolean isEmpty(){ return this.head == null && this.tail == null;}
}

class Node
{
    private Node prev;
    private Flow flow;
    private Node next;

    public Node(Flow flow){this.flow = flow;}

    public void setPrev(Node prev){ this.prev = prev; }

    public void setNext(Node next){ this.next = next; }

    public Node getNext(){ return this.next; }

    public Node getPrev(){ return this.prev; }

    public Flow getFlow() { return this.flow; }
}
