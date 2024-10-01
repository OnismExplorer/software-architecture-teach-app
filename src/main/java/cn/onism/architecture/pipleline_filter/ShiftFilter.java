package cn.onism.architecture.pipleline_filter;

import java.util.ArrayList;
import java.util.List;

/**
 * 交换过滤器
 *
 * @author Onism
 * @date 2024/10/01
 */
public class ShiftFilter  implements Filter {
    @Override
    public List<String> process(List<String> lines) {
        List<String> shifts = new ArrayList<>();
        for (String line : lines) {
            String[] words = line.split(" ");
            for (int i = 0; i < words.length; i++) {
                String shiftedLine = String.join(" ", shiftArray(words, i));
                shifts.add(shiftedLine);
            }
        }
        return shifts;
    }

    private String[] shiftArray(String[] words, int shift) {
        String[] shifted = new String[words.length];
        for (int i = 0; i < words.length; i++) {
            shifted[i] = words[(i + shift) % words.length];
        }
        return shifted;
    }
}