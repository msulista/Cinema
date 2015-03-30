package view;

import repositorio.RepositorioFilme;
import view.menu.FilmeMenu;

/**
 * Created by marcus.rodrigues on 30/03/2015.
 */
public class FilmeUI {

    private RepositorioFilme listaPacientes;

    public FilmeUI(RepositorioFilme listaPacientes) {
        this.listaPacientes = listaPacientes;
    }

    public void executar(){
        int opcao = 0;
        do {
            System.out.println(FilmeMenu.getOpcoes());
        }while (opcao != 0);
    }
}
