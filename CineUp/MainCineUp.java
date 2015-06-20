package CineUp;

import controller.FilmeController;
import view.MainUI;
import view.janela.JanelaCrud;

/**
 * Created by marcus.rodrigues on 30/03/2015.
 *
 * Classe MainCineUP utilizada apenas para estanciar classe MainUI e executar metodo executar().
 */
public class MainCineUp {

    public static void main(String[] args) {

       // new MainUI().executar();
        FilmeController filmeController = new FilmeController();
        JanelaCrud janelaCrud = new JanelaCrud(filmeController);
        filmeController.setJanela(janelaCrud);
    }
}
