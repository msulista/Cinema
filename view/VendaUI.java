package view;

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

                case VendaMenu.OP_VOLTAR:{
                    System.out.println("Voltar ao menu principal!!!");
                    break;
                }
                default:
                    System.out.println("Digite uma opção válida!!!");
            }
        }while (opcao != VendaMenu.OP_VOLTAR);
    }

    public void cadastrarVendaInteira(){
        System.out.println("Escolha um dos filme abaixo: ");
        new SecaoUI(listaSessoes,listaFilmes, listaSalas).listaSessoesCadastradas();
        int codFilme = Console.lerInt("Digite o código do filme desejado: ");
        String horario = Console.lerString("Digite o horário de filme desejado: ");
        if(DateUtil.verificaHorario(horario)){
            try {
                horaFilme = DateUtil.stringToHour(horario);

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("Horário inválido!!!");
        }
        Secao sessao = listaSessoes.buscaSecaoPorCodFilmeEHorario(codFilme, horaFilme);
        System.out.printf("\n-----------------------------" +
                "\n\tVALOR INGRESSO: R$ %.2f\n", listaVendas.valorIngressoSemDesconto(sessao));
        System.out.println("\n-----------------------------" +
                "\n1 - Confirma venda\n" +
                "0 - Cancela Venda ");
        int confirma = Console.lerInt("Digite a opção desejada: ");
        if (confirma == 1 && sessao.getSala().getQtdAssentos() > 0){

            listaVendas.addVendas(new Venda(sessao, listaVendas.valorIngressoSemDesconto(sessao)));
            sessao.getSala().diminuiAssentos();
            System.out.println("\n---------------------------" +
                    "\nVenda realizada com sucesso!!!" +
                    "\n---------------------------");
        }else {
            System.out.println("\n------------------------------------------" +
                    "Venda Canceladaou ingresso indisponivel!!!" +
                    "\n------------------------------------------");
        }
    }

    public void cadastrarVendaMeiaEntrada(){
        System.out.println("Escolha um dos filme abaixo: ");
        new SecaoUI(listaSessoes,listaFilmes, listaSalas).listaSessoesCadastradas();
        int codFilme = Console.lerInt("Digite o código do filme desejado: ");
        String horario = Console.lerString("Digite o horário de filme desejado: ");
        if(DateUtil.verificaHorario(horario)){
            try {
                horaFilme = DateUtil.stringToHour(horario);

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("Horário inválido!!!");
        }
        Secao sessao = listaSessoes.buscaSecaoPorCodFilmeEHorario(codFilme, horaFilme);
        System.out.printf("\n-----------------------------" +
                "\n\tVALOR INGRESSO: R$%.2f", listaVendas.ValorIngressoComDesconto(sessao));
        System.out.println("\n----------------------------" +
                "\n1 - Confirma venda\n" +
                "0 - Cancela Venda ");
        int confirma = Console.lerInt("Digite a opção desejada: ");
        if (confirma == 1 && sessao.getSala().getQtdAssentos() > 0){

            listaVendas.addVendas(new Venda(sessao, listaVendas.ValorIngressoComDesconto(sessao)));
            sessao.getSala().diminuiAssentos();
            System.out.println("\n---------------------------" +
                                 "\nVenda realizada com sucesso!!!" +
                                 "\n---------------------------");
        }else {
            System.out.println("\n------------------------------------------" +
                                 "Venda Canceladaou ingresso indisponivel!!!" +
                                "\n------------------------------------------");
        }
    }

    public void listaVendasCadastradas(){
        System.out.println("===============================================\n");
        System.out.println(String.format("%-10s", "COD FILME") + "\t" +
                String.format("%-20s", "TITULO") + "\t" +
                String.format("%-20s", "SALA") + "\t" +
                String.format("%-20s", "HORÁRIO") + "\t" +
                String.format("%-20s", "VALOR"));
        for (Venda venda : listaVendas.getVenda()){
            System.out.println(String.format("%-10s", venda.getSecao().getFilme().getCodigo()) + "\t" +
                    String.format("%-20s", venda.getSecao().getFilme().getTitulo()) + "\t" +
                    String.format("%-20s", venda.getSecao().getSala().getNumero()) + "\t" +
                    String.format("%-20s", DateUtil.hourToStringHour(venda.getSecao().getHorario())) + "\t" +
                    String.format("%-20s", venda.getValor()));
        }
    }
}
