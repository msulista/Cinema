package view;

import dao.SessaoDao;
import dao.SessaoDaoBd;
import dao.VendaDao;
import dao.VendaDaoBd;
import model.Secao;
import model.Venda;
import repositorio.RepositorioFilme;
import repositorio.RepositorioSala;
import repositorio.RepositorioSecao;
import repositorio.RepositorioVenda;
import util.Console;
import util.DateUtil;
import view.menu.VendaMenu;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * Created by marcus.rodrigues on 12/04/2015.
 */
public class VendaUI {

    private RepositorioVenda listaVendas;
    private RepositorioSecao listaSessoes;
    private RepositorioFilme listaFilmes;
    private RepositorioSala listaSalas;
    private  Date horaFilme;

    public VendaUI(RepositorioVenda listaVendas, RepositorioSecao listaSessoes, RepositorioFilme listaFilmes, RepositorioSala listaSalas) {
        this.listaVendas = listaVendas;
        this.listaSessoes = listaSessoes;
        this.listaFilmes = listaFilmes;
        this.listaSalas = listaSalas;
        this.horaFilme = horaFilme;
    }

    /**
     * Método executar contem um switch com as opções de ação do usuario.
     */
    public void executar(){
        int opcao = 0;
        do {
            System.out.println(VendaMenu.getOpcoes());
            opcao = Console.lerInt("Digite opção desejada: ");
            switch (opcao){
                case VendaMenu.OP_VENDER: {
                    cadastrarVendaInteira();
                    break;
                }
                case VendaMenu.OP_VENDER_MEIA_ENTRADA:{
                    cadastrarVendaMeiaEntrada();
                    break;
                }
                case VendaMenu.OP_LISTAR:{
                    listaVendasCadastradas();
                    break;
                }
                case VendaMenu.OP_RELATORIO_FILME:{
                    relatorioDeVendaPorFilme();
                    break;
                }
                case VendaMenu.OP_VOLTAR:{
                    System.out.println("Voltar ao menu principal!!!");
                    break;
                }
                default:
                    System.out.println("Digite uma opção válida!!!");
            }
        }while (opcao != VendaMenu.OP_VOLTAR);
    }

    /**
     * Método cadastrarVendaInteira responsavel por realizar a interação com o usuario e receber os dados do teclado
     * por fim cria uma venda e a adiciona a lista de vendas e diminui o numero de cadeiras disponiveis.
     */
    public void cadastrarVendaInteira(){
        VendaDao vendaDao = new VendaDaoBd();
        System.out.println("Escolha uma das Sessões abaixo: ");
        SessaoDao sessaoDao = new SessaoDaoBd();
        SecaoUI secaoUI = new SecaoUI();
        secaoUI.listaSessoesCadastradasDisponiveisParaVenda();

        int id_sessao = Console.lerInt("Digite o ID da sessão desejada: ");
        Secao sessao = sessaoDao.buscaPorID(id_sessao);

        System.out.printf("\n-----------------------------" +
                "\n\tVALOR INGRESSO: R$ %.2f\n", listaVendas.valorIngressoSemDesconto(sessao));
        System.out.println("\n-----------------------------" +
                "\n1 - Confirma venda\n" +
                  "0 - Cancela Venda ");
        int confirma = Console.lerInt("Digite a opção desejada: ");

        if (confirma == 1){

            Venda venda = new Venda(sessao, listaVendas.valorIngressoSemDesconto(sessao));
           // sessao.getSala().diminuiAssentos();

            String horaSistema = DateUtil.pegaHoraDoSistema();
            // System.out.println("Hora agora: "+ horaSistema);

            Date dataSistema = DateUtil.stringToDatePostgre(DateUtil.pegaDataDoSistema());


            vendaDao.inserir(venda, dataSistema, horaSistema, id_sessao);
            System.out.println("\n---------------------------" +
                    "\nVenda realizada com sucesso!!!" +
                    "\n---------------------------");
        }else {
            System.out.println("\n------------------------------------------" +
                    "Venda Cancelada ou ingresso indisponivel!!!" +
                    "\n------------------------------------------");
        }
    }

