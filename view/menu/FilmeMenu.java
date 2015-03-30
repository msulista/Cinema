package view.menu;

/**
 * Created by marcus.rodrigues on 30/03/2015.
 */
public class FilmeMenu {
    public static final int OP_CADASTRAR = 1;
    public static final int OP_LISTAR = 2;
    public static final int OP_VOLTAR = 0;

    public static String getOpcoes(){

        return ("\n===============================================\n" +
                "\t1 - Cadastrar Pacientes\n" +
                "\t2 - Listar Pacientes\n" +
                "\t0 - Voltar" +
                "\n===============================================\n");
    }
}
