package view.menu;

/**
 * Created by marcus.rodrigues on 30/03/2015.
 */
public class SalaMenu {

    public static final int OP_CADASTRAR = 1;
    public static final int OP_ATUALIZAR = 1;
    public static final int OP_DELETAR = 1;
    public static final int OP_LISTAR = 2;
    public static final int OP_VOLTAR = 0;

    public static String getOpcoes(){

        return ("\n===============================================\n" +
                "\n=====================SALA======================\n" +
                "\t1 - Cadastrar Sala\n" +
                "\t2 - Atualizar Sala\n" +
                "\t3 - Deletar Sala\n" +
                "\t4 - Listar Salas\n" +
                "\t0 - Voltar" +
                "\n===============================================\n");
    }
}
