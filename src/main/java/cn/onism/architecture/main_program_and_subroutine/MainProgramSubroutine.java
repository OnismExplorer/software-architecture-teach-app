package cn.onism.architecture.main_program_and_subroutine;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * KWIC 主程序(主程序-子程序)
 *
 * @author Onism
 * @date 2024/10/01
 */
public class MainProgramSubroutine {

    private MainProgramSubroutine() {
        // do nothing
    }

    /**
     * 处理文件
     *
     * @param file 文件
     * @return {@link List }<{@link String }>
     */
    public static List<String> processFile(File file) {
        // 从文件中读取输入行
        List<String> lines = readLinesFromFile(file);

        // 生成循环移位
        List<String> shifts = generateShifts(lines);

        // 排序
        shifts.sort(String.CASE_INSENSITIVE_ORDER);

        // 输出结果到控制台
        printShifts(shifts);

        // 输出结果到文件
        writeLinesToFile(shifts, "output_main_program_subroutine.txt");

        return shifts;
    }

    /**
     * 从文件中读取文本行
     *
     * @param file 文件
     * @return {@link List }<{@link String }>
     */
    public static List<String> readLinesFromFile(File file) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    /**
     * 将文本行写入文件
     *
     * @param lines    文本行
     * @param filename 文件名
     */
    public static void writeLinesToFile(List<String> lines, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成已交换的结果
     *
     * @param lines 文本行
     * @return {@link List }<{@link String }>
     */
    public static List<String> generateShifts(List<String> lines) {
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

    /**
     * 交换字符串数组
     *
     * @param words 话
     * @param shift 转变
     * @return {@link String[] }
     */
    public static String[] shiftArray(String[] words, int shift) {
        String[] shifted = new String[words.length];
        for (int i = 0; i < words.length; i++) {
            shifted[i] = words[(i + shift) % words.length];
        }
        return shifted;
    }

    /**
     * 打印交换结果
     *
     * @param shifts 变化
     */
    public static void printShifts(List<String> shifts) {
        for (String shift : shifts) {
            System.out.println(shift);
        }
    }
}
