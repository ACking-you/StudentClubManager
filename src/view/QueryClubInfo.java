package view;

import model.entity.Club;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;
import service.ClubService;
import util.MessageUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class QueryClubInfo extends JFrame implements ActionListener {
    private final JScrollPane panel;
    //���о�������İ�ť
    private final JButton normalOrder;
    private final JButton reverseOrder;
    private final JButton query;
    private final JButton queryAll;
    private final JButton queryMembers;
    private final JButton updateBtn;
    private final JButton deleteBtn;
    private final JButton exportExcelBtn;
    private final JButton backBtn;
    private final JTable table;
    public String[] columnNames = {"����id", "��������", "��������","����������"};
    public DefaultTableModel model = null;//Ĭ�ϵı�����ģ��

    JLabel labelName;
    JTextField inputName;
    /*
     * ���弰��Ľ���
     */
    public QueryClubInfo() {
        super("������Ϣ��ѯͳ��");
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("src/images/logo.png"));
        this.setSize(1040, 680);
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");// ʹ��windows���
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.setResizable(false);

        Font font = new Font("����", Font.BOLD, 12);

        labelName = new JLabel("�������ƣ�");
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

        queryAll = new JButton("��ѯ��������");
        queryAll.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightBlue));
        queryAll.setBounds(700, 30, 150, 30);
        queryAll.setForeground(Color.blue);

        table = new JTable();
        panel = new JScrollPane();//���ù�����
        panel.setViewportView(table);
        panel.setBounds(42, 136, 950, 420);



        queryMembers = new JButton("��ѯ��Ա");
        queryMembers.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.red));
        queryMembers.setBounds(400,570,90,30);

        normalOrder = new JButton("��������");
        normalOrder.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.normal));
        normalOrder.setBounds(300,570,90,30);

        reverseOrder = new JButton("��������");
        reverseOrder.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.normal));;
        reverseOrder.setBounds(200,570,90,30);

        updateBtn = new JButton("����");
        updateBtn.setUI(new BEButtonUI()
                .setNormalColor(BEButtonUI.NormalColor.green));
        updateBtn.setBounds(524, 570, 90, 30);

        deleteBtn = new JButton("ɾ��");
        deleteBtn.setUI(new BEButtonUI()
                .setNormalColor(BEButtonUI.NormalColor.green));
        deleteBtn.setBounds(644, 570, 90, 30);

        exportExcelBtn = new JButton("������Excel");
        exportExcelBtn.setUI(new BEButtonUI()
                .setNormalColor(BEButtonUI.NormalColor.green));
        exportExcelBtn.setBounds(764, 570, 120, 30);
        backBtn = new JButton("����");
        backBtn.setUI(new BEButtonUI()
                .setNormalColor(BEButtonUI.NormalColor.green));
        backBtn.setBounds(908, 570, 90, 30);

        query.addActionListener(this);
        queryAll.addActionListener(this);
        updateBtn.addActionListener(this);
        deleteBtn.addActionListener(this);
        exportExcelBtn.addActionListener(this);
        backBtn.addActionListener(this);
        queryMembers.addActionListener(this);
        reverseOrder.addActionListener(this);
        normalOrder.addActionListener(this);

        this.getContentPane().setLayout(null);
        this.getContentPane().add(queryMembers);
        this.getContentPane().add(panel);
        this.getContentPane().add(labelName);
        this.getContentPane().add(inputName);
        this.getContentPane().add(updateBtn);
        this.getContentPane().add(deleteBtn);
        this.getContentPane().add(exportExcelBtn);
        this.getContentPane().add(backBtn);
        this.getContentPane().add(reverseOrder);
        this.getContentPane().add(normalOrder);
        this.getContentPane().add(query);
        this.getContentPane().add(queryAll);


        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        showAllStudent();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub

        QueryClubInfo q = new QueryClubInfo();
    }




    /**
     * ���ԭʼ���ݼ�
     *
     * @param clubs
     * @return String sId, String sName, String sSex, String sBirthday,
     * String sProvince, String sHobby, String sPhone
     */
    public Object[][] getData(List<Club> clubs) {
        if (clubs.size() > 0) {
            Object[][] data = new Object[clubs.size()][columnNames.length];
            for (int i = 0;i<clubs.size();i++) {
                Club s = clubs.get(i);
                Object[] a = {s.getId(), s.getName(), s.getCategory(),s.getMemberCount()};
                data[i] = a;//�������ֵ������ά�����һ��
            }
            return data;
        }
        return null;
    }


    public void showAllStudent() {
        List<Club> list = ClubService.QueryAllClub();
        System.out.println(list);
        Object[][] data = getData(list);
        initTable(data);
    }

    /**
     * ��ʼ���������
     */
    public void initTable(Object[][] data) {
        if(model==null){
            DefaultTableCellRenderer r = new DefaultTableCellRenderer();
            r.setHorizontalAlignment(JLabel.CENTER);
            table.setDefaultRenderer(Object.class, r);
        }

        //��id���ɱ༭
        model = new DefaultTableModel(data, columnNames){
            @Override
            public boolean isCellEditable(int row,int column){
                return column != 0;
            }
        };
        table.setModel(model);
        table.setRowHeight(20);
    }

    private void doQuery(){
        String name = inputName.getText();
        try{
            initTable(getData(ClubService.QueryClubsByName(name)));
        }catch (RuntimeException e){
            e.printStackTrace();
            MessageUtil.Warning(e.getMessage());
        }
    }

    Club getClubByChoose(){
        Club c = new Club();
        int row = table.getSelectedRow();
        if(row==-1){
            throw new RuntimeException("��ѡ����Ҫ�����Ķ���");
        }
        int id = Integer.parseInt(table.getValueAt(row,0).toString());//��������ʱ�쳣
        String name = table.getValueAt(row,1).toString();
        String category = table.getValueAt(row,2).toString();
        int count = Integer.parseInt(table.getValueAt(row,3).toString());
        c.setId(id).setName(name).setCategory(category).setMemberCount(count);
        System.out.println(c);
        return c;
    }

    //TODO
    private void doQueryMember(){

    }
    private void doUpdate(){
        try {
            ClubService.UpdateClub(getClubByChoose());
        } catch (RuntimeException e) {
            e.printStackTrace();
            MessageUtil.Warning(e.getMessage());
        }
    }
    private void doDelete(){
        try {
            ClubService.DeleteClub(getClubByChoose());
        } catch (RuntimeException e) {
            e.printStackTrace();
            MessageUtil.Warning(e.getMessage());
            showAllStudent();
        }
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
        }
        else if (button.equals(queryAll)) {
            System.out.println("=============>��ʼ��ѯ����ѧ��...");
            showAllStudent();
        }
        else if(button.equals(normalOrder)){
            System.out.println("===============>��ʼ˳������");
            try {
                initTable(getData(ClubService.QueryAllClubByOrder(true)));
            } catch (RuntimeException exception) {
                exception.printStackTrace();
                MessageUtil.Warning(exception.getMessage());
            }
        }
        else if(button.equals(reverseOrder)){
            System.out.println("=======================>��ʼ��������");
            try {
                initTable(getData(ClubService.QueryAllClubByOrder(false)));
            } catch (RuntimeException exception) {
                exception.printStackTrace();
                MessageUtil.Warning(exception.getMessage());
            }
        }
        else if(button.equals(queryMembers)){
            System.out.println("==============================>��ʼ��ѯ���ŵĳ�Ա");
            doQueryMember();
        }
        else if (button.equals(updateBtn)) {
            System.out.println("===============================>��ʼ����������Ϣ");
            doUpdate();
            showAllStudent();
        }
        else if (button.equals(deleteBtn)) {
            System.out.println("===========================>��ʼɾ������");
            doDelete();
            showAllStudent();
        }
        else if (button.equals(exportExcelBtn)) {

        }
        else if(button.equals(backBtn)){
            setVisible(false);
        }
    }
}
