package view;

import model.dao.StudentDAO;
import model.entity.Club;
import model.entity.Student;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;
import service.ClubService;
import util.Information;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddClubInfo extends JFrame implements ActionListener {
    private static JTextField nameText;
    private static JCheckBox category;
    private final String[] categoryOptions = {"�Ļ�������", "ѧ���Ƽ���", "־Ը������", "���´�ҵ��", "���ɻ�����", "������"};
    JCheckBox category1;
    JCheckBox category2;
    JCheckBox category3;
    JCheckBox category4;
    JCheckBox category5;
    JCheckBox category6;
    private JButton Save, Cancel, Return;//1.���水ť��2.ȡ����ť,3.���ذ�ť
    private Font font1, font2, font3;//��������

    public AddClubInfo() {
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("src/images/logo.png"));
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");// ʹ��windows���
        } catch (Exception e) {
            e.printStackTrace();
        }
        setTitle("���������Ϣ");//������������
        setSize(650, 350);//����������С
        setLocationRelativeTo(null);//��������ʾ����Ļ����
        initUI();//���ֽ��溯��
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);//������ϽǵĹرգ�ֻ�رձ����ڣ���Ӱ��ס����
        setVisible(true);//���ô��ڿɼ�
        setResizable(true);//���ô��ڴ�С���Ըı�
    }

    public void clearText() {
        nameText.setText("");//��������
        category1.setSelected(false);//ȡ��ѡ��
        category2.setSelected(false);//ȡ��ѡ��
        category3.setSelected(false);//ȡ��ѡ��
        category4.setSelected(false);//ȡ��ѡ��
        category5.setSelected(false);//ȡ��ѡ��
        category6.setSelected(false);//ȡ��ѡ��
    }

    public static void main(String[] args) {
        new AddClubInfo();
    }

    private void initUI() {
        setLayout(null);//�Ծ��Բ��ֵķ�ʽ����
        font1 = new Font("����", 0, 20);
        font3 = new Font("΢���ź�", 0, 18);
        //���ø��������λ�ã�����
        int left = 120;
        int lb_height = 50;
        int lb_width = 150;
        JLabel labelName = new JLabel("��������:");
        labelName.setBounds(left, 30, 150, 50);
        labelName.setFont(font1);
        nameText = new JTextField();
        nameText.setBounds(new Rectangle(left+100, 40, 150, 30));
        nameText.setFont(font1);


        JLabel labelCategory = new JLabel("���ŷ��ࣺ");
        labelCategory.setBounds(left, 100, 150, 50);
        labelCategory.setFont(font1);
        //������Ȥ��ѡ�����
        JPanel checkbox = new JPanel();
        checkbox.setLayout(new GridLayout(2, 3));
        category1 = new JCheckBox(categoryOptions[0]);
        category1.setFont(font3);
        checkbox.add(category1);

        category2 = new JCheckBox(categoryOptions[1]);
        category2.setFont(font3);
        checkbox.add(category2);
        System.out.println(category2.getText());
        category3 = new JCheckBox(categoryOptions[2]);
        category3.setFont(font3);
        checkbox.add(category3);

        category4 = new JCheckBox(categoryOptions[3]);
        category4.setFont(font3);
        checkbox.add(category4);

        category5 = new JCheckBox(categoryOptions[4]);
        category5.setFont(font3);
        checkbox.add(category5);

        category6 = new JCheckBox(categoryOptions[5]);
        category6.setFont(font3);
        checkbox.add(category6);

        checkbox.setBounds(left+100, 100, 400, 80);


        font2 = new Font("�꿬��", Font.PLAIN, 15);
        Save = new JButton("����");
        Save.setUI(new BEButtonUI()
                .setNormalColor(BEButtonUI.NormalColor.blue));//���ð�ť�ı�����ɫ
        Save.setBounds(new Rectangle(left, 250, 70, 30));
        Save.setFont(font2);
        Cancel = new JButton("ȡ��");
        Cancel.setUI(new BEButtonUI()
                .setNormalColor(BEButtonUI.NormalColor.red));
        Cancel.setBounds(new Rectangle(left+100, 250, 70, 30));
        Cancel.setFont(font2);
        Return = new JButton("����");
        Return.setUI(new BEButtonUI()
                .setNormalColor(BEButtonUI.NormalColor.green));
        Return.setBounds(new Rectangle(left+200, 250, 70, 30));
        Return.setFont(font2);

        //�������齫��ӵ���������
        add(labelName);
        add(nameText);


        add(labelCategory);
        add(checkbox);

        add(Save);
        add(Cancel);
        add(Return);

        //Ϊ�����������¼�������
        nameText.addActionListener(this);
        category1.addActionListener(this);
        category2.addActionListener(this);
        category3.addActionListener(this);
        category4.addActionListener(this);
        category5.addActionListener(this);
        category6.addActionListener(this);
        Save.addActionListener(this);
        Cancel.addActionListener(this);
        Return.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();//�õ���ť�ϵı�ǩ
        switch (cmd) {
            case "����":
                if (nameText.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "�������Ʋ���Ϊ�գ�", "��Ϣ��ʾ", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (!category1.isSelected() && !category2.isSelected() &&
                        !category3.isSelected() && !category4.isSelected() &&
                        !category5.isSelected() && !category6.isSelected()) {
                    JOptionPane.showMessageDialog(null, "���ŷ��಻��Ϊ�գ�", "��Ϣ��ʾ", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                StringBuilder stringBuilder = new StringBuilder();
                if (category1.isSelected()) {
                    stringBuilder.append(category1.getText()).append(",");
                }
                if (category2.isSelected()) {
                    stringBuilder.append(category2.getText()).append(",");
                }
                if (category3.isSelected()) {
                    stringBuilder.append(category3.getText()).append(",");
                }
                if (category4.isSelected()) {
                    stringBuilder.append(category4.getText()).append(",");
                }
                if (category5.isSelected()) {
                    stringBuilder.append(category5.getText()).append(",");
                }
                if (category6.isSelected()) {
                    stringBuilder.append(category6.getText()).append(",");
                }
                stringBuilder.deleteCharAt(stringBuilder.length()-1);
                Club club = new Club().setMemberCount(0).setCategory(stringBuilder.toString()).setName(nameText.getText());

                try {
                    ClubService.AddClub(club);
                } catch (RuntimeException exception) {
                    exception.printStackTrace();
                    JOptionPane.showMessageDialog(null, exception.getMessage(), "ע��",
                            JOptionPane.WARNING_MESSAGE);
                }
                clearText();
                setVisible(false);
                break;
            case "ȡ��":
                clearText();//���ȡ����ť�����������ѡ��
                setVisible(false);
                break;
            case "����":
                setVisible(false);
                break;
        }
    }
}
