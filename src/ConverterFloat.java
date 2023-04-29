import java.util.ArrayList;
import java.util.List;

public class ConverterFloat extends Converter {
    @Override
    Spring createSpring(char[] bits) {
        StringBuilder parallels = new StringBuilder();
        StringBuilder series = new StringBuilder();
        int powerOfTwo = 1;
        List<Character> integerPart = new ArrayList<>();
        List<Character> floatPart = new ArrayList<>();
        int f = 0;
        while (f < bits.length && bits[f] != '.')
            integerPart.add(bits[f++]);
        f++;
        while (f < bits.length)
            floatPart.add(bits[f++]);

        for (int i = integerPart.size() - 1; i >= 0; i--) {
            if (integerPart.get(i) == '1') {
                for (int j = 0; j < powerOfTwo; j++)
                    parallels.append("[]");
            }
            powerOfTwo *= 2;
        }
        powerOfTwo = 1;

        for (int i = floatPart.size() - 1; i >= 0; i--) {
            if (floatPart.get(i) == '1') {
                for (int j = 0; j < powerOfTwo; j++)
                    series.append("[]");
            }
            powerOfTwo *= 2;
        }

        return SpringArray.equivalentSpring("[" + parallels + "{" + series + "}]");
    }

    @Override
    double evaluateByAmplitudes(double[] amplitudes, double dt, int N) {
        return FT.approximate(amplitudes, dt, N);

    }
}
