/**
 * my implementation of Fourier Transform
 */
public class FT {

    public static double approximate(double[] x, double dt, int N) {
        double[] a = findAk(x, dt, N);
        double[] b = findBk(x, dt, N);

        double[] c = new double[a.length];
        for (int i = 0; i < a.length; i++)
            c[i] = (a[i] * a[i]) + (b[i] * b[i]);

        return findMax(c);
    }

    public static double[] applyFourier(double[] x) {
        double[] coordinates = new double[x.length];
        int i = 0;
        int N = 3;
        double dt = 1;
        double[] a = findAk(x, dt, N);
        double[] b = findBk(x, dt, N);

        for (double t : x)
            coordinates[i++] = fourierFormula(t, a, b, N);
        return coordinates;
    }

    /*
    f(t) = a[0]/2 + sum[a[k]*cos(ω[k]*t) + b[k]*sin(ω[k]*t)]
                  k:1->inf
       ω[k] = k*ω[0]   ω0 = 2*pi/T   ω0=sqrt(k/m)
    */
    public static double fourierFormula(double t, double[] a, double[] b, int N) {
        double w0 = Math.sqrt(1);
        double sumElement;
        int i = 1;

        double result = 0;
        for (int j = 0; j < N; j++) { //precision control
            sumElement = a[i] * Math.cos(w0 * j * t) + b[i] * Math.sin(w0 * j * t);
            result += sumElement;
            i++;
        }
        return result;
    }

    public static double[] findAk(double[] x, double dt, int N) {
        double[] a = new double[N];
        for (int j = 0; j < N; j++) {
            double sumElement = 0;
            for (int n = 0; n < N; n++)
                sumElement += x[n] * Math.cos(j * n * dt);
            a[j] = (2.0 / N) * sumElement;
        }
        return a;
    }

    public static double[] findBk(double[] x, double dt, int N) {
        double[] b = new double[N];
        for (int j = 0; j < N; j++) {
            double sumElement = 0;
            for (int n = 0; n < N; n++)
                sumElement += x[n] * Math.sin(j * n * dt);
            b[j] = (2.0 / N) * sumElement;
        }
        return b;
    }

    public static double findMax(double[] a, double[] b) {
        int maxIndex = 0;
        Double maxValue = null;
        for (int j = 0; j < a.length; j++) {
            double c = (a[j] * a[j]) + (b[j] * b[j]);
            if (maxValue == null || c > maxValue) {
                maxValue = c;
                maxIndex = j;
            }
        }
        return maxIndex;
    }

    public static double findMax(double[] amplitudes) {
        int maxIndex = 0;
        double maxValue = amplitudes[0];
        for (int i = 1; i < amplitudes.length; i++) {
            if (amplitudes[i] > maxValue) {
                maxValue = amplitudes[i];
                maxIndex = i;
            }
        }
        return maxIndex * maxIndex;
    }
}
