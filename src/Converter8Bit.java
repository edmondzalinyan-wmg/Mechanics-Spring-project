public class Converter8Bit extends Converter {
    @Override
    Spring createSpring(int[] bits) {
        StringBuilder expression = new StringBuilder("[");
        int powerOfTwo = 1;
        for (int i = bits.length - 1; i >= 0; i--) {
            if (bits[i] == 1) {
                for (int j = 0; j < powerOfTwo; j++)
                    expression.append("[]");
            }
            powerOfTwo *= 2;
        }
        expression.append("]");
        return SpringArray.equivalentSpring(expression.toString());
    }

    @Override
    double evaluateByAmplitudes(double[] amplitudes, double dt, int N) {
        return FT.approximate(amplitudes, dt, N);
    }
}
