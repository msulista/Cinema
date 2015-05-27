package view;

import dao.*;
import model.Filme;
import model.Sala;
import model.Secao;
import repositorio.RepositorioFilme;
import repositorio.RepositorioSala;
import repositorio.RepositorioSecao;
import util.Console;
import util.DateUtil;
import view.menu.SecaoMenu;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by marcus.rodrigues on 30/03/2015.
 */
public class SecaoUI {
    private RepositorioSecao listaSecoes;
    private RepositorioFilme listaFilmes;
    private RepositorioSala listaSalas;
    private Date horario;
    private String horaSql;

    public SecaoUI(RepositorioSecao listaSecoes, RepositorioFilme listaFilmes, RepositorioSala listaSalas) {

        this.listaSecoes = listaSecoes;
        this.listaFilmes = listaFilmes;
        this.listaSalas = listaSalas;
        this.horario = horario;
        this.horaSql = horaSql;
    }

    public SecaoUI() {
        this.listaSecoes = listaSecoes;
        this.listaFilmes = listaFilmes;
        this.listaSalas = listaSalas;
        this.horario = horario;
        this.horaSql = horaSql;
    }

    /**
     * Método executar contem um switch com as opções de ação do usuario.
     */
    public void executar(){
        int opcao = 0;
        do {
            System.out.println(SecaoMenu.getOpcoes());
            opcao = Console.lerInt("Digite opção desejada: ");
            switch (opcao){
                case SecaoMenu.OP_CADASTRAR:{
                    cadastrarSecao();
                    break;
                }
                case SecaoMenu.OP_ATUALIZAR: {
                    atualizar();
                    break;
                }
                case SecaoMenu.OP_DELETAR: {
                    deletaSessaoPorID();
                    break;
                }
                case SecaoMenu.OP_LISTAR: {
                    listaSessoesCadastradas();
                    break;
                }
                case SecaoMenu.OP_BUSCA_FILME: {
                    buscaSessaoPorCodFilme();
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

    /**
     * Método cadastrarSecao responsavel por realizar a interação com o usuario e receber os dados do teclado
     * por fim cria uma sessao e a adiciona a lista de sessoes.
     */
    public void cadastrarSecao(){
        SessaoDao dao = new SessaoDaoBd();
        FilmeDao daoFilme = new FilmeDaoBd();
        SalaDao daoSala = new SalaDaoBd();

        System.out.println("Escolha um dos filme abaixo: ");
        new FilmeUI(listaFilmes).listaFilmesCadastrados();

        int codFilme = Console.lerInt("Digite o código do filme desejado: ");
        Filme filme = listaFilmes.buscaFilmePorCodigo(codFilme);

        System.out.println("Escolha uma das salas abaixo: ");
        new SalaUI(listaSalas).listaSalasCadastradas();
        String numSala = Console.lerString("Digite o numero da sala desejada: ");
        Sala sala = listaSalas.buscaSalaPorNumero(numSala);

        String horarioSessao = Console.lerString("Digite o horário da sessão: ");
        if(DateUtil.verificaHorario(horarioSessao)){
            try {
                horario = DateUtil.stringToHour(horarioSessao);
                horaSql = horarioSessao;

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("Horário inválido!!!");
        }
        double valor = Console.lerDouble("Digite valor da sessão: ");
        Secao secao = new Secao(sala, filme, horario, valor);
        secao.setContadorDeCadeirasDisponiveis(daoSala.retornaQtdAssentos(numSala));
        System.out.println("Classe SessaoUI - hora: " + horario);
        listaSecoes.adicionaSecao(secao);
        dao.inserir(secao, horarioSessao, numSala, codFilme);
        System.out.println("Sessao das " + horarioSessao + " dadastrada com sucesso!!!" );
    }

    public void atualizar(){

        SessaoDao sessaoDao = new SessaoDaoBd();
        FilmeDao filmeDao = new FilmeDaoBd();

        listaSessoesCadastradas();
        int idSessao = Console.lerInt("Informe ID sessão: ");
        Secao secao = sessaoDao.buscaPorID(idSessao);
        System.out.println(String.format("%-10s", "COD FILME") + "\t" +
                String.format("%-20s", "TITULO") + "\t" +
                String.format("%-20s", "SALA") + "\t" +
                String.format("%-20s", "HORÁRIO") + "\t" +
                String.format("%-20s", "DATA INICIO") + "\t" +
                String.format("%-20s", "DATA TERMINO") + "\t" +
                String.format("%-20s", "VALOR") + "\t" +
                String.format("%-20s", "QTD DISPONIVEL"));
            System.out.println(String.format("%-10s", secao.getFilme().getCodigo()) + "\t" +
                    String.format("%-20s", secao.getFilme().getTitulo()) + "\t" +
                    String.format("%-20s", secao.getSala().getNumero()) + "\t" +
                    String.format("%-20s", DateUtil.hourToStringHour(secao.getHorario())) + "\t" +
                    String.format("%-20s", DateUtil.dateToStringDate(secao.getFilme().getDataInicio())) + "\t" +
                    String.format("%-20s", DateUtil.dateToStringDate(secao.getFilme().getDataTermino())) + "\t" +
                    String.format("%-20s", secao.getValor()) + "\t" +
                    String.format("%-20s", secao.getContadorDeCadeirasDisponiveis()));
        System.out.println("\n Informe as alterações:");
        System.out.println("Escolha um dos filme abaixo: ");
        new FilmeUI(listaFilmes).listaFilmesCadastrados();
        int codFilme = Console.lerInt("Digite o código do filme desejado: ");
        Filme filme = filmeDao.buscarPorCodigo(codFilme);

        System.out.println("Escolha uma das salas abaixo: ");
        new SalaUI(listaSalas).listaSalasCadastradas();
        String numSala = Console.lerString("Digite o numero da sala desejada: ");
        Sala sala = listaSalas.buscaSalaPorNumero(numSala);

        String horarioSessao = Console.lerString("Digite o horário da sessão: ");
        if(DateUtil.verificaHorario(horarioSessao)){
            try {
                horario = DateUtil.stringToHour(horarioSessao);
                horaSql = horarioSessao;

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("Horário inválido!!!");
        }
        double valorSessao = Console.lerDouble("Valor Sessão: ");

        secao = new Secao(sala, filme, horario, valorSessao);
        sessaoDao.atualizar(secao, horarioSessao, numSala, codFilme, idSessao); //insere no bd
        System.out.println("Sessão id " + idSessao + " foi atualizada com sucesso!!!");


    }

    public void deletaSessaoPorID(){
        SessaoDao sessaoDao = new SessaoDaoBd();

        listaSessoesCadastradas();
        int idSessao = Console.lerInt("Informe ID sessão para deletar: ");
        Secao secao = sessaoDao.buscaPorID(idSessao);
        System.out.println(String.format("%-10s", "COD FILME") + "\t" +
                String.format("%-20s", "TITULO") + "\t" +
                String.format("%-20s", "SALA") + "\t" +
                String.format("%-20s", "HORÁRIO") + "\t" +
                String.format("%-20s", "DATA INICIO") + "\t" +
                String.format("%-20s", "DATA TERMINO") + "\t" +
                String.format("%-20s", "VALOR") + "\t" +
                String.format("%-20s", "QTD DISPONIVEL"));
            System.out.println(String.format("%-10s", secao.getFilme().getCodigo()) + "\t" +
                    String.format("%-20s", secao.getFilme().getTitulo()) + "\t" +
                    String.format("%-20s", secao.getSala().getNumero()) + "\t" +
                    String.format("%-20s", DateUtil.hourToStringHour(secao.getHorario())) + "\t" +
                    String.format("%-20s", DateUtil.dateToStringDate(secao.getFilme().getDataInicio())) + "\t" +
                    String.format("%-20s", DateUtil.dateToStringDate(secao.getFilme().getDataTermino())) + "\t" +
                    String.format("%-20s", secao.getValor()) + "\t" +
                    String.format("%-20s", secao.getContadorDeCadeirasDisponiveis()));

        int confirma = Console.lerInt("\n Deseja realmente deletar este filme? 1- Sim 2 - Não ");
        if (confirma == 1){
            sessaoDao.deletar(idSessao);
            System.out.println("SEssão deletada com sucesso!!!");
        }else
            System.out.println("Sessão não sera deletada!!!");
    }

    /**
     * Método listaSessoesCadastradas lista todas as sessoes já cadastradas
     */
    public void listaSessoesCadastradas(){
        SessaoDao dao = new SessaoDaoBd();
        System.out.println("===============================================\n");
        System.out.println(String.format("%-10s", "ID SESSÃO") + "\t" +
                String.format("%-20s", "TITULO") + "\t" +
                String.format("%-20s", "SALA") + "\t" +
                String.format("%-20s", "HORÁRIO") + "\t" +
                String.format("%-20s", "DATA INICIO") + "\t" +
                String.format("%-20s", "DATA TERMINO") + "\t" +
                String.format("%-20s", "VALOR") + "\t" +
                String.format("%-20s", "QTD DISPONIVEL"));
        for (Secao secao : dao.listar()){
            System.out.println(String.format("%-10s", secao.getId()) + "\t" +
                    String.format("%-20s", secao.getFilme().getTitulo()) + "\t" +
                    String.format("%-20s", secao.getSala().getNumero()) + "\t" +
                    String.format("%-20s", DateUtil.hourToStringHour(secao.getHorario())) + "\t" +
                    String.format("%-20s", DateUtil.dateToStringDate(secao.getFilme().getDataInicio())) + "\t" +
                    String.format("%-20s", DateUtil.dateToStringDate(secao.getFilme().getDataTermino())) + "\t" +
                    String.format("%-20s", secao.getValor()) + "\t" +
                    String.format("%-20s", secao.getContadorDeCadeirasDisponiveis()));
        }
    }

    public void listaSessoesCadastradasDisponiveisParaVenda(){
        SessaoDao dao = new SessaoDaoBd();
        System.out.println("===============================================\n");
        System.out.println(String.format("%-10s", "ID SESSÃO") + "\t" +
                String.format("%-20s", "TITULO") + "\t" +
                String.format("%-20s", "SALA") + "\t" +
                String.format("%-20s", "HORÁRIO") + "\t" +
                String.format("%-20s", "DATA INICIO") + "\t" +
                String.format("%-20s", "DATA TERMINO") + "\t" +
                String.format("%-20s", "VALOR") + "\t" +
                String.format("%-20s", "QTD DISPONIVEL"));
        for (Secao secao : dao.listarSessoesDisponiveisParaVenda()){
            System.out.println(String.format("%-10s", secao.getId()) + "\t" +
                    String.format("%-20s", secao.getFilme().getTitulo()) + "\t" +
                    String.format("%-20s", secao.getSala().getNumero()) + "\t" +
                    String.format("%-20s", DateUtil.hourToStringHour(secao.getHorario())) + "\t" +
                    String.format("%-20s", DateUtil.dateToStringDate(secao.getFilme().getDataInicio())) + "\t" +
                    String.format("%-20s", DateUtil.dateToStringDate(secao.getFilme().getDataTermino())) + "\t" +
                    String.format("%-20s", secao.getValor()) + "\t" +
                    String.format("%-20s", secao.getContadorDeCadeirasDisponiveis()));
        }
    }

    public void buscaSessaoPorCodFilme(){
        SessaoDao sessaoDao = new SessaoDaoBd();
        FilmeDao filmeDao = new FilmeDaoBd();
        List<Secao> secaoList = new ArrayList<Secao>();

        FilmeUI filmeUI = new FilmeUI(listaFilmes);
        filmeUI.listaFilmesCadastrados();

        int codFilme = Console.lerInt("Informe código do filme: ");
        secaoList = sessaoDao.buscaSessaoPorCodFilme(codFilme);
        System.out.println(String.format("%-10s", "COD FILME") + "\t" +
                String.format("%-20s", "TITULO") + "\t" +
                String.format("%-20s", "SALA") + "\t" +
                String.format("%-20s", "HORÁRIO") + "\t" +
                String.format("%-20s", "DATA INICIO") + "\t" +
                String.format("%-20s", "DATA TERMINO") + "\t" +
                String.format("%-20s", "VALOR") + "\t" +
                String.format("%-20s", "QTD DISPONIVEL"));
        for (Secao secao : secaoList){
            System.out.println(String.format("%-10s", secao.getFilme().getCodigo()) + "\t" +
                    String.format("%-20s", secao.getFilme().getTitulo()) + "\t" +
                    String.format("%-20s", secao.getSala().getNumero()) + "\t" +
                    String.format("%-20s", DateUtil.hourToStringHour(secao.getHorario())) + "\t" +
                    String.format("%-20s", DateUtil.dateToStringDate(secao.getFilme().getDataInicio())) + "\t" +
                    String.format("%-20s", DateUtil.dateToStringDate(secao.getFilme().getDataTermino())) + "\t" +
                    String.format("%-20s", secao.getValor()) + "\t" +
                    String.format("%-20s", secao.getContadorDeCadeirasDisponiveis()));
        }
    }
}
