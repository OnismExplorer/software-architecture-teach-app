package cn.onism.architecture.pipleline_filter;

import java.util.List;

/**
 * 排序过滤器
 *
 * @author Onism
 * @date 2024/10/01
 */
public class SortFilter  implements Filter {
    @Override
    public List<String> process(List<String> input) {
        input.sort(String.CASE_INSENSITIVE_ORDER);
        return input;
    }
}
