package view;

import model.Filme;
import repositorio.RepositorioFilme;
import util.Console;
import util.DateUtil;
import view.menu.FilmeMenu;

import java.text.ParseException;
import java.util.Date;


/**
 * Created by marcus.rodrigues on 30/03/2015.
 */
public class FilmeUI {

    private RepositorioFilme listaFilmes;
    private Date dataInicio;
    private Date dataFinal;

    public FilmeUI(RepositorioFilme listaFilmes) {
        this.listaFilmes = listaFilmes;
        dataInicio = new Date();
        dataFinal = new Date();
    }

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

    public void cadastrarFilme(){
        int codFilme = Console.lerInt("Código: ");
        if (listaFilmes.verificaSeJaExisteFilmePorCodigo(codFilme)){
            System.out.println("Filme com código " + codFilme + " já esta cadastrado!!!");
        }else{
            String titulo = Console.lerString("Titulo: ");
            String genero = Console.lerString("Genero: ");
            String sinopse = Console.lerString("Sinopse: ");
            String dataInicioString = Console.lerString("Data de inicio: ");
            if(DateUtil.verificaData(dataInicioString)){
                try {
                    dataInicio = DateUtil.stringToDate(dataInicioString);

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }else {
                System.out.println("Data inválida!!!");
            }
            String dataFinalString = Console.lerString("Data de termino: ");
            if(DateUtil.verificaData(dataFinalString)){
                try {
                    dataFinal = DateUtil.stringToDate(dataFinalString);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }else {
                System.out.println("Data inválida!!!");
            }
            listaFilmes.addFilmes(new Filme(codFilme, titulo, genero, sinopse, dataInicio, dataFinal));
            System.out.println("Filme " + titulo + "Foi cadastrado com sucesso!!!");
        }
    }

    public void listaFilmesCadastrados(){
        System.out.println("===============================================\n");
        System.out.println(String.format("%-10s", "COD FILME") + "\t" +
                String.format("%-20s", "TITULO") + "\t" +
                String.format("%-20s", "GENERO") + "\t" +
                String.format("%-20s", "DATA INICIO") + "\t" +
                String.format("%-20s", "DATA TERMINO") + "\t" +
                String.format("%-20s", "SINOPSE"));
        for (Filme filme : listaFilmes.getFilmes()){
            System.out.println(String.format("%-10s", filme.getCodigo()) + "\t" +
                    String.format("%-20s", filme.getTitulo()) + "\t" +
                    String.format("%-20s", filme.getGenero()) + "\t" +
                    String.format("%-20s", filme.getDataInicio()) + "\t" +
                    String.format("%-20s", filme.getDataTermino()) + "\t" +
                    String.format("%-20s", filme.getSinopse()));
        }
    }
}
