package view;

import model.entity.ClubMember;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;
import service.ClubMemberService;
import util.MessageUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AddMemberInfo extends JFrame implements ActionListener {
    static AddMemberInfo instance = null;
    public static final String[] majors =
            {"�������ѧ�뼼��", "�������", "��ľ����", "�����Ϣ��ѧ�빤��",
                    "��ѧ", "��ѧ��Ӧ����ѧ", "�˹�����", "����"};
    private JTextField inputStudentId, inputName, inputAge;
    private ButtonGroup sexGroup;//������ť��
    private JComboBox<String> comboBoxMajor;//ʡ�������б��
    private JCheckBox hobby1, hobby2, hobby3, hobby4, hobby5, hobby6;
    private int clubId;
    //���������͹��߰�ť
    private JButton Save, Cancel, Return;//1.���水ť��2.ȡ����ť,3.���ذ�ť
    private JLabel labelStudentId, labelName, labelSex, labelAge, labelMajor, labelInterest, Sphone;//1.��ʾ����š����֣�
    private JRadioButton radioBtnMale, radioBtnFemale;//�Ա�ѡ��ť
    private JPanel checkbox;//������Ȥ��ѡ�����
    private Font font1, font2, font3;//��������

    private AddMemberInfo() {
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("src/images/logo.png"));
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");// ʹ��windows���
        } catch (Exception e) {
            e.printStackTrace();
        }
        setTitle("���ѧ����Ϣ");//������������
        setSize(850, 650);//����������С
        setLocationRelativeTo(null);//��������ʾ����Ļ����
        initUI();//���ֽ��溯��
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);//������ϽǵĹرգ�ֻ�رձ����ڣ���Ӱ��ס����
        setResizable(true);//���ô��ڴ�С���Ըı�
    }

    public static AddMemberInfo getInstance(int clubId) {
        if (instance == null) {
            instance = new AddMemberInfo();
        }
        instance.clubId = clubId;
        return instance;
    }

    public static void main(String[] args) {
        getInstance(48).reset();
    }

    public void reset() {
        clearText();
        setVisible(true);
    }

    public void clearText() {
        inputStudentId.setText("");//��������
        inputName.setText("");//��������
        sexGroup.clearSelection();//��������
        inputAge.setText("");//��������
        comboBoxMajor.setSelectedIndex(0);//ȡ��ѡ��
        hobby1.setSelected(false);//ȡ��ѡ��
        hobby2.setSelected(false);//ȡ��ѡ��
        hobby3.setSelected(false);//ȡ��ѡ��
        hobby4.setSelected(false);//ȡ��ѡ��
        hobby5.setSelected(false);//ȡ��ѡ��
        hobby6.setSelected(false);//ȡ��ѡ��
    }

    private void initUI() {
        setLayout(null);//�Ծ��Բ��ֵķ�ʽ����
        font1 = new Font("����", Font.PLAIN, 20);
        font3 = new Font("΢���ź�", Font.PLAIN, 18);
        //���ø��������λ�ã�����
        labelStudentId = new JLabel("ѧ��:");//����Sid������
        labelStudentId.setBounds(250, 30, 150, 50);//�����������ڵ�λ��
        labelStudentId.setFont(font1);//��������
        inputStudentId = new JTextField();//���������
        inputStudentId.setBounds(new Rectangle(350, 40, 150, 30));//����λ��
        inputStudentId.setFont(font1);//��������

        labelName = new JLabel("����:");
        labelName.setBounds(250, 90, 150, 50);
        labelName.setFont(font1);
        inputName = new JTextField();
        inputName.setBounds(new Rectangle(350, 100, 150, 30));
        inputName.setFont(font1);


        labelSex = new JLabel("�Ա�:");
        labelSex.setBounds(250, 150, 150, 50);
        labelSex.setFont(font1);
        sexGroup = new ButtonGroup();
        radioBtnMale = new JRadioButton("��");
        radioBtnMale.setBounds(350, 160, 50, 30);
        radioBtnMale.setFont(font1);
        radioBtnFemale = new JRadioButton("Ů");
        radioBtnFemale.setBounds(450, 160, 50, 30);
        radioBtnFemale.setFont(font1);


        labelAge = new JLabel("����:");
        labelAge.setBounds(250, 210, 150, 50);
        labelAge.setFont(font1);
        inputAge = new JTextField();
        inputAge.setBounds(350, 220, 150, 30);
        inputAge.setFont(font1);

        labelMajor = new JLabel("����רҵ:");
        labelMajor.setBounds(250, 270, 150, 50);
        labelMajor.setFont(font1);
        comboBoxMajor = new JComboBox<>(majors);
        comboBoxMajor.setBounds(350, 280, 250, 30);
        comboBoxMajor.setFont(font1);


        labelInterest = new JLabel("��Ȥ����:");
        labelInterest.setBounds(250, 330, 200, 50);
        labelInterest.setFont(font1);


        hobby1 = new JCheckBox("��C++ Primer");
        hobby1.setFont(font3);

        hobby2 = new JCheckBox("��Rust��Ϸ");
        hobby2.setFont(font3);

        hobby3 = new JCheckBox("��һ��Java");
        hobby3.setFont(font3);

        hobby4 = new JCheckBox("��Go��");
        hobby4.setFont(font3);

        hobby5 = new JCheckBox("׽Python");
        hobby5.setFont(font3);

        hobby6 = new JCheckBox("����JS");
        hobby6.setFont(font3);

        checkbox = new JPanel();
        checkbox.setLayout(new GridLayout(2, 3));
        checkbox.add(hobby1);
        checkbox.add(hobby2);
        checkbox.add(hobby3);
        checkbox.add(hobby4);
        checkbox.add(hobby5);
        checkbox.add(hobby6);
        checkbox.setBounds(350, 325, 500, 130);


        //����Ĳ�����ť
        font2 = new Font("�꿬��", Font.PLAIN, 15);
        Save = new JButton("����");
        Save.setUI(new BEButtonUI()
                .setNormalColor(BEButtonUI.NormalColor.blue));//���ð�ť�ı�����ɫ
        Save.setBounds(new Rectangle(250, 520, 70, 30));
        Save.setFont(font2);
        Cancel = new JButton("ȡ��");
        Cancel.setUI(new BEButtonUI()
                .setNormalColor(BEButtonUI.NormalColor.red));
        Cancel.setBounds(new Rectangle(350, 520, 70, 30));
        Cancel.setFont(font2);
        Return = new JButton("����");
        Return.setUI(new BEButtonUI()
                .setNormalColor(BEButtonUI.NormalColor.green));
        Return.setBounds(new Rectangle(450, 520, 70, 30));
        Return.setFont(font2);

        //�����ӵ�����
        add(labelStudentId);
        add(inputStudentId);
        add(labelName);
        add(inputName);
        sexGroup.add(radioBtnMale);
        sexGroup.add(radioBtnFemale);
        add(labelSex);
        add(radioBtnMale);
        add(radioBtnFemale);
        add(labelAge);
        add(inputAge);
        add(labelMajor);
        add(comboBoxMajor);
        add(labelInterest);
        add(checkbox);
        add(Save);
        add(Cancel);
        add(Return);

        //Ϊ�����������¼�������
        inputStudentId.addActionListener(this);
        inputName.addActionListener(this);
        inputAge.addActionListener(this);
        Save.addActionListener(this);
        Cancel.addActionListener(this);
        Return.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();//�õ���ť�ϵı�ǩ
        switch (cmd) {
            case "����":
                String sid = inputStudentId.getText();
                String name = inputName.getText();
                int age;
                try {
                    age = Integer.parseInt(inputAge.getText());
                } catch (RuntimeException exception) {
                    exception.printStackTrace();
                    MessageUtil.Warning("��������������");
                    return;
                }
                if (sid.equals("")) {
                    MessageUtil.Warning("ѧ�Ų���Ϊ��");
                    return;
                }
                if (name.equals("")) {
                    MessageUtil.Warning("��������Ϊ��");
                    return;
                }
                if (!radioBtnMale.isSelected() && !radioBtnFemale.isSelected()) {//isSelected()�������ť��ѡ�з���true
                    MessageUtil.Warning("��ѡ���Ա�");
                    return;
                }
                if (!hobby1.isSelected() && !hobby2.isSelected() &&
                        !hobby3.isSelected() && !hobby4.isSelected() &&
                        !hobby5.isSelected() && !hobby6.isSelected()) {
                    MessageUtil.Warning("��Ȥ���ò���Ϊ��");
                    return;
                }


                ClubMember clubMember = new ClubMember();
                clubMember.setStudentId(sid);
                clubMember.setName(name);
                clubMember.setAge(age);

                if (radioBtnMale.isSelected()) {//��ȡ��ѡ��ťѡ�е���������浽student��
                    clubMember.setSex(radioBtnMale.getText());
                } else {
                    clubMember.setSex(radioBtnFemale.getText());
                }
                clubMember.setMajor((String) comboBoxMajor.getSelectedItem());//�õ������б��е����ݱ��浽student��


                StringBuilder hobby = new StringBuilder();
                if (hobby1.isSelected()) {
                    hobby.append(hobby1.getText()).append("��");
                }
                if (hobby2.isSelected()) {
                    hobby.append(hobby2.getText()).append("��");
                }
                if (hobby3.isSelected()) {
                    hobby.append(hobby3.getText()).append("��");
                }
                if (hobby4.isSelected()) {
                    hobby.append(hobby4.getText()).append("��");
                }
                if (hobby5.isSelected()) {
                    hobby.append(hobby5.getText()).append("��");
                }
                if (hobby6.isSelected()) {
                    hobby.append(hobby6.getText()).append("��");
                }
                if (hobby.charAt(hobby.length() - 1) == '��') {//�жϸ�ѡ�����һ��ѡ����ʲô��Ȼ��ȥ��������
                    hobby.deleteCharAt(hobby.length() - 1);
                }

                clubMember.setInterest(hobby.toString());

                System.out.println(clubMember);
                try {
                    ClubMemberService.AddClubMember(clubId, clubMember);
                } catch (RuntimeException exception) {
                    exception.printStackTrace();
                    MessageUtil.Warning(exception.getMessage());
                    return;
                }
                MessageUtil.Info("��ӳɹ�");
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
