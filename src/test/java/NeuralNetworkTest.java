import com.bone.neuralnetwork.NeuralNetwork;
import org.junit.Test;

public class NeuralNetworkTest
{
    @Test
    public void testFeedForward()
    {
            NeuralNetwork test = new NeuralNetwork(2,4,1);
            double [] inputs = {1,0};
            double[] output = test.feedForward(inputs);
        for (double v : output) {
            System.out.println(v);

        }
    }
}