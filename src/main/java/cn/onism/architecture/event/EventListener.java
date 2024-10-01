package cn.onism.architecture.event;

/**
 * 事件侦听器
 *
 * @author Onism
 * @date 2024/10/01
 */
public interface EventListener {
    /**
     * 开启事务
     *
     * @param line 线
     */
    void onEvent(String line);
}
