import java.util.ArrayList;
import java.util.List;

public class ConverterInt extends Converter {
    @Override
    Spring createSpring(char[] bits) {
        StringBuilder expression = new StringBuilder("[");
        int powerOfTwo = 1;
        List<Spring> springs = new ArrayList<>();
        for (int i = bits.length - 1; i >= 0; i--) {
            if (bits[i] == '1') {
                springs.add(new Spring(powerOfTwo));
                expression.append("[]");
            }
            powerOfTwo *= 2;
        }
        expression.append("]");
        return SpringArray.equivalentSpring(expression.toString(), springs);
    }

    @Override
    double evaluateByAmplitudes(double[] amplitudes, double dt, int N) {
        return FT.approximate(amplitudes, dt, N);
    }
}