    /**
     * Método cadastrarVendaMeiaEntrada responsavel por realizar a interação com o usuario e receber os dados do teclado
     * por fim cria uma venda e a adiciona a lista de vendas e diminui o numero de cadeiras disoniveis.
     */
    public void cadastrarVendaMeiaEntrada(){
        VendaDao vendaDao = new VendaDaoBd();
        System.out.println("Escolha uma das Sessões abaixo: ");
        SessaoDao sessaoDao = new SessaoDaoBd();
        SecaoUI secaoUI = new SecaoUI();
        secaoUI.listaSessoesCadastradasDisponiveisParaVenda();

        int id_sessao = Console.lerInt("Digite o ID da sessão desejada: ");
        Secao sessao = sessaoDao.buscaPorID(id_sessao);

        System.out.printf("\n-----------------------------" +
                "\n\tVALOR INGRESSO: R$ %.2f\n", listaVendas.ValorIngressoComDesconto(sessao));
        System.out.println("\n-----------------------------" +
                "\n1 - Confirma venda\n" +
                "0 - Cancela Venda ");
        int confirma = Console.lerInt("Digite a opção desejada: ");

        if (confirma == 1){

            Venda venda = new Venda(sessao, listaVendas.ValorIngressoComDesconto(sessao));
            // sessao.getSala().diminuiAssentos();

            String horaSistema = DateUtil.pegaHoraDoSistema();
            // System.out.println("Hora agora: "+ horaSistema);

            Date dataSistema = DateUtil.stringToDatePostgre(DateUtil.pegaDataDoSistema());


            vendaDao.inserir(venda, dataSistema, horaSistema, id_sessao);
            System.out.println("\n---------------------------" +
                    "\nVenda realizada com sucesso!!!" +
                    "\n---------------------------");
        }else {
            System.out.println("\n------------------------------------------" +
                    "\nVenda Cancelada ou ingresso indisponivel!!!" +
                    "\n------------------------------------------");
        }
    }

    /**
     * Lista todas as vendas já cadastradas
     */
    public void listaVendasCadastradas(){
        VendaDao vendaDao = new VendaDaoBd();
        System.out.println("===============================================\n");
        System.out.println(String.format("%-10s", "ID VENDA") + "\t" +
                String.format("%-10s", "COD FILME") + "\t" +
                String.format("%-20s", "TITULO") + "\t" +
                String.format("%-20s", "SALA") + "\t" +
                String.format("%-20s", "HORÁRIO") + "\t" +
                String.format("%-20s", "VALOR"));
        for (Venda venda : vendaDao.listar()){
            System.out.println(String.format("%-10s", venda.getId()) + "\t" +
                    String.format("%-10s", venda.getSecao().getFilme().getCodigo()) + "\t" +
                    String.format("%-20s", venda.getSecao().getFilme().getTitulo()) + "\t" +
                    String.format("%-20s", venda.getSecao().getSala().getNumero()) + "\t" +
                    String.format("%-20s", DateUtil.hourToStringHour(venda.getSecao().getHorario())) + "\t" +
                    String.format("%-20s", venda.getValor()));
        }
    }

    public void relatorioDeVendaPorFilme(){
        VendaDao vendaDao = new VendaDaoBd();
        FilmeUI filmeUI = new FilmeUI();
        double arrecadacaoTotal = 0;

        filmeUI.listaFilmesCadastrados();
        int cod_Filme = Console.lerInt("Digite Codigo do Filme: ");

        System.out.println("================RELATÓRIO======================\n");
        System.out.println(String.format("%-10s", "ID VENDA") + "\t" +
                String.format("%-10s", "COD FILME") + "\t" +
                String.format("%-20s", "TITULO") + "\t" +
                String.format("%-20s", "SALA") + "\t" +
                String.format("%-20s", "HORÁRIO") + "\t" +
                String.format("%-20s", "VALOR"));
        //List<Venda> vendaList = vendaDao.buscaVendasPorCodFilme(cod_Filme);
        for (Venda venda : vendaDao.buscaVendasPorCodFilme(cod_Filme)){
            System.out.println(String.format("%-10s", venda.getId()) + "\t" +
                    String.format("%-10s", venda.getSecao().getFilme().getCodigo()) + "\t" +
                    String.format("%-20s", venda.getSecao().getFilme().getTitulo()) + "\t" +
                    String.format("%-20s", venda.getSecao().getSala().getNumero()) + "\t" +
                    String.format("%-20s", DateUtil.hourToStringHour(venda.getSecao().getHorario())) + "\t" +
                    String.format("%-20s", venda.getValor()));
            arrecadacaoTotal = arrecadacaoTotal + venda.getValor();
        }
        System.out.println("==============================================\n");
        System.out.println(String.format("%-10s", "VALOR TOTAL ARECADADO: R$") + arrecadacaoTotal);

    }
}
