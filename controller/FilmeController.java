package controller;

import dao.FilmeDao;
import dao.FilmeDaoBd;
import model.Filme;
import util.DateUtil;
import view.PrintUtil;
import view.janela.JanelaCrud;
import view.form.FilmePainelFormulario;
import view.table.FilmePainelTabela;
import view.table.FilmeTableModel;

import java.text.ParseException;
import java.util.Date;

/**
 * Created by marcus.rodrigues on 18/06/2015.
 */
public class FilmeController {

    private final static int TABELA = 0;
    private final static int FORMCADASTRO = 1;
    private final static int FORMEDICAO = 2;
    private final static int FORMVISUALIZACAO = 3;

    private int telaAtual = 0;
    private int linhaSelecionada = -1;

    private JanelaCrud janela;

    public FilmeController() {
        telaAtual = TABELA;
    }

    public void setJanela(JanelaCrud janela) {
        this.janela = janela;
    }

    public void inserirFilme() {
        FilmePainelFormulario filmePainelFormulario = this.janela.getFilmePainelFormulario();

        filmePainelFormulario.zerarCampos();

        filmePainelFormulario.getLabelPainelFormulario().setText("Cadastrar Paciente");
        filmePainelFormulario.getBotaoSalvar().setVisible(true);
        filmePainelFormulario.getBotaoSalvar().setText("Cadastrar");
        filmePainelFormulario.habilitaEdicaoFormFilme(true);

        telaAtual = FORMCADASTRO;
        this.janela.mostrarPainel(JanelaCrud.PAINELFORM);
    }

    public void editarFilme() {
        FilmePainelTabela filmePainelTabela = this.janela.getFilmePainelTabela();
        FilmePainelFormulario filmePainelFormulario = this.janela.getFilmePainelFormulario();
        FilmeTableModel filmeTableModel = (FilmeTableModel) filmePainelTabela.getTabelaFilmes().getModel();

        linhaSelecionada = filmePainelTabela.getTabelaFilmes().getSelectedRow();
        if(linhaSelecionada < 0)
        {
            PrintUtil.printMessageError(janela, "Não há nenhum elemento selecionado na tabela");
            return;
        }
        Filme filme = filmeTableModel.getFilme(linhaSelecionada);
        String cod = String.valueOf(filme.getCodigo());
        String dataInicio = DateUtil.dateToStringDate(filme.getDataInicio());
        String dataFinal = DateUtil.dateToStringDate(filme.getDataTermino());
        filmePainelFormulario.carregaDados(cod, filme.getTitulo(), filme.getGenero(), filme.getSinopse(), dataInicio, dataFinal);

        filmePainelFormulario.getLabelPainelFormulario().setText("Editar Paciente");
        filmePainelFormulario.getBotaoSalvar().setVisible(true);
        filmePainelFormulario.getBotaoSalvar().setText("Editar");
        filmePainelFormulario.habilitaEdicaoFormFilme(true);

        telaAtual = FORMEDICAO;
        this.janela.mostrarPainel(JanelaCrud.PAINELFORM);
    }

    public void visualizarFilme() {
        FilmePainelTabela filmePainelTabela = this.janela.getFilmePainelTabela();
        FilmePainelFormulario filmePainelFormulario = this.janela.getFilmePainelFormulario();
        FilmeTableModel tableModel = (FilmeTableModel) filmePainelTabela.getTabelaFilmes().getModel();

        linhaSelecionada = filmePainelTabela.getTabelaFilmes().getSelectedRow();
        if(linhaSelecionada < 0)
        {
            PrintUtil.printMessageError(janela, "Não há nenhum elemento selecionado na tabela");
            return;
        }
        Filme filme = tableModel.getFilme(linhaSelecionada);
        String cod = String.valueOf(filme.getCodigo());
        String dataInicio = DateUtil.dateToStringDate(filme.getDataInicio());
        String dataFinal = DateUtil.dateToStringDate(filme.getDataTermino());
        filmePainelFormulario.carregaDados(cod, filme.getTitulo(), filme.getGenero(), filme.getSinopse(), dataInicio, dataFinal);


        filmePainelFormulario.getLabelPainelFormulario().setText("Visualizar Paciente");
        filmePainelFormulario.getBotaoSalvar().setVisible(false);
        filmePainelFormulario.getBotaoSalvar().setText("");
        filmePainelFormulario.habilitaEdicaoFormFilme(false);

        telaAtual = FORMVISUALIZACAO;
        this.janela.mostrarPainel(JanelaCrud.PAINELFORM);
    }

    public void removerFilme() {
        FilmePainelTabela filmePainelTabela = this.janela.getFilmePainelTabela();
        FilmeTableModel filmeTableModel = (FilmeTableModel) filmePainelTabela.getTabelaFilmes().getModel();
        linhaSelecionada = filmePainelTabela.getTabelaFilmes().getSelectedRow();
        if(linhaSelecionada < 0)
        {
            PrintUtil.printMessageError(janela,"Não há nenhum elemento selecionado na tabela");
            return;
        }
        Filme filme = filmeTableModel.getFilme(linhaSelecionada);
        FilmeDao dao = new FilmeDaoBd();
        dao.deletar(filme);
        PrintUtil.printMessageSucesso(janela,"Remoção realizada com sucesso!");

        this.atualizaTabela();
    }

    public void salvarFilme(String codFilme, String titulo, String genero, String sinopse, String dataInicio, String dataFinal) {
        FilmePainelFormulario filmePainelFormulario = this.janela.getFilmePainelFormulario();
        FilmePainelTabela filmePainelTabela= this.janela.getFilmePainelTabela();
        FilmeTableModel filmeTableModell = (FilmeTableModel) filmePainelTabela.getTabelaFilmes().getModel();
        int cod = Integer.parseInt(codFilme);
        Date dataInicial =null
                , dataFim = null;
        try {
            dataInicial = DateUtil.stringToDate(dataInicio);
            dataFim = DateUtil.stringToDate(dataFinal);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(telaAtual==FORMCADASTRO){
            Filme filme = new Filme(cod, titulo, genero, sinopse, dataInicial, dataFim);
            FilmeDao filmeDao = new FilmeDaoBd();
            filmeDao.inserir(filme);
            PrintUtil.printMessageSucesso(janela,"Cadastro realizado com sucesso!");
            filmePainelFormulario.zerarCampos();
        }
        else{
            linhaSelecionada = filmePainelTabela.getTabelaFilmes().getSelectedRow();
            int idFilme = filmeTableModell.getFilme(linhaSelecionada).getCodigo();
            Filme filme = new Filme(cod, titulo, genero, sinopse, dataInicial, dataFim);
            FilmeDao filmeDao = new FilmeDaoBd();
            filmeDao.atualizar(filme, idFilme);
            PrintUtil.printMessageSucesso(janela,"Edição realizada com sucesso!");

        }

    }

    public void voltarPrincipal() {
        telaAtual = TABELA;
        this.atualizaTabela();
        this.janela.mostrarPainel(JanelaCrud.PAINELTABELA);
    }


    public void atualizaTabela() {
        FilmePainelTabela filmePainelTabela = this.janela.getFilmePainelTabela();
        FilmeTableModel filmeTableModel = (FilmeTableModel) filmePainelTabela.getTabelaFilmes().getModel();

        FilmeDao filmeDao = new FilmeDaoBd();
        filmeTableModel.setPacientes(filmeDao.listar());

        filmePainelTabela.getTabelaFilmes().updateUI();
    }
}
