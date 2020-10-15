public class Main
{
    public static void main(String[] args) throws InterruptedException
    {
        Scheduler round_roubin = new Scheduler();
        new PIC(1000, round_roubin);

        round_roubin.add(new ProcessImp(1, 5000));
        round_roubin.add(new ProcessImp(2, 7000));
        round_roubin.add(new ProcessImp(3, 6000));
        round_roubin.add(new ProcessImp(4, 2000));
        round_roubin.add(new ProcessImp(5, 9000));
        round_roubin.add(new ProcessImp(6, 5000));
    }
}