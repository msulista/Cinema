package view;

import dao.SalaDao;
import dao.SalaDaoBd;
import model.Sala;
import repositorio.RepositorioSala;
import util.Console;
import view.menu.SalaMenu;

import java.util.List;

/**
 * Created by marcus.rodrigues on 30/03/2015.
 */
public class SalaUI {

    private RepositorioSala listaSalas;

    public SalaUI(RepositorioSala listaSalas) {
        this.listaSalas = listaSalas;
    }

    /**
     * Método executar contem um switch com as opções de ação do usuario.
     */
    public void executar(){
        int opcao = 0;
        do {
            System.out.println(SalaMenu.getOpcoes());
            opcao = Console.lerInt("Digite opção desejada: ");
            switch (opcao){
                case SalaMenu.OP_CADASTRAR: {
                    cadastrarSala();
                    break;
                }
                case SalaMenu.OP_ATUALIZAR:{
                    atualizarSala();
                    break;
                }
                case SalaMenu.OP_DELETAR:{
                    deletarSalaPorNumero();
                    break;
                }
                case SalaMenu.OP_LISTAR:{
                    listaSalasCadastradas();
                    break;
                }
                case SalaMenu.OP_VOLTAR:{
                    System.out.println("Voltar ao menu principal!!!");
                    break;
                }
                default:
                    System.out.println("Digite uma opção válida!!!");
            }
        }while (opcao != SalaMenu.OP_VOLTAR);
    }

    /**
     * Método cadastrarSala responsavel por realizar a interação com o usuario e receber os dados do teclado
     * por fim cria uma sala e o adiciona a lista de salas.
     */
    public void cadastrarSala(){
        SalaDao dao = new SalaDaoBd();
        String numeroSala = Console.lerString("Numero da Sala: ");
        if(listaSalas.verificaSeJaExisteSalaPorNumero(numeroSala)){
            System.out.println("Sala de numero " + numeroSala + " já esta cadastrada!!!");
        }else{
            int qtdAssentos = Console.lerInt("Quantidade Assentos: ");
            Sala sala = new Sala(numeroSala, qtdAssentos);
            listaSalas.adicionaSala(sala);
            dao.inserir(sala);
            System.out.println("Sala numero " + numeroSala + " cadastrada com sucesso!!!");
        }
    }

    public void atualizarSala(){
        listaSalasCadastradas();
        SalaDao dao = new SalaDaoBd();

        String numSala = Console.lerString("Informe numero da sala: ");
        Sala sala = dao.buscarPorNumero(numSala);
        System.out.println("===============================================\n");
        System.out.println(String.format("%-10s", "NUMERO SALA") + "\t" +
                             String.format("%-20s", "QUANTIDADE DE ASSENTOS"));
        System.out.println(String.format("%-10s", sala.getNumero()) + "\t" +
                            String.format("%-20s", sala.getQtdAssentos()));

        System.out.println("\n Informe as alterações:");
        String numero = Console.lerString("Numero Sala: ");
        int qtdAssentos = Console.lerInt("Quantidade assentos: ");

        sala = new Sala(numero, qtdAssentos);
        dao.atualizar(sala, numSala); //insere no bd
        System.out.println("Filme " + sala.getNumero() + " foi atualizado com sucesso!!!");
    }

    public void deletarSalaPorNumero(){
        SalaDao dao = new SalaDaoBd();

        String numSala = Console.lerString("Informe numero da sala: ");
        Sala sala = dao.buscarPorNumero(numSala);
        System.out.println("===============================================\n");
        System.out.println(String.format("%-10s", "NUMERO SALA") + "\t" +
                String.format("%-20s", "QUANTIDADE DE ASSENTOS"));
        System.out.println(String.format("%-10s", sala.getNumero()) + "\t" +
                String.format("%-20s", sala.getQtdAssentos()));
        int confirma = Console.lerInt("\n Deseja realmente deletar esta sala? 1- Sim 2 - Não ");
        if (confirma == 1){
            dao.deletar(sala);
            System.out.println("Sala deletado com sucesso!!!");
        }else
            System.out.println("Sala não sera deletado!!!");
    }
    /**
     * Método listaDeSalasCadastradas lista todas as salas já cadastradas.
     */
    public void listaSalasCadastradas(){
        SalaDao dao = new SalaDaoBd();
        System.out.println("===============================================\n");
        System.out.println(String.format("%-10s", "NUMERO SALA") + "\t" +
                String.format("%-20s", "QUANTIDADE DE ASSENTOS"));
        for (Sala sala : dao.listar()){
            System.out.println(String.format("%-10s", sala.getNumero()) + "\t" +
                    String.format("%-20s", sala.getQtdAssentos()));
        }
    }
}
