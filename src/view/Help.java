package view;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;

public class Help extends JFrame {
    private static Help instance;
    private JTextArea area;//�ı������

    private Help() {
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("src/images/logo.png"));
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");// ʹ��windows���
        } catch (Exception e) {
            e.printStackTrace();
        }
        setTitle("ϵͳ����");//������������
        setSize(450, 450);//����������С
        setLocationRelativeTo(null);//��������ʾ����Ļ����
        initUI();
        area.setEditable(false);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);//������ϽǵĹرգ�ֻ�رձ����ڣ���Ӱ��ס����
        setResizable(true);//���ô��ڴ�С���Ըı�
    }

    public static Help getInstance() {
        if (instance == null) {
            instance = new Help();
        }
        return instance;
    }

    public static void main(String[] args) {
        new Help();
    }

    public void reset() {
        setVisible(true);
    }

    private void initUI() {
        area = new JTextArea();
        File file = new File("./src/docs/ѧ������ϵͳ.txt");
        long file_length = file.length();
        byte[] file_content = new byte[(int) file_length];

        try (FileInputStream in = new FileInputStream(file)) {

            in.read(file_content);
            area.setText(new String(file_content, "GBK"));

        } catch (Exception e) {
            e.printStackTrace();
        }
        Font font = new Font("΢���ź�", Font.PLAIN, 15);
        area.setFont(font);
        area.setLineWrap(true);//������ݹ������Զ����У����ı�����Ϲ�������ˮƽ�ʹ�ֱ������ʼ�ճ��֡�
        area.setWrapStyleWord(true);// ������в����ֹ���
        //������
        JScrollPane pane = new JScrollPane(area, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        area.setForeground(Color.GRAY);
        add(pane);
    }
}
