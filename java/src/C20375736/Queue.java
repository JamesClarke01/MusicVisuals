package C20375736;

public class Queue {
    private float[] queue;
    private int tail;

    private int QUEUE_SIZE;

    public Queue(int size)
    {
        QUEUE_SIZE = size;
        queue = new float[QUEUE_SIZE];
        
        tail = 0;
    }
   
    //adding elements from queue
    public void enqueue(float element)
    {       
        queue[tail] = element;
        tail++;
        
        return;
    }

    //dequeue elements from queue
    public void dequeue()
    {
 

        for (int i = 0; i < tail - 1; i++)
        {
            queue[i] = queue[i + 1];
        }

        // store 0 at rear indicating there's no element
       // if (tail < queue.length)
        //    queue[tail] = 0;

        // decrement rear
        tail--;
    }

    public boolean isFull()
    {
        return tail == QUEUE_SIZE;
    }

    public float average()
    {
        float sum = 0;
       

        for(int i = 0; i < QUEUE_SIZE; i++)
        {
            sum += queue[i];
        }

        return sum/QUEUE_SIZE;
    }


}
