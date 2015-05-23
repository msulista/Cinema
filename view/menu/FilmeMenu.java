package view.menu;

/**
 * Created by marcus.rodrigues on 30/03/2015.
 */
public class FilmeMenu {
    public static final int OP_CADASTRAR = 1;
    public static final int OP_ATUALIZAR = 2;
    public static final int OP_DELETAR = 3;
    public static final int OP_LISTAR = 4;
    public static final int OP_VOLTAR = 0;

    public static String getOpcoes(){

        return ("\n===============================================\n" +
                "\n====================FILME======================\n" +
                "\t1 - Cadastrar Filme\n" +
                "\t2 - Atualizar Filme\n" +
                "\t3 - Deletar Filme\n" +
                "\t4 - Listar Filmes\n" +
                "\t0 - Voltar" +
                "\n===============================================\n");
    }
}
