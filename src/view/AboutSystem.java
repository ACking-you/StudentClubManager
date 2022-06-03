package view;

import javax.swing.*;
import java.awt.*;


public class AboutSystem extends JFrame {

    private static AboutSystem instance;

    private AboutSystem() {
        setTitle("���ڱ�ϵͳ");//������������
        setSize(600, 450);//����������С
        setLocationRelativeTo(null);//��������ʾ����Ļ����
        initUI();
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);//������ϽǵĹرգ�ֻ�رձ����ڣ���Ӱ��ס����
        setResizable(true);//���ô��ڴ�С���Ըı�
    }

    public static AboutSystem getInstance() {
        if (instance == null) {
            instance = new AboutSystem();
        }
        return instance;
    }

    public static void main(String[] args) {
        new AboutSystem();
    }

    public void reset() {
        setVisible(true);
    }

    private void initUI() {
        setLayout(null);//�Ծ��Բ��ֵķ�ʽ����
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("src/images/logo.png"));
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");// ʹ��windows���
        } catch (Exception e) {
            e.printStackTrace();
        }
        Font font = new Font("΢���ź�", Font.PLAIN, 20);
        JLabel jlabel1 = new JLabel("ѧ������ϵͳ");
        jlabel1.setBounds(230, 50, 150, 50);
        jlabel1.setFont(font);
        JLabel jlabel2 = new JLabel("�༶��20�ƿ�2��");
        jlabel2.setBounds(200, 100, 450, 50);
        jlabel2.setFont(font);
        JLabel jLabel3 = new JLabel("���ߣ�������");
        jLabel3.setBounds(200, 150, 450, 50);
        jLabel3.setFont(font);
        JLabel jLbel4 = new JLabel("������ڣ�2022-6-2");
        jLbel4.setBounds(200, 200, 450, 50);
        jLbel4.setFont(font);

        add(jlabel1);
        add(jlabel2);
        add(jLabel3);
        add(jLbel4);
    }
}
