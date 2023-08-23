/**
 * This RingBuffer object models the medium (a string tied down 
 * at both ends) in which the energy travels back and forth.
 * 
 * @author  Nikash Chhadia
 * @version 10/15/21
 */
public class RingBuffer 
{
    private double[] x;
    private int front;
    private int rear;
    private int size;

    public RingBuffer(int capacity)
    {
        x = new double[capacity];
        front = 0;
        rear = capacity - 1;
        size = 0;
    }
    
    public int capacity()
    {
        return x.length;
    }

    public int size()
    {
        return size;
    }

    public boolean isEmpty()
    {
        return size == 0;
    }

    public boolean isFull()
    {
        return size == x.length;
    }

    public void add(double value)
    {
        rear = (rear + 1) % x.length;
        x[rear] = value;
        size++;
    }

    public double peek()
    {
        return x[front];
    }

    public double remove()
    {
        double result = x[front];
        x[front] = 0;
        front = (front + 1) % x.length;
        size--;
        return result;
    }

    // a simple test of the constructor and methods in RingBuffer
    public static void main(String[] args) 
    {
        int capacity = 100;
        RingBuffer buffer = new RingBuffer(capacity);  
        for (int i = 1; i <= capacity; i++) 
            buffer.add(i);

        double t = buffer.remove();
        buffer.add(t);
        System.out.println("Size after wrap-around is " + buffer.size());
        while (buffer.size() >= 2) 
        {
            double x = buffer.remove();
            double y = buffer.remove();
            buffer.add(x + y);
        }
        System.out.println(buffer.peek());
    }

}
