package cn.onism.architecture.pipleline_filter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * KWIC 管道-过滤结构
 *
 * @author Onism
 * @date 2024/10/01
 */
public class PipelineFilter {

    private PipelineFilter() {
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

        // 使用管道-过滤器架构处理 KWIC
        KWICPipelineSystem pipeline = new KWICPipelineSystem();
        List<String> shifts = pipeline.applyPipeline(lines);

        // 输出结果到控制台
        for (String shift : shifts) {
            System.out.println(shift);
        }

        // 输出结果到文件
        writeLinesToFile(shifts, "output_pipeline_filter.txt");

        return shifts;
    }

    /**
     * 从文件中读取行
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
