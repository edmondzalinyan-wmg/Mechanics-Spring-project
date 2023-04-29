public class Main {
    public static void main(String[] args) {

        double t0 = 0;
        double t1 = 10;
        double dt = 0.1;
        int N = 30;

        show("00001101", t0, t1, dt, N); //13
        show("00001111", t0, t1, dt, N); //15
        show("01011001", t0, t1, dt, N); //89
        show("01001010", t0, t1, dt, N); //74
        System.out.println("\nConclusion: By this methods we approximate the binary " +
                "number to the closest square number");

    }

    private static void show(String binaryRepresentation, double t0, double t1, double dt, int N) {
        Converter8Bit eightBitConverter = new Converter8Bit();
        ConverterInt intConverter = new ConverterInt();
        ConverterFloat floatConverter = new ConverterFloat();
        boolean isFloat = binaryRepresentation.contains(".");
        char[] binaryNumber = binaryRepresentation.toCharArray();
        Spring springBit = eightBitConverter.createSpring(binaryNumber);
        Spring springInt = intConverter.createSpring(binaryNumber);
        Spring springFloat = floatConverter.createSpring(binaryNumber);

        double[] coordinatesBit = springBit.move(t0, t1, dt, 2, 3, 1);
        double[] coordinatesInt = springInt.move(t0, t1, dt, 2, 3, 1);
        double[] coordinatesFloat = springFloat.move(t0, t1, dt, 2, 3, 1);

        System.out.println("binary representation: " + binaryRepresentation);
        if (!isFloat) {
            System.out.println("real decimal: " + springInt.getK());
            System.out.println("8bit converter: " + eightBitConverter.evaluateByAmplitudes(coordinatesBit, dt, N));
            System.out.println("int converter: " + intConverter.evaluateByAmplitudes(coordinatesInt, dt, N));
        } else {
            System.out.println("real decimal: " + springFloat.getK());
            System.out.println("float converter: " + floatConverter.evaluateByAmplitudes(coordinatesFloat, dt, N));
        }

        System.out.println("*****************************");
    }
}