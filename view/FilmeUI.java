package view;

import dao.FilmeDao;
import dao.FilmeDaoBd;
import model.Filme;
import repositorio.RepositorioFilme;
import util.Console;
import util.DateUtil;
import view.menu.FilmeMenu;

import java.text.ParseException;
import java.util.Date;
import java.util.List;


/**
 * Classe FilmeUI responsavel por chamar os menus e interagir com o usuario
 *
 * Created by marcus.rodrigues on 30/03/2015.
 */
public class FilmeUI {

    private RepositorioFilme listaFilmes;
    private Date dataInicio;
    private Date dataFinal;

    public FilmeUI(RepositorioFilme listaFilmes) {
        this.listaFilmes = listaFilmes;
    }

    /**
     * Método executar contem um switch com as opções de ação do usuario.
     */
    public void executar(){
        int opcao = 0;
        do {
            System.out.println(FilmeMenu.getOpcoes());
            opcao = Console.lerInt("Digite opção desejada: ");
            switch (opcao){
                case FilmeMenu.OP_CADASTRAR: {
                    cadastrarFilme();
                    break;
                }
                case FilmeMenu.OP_ATUALIZAR:{
                    atualizar();
                    break;
                }
                case FilmeMenu.OP_DELETAR:{
                    deletaFilmePorCodigo();
                    break;
                }
                case FilmeMenu.OP_BUSCA_GENERO:{
                    buscaFilmePorGenero();
                    break;
                }
                case FilmeMenu.OP_BUSCA_COD:{
                    buscaFilmePorCodigo();
                    break;
                }
                case FilmeMenu.OP_LISTAR:{
                    listaFilmesCadastrados();
                    break;
                }
                case FilmeMenu.OP_VOLTAR:{
                    System.out.println("Voltar ao menu principal!!!");
                    break;
                }
                default:
                    System.out.println("Digite uma opção válida!!!");
            }
        }while (opcao != FilmeMenu.OP_VOLTAR);
    }

    /**
     * Método cadastrar filmes responsavel por realizar a nteração com o usuario e receber os dados do teclado
     * por fim cria um fime e o adiciona a lista de filmes.
     */
    public void cadastrarFilme(){
        FilmeDao dao = new FilmeDaoBd();
        int codFilme = Console.lerInt("Código: ");
        if (listaFilmes.verificaSeJaExisteFilmePorCodigo(codFilme)){
            System.out.println("Filme com código " + codFilme + " já esta cadastrado!!!");
        }else{
            String titulo = Console.lerString("Titulo: ");
            String genero = Console.lerString("Genero: ");
            String sinopse = Console.lerString("Sinopse: ");
            String dataInicioString = Console.lerString("Data de inicio: ");
            if(DateUtil.verificaData(dataInicioString)){
                    dataInicio = DateUtil.stringToDatePostgre(dataInicioString);
                   // System.out.println("==>"  + dataInicio);

            }else {
                System.out.println("Data inválida!!!");
            }
            String dataFinalString = Console.lerString("Data de termino: ");
            if(DateUtil.verificaData(dataFinalString)){

                    dataFinal = DateUtil.stringToDatePostgre(dataFinalString);

            }else {
                System.out.println("Data inválida!!!");
            }
            Filme filme = new Filme(codFilme, titulo, genero, sinopse, dataInicio, dataFinal);
            listaFilmes.addFilmes(filme);// insere no repositorio
            dao.inserir(filme); //insere no bd
            System.out.println("Filme " + titulo + " foi cadastrado com sucesso!!!");
        }
    }

    public void atualizar(){
        listaFilmesCadastrados();
        FilmeDao dao = new FilmeDaoBd();

        int codFilme = Console.lerInt("Informe código do filme: ");
        Filme filme = dao.buscarPorCodigo(codFilme);
        System.out.println(String.format("%-10s", "COD FILME") + "\t" +
                String.format("%-20s", "TITULO") + "\t" +
                String.format("%-20s", "GENERO") + "\t" +
                String.format("%-20s", "DATA INICIO") + "\t" +
                String.format("%-20s", "DATA TERMINO") + "\t" +
                String.format("%-20s", "SINOPSE"));
        System.out.println(String.format("%-10s", filme.getCodigo()) + "\t" +
                String.format("%-20s", filme.getTitulo()) + "\t" +
                String.format("%-20s", filme.getGenero()) + "\t" +
                String.format("%-20s", DateUtil.dateToStringDate(filme.getDataInicio())) + "\t" +
                String.format("%-20s", DateUtil.dateToStringDate(filme.getDataTermino())) + "\t" +
                String.format("%-20s", filme.getSinopse()));
        System.out.println("\n Informe as alterações:");
            String titulo = Console.lerString("Titulo: ");
            String genero = Console.lerString("Genero: ");
            String sinopse = Console.lerString("Sinopse: ");
            String dataInicioString = Console.lerString("Data de inicio: ");
            if(DateUtil.verificaData(dataInicioString)){
                dataInicio = DateUtil.stringToDatePostgre(dataInicioString);
                // System.out.println("==>"  + dataInicio);

            }else {
                System.out.println("Data inválida!!!");
            }
            String dataFinalString = Console.lerString("Data de termino: ");
            if(DateUtil.verificaData(dataFinalString)){

                dataFinal = DateUtil.stringToDatePostgre(dataFinalString);

            }else {
                System.out.println("Data inválida!!!");
            }
            filme = new Filme(codFilme, titulo, genero, sinopse, dataInicio, dataFinal);
            dao.atualizar(filme, codFilme); //insere no bd
            System.out.println("Filme " + titulo + " foi atualizado com sucesso!!!");


    }

