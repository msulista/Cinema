package view;

import model.Sala;
import repositorio.RepositorioSala;
import util.Console;
import view.menu.SalaMenu;

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
        String numeroSala = Console.lerString("Numero da Sala: ");
        if(listaSalas.verificaSeJaExisteSalaPorNumero(numeroSala)){
            System.out.println("Sala de numero " + numeroSala + " já esta cadastrada!!!");
        }else{
            int qtdAssentos = Console.lerInt("Quantidade Assentos: ");
            listaSalas.adicionaSala(new Sala(numeroSala, qtdAssentos));
            System.out.println("Sala numero " + numeroSala + " cadastrada com sucesso!!!");
        }
    }

    /**
     * Método listaDeSalasCadastradas lista todas as salas já cadastradas.
     */
    public void listaSalasCadastradas(){
        System.out.println("===============================================\n");
        System.out.println(String.format("%-10s", "NUMERO SALA") + "\t" +
                String.format("%-20s", "QUANTIDADE DE ASSENTOS"));

        for (Sala sala : listaSalas.getSalas()){
            System.out.println(String.format("%-10s", sala.getNumero()) + "\t" +
                    String.format("%-20s", sala.getQtdAssentos()));
        }
    }
}
