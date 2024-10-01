package cn.onism.architecture.event;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * kwic 事务系统
 *
 * @author Onism
 * @date 2024/10/01
 */
public class EventSystem {

    private EventSystem() {
        // do nothing
    }

    /**
     * 处理文件
     *
     * @param file 文件
     * @return {@link List }<{@link String }>
     */
    public static List<String> processFile(File file) {
        KWICEventManager eventManager = new KWICEventManager();
        KWICShiftGenerator shiftGenerator = new KWICShiftGenerator(eventManager);
        KWICSorter sorter = new KWICSorter();
        KWICPrinter printer = new KWICPrinter();

        // 注册事件监听器
        eventManager.registerListener(shiftGenerator);
        eventManager.registerListener(sorter);
        eventManager.registerListener(printer);

        // 从文件中读取输入行并发布事件
        List<String> lines = readLinesFromFile(file);
        for (String line : lines) {
            eventManager.publish(line);
        }

        // 处理事件并排序
        eventManager.process();

        // 输出结果到文件
        writeLinesToFile(eventManager.getShifts(), "output_event_system.txt");

        return eventManager.getShifts();
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
}
