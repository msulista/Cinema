package view.menu;

/**
 * Created by marcus.rodrigues on 11/04/2015.
 */
public class MainMenu {
    public static final int OP_FILME = 1;
    public static final int OP_SALA = 2;
    public static final int OP_SESSAO = 3;
    public static final int OP_VENDA = 4;
    public static final int OP_SAIR = 0;

    public static String getOpcoes() {
        return ("\n----------------------------------------\n" +
                "\t\t###CINE UP###" +
                "\n--------------------------------------\n"
                + "1- Menu Filme\n"
                + "2- Menu Sala\n"
                + "3- Menu Sessão\n"
                + "4- Menu Venda\n"
                + "0- Sair da Aplicação"
                + "\n--------------------------------------");
    }
}
