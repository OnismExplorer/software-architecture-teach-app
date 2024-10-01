package cn.onism.architecture.event;

import java.util.ArrayList;
import java.util.List;

/**
 * Kwic 事务管理器
 *
 * @author Onism
 * @date 2024/10/01
 */
public class KWICEventManager {

    /**
     * 监听器群
     */
    private final List<EventListener> listeners = new ArrayList<>();

    /**
     * 已转换数组
     */
    private final List<String> shifts = new ArrayList<>();

    /**
     * 注册侦听器
     *
     * @param listener 听者
     */
    public void registerListener(EventListener listener) {
        listeners.add(listener);
    }

    /**
     * 发布
     *
     * @param line 线
     */
    public void publish(String line) {
        for (EventListener listener : listeners) {
            listener.onEvent(line);
        }
    }

    /**
     * 添加需转换字符串
     *
     * @param shift 转变
     */
    public void addShift(String shift) {
        shifts.add(shift);
    }

    /**
     * 过程
     */
    public void process() {
        shifts.sort(String.CASE_INSENSITIVE_ORDER);
        for (String shift : shifts) {
            System.out.println(shift);
        }
    }

    /**
     * 获取已转换数组
     *
     * @return {@link List }<{@link String }>
     */
    public List<String> getShifts() {
        return shifts;
    }
}
