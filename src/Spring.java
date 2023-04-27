import java.util.List;

public class Spring {
    //stiffness
    private double k = 1;

    Spring(double k) {
        setK(k);
    }

    Spring() {
        new Spring(1);
    }

    public double[] move(double t, double dt, double x0, double v0) {
        return move(0, t, dt, x0, v0);
    }

    public double[] move(double t, double dt, double x0) {
        return move(t, dt, x0, 0);
    }

    public double[] move(double t0, double t1, double dt, double x0, double v0) {
        return move(t0, t1, dt, x0, v0, 1);
    }

    public double[] move(double t0, double t1, double dt, double x0, double v0, double m) {
        double w = Math.sqrt(k / m);
/*
        x(t) = Asinωt + Bcosωt
        v(t) = x'(t) = ωAcosωt − ωBsinωt

        v(0) = ωAcos0 − ωBsin0 = ωA
        x(0) = Asin0 + Bcos0 = B
*/
        double A = v0 / w;
        double B = x0;

        int n = (int) ((t1 - t0) / dt) + 1;
        double[] coordinates = new double[n];
        for (int i = 0; i < n; i++) {
            double tt = t0 + dt * i;
//          x(t) = Asinωt + Bcosωt
            coordinates[i] =
                    A * Math.sin(w * tt) + B * Math.cos(w * tt);
        }
        return coordinates;
    }

    public Spring inParallel(Spring that) {
        return new Spring(this.getK() + that.getK());
    }

    public Spring inSeries(Spring that) {
//      1/K = 1/k1 + 1/k2
        return new Spring(
                1 /
                        (1 / (this.getK() + 1 / that.getK()))
        );
    }

    public static Spring inParallel(List<Spring> springList) {
        double k = 0;
        for (Spring s : springList)
            k += s.getK();
        return new Spring(k);
    }

    public static Spring inSeries(List<Spring> springList) {
//      1/K = 1/k1 + 1/k2 + 1/k3....
        double k = 0;
        for (Spring s : springList)
            k += 1 / s.getK();
        return new Spring(1 / k);
    }

    public double getK() {
        return k;
    }

    private void setK(double k) {
        this.k = k;
    }
}
