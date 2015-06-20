package view.table;

import controller.FilmeController;

import javax.swing.*;

/**
 * Created by marcus.rodrigues on 20/06/2015.
 */
public class FilmePainelTabela extends JPanel{
    private JTable filmeTable;
    private JButton inserirButton;
    private JButton editarButton;
    private JButton removerButton;
    private JButton vizualizarButton;

    private FilmeController filmeController;


    private void createUIComponents() {
        // TODO: place custom component creation code here
        filmeTable = new JTable(new FilmeTableModel());
    }

    public FilmePainelTabela(FilmeController filmeController) {
        this.filmeController = filmeController;
    }

    private void botaoInserirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoInserirActionPerformed
        filmeController.inserirFilme();
    }//GEN-LAST:event_botaoInserirActionPerformed

    private void botaoEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoEditarActionPerformed
        filmeController.editarFilme();
    }//GEN-LAST:event_botaoEditarActionPerformed

    private void botaoVisualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoVisualizarActionPerformed
        filmeController.visualizarFilme();
    }//GEN-LAST:event_botaoVisualizarActionPerformed

    private void botaoRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoRemoverActionPerformed
        filmeController.removerFilme();
    }//GEN-LAST:event_botaoRemoverActionPerformed


    //Métodos get para todos componentes

    public JButton getBotaoInserir() {
        return botaoInserir;
    }

    public JButton getBotaoEditar() {
        return botaoEditar;
    }

    public JButton getBotaoRemover() {
        return botaoRemover;
    }

    public JButton getBotaoVisualizar() {
        return botaoVisualizar;
    }

    public JTable getTabelaFilmes() {
        return filmeTable;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoEditar;
    private javax.swing.JButton botaoInserir;
    private javax.swing.JButton botaoRemover;
    private javax.swing.JButton botaoVisualizar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelaPacientes;
    // End of variables declaration//GEN-END:variables

    public void zerarCampos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
