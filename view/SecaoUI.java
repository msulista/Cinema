package view;

import model.Filme;
import model.Sala;
import repositorio.RepositorioFilme;
import repositorio.RepositorioSala;
import repositorio.RepositorioSecao;
import util.Console;
import view.menu.SecaoMenu;

import java.util.Date;

/**
 * Created by marcus.rodrigues on 30/03/2015.
 */
public class SecaoUI {
    private RepositorioSecao listaSecoes;
    private RepositorioFilme listaFilme;
    private RepositorioSala listaSala;
    private Date horario;

    public SecaoUI(RepositorioSecao listaSecoes, RepositorioFilme listaFilme, RepositorioSala listaSala) {

        this.listaSecoes = listaSecoes;
        this.listaFilme = listaFilme;
        this.listaSala = listaSala;
    }

    public void executar(){
        int opcao = 0;
        do {
            System.out.println(SecaoMenu.getOpcoes());
            opcao = Console.lerInt("Digite opção desejada: ");
            switch (opcao){
                case SecaoMenu.OP_CADASTRAR:{
                    break;
                }
                case SecaoMenu.OP_LISTAR: {
                    break;
                }
                case SecaoMenu.OP_VOLTAR: {
                    System.out.println("Voltar ao menu principal!!!");
                    break;
                }
                default:
                    System.out.println("Digite uma opção válida!!!");
            }

        }while (opcao != SecaoMenu.OP_VOLTAR);
    }

    public void cadastrarSecao(){
        System.out.println("Escolha um dos filme abaixo: ");
        new FilmeUI(listaFilme).listaFilmesCadastrados();
        int codFilme = Console.lerInt("Digite o código do filme desejado: ");
        Filme filme = listaFilme.buscaFilmePorCodigo(codFilme);

        System.out.println("Escolha uma das salas abaixo: ");
        new SalaUI(listaSala).listaSalasCadastradas();
        int numeroSala = Console.lerInt("Digite o numero da sala desejada: ");
        Sala sala = listaSala.buscaSalaPorNumero(numeroSala);

        //Fala por o horario da sessão
    }
}
