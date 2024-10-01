package cn.onism.architecture.pipleline_filter;

import java.util.List;

/**
 * KWIC 管道过滤系统
 *
 * @author Onism
 * @date 2024/10/01
 */
public class KWICPipelineSystem {
    public List<String> applyPipeline(List<String> lines) {
        // 生成移位
        List<String> shifts = new ShiftFilter().process(lines);

        // 排序移位
        shifts = new SortFilter().process(shifts);

        return shifts;
    }
}
