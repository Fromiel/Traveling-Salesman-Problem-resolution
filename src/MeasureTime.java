public class MeasureTime {

    public long start;
    public long end;

    public int currentThread = 1;

    public int totalThreads;

    public MeasureTime(int nbThread)
    {
        this.totalThreads = nbThread;
        this.start = System.nanoTime();
    }


    public void displayTotalTime()
    {
        if(currentThread != totalThreads)
        {
            currentThread++;
            return;
        }
        this.end = System.nanoTime();
        System.out.println("Location: " + (end - start));
    }

}
