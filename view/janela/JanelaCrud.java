package view.janela;

import controller.FilmeController;
import view.form.FilmePainelFormulario;
import view.table.FilmePainelTabela;

import javax.swing.*;
import java.awt.*;

/**
 * Created by marcus.rodrigues on 20/06/2015.
 */
public class JanelaCrud extends  JFrame{

        private FilmeController filmeController;

        public final static String PAINELFORM = "Formulario";
        public final static String PAINELTABELA = "Tabela";
        private JPanel painelPrincipal;
        private FilmePainelFormulario filmePainelFormulario;
        private FilmePainelTabela filmePainelTabela;

        public JanelaCrud(FilmeController filmeController) {
            this.filmeController = filmeController;
            this.filmeController.setJanela(this);
            iniciaComponentes();
            filmeController.atualizaTabela();
            this.setTitle("Aplicação CRUD Pacientes");
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.pack();
            this.setVisible(true);
        }

        private void iniciaComponentes() {
            painelPrincipal = new JPanel(new CardLayout());
            filmePainelTabela = new FilmePainelTabela(filmeController);
            painelPrincipal.add(filmePainelTabela, PAINELTABELA);
            filmePainelFormulario = new FilmePainelFormulario(filmeController);
            painelPrincipal.add(filmePainelFormulario, PAINELFORM);
            this.add(painelPrincipal);
        }

        public void mostrarPainel(String painel) {
            CardLayout card = (CardLayout) (painelPrincipal.getLayout());
            card.show(painelPrincipal, painel);

        }

        public FilmePainelFormulario getFilmePainelFormulario() {
            return filmePainelFormulario;
        }

        public FilmePainelTabela getFilmePainelTabela() {
            return filmePainelTabela;
        }

        public void setFilmeController(FilmeController filmeController) {
            this.filmeController = filmeController;
        }


}
