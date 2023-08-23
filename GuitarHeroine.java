/**
 * Supports a total of 37 notes on the chromatic scale from 110Hz to 880Hz.
 * 
 * @author  Nikash Chhadia
 * @version 10/15/21
 */
public class GuitarHeroine
{
    public static void main(String[] args) 
    {
        // Create guitar strings
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        GuitarString[] strings = new GuitarString[37];
        for(int i = 0; i < 37; i++)
            strings[i] = new GuitarString(440 * Math.pow(1.05956, i-24));

        // the main input loop
        while(true)
        {
            // check if the user has typed a key, and, if so, process it
            if(StdDraw.hasNextKeyTyped()) 
            {
                // the user types this character
                char key = StdDraw.nextKeyTyped();

                // pluck the corresponding string
                if(keyboard.indexOf(key) != -1)
                    strings[keyboard.indexOf(key)].pluck();
            }

            // compute the superposition of the samples
            double sample = 0;
            for(GuitarString s : strings)
            {
                sample += s.sample();
                s.tic();
            }

            // send the result to standard audio
            StdAudio.play(sample);
        }
    }
}
