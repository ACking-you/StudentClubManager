package util;

import javax.swing.*;

public class MessageUtil {
    public static void Warning(String msg) {
        JOptionPane.showMessageDialog(null, msg, "����",
                JOptionPane.WARNING_MESSAGE);
    }

    public static void Info(String msg) {
        JOptionPane.showMessageDialog(null, msg, "��Ϣ",
                JOptionPane.PLAIN_MESSAGE);
    }

    public static void QuestionMessage(String msg) {
        JOptionPane.showMessageDialog(null, msg, "ע��",
                JOptionPane.QUESTION_MESSAGE);
    }
}
