package util;

import java.util.ArrayList;
import java.util.List;

public class ChangeTool {
    public static String breakdown(int change) {
        int[] money = {1000, 500, 100, 50, 10, 5, 1};
        int[] count = new int[money.length];
        int remain = change;
        for (int i = 0; i < money.length; i++) {
            count[i] = remain / money[i];
            remain -= count[i] * money[i];
        }

        List<String> lines = new ArrayList<>();
        lines.add("找零：$" + change);
        for (int i = 0; i < money.length; i++) {
            lines.add("$" + money[i] + " × " + count[i]);
        }
        return String.join("\n", lines);
    }
}
