package cn.onism.architecture.event;

/**
 * KWIC 交换器
 *
 * @author Onism
 * @date 2024/10/01
 */
public class KWICShiftGenerator implements EventListener {

    private final KWICEventManager eventManager;

    public KWICShiftGenerator(KWICEventManager eventManager) {
        this.eventManager = eventManager;
    }

    @Override
    public void onEvent(String line) {
        String[] words = line.split(" ");
        for (int i = 0; i < words.length; i++) {
            String shiftedLine = String.join(" ", shiftArray(words, i));
            eventManager.addShift(shiftedLine);
        }
    }

    /**
     * 交换数组
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