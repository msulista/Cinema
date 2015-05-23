package view.menu;

/**
 * Created by marcus.rodrigues on 30/03/2015.
 */
public class SecaoMenu {
    public static final int OP_CADASTRAR = 1;
    public static final int OP_ATUALIZAR = 2;
    public static final int OP_DELETAR = 3;
    public static final int OP_LISTAR = 4;
    public static final int OP_BUSCA_FILME = 5;
    public static final int OP_VOLTAR = 0;

    public static String getOpcoes(){

        return ("\n===============================================\n" +
                "\n==========SESSÃO=================BUSCAS========\n" +
                "\t1 - Cadastrar Sessão\t5 - Busca por Filme\n" +
                "\t2 - Atualizar Sessão\n" +
                "\t3 - Deletar Sessão\n" +
                "\t4 - Listar Sessões\n" +
                "\t0 - Voltar" +
                "\n===============================================\n");
    }
}
