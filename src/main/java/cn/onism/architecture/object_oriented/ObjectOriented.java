package cn.onism.architecture.object_oriented;

import java.io.File;
import java.util.List;

/**
 * kwic面向对象
 *
 * @author Onism
 * @date 2024/10/01
 */
public class ObjectOriented {

    private ObjectOriented() {
        // do nothing
    }

    /**
     * 处理文件
     *
     * @param file 文件
     * @return {@link List }<{@link String }>
     */
    public static List<String> processFile(File file) {
        KWICSystem kwicSystem = new KWICSystem();

        // 从文件中读取输入行
        kwicSystem.readLinesFromFile(file);

        // 生成循环移位
        kwicSystem.generateShifts();

        // 排序移位
        kwicSystem.sortShifts();

        // 输出结果到控制台和文件
        kwicSystem.printShifts();
        kwicSystem.writeShiftsToFile("output_object_oriented.txt");

        return kwicSystem.getShifts();
    }
}
