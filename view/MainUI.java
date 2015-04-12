package view;

import model.Venda;
import repositorio.RepositorioFilme;
import repositorio.RepositorioSala;
import repositorio.RepositorioSecao;
import repositorio.RepositorioVenda;
import util.Console;
import view.menu.MainMenu;

/**
 * Created by marcus.rodrigues on 11/04/2015.
 */
public class MainUI {
    private RepositorioFilme repositorioFilme;
    private RepositorioSala repositorioSala;
    private RepositorioSecao repositorioSecao;
    private RepositorioVenda repositorioVenda;

    public MainUI(){
        this.repositorioFilme = new RepositorioFilme();
        this.repositorioSala = new RepositorioSala();
        this.repositorioSecao = new RepositorioSecao();
        this.repositorioVenda = new RepositorioVenda();
    }

    public void executar() {
        int opcao = 0;
        do {
            System.out.println(MainMenu.getOpcoes());
            opcao = Console.lerInt("Digite sua opção:");
            switch (opcao) {
                case MainMenu.OP_FILME: {
                    new FilmeUI(repositorioFilme).executar();
                    break;
                }
                case MainMenu.OP_SALA: {
                    new SalaUI(repositorioSala).executar();
                    break;
                }
                case MainMenu.OP_SESSAO: {
                    new SecaoUI(repositorioSecao, repositorioFilme, repositorioSala).executar();
                    break;
                }
                case MainMenu.OP_VENDA: {
                    new VendaUI(repositorioVenda, repositorioSecao, repositorioFilme, repositorioSala).executar();
                    break;
                }
                case MainMenu.OP_SAIR: {
                    System.out.println("Aplicação finalizada!!!");
                    break;
                }
                default:
                    System.out.println("Opção inválida..");

            }
        } while (opcao != MainMenu.OP_SAIR);
    }
}
