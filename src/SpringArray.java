import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class SpringArray {
    private static int s;

    public static Spring equivalentSpring(String exp) {
        Spring[] defaultSprings = new Spring[exp.length() / 2];
        for (int i = 0; i < exp.length() / 2; i++)
            defaultSprings[i] = new Spring();
        return equivalentSpring(exp, defaultSprings);
    }

    private static Spring rec(Stack<Character> stack, List<Character> list, Spring[] springs) {
        char c = list.get(0);
        char next = list.get(1);
        stack.push(c);
        trim(list, 1);
        if (stack.peek() == '{' && next == '}'
                || (stack.peek() == '[' && next == ']')) {
            stack.pop();
            trim(list, 1);
            return springs[s++];
        }
        List<Spring> parallels = new ArrayList<>();
        List<Spring> series = new ArrayList<>();
        if (c == '[') {
            while (list.get(0) != ']')
                parallels.add(rec(stack, list, springs));
            return Spring.inParallel(parallels);
        } else // (c == '{')
        {
            while (list.get(0) != '}')
                series.add(rec(stack, list, springs));
            return Spring.inSeries(series);
        }
    }

    public static Spring equivalentSpring(String springExpr, Spring[] springs) {
        Stack<Character> stack = new Stack<>();
        List<Character> e = new ArrayList<>();
        for (char c : springExpr.toCharArray()) {
            e.add(c);
        }
        s = 0;
        return rec(stack, e, springs);
    }

    private static void trim(List<Character> source, int i) {
        source.subList(0, i).clear();
    }
}