package view;

import util.ExcleUtil;
import util.LinkLabel;
import util.MessageUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;


/*
 * ��½�ɹ���������
 */
public class MainMenu extends JFrame implements ActionListener {
    static String IMG_PATH = "src/images/background";
    private static MainMenu instance;
    ImageIcon[] images = null;
    private JMenuItem addClubInfo, queryClubInfo, modifyPassword, Reload;
    private JMenuItem importExcel, outputExcel;
    private JMenuItem aboutSystem, help;

    private MainMenu() {
        super("��ӭʹ��������Ϣ����ϵͳ made by L_B__");
        initImages();
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");// ʹ��windows���
        } catch (Exception e) {
            e.printStackTrace();
        }
        setSize(1200, 785);
        initUI();
    }

    public static MainMenu getInstance() {
        if (instance == null) {
            instance = new MainMenu();
        }
        return instance;
    }

    public static void main(String[] args) {
        getInstance().reset();
    }

    public void reset() {
        setVisible(true);
    }

    private void initImages() {
        if (images == null) {
            File file = new File(IMG_PATH);
            File[] files = file.listFiles();    //�г�����ͼƬ������
            assert files != null;
            images = new ImageIcon[files.length];
            for (int i = 0; i < files.length; i++) {
                images[i] = new ImageIcon(files[i].getPath());
            }
        }
    }

    public void initUI() {
        setIconImage(Toolkit.getDefaultToolkit().getImage("src/images/logo.png"));
        Color bgk_color = new Color(0xE6F7FA);
        getContentPane().setBackground(bgk_color);
        //���ڻ����ֲ���ֽ�Ļ���
        MyJPanel myJPanel = new MyJPanel();
        myJPanel.setBounds(0, 220, 1200, 500);
        setResizable(false);//���ڲ��ɱ�
        getContentPane().add(myJPanel);


        //������������
        JLabel welcome_text = new JLabel("��ӭʹ��������Ϣ����ϵͳ");
        Font font_welcome = new Font("����", Font.BOLD, 40);
        welcome_text.setFont(font_welcome);
        welcome_text.setBounds(350, 50, 500, 50);
        welcome_text.setForeground(Color.DARK_GRAY);
        getContentPane().add(welcome_text);

        JLabel jLabel1 = new JLabel();
        Font font_author = new Font("����", Font.PLAIN, 14);
        jLabel1.setFont(font_author);
        jLabel1.setBounds(20, 20, 50, 20);
        jLabel1.setForeground(Color.gray);
        jLabel1.setText("���ߣ�");
        getContentPane().add(jLabel1);

        JPanel myWebsiteLink = LinkLabel.getInstance(" @ L_B__", "https://acking-you.gitee.io/");
        myWebsiteLink.setBackground(bgk_color);
        myWebsiteLink.setFont(new Font("Arial", Font.PLAIN, 14));
        myWebsiteLink.setBounds(50, 18, 50, 20);
        getContentPane().add(myWebsiteLink);

        LinkLabel myGithub = LinkLabel.getInstance(new ImageIcon("src/images/github.png"), "https://github.com/ACking-you");
        myGithub.setBounds(550, 17, 24, 24);
        getContentPane().add(myGithub);

        LinkLabel myBilibili = LinkLabel.getInstance(new ImageIcon("src/images/bilibili.png"), "https://space.bilibili.com/24264499");
        myBilibili.setBounds(590, 17, 24, 24);
        getContentPane().add(myBilibili);

        JLabel author_text = new JLabel();
        author_text.setText("��ϵͳ�� L_B__ �� 2022.06.3 ���");
        author_text.setFont(new Font("����", Font.PLAIN, 15));
        author_text.setForeground(Color.LIGHT_GRAY);
        author_text.setBounds(450, 100, 400, 20);
        getContentPane().add(author_text);

        JLabel webSite_description = new JLabel();
        webSite_description.setText("������Ϣ�ɽ����ҵĸ�����վ�鿴��");
        webSite_description.setFont(new Font("����", Font.PLAIN, 14));
        webSite_description.setForeground(Color.GRAY);
        webSite_description.setBounds(420, 130, 230, 20);
        getContentPane().add(webSite_description);

        JPanel webSiteLink = LinkLabel.getInstance("acking-you.gitee.io", "https://acking-you.gitee.io/");
        webSiteLink.setBounds(640, 128, 120, 20);
        webSiteLink.setBackground(bgk_color);
        webSiteLink.setFont(new Font("Arial", Font.PLAIN, 15));
        getContentPane().add(webSiteLink);

        JLabel sponsor_text = new JLabel();
        sponsor_text.setText("ϲ���Ļ�����ɨ���ұߵĶ�ά���������^_^");
        sponsor_text.setFont(new Font("����", Font.PLAIN, 16));
        sponsor_text.setForeground(Color.GRAY);
        sponsor_text.setBounds(420, 160, 380, 20);
        getContentPane().add(sponsor_text);


        //wechat�տ���
        JLabel wechat = new JLabel();
        wechat.setIcon(new ImageIcon("src/images/wechat.png"));
        wechat.setBounds(980, 50, 119, 119);
        getContentPane().add(wechat);

        //Menu�����
        JMenu menuFile1 = new JMenu("��������(0)");
        Font font = new Font("����", Font.BOLD, 16);
        menuFile1.setFont(font);
        menuFile1.setIcon(new ImageIcon("src/images/icons/base1.png"));
        menuFile1.setMnemonic('O');
        add(menuFile1);

        JMenuBar menuBar = new JMenuBar();
        addClubInfo = new JMenuItem("����", new ImageIcon("src/images/icons/add.png"));
        addClubInfo.setMnemonic('A');
        addClubInfo.setAccelerator(KeyStroke.getKeyStroke('A', InputEvent.CTRL_DOWN_MASK));
        menuFile1.add(addClubInfo);

        queryClubInfo = new JMenuItem("��ѯ", new ImageIcon("src/images/icons/query.png"));
        queryClubInfo.setMnemonic('Q');
        queryClubInfo.setAccelerator(KeyStroke.getKeyStroke('Q', InputEvent.CTRL_DOWN_MASK));
        menuFile1.add(queryClubInfo);

        modifyPassword = new JMenuItem("�����޸�", new ImageIcon("src/images/icons/modifyPassword.png"));
        modifyPassword.setMnemonic('M');
        modifyPassword.setAccelerator(KeyStroke.getKeyStroke('M', InputEvent.CTRL_DOWN_MASK));
        menuFile1.add(modifyPassword);

        Reload = new JMenuItem("���µ�¼", new ImageIcon("src/images/icons/reload.png"));
        Reload.setMnemonic('R');
        Reload.setAccelerator(KeyStroke.getKeyStroke('R', InputEvent.CTRL_DOWN_MASK));
        menuFile1.add(Reload);

        menuBar.add(menuFile1);

        //====================>���뵼��
        JMenu menuFile2 = new JMenu("���뵼��(1)");
        menuFile2.setFont(font);
        menuFile2.setIcon(new ImageIcon("src/images/icons/base2.png"));
        importExcel = new JMenuItem("��excel����", new ImageIcon("src/images/icons/import.png"));
        importExcel.setMnemonic('C');
        importExcel.setAccelerator(KeyStroke.getKeyStroke('C', InputEvent.CTRL_DOWN_MASK));
        menuFile2.add(importExcel);


        outputExcel = new JMenuItem("����excle", new ImageIcon("src/images/icons/import1.png"));
        outputExcel.setMnemonic('L');
        outputExcel.setAccelerator(KeyStroke.getKeyStroke('L', InputEvent.CTRL_DOWN_MASK));
        menuFile2.add(outputExcel);


        menuBar.add(menuFile2);
        JMenu menuFile3 = new JMenu("����(2)");
        menuFile3.setFont(font);
        menuFile3.setIcon(new ImageIcon("src/images/icons/base3.png"));
        aboutSystem = new JMenuItem("���ڱ�ϵͳ", new ImageIcon("src/images/icons/about.png"));
        aboutSystem.setMnemonic('B');
        aboutSystem.setAccelerator(KeyStroke.getKeyStroke('B', InputEvent.CTRL_DOWN_MASK));
        menuFile3.add(aboutSystem);

        help = new JMenuItem("ϵͳ����", new ImageIcon("src/images/icons/help.png"));
        help.setMnemonic('H');
        help.setAccelerator(KeyStroke.getKeyStroke('H', InputEvent.CTRL_DOWN_MASK));
        menuFile3.add(help);
        menuBar.add(menuFile3);
        setJMenuBar(menuBar);

        //ͳһ�����¼�����
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent arg0) {
                System.exit(1);
            }
        });
        addClubInfo.addActionListener(this);
        queryClubInfo.addActionListener(this);
        modifyPassword.addActionListener(this);
        importExcel.addActionListener(this);
        outputExcel.addActionListener(this);
        aboutSystem.addActionListener(this);
        help.addActionListener(this);
        Reload.addActionListener(this);

        //��ֽ�ֲ�
        final Timer timer = new Timer(8000, (e) -> {
            myJPanel.repaint();
        });
        timer.start();
        setLocation(40, 0);
    }

    //ͳһ�����¼��ļ����ص�
    @Override
    public void actionPerformed(ActionEvent event) {
        JMenuItem item = (JMenuItem) event.getSource();
        if (item.equals(addClubInfo)) {
            System.out.println("=======>�û�ѡ���ˡ����������Ϣ���˵���");
            AddClubInfo.getInstance().reset();
        } else if (item.equals(queryClubInfo)) {
            System.out.println("=======>�û�ѡ���ˡ���ѯ������Ϣ���˵���");
            QueryClubInfo.getInstance().reset();
        } else if (item.equals(modifyPassword)) {
            System.out.println("=======>�û�ѡ���ˡ��޸����롯�˵���");
            ModifyPasswordInfo.getInstance().reset();
        } else if (item.equals(importExcel)) {
            System.out.println("=======>�û�ѡ���ˡ����뵽excel���˵���");
            try {
                ExcleUtil.ImportClubInfoByExcleUI();
            } catch (IOException | RuntimeException e) {
                e.printStackTrace();
                MessageUtil.Warning(e.getMessage());
            }
        } else if (item.equals(outputExcel)) {
            System.out.println("=======>�û�ѡ���ˡ�������excel���˵���");
            try {
                ExcleUtil.ExportClubToExcleUI();
            } catch (IOException | RuntimeException e) {
                e.printStackTrace();
                MessageUtil.Warning(e.getMessage());
            }
        } else if (item.equals(aboutSystem)) {
            System.out.println("=======>�û�ѡ���ˡ�����ϵͳ���˵���");
            AboutSystem.getInstance().reset();
        } else if (item.equals(help)) {
            System.out.println("=======>�û�ѡ���ˡ��������˵���");
            Help.getInstance().reset();
        } else if (item.equals(Reload)) {
            System.out.println("=======>�û�ѡ���ˡ����µ�¼'�˵���");
            setVisible(false);
            Login.getInstance().reset();
        }
    }

    //����ѭ���ֲ���Panel
    class MyJPanel extends JPanel {
        int index = 0;

        @Override
        public void paint(Graphics g) {
            if (index > 100000)
                index = 0;
            super.paint(g);
            g.drawImage(images[index % images.length].getImage(), 0, 0, this);
            index++;
        }
    }
}
