

public class main {
    public static void main(String[] args) {

        String s = "{[][[][]]}";
        String one = "[]";
        String p = "[[][][]]";
        String series = "{[][][]}";
        Spring[] springs = {new Spring(2), new Spring(5), new Spring(3)};


        Spring result = SpringArray.equivalentSpring(p);
        System.out.println(result.getK());
    }
}