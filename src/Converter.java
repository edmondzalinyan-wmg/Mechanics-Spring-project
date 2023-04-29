public abstract class Converter {

    /**
     * Declare an abstract method for taking as its argument a sequence of bits and
     * returning the corresponding system of springs.
     */
    abstract Spring createSpring(char[] bits);

    /**
     * - Add a concrete method that connects to the system of springs a body of unit mass
     * and computes its oscillations.
     *///todo
    public static double[] computeOscillations(Spring spring) {
        return null;
    }


    /**    - Add a concrete method that calculates the frequency amplitudes of the oscillations
     using the implemented Fourier Transform.
     */
//todo

    /**
     * - Declare an abstract method for evaluation of the decimal value of the original binary
     * sequence using the computed frequency amplitudes
     */
    abstract double evaluateByAmplitudes(double[] amplitudes, double dt, int N);
}
