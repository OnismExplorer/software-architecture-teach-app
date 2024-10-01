package cn.onism.architecture.object_oriented;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * KWIC 系统
 *
 * @author Onism
 * @date 2024/10/01
 */
public class KWICSystem {
    private final List<String> lines = new ArrayList<>();
    private final List<String> shifts = new ArrayList<>();

    /**
     * 从文件中读取文本行
     *
     * @param file 文件
     */
    public void readLinesFromFile(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将交换结果写入文件
     *
     * @param filename 文件名
     */
    public void writeShiftsToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (String shift : shifts) {
                writer.write(shift);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成交换结果
     */
    public void generateShifts() {
        for (String line : lines) {
            String[] words = line.split(" ");
            for (int i = 0; i < words.length; i++) {
                String shiftedLine = String.join(" ", shiftArray(words, i));
                shifts.add(shiftedLine);
            }
        }
    }

    /**
     * 排序交换结果
     */
    public void sortShifts() {
        shifts.sort(String.CASE_INSENSITIVE_ORDER);
    }

    /**
     * 打印交换好的结果
     */
    public void printShifts() {
        for (String shift : shifts) {
            System.out.println(shift);
        }
    }

    public List<String> getShifts() {
        return shifts;
    }

    /**
     * 交换字符数组
     *
     * @param words 话
     * @param shift 转变
     * @return {@link String[] }
     */
    private String[] shiftArray(String[] words, int shift) {
        String[] shifted = new String[words.length];
        for (int i = 0; i < words.length; i++) {
            shifted[i] = words[(i + shift) % words.length];
        }
        return shifted;
    }
}
