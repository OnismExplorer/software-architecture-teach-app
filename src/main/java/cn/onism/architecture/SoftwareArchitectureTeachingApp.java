package cn.onism.architecture;

import cn.onism.architecture.event.EventSystem;
import cn.onism.architecture.main_program_and_subroutine.MainProgramSubroutine;
import cn.onism.architecture.object_oriented.ObjectOriented;
import cn.onism.architecture.pipleline_filter.PipelineFilter;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.List;

/**
 * 软件架构教学应用程序
 *
 * @author Onism
 * @date 2024/10/01
 */
public class SoftwareArchitectureTeachingApp extends JFrame {

    private JTextArea resultArea;
    private final JComboBox<String> architectureSelection;
    private final JFileChooser fileChooser;
    private File selectedFile = null;

    public SoftwareArchitectureTeachingApp() {
        setTitle("经典软件体系结构教学软件");
        setSize(600, 400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 创建体系结构选择下拉框
        architectureSelection = new JComboBox<>(new String[]{
                "主程序-子程序", "面向对象", "事件系统", "管道-过滤器"
        });

        // 文件选择按钮
        JButton fileButton = new JButton("选择输入文件");
        fileChooser = new JFileChooser();
        fileButton.addActionListener(e -> {
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                selectedFile = fileChooser.getSelectedFile();
                resultArea.setText("选择的文件: " + selectedFile.getAbsolutePath() + "\n");
            }
        });

        // 结果显示区域
        resultArea = new JTextArea();
        resultArea.setEditable(false);

        // 创建处理按钮
        JButton processButton = getButton();

        // 布局
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("选择体系结构:"));
        topPanel.add(architectureSelection);
        topPanel.add(fileButton);
        topPanel.add(processButton);

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(resultArea), BorderLayout.CENTER);

        setVisible(true);
    }

    /**
     * 获取处理按钮
     *
     * @return {@link JButton }
     */
    private JButton getButton() {
        JButton processButton = new JButton("处理文件");
        processButton.addActionListener(e -> {
            if (selectedFile != null) {
                String selectedArchitecture = (String) architectureSelection.getSelectedItem();
                if (selectedArchitecture != null) {
                    processArchitecture(selectedArchitecture, selectedFile);
                } else {
                    resultArea.setText("请选择体系结构风格。\n");
                }
            } else {
                resultArea.setText("请先选择一个输入文件。\n");
            }
        });
        return processButton;
    }

    // 处理体系结构逻辑
    private void processArchitecture(String architecture, File file) {
        resultArea.append("使用 " + architecture + " 处理...\n");
        try {
            List<String> result = switch (architecture) {
                case "主程序-子程序" -> MainProgramSubroutine.processFile(file);
                case "面向对象" -> ObjectOriented.processFile(file);
                case "事件系统" -> EventSystem.processFile(file);
                case "管道-过滤器" -> PipelineFilter.processFile(file);
                default -> throw new IllegalStateException("未知的体系结构: " + architecture);
            };
            resultArea.append("处理结果：\n");
            for (String line : result) {
                resultArea.append(line + "\n");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            resultArea.append("处理文件时出错：" + ex.getMessage() + "\n");
        }
    }

    public static void main(String[] args) {
        new SoftwareArchitectureTeachingApp();
    }
}