    public void buscaFilmePorGenero(){
        FilmeDao dao = new FilmeDaoBd();

        String buscaGenero = Console.lerString("Informe Genero da busca: ");
        List<Filme> listaBusca = dao.buscaPorGenero(buscaGenero);
        System.out.println("===============================================\n");
        System.out.println(String.format("%-10s", "COD FILME") + "\t" +
                String.format("%-20s", "TITULO") + "\t" +
                String.format("%-20s", "GENERO") + "\t" +
                String.format("%-20s", "DATA INICIO") + "\t" +
                String.format("%-20s", "DATA TERMINO") + "\t" +
                String.format("%-20s", "SINOPSE"));
        for (Filme filme : listaBusca){
            System.out.println(String.format("%-10s", filme.getCodigo()) + "\t" +
                    String.format("%-20s", filme.getTitulo()) + "\t" +
                    String.format("%-20s", filme.getGenero()) + "\t" +
                    String.format("%-20s", DateUtil.dateToStringDate(filme.getDataInicio())) + "\t" +
                    String.format("%-20s", DateUtil.dateToStringDate(filme.getDataTermino())) + "\t" +
                    String.format("%-20s", filme.getSinopse()));
        }
    }

    public void buscaFilmePorCodigo(){
        FilmeDao dao = new FilmeDaoBd();

        int buscaCodigo = Console.lerInt("Informe Código da busca: ");
        Filme filme = dao.buscarPorCodigo(buscaCodigo);
        System.out.println("===============================================\n");
        System.out.println(String.format("%-10s", "COD FILME") + "\t" +
                String.format("%-20s", "TITULO") + "\t" +
                String.format("%-20s", "GENERO") + "\t" +
                String.format("%-20s", "DATA INICIO") + "\t" +
                String.format("%-20s", "DATA TERMINO") + "\t" +
                String.format("%-20s", "SINOPSE"));
            System.out.println(String.format("%-10s", filme.getCodigo()) + "\t" +
                    String.format("%-20s", filme.getTitulo()) + "\t" +
                    String.format("%-20s", filme.getGenero()) + "\t" +
                    String.format("%-20s", DateUtil.dateToStringDate(filme.getDataInicio())) + "\t" +
                    String.format("%-20s", DateUtil.dateToStringDate(filme.getDataTermino())) + "\t" +
                    String.format("%-20s", filme.getSinopse()));
    }

    public void deletaFilmePorCodigo(){
        FilmeDao dao = new FilmeDaoBd();

        int buscaCodigo = Console.lerInt("Informe Código para deletar: ");
        Filme filme = dao.buscarPorCodigo(buscaCodigo);
        System.out.println(String.format("%-10s", "COD FILME") + "\t" +
                String.format("%-20s", "TITULO") + "\t" +
                String.format("%-20s", "GENERO") + "\t" +
                String.format("%-20s", "DATA INICIO") + "\t" +
                String.format("%-20s", "DATA TERMINO") + "\t" +
                String.format("%-20s", "SINOPSE"));
        System.out.println(String.format("%-10s", filme.getCodigo()) + "\t" +
                String.format("%-20s", filme.getTitulo()) + "\t" +
                String.format("%-20s", filme.getGenero()) + "\t" +
                String.format("%-20s", DateUtil.dateToStringDate(filme.getDataInicio())) + "\t" +
                String.format("%-20s", DateUtil.dateToStringDate(filme.getDataTermino())) + "\t" +
                String.format("%-20s", filme.getSinopse()));
        int confirma = Console.lerInt("\n Deseja realmente deletar este filme? 1- Sim 2 - Não ");
        if (confirma == 1){
            dao.deletar(filme);
            System.out.println("Filme deletado com sucesso!!!");
        }else
            System.out.println("Filme não sera deletado!!!");

    }

    /**
     * Método listaFilmesCadastrados responsavel por listar todos os filmes cadastrados.
     */
    public void listaFilmesCadastrados(){
        FilmeDao dao = new FilmeDaoBd();
        System.out.println("===============================================\n");
        System.out.println(String.format("%-10s", "COD FILME") + "\t" +
                String.format("%-20s", "TITULO") + "\t" +
                String.format("%-20s", "GENERO") + "\t" +
                String.format("%-20s", "DATA INICIO") + "\t" +
                String.format("%-20s", "DATA TERMINO") + "\t" +
                String.format("%-20s", "SINOPSE"));
        for (Filme filme : dao.listar()){
            System.out.println(String.format("%-10s", filme.getCodigo()) + "\t" +
                    String.format("%-20s", filme.getTitulo()) + "\t" +
                    String.format("%-20s", filme.getGenero()) + "\t" +
                    String.format("%-20s", DateUtil.dateToStringDate(filme.getDataInicio())) + "\t" +
                    String.format("%-20s", DateUtil.dateToStringDate(filme.getDataTermino())) + "\t" +
                    String.format("%-20s", filme.getSinopse()));
        }
    }
}
