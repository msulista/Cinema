package view.menu;

/**
 * Created by marcus.rodrigues on 12/04/2015.
 */
public class VendaMenu {
    public static final int OP_VENDER = 1;
    public static final int OP_VENDER_MEIA_ENTRADA = 2;
    public static final int OP_LISTAR = 3;
    public static final int OP_RELATORIO_HORARIO = 3;
    public static final int OP_VOLTAR = 0;

    public static String getOpcoes(){

        return ("\n===============================================\n" +
                " ====================VENDA======================\n" +
                "\t1 - Vender Inteira\n" +
                "\t2 - Vender Meia Entrada\n" +
                "\t3 - Listar Vendas\n" +
                "\t===================RELATÓRIOS==================\n" +
                "\t4 - Relatório de vendas por horário\n" +
                "\t0 - Voltar" +
                "\n===============================================\n");
    }
}
