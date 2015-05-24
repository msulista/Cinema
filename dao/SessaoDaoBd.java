package dao;

import banco.ConnectionFactory;
import model.Secao;
import util.DateUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcus.rodrigues on 16/05/2015.
 */
public class SessaoDaoBd implements SessaoDao{

    private Connection conexao;
    private PreparedStatement comando;

    @Override
    public void inserir(Secao secao, String horaSql, String numSala, int codFilme) {

        SalaDao salaDao = new SalaDaoBd();
        FilmeDao filmeDao = new FilmeDaoBd();
        int idSala = salaDao.retornaIDSala(numSala);
        int idFilme = filmeDao.retornaIDFilme(codFilme);

        String sql = "INSERT INTO Sessao (horario, valor, id_sala, id_filme) VALUES(?,?,?,?)";
        try {
            conexao = ConnectionFactory.getConnection();
            comando = conexao.prepareStatement(sql);
            comando.setString(1, horaSql);
            comando.setDouble(2, secao.getValor());
            comando.setInt(3, idSala);
            comando.setInt(4, idFilme);
            comando.executeUpdate();
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletar(int idSessao) {

        String sql = "DELETE FROM Sessao WHERE id_sessao = ?";
        try {
            conexao = ConnectionFactory.getConnection();
            comando = conexao.prepareStatement(sql);
            comando.setInt(1, idSessao);
            comando.executeUpdate();
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(Secao secao, String horario, String numSala, int codFilme, int id_sessao) {

        SalaDao salaDao = new SalaDaoBd();
        FilmeDao filmeDao = new FilmeDaoBd();
        int id_sala = salaDao.retornaIDSala(numSala);
        int id_filme = filmeDao.retornaIDFilme(codFilme);

        String sql = "UPDATE Sessao SET horario=?, valor=?, id_sala=?, id_filme=? WHERE id_sessao = ?";
        try {
            conexao = ConnectionFactory.getConnection();
            comando = conexao.prepareStatement(sql);
            comando.setString(1, horario);
            comando.setDouble(2, secao.getValor());
            comando.setInt(3, id_sala);
            comando.setInt(4, id_filme);
            comando.setInt(5, id_sessao);
            comando.executeUpdate();
            conexao.close();
        }catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Secao buscaSessaoPorFilmeEHorario(int codFilme, String horario) {
        FilmeDao filmeDao = new FilmeDaoBd();
        SalaDao salaDao = new SalaDaoBd();
        List<Secao> secaoList = new ArrayList<Secao>();

        int idFilme = filmeDao.retornaIDFilme(codFilme);
        Secao secao = null;
        String sql = "SELECT * FROM Sessao WHERE id_filme = ? AND horario =?";
        try {
            conexao = ConnectionFactory.getConnection();
            comando = conexao.prepareStatement(sql);
            comando.setInt(1, idFilme);
            comando.setString(2, horario);
            ResultSet resultado = comando.executeQuery();
            if (resultado.next()){
                secao = new Secao(salaDao.buscaPorID(resultado.getInt("id_sala")),
                                        filmeDao.buscaPorID(resultado.getInt("id_filme")),
                                        DateUtil.stringToHour(resultado.getString("horario")),
                                        resultado.getDouble("valor"));
            }
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return secao;


    }

    @Override
    public List<Secao> buscaSessaoPorCodFilme(int codFilme) {
        FilmeDao filmeDao = new FilmeDaoBd();
        SalaDao salaDao = new SalaDaoBd();
        List<Secao> secaoList = new ArrayList<Secao>();

        int idFilme = filmeDao.retornaIDFilme(codFilme);
        Secao secao = null;
        String sql = "SELECT * FROM Sessao WHERE id_filme = ?";
        try {
            conexao = ConnectionFactory.getConnection();
            comando = conexao.prepareStatement(sql);
            comando.setInt(1, idFilme);
            ResultSet resultado = comando.executeQuery();
            if (resultado.next()){
                secao = new Secao(salaDao.buscaPorID(resultado.getInt("id_sala")),
                        filmeDao.buscaPorID(resultado.getInt("id_filme")),
                        DateUtil.stringToHour(resultado.getString("horario")),
                        resultado.getDouble("valor"));
                secaoList.add(secao);
            }
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return secaoList;
    }

    @Override
    public List<Secao> buscaSessaoPorHorario(String horario) {
        List<Secao> filmeList = new ArrayList<Secao>();


        return null;
    }

    @Override
    public Secao buscaPorID(int id){
        SalaDao salaDao = new SalaDaoBd();
        FilmeDao filmeDao = new FilmeDaoBd();
        Secao secao = null;
        String sql = "SELECT * FROM Sessao WHERE id_sessao = ?";
        try {
            conexao = ConnectionFactory.getConnection();
            comando = conexao.prepareStatement(sql);
            comando.setInt(1, id);
            ResultSet resultado = comando.executeQuery();
            if (resultado.next()){
                secao = new Secao(salaDao.buscaPorID(resultado.getInt("id_sala")),
                        filmeDao.buscaPorID(resultado.getInt("id_filme")),
                        DateUtil.stringToHour(resultado.getString("horario")),
                        resultado.getDouble("valor"));
            }
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return secao;
    }
    @Override
    public List<Secao> listar() {
        FilmeDao filmeDao = new FilmeDaoBd();
        SalaDao salaDao = new SalaDaoBd();
        List<Secao> secaoList = new ArrayList<Secao>();
        String sql = "SELECT * FROM Sessao";
        try {
            conexao = ConnectionFactory.getConnection();
            comando = conexao.prepareStatement(sql);
            ResultSet resultado = comando.executeQuery();
            while(resultado.next()){
                Secao secao = new Secao(salaDao.buscaPorID(resultado.getInt("id_sala")),
                                        filmeDao.buscaPorID(resultado.getInt("id_filme")),
                                                DateUtil.stringToHour(resultado.getString("horario")),
                                                resultado.getDouble("valor"),
                                                resultado.getInt("id_sessao"));
                secaoList.add(secao);
            }
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return secaoList;
    }

    @Override
    public int retornaIDSessao(int id_sala, int id_filme, String hora) {
        int idSessao = 0;
        String slqSessao = "SELECT id_Sessao FROM Sessao " +
                            "WHERE " +
                                "id_sala = ? AND " +
                                "id_filme = ? AND " +
                                "horario = ?";
        try {
            conexao = ConnectionFactory.getConnection();
            comando = conexao.prepareStatement(slqSessao);
            comando.setInt(1, id_sala);
            comando.setInt(1, id_filme);
            comando.setString(1, hora);
            ResultSet resultado = comando.executeQuery();
            if (resultado.next()) {
                idSessao = resultado.getInt("id_sessao");
            }
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return idSessao;
    }

}
