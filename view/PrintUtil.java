package view;

import javax.swing.*;

/**
 * Created by marcus.rodrigues on 20/06/2015.
 */
public class PrintUtil {
    public static void printMessageError(JFrame janela, String msg) {
        JOptionPane.showMessageDialog(janela,
                msg,
                "Erro",
                JOptionPane.ERROR_MESSAGE);
    }

    public static void printMessageSucesso(JFrame janela, String msg) {
        JOptionPane.showMessageDialog(janela,
                msg,
                "Sucesso",
                JOptionPane.PLAIN_MESSAGE);
    }
}
