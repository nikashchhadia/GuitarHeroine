/**
 * This GuitarString object models a vibrating guitar string.
 * 
 * @author  Nikash Chhadia
 * @version 10/15/21
 */
public class GuitarString 
{
    private RingBuffer buffer;
    private int numTics = 0;

    public GuitarString(double frequency) 
    {
        buffer = new RingBuffer((int)(44100 / frequency + 1));
    }

    public GuitarString(double[] array)
    {
        buffer = new RingBuffer(array.length);
        for(double d : array)
            buffer.add(d);
    }

    public void pluck() 
    {
        for(int i = 0; i < buffer.capacity(); i++)
            buffer.remove();

        for(int i = 0; i < buffer.capacity(); i++)
            buffer.add(Math.random() - 0.5);
    }

    // advance the simulation one time step
    public void tic()
    {
        double a = buffer.remove();
        double b = buffer.peek();
        buffer.add(0.497 * (a + b));
        numTics++;
    }

    // return the current sample
    public double sample()
    {
        return buffer.peek();
    }

    // return number of times tic was called
    public int time() 
    {
        return numTics;
    }

    public static void main(String[] args) 
    {
        double[] samples = { .2, .4, .5, .3, -.2, .4, .3, .0, -.1, -.3 };  
        GuitarString testString = new GuitarString(samples);
        for (int i = 0; i < 25; i++) 
        {
            int t = testString.time();
            double sample = testString.sample();
            System.out.printf("%6d %8.4f\n", t, sample);
            testString.tic();
        }
    }
}
