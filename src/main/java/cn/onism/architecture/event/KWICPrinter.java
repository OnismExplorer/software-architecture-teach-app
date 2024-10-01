package cn.onism.architecture.event;

/**
 * kwic 打印机
 *
 * @author Onism
 * @date 2024/10/01
 */
public class KWICPrinter implements EventListener {

    @Override
    public void onEvent(String line) {
        // 打印将延迟到所有事件处理完成后
    }
}
