package cn.onism.architecture.pipleline_filter;

import java.util.List;

/**
 * 过滤器接口
 *
 * @author Onism
 * @date 2024/10/01
 */
public interface Filter {
    /**
     * 交换过程
     *
     * @param input 输入
     * @return {@link List }<{@link String }>
     */
    List<String> process(List<String> input);
}
