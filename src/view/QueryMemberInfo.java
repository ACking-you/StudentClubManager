package view;

import model.entity.ClubMember;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;
import service.ClubMemberService;
import util.ExcleUtil;
import util.MessageUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class QueryMemberInfo extends JFrame implements ActionListener {
    private static QueryMemberInfo instance;
    private final JScrollPane panel;
    //���о�������İ�ť
    private final JButton query;
    private final JButton queryAll;
    private final JButton queryClubsBtn;
    private final JButton addBtn;
    private final JButton updateBtn;
    private final JButton deleteBtn;
    private final JButton exportExcelBtn;
    private final JButton importExcleBtn;
    private final JButton backBtn;
    private final JTable table;
    public String[] columnNames = {"��Ա����id", "ѧ��", "����", "�Ա�", "����", "רҵ", "��Ȥ����"};
    public DefaultTableModel model = null;//Ĭ�ϵı�����ģ��
    JLabel labelName;
    JTextField inputName;
    private int clubId;

    private QueryMemberInfo() {
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("src/images/logo.png"));
        this.setSize(1140, 680);
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");// ʹ��windows���
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.setResizable(false);

        Font font = new Font("����", Font.BOLD, 12);

        labelName = new JLabel("ѧ��������");
        labelName.setBounds(180, 30, 100, 30);
        labelName.setFont(font);

        inputName = new JTextField();
        inputName.setBounds(250, 30, 200, 30);


        query = new JButton("��ѯ");
        query.setUI(new BEButtonUI()
                .setNormalColor(BEButtonUI.NormalColor.lightBlue));
        query.setBounds(500, 30, 95, 30);

        query.setForeground(Color.blue);
        ImageIcon icon1 = new ImageIcon("src/images/query2.png");
        query.setIcon(icon1);

        queryAll = new JButton("��ѯ���г�Ա");
        queryAll.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightBlue));
        queryAll.setBounds(700, 30, 150, 30);
        queryAll.setForeground(Color.blue);

        table = new JTable();
        panel = new JScrollPane();//���ù�����
        panel.setViewportView(table);
        panel.setBounds(42, 136, 950, 420);


        queryClubsBtn = new JButton("��ѯ���μ�����");
        queryClubsBtn.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.red));
        queryClubsBtn.setBounds(100, 570, 120, 30);

        updateBtn = new JButton("����");
        updateBtn.setUI(new BEButtonUI()
                .setNormalColor(BEButtonUI.NormalColor.green));
        updateBtn.setBounds(270, 570, 90, 30);

        addBtn = new JButton("���");
        addBtn.setUI(new BEButtonUI()
                .setNormalColor(BEButtonUI.NormalColor.green));
        addBtn.setBounds(410, 570, 90, 30);

        deleteBtn = new JButton("ɾ��");
        deleteBtn.setUI(new BEButtonUI()
                .setNormalColor(BEButtonUI.NormalColor.green));
        deleteBtn.setBounds(550, 570, 90, 30);

        importExcleBtn = new JButton("��Excel��������");
        importExcleBtn.setUI(new BEButtonUI()
                .setNormalColor(BEButtonUI.NormalColor.green));
        importExcleBtn.setBounds(690, 570, 120, 30);

        exportExcelBtn = new JButton("������Excel");
        exportExcelBtn.setUI(new BEButtonUI()
                .setNormalColor(BEButtonUI.NormalColor.green));
        exportExcelBtn.setBounds(860, 570, 120, 30);

        backBtn = new JButton("����");
        backBtn.setUI(new BEButtonUI()
                .setNormalColor(BEButtonUI.NormalColor.green));
        backBtn.setBounds(1020, 570, 90, 30);

        query.addActionListener(this);
        queryAll.addActionListener(this);
        updateBtn.addActionListener(this);
        addBtn.addActionListener(this);
        deleteBtn.addActionListener(this);
        exportExcelBtn.addActionListener(this);
        backBtn.addActionListener(this);
        queryClubsBtn.addActionListener(this);
        importExcleBtn.addActionListener(this);

        this.getContentPane().setLayout(null);
        this.getContentPane().add(addBtn);
        this.getContentPane().add(importExcleBtn);
        this.getContentPane().add(queryClubsBtn);
        this.getContentPane().add(panel);
        this.getContentPane().add(labelName);
        this.getContentPane().add(inputName);
        this.getContentPane().add(updateBtn);
        this.getContentPane().add(deleteBtn);
        this.getContentPane().add(exportExcelBtn);
        this.getContentPane().add(backBtn);
        this.getContentPane().add(query);
        this.getContentPane().add(queryAll);


        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    public static QueryMemberInfo getInstance(int clubId) {
        if (instance == null) {
            instance = new QueryMemberInfo();
        }
        instance.setClubId(clubId);
        return instance;
    }

    public static void main(String[] args) {
        getInstance(49).reset();
    }

    public void setClubId(int clubId) {
        this.clubId = clubId;
    }

    //��ÿ��getInstance����Ҫreset
    public void reset() {
        showAll();
        inputName.setText("");
        //������ѡid���ö�Ӧ�ı���
        setTitle(String.format("����IDΪ%d�ĳ�Ա", clubId));
        setVisible(true);
    }

    // ����List�������ɶ�Ӧ��Object����
    public Object[][] getData(List<ClubMember> clubMembers) {
        if (clubMembers.size() > 0) {
            Object[][] data = new Object[clubMembers.size()][columnNames.length];
            for (int i = 0; i < clubMembers.size(); i++) {
                ClubMember s = clubMembers.get(i);
                Object[] a = {s.getId(), s.getStudentId(), s.getName(), s.getSex(), s.getAge(), s.getMajor(), s.getInterest()};
                data[i] = a;//�������ֵ������ά�����һ��
            }
            return data;
        }
        return null;
    }

    ClubMember getClubMemberByChoose() {
        ClubMember c = new ClubMember();
        int row = table.getSelectedRow();
        if (row == -1) {
            throw new RuntimeException("��ѡ����Ҫ�����Ķ���");
        }
        int id = Integer.parseInt(table.getValueAt(row, 0).toString());
        String studentId = table.getValueAt(row, 1).toString();//��������ʱ�쳣
        String name = table.getValueAt(row, 2).toString();
        String sex = table.getValueAt(row, 3).toString();
        int age = Integer.parseInt(table.getValueAt(row, 4).toString());
        String major = table.getValueAt(row, 5).toString();
        String interest = table.getValueAt(row, 6).toString();
        c.setId(id).setStudentId(studentId).setName(name).setSex(sex).setAge(age)
                .setMajor(major).setInterest(interest);
        System.out.println(c);
        return c;
    }

    public void showAll() {
        List<ClubMember> list = ClubMemberService.QueryClubMembersByClubId(clubId);
        System.out.println(list);
        Object[][] data = getData(list);
        createTable(data);
    }

    public void createTable(Object[][] data) {
        if (model == null) {
            DefaultTableCellRenderer r = new DefaultTableCellRenderer();
            r.setHorizontalAlignment(JLabel.CENTER);
            table.setDefaultRenderer(Object.class, r);
        }

        //��id�ֶβ��ɱ༭
        model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0;
            }
        };
        table.setModel(model);
        table.setRowHeight(20);
    }


    //���ж�Ӧ�Ļص�
    private void doQuery() {
        String name = inputName.getText();
        try {
            createTable(getData(ClubMemberService.QueryClubMemberByName(clubId, name)));
        } catch (RuntimeException e) {
            e.printStackTrace();
            MessageUtil.Warning(e.getMessage());
        }
    }


    //TODO
    private void doQueryClubs() {

    }

    private void doUpdate() {
        try {
            ClubMemberService.UpdateClubMember(getClubMemberByChoose());
        } catch (RuntimeException e) {
            e.printStackTrace();
            MessageUtil.Warning(e.getMessage());
            return;
        }
        MessageUtil.Info("���³ɹ�");
    }

    private void doDelete() {
        try {
            ClubMemberService.DeleteClubMemberById(clubId, getClubMemberByChoose().getId());
        } catch (RuntimeException e) {
            e.printStackTrace();
            MessageUtil.Warning(e.getMessage());
            showAll();
            return;
        }
        MessageUtil.Info("ɾ���ɹ�");
    }

    private void doAdd() {
        AddMemberInfo.getInstance(clubId).reset();
    }

    private void doImportExcle() throws IOException {
        ExcleUtil.ImportClubMemberInfoByExcleUI(clubId);
    }

    private void doExportExcle() throws IOException {
        ExcleUtil.ExportClubMemberToExcleUI(clubId);
    }

    /**
     * ��ť�¼�
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();

        if (button.equals(query)) {
            System.out.println("=============>��ʼ��������ģ����ѯ...");
            doQuery();
        } else if (button.equals(queryAll)) {
            System.out.println("=============>��ʼ��ѯ���г�Ա...");
            showAll();
        } else if (button.equals(queryClubsBtn)) {
            System.out.println("==============================>��ʼ��ѯ�����μӵ�����");
            doQueryClubs();
        } else if (button.equals(updateBtn)) {
            System.out.println("===============================>��ʼ���³�Ա��Ϣ");
            doUpdate();
            showAll();
        } else if (button.equals(addBtn)) {
            System.out.println("===============================>��ʼ���ӳ�Ա");
            doAdd();
            showAll();
        } else if (button.equals(deleteBtn)) {
            System.out.println("=============================>��ʼɾ������");
            doDelete();
            showAll();
        } else if (button.equals(importExcleBtn)) {
            System.out.println("=============================>��ʼ����Excle��Ϣ");
            try {
                doImportExcle();
            } catch (IOException | RuntimeException exception) {
                exception.printStackTrace();
                MessageUtil.Warning(exception.getMessage());
            }
        } else if (button.equals(exportExcelBtn)) {
            System.out.println("=============================>��ʼ����Excle��Ϣ");
            try {
                doExportExcle();
            } catch (IOException | RuntimeException exception) {
                exception.printStackTrace();
                MessageUtil.Warning(exception.getMessage());
            }
        } else if (button.equals(backBtn)) {
            setVisible(false);
        }
    }
}
