package dao;

import banco.ConnectionFactory;
import model.Secao;
import model.Venda;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by marcus.rodrigues on 24/05/2015.
 */
public class VendaDaoBd implements VendaDao {

    private Connection conexao;
    private PreparedStatement comando;

    @Override
    public void inserir(Venda venda, Date data, String hora, int id_sessao) {
        SessaoDao sessaoDao = new SessaoDaoBd();
        sessaoDao.diminuiAssentosDisponiveis(sessaoDao.buscaPorID(id_sessao));

        String sql = "INSERT INTO Venda (valor_venda, data_venda, hora_venda, id_sessao) VALUES(?,?,?,?)";
        try {
            conexao = ConnectionFactory.getConnection();
            comando = conexao.prepareStatement(sql);
            comando.setDouble(1, venda.getValor());
            comando.setDate(2, (java.sql.Date) data);
            comando.setString(3, hora);
            comando.setInt(4, id_sessao);
            comando.executeUpdate();
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletar(Venda venda) {

    }

    @Override
    public void atualizar(Venda venda, int id_venda) {

    }

    @Override
    public List<Venda> buscaVendasPorCodFilme(int cod_filme) {
        List<Venda> vendaList = new ArrayList<Venda>();

        String sql = "SELECT Venda.id_venda FROM Venda, Sessao, Filme\n" +
                "WHERE\n" +
                "\tVenda.id_sessao = Sessao.id_sessao AND\n" +
                "\tSessao.id_filme = Filme.id_filme AND\n" +
                "\tFilme.codigo = ?;";
        try {
            conexao = ConnectionFactory.getConnection();
            comando = conexao.prepareStatement(sql);
            comando.setInt(1, cod_filme);
            ResultSet resultado = comando.executeQuery();
            while(resultado.next()){
                Venda venda = buscaPorID(resultado.getInt("id_venda"));
                vendaList.add(venda);
            }
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return vendaList;
    }

    @Override
    public Venda buscaPorID(int id) {
        SessaoDao sessaoDao = new SessaoDaoBd();
        Venda venda = null;
        String sql = "SELECT * FROM Venda WHERE id_venda = ?";
        try {
            conexao = ConnectionFactory.getConnection();
            comando = conexao.prepareStatement(sql);
            comando.setInt(1, id);
            ResultSet resultado = comando.executeQuery();
            if (resultado.next()){
                venda = new Venda(sessaoDao.buscaPorID(resultado.getInt("id_sessao")), resultado.getDouble("valor_venda"), resultado.getInt("id_venda"));
            }
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return venda;
    }

    @Override
    public List<Venda> listar() {
        FilmeDao filmeDao = new FilmeDaoBd();
        SalaDao salaDao = new SalaDaoBd();
        SessaoDao sessaoDao = new SessaoDaoBd();

        List<Venda> vendaList = new ArrayList<Venda>();

        String sql = "SELECT * FROM Venda";
        try {
            conexao = ConnectionFactory.getConnection();
            comando = conexao.prepareStatement(sql);
            ResultSet resultado = comando.executeQuery();
            while(resultado.next()){
                Venda venda = new Venda(sessaoDao.buscaPorID(resultado.getInt("id_sessao")), resultado.getDouble("valor_venda"), resultado.getInt("id_venda"));
                vendaList.add(venda);
            }
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return vendaList;
    }

    @Override
    public int retornaIDVenda(String num_sala, String hora_sessao) {
        return 0;
    }
}
