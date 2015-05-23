package dao;

import banco.ConnectionFactory;
import model.Secao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by marcus.rodrigues on 16/05/2015.
 */
public class SessaoDaoBd implements SessaoDao{

    private Connection conexao;
    private PreparedStatement comando;

    @Override
    public void inserir(Secao secao, String horaSql, String numSala, int codFilme) {

        int idSala = 0;
        int idFilme =0;
        String slqSala = "SELECT id_sala FROM Sala WHERE numero = ?";
        try {
            conexao = ConnectionFactory.getConnection();
            comando = conexao.prepareStatement(slqSala);
            comando.setString(1, numSala);
            ResultSet resultado = comando.executeQuery();
            if (resultado.next()) {
                idSala = resultado.getInt("id_sala");
            }
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String slqFilme = "SELECT id_filme FROM Filme WHERE codigo = ?";
        try {
            conexao = ConnectionFactory.getConnection();
            comando = conexao.prepareStatement(slqFilme);
            comando.setInt(1, codFilme);
            ResultSet resultado = comando.executeQuery();
            if (resultado.next()) {
                idFilme = resultado.getInt("id_filme");
            }
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("idFilme: " + idFilme);
        System.out.println("idSala: " + idSala);

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
    public void deletar(Secao secao) {

        String sql = "DELETE FROM Sessao WHERE horario = ?";
        try {
            conexao = ConnectionFactory.getConnection();
            comando = conexao.prepareStatement(sql);
            comando.setDate(1, (java.sql.Date) secao.getHorario());
            comando.executeUpdate();
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(Secao secao) {

        String numeroSala = secao.getSala().getNumero();
        int codigoFilme = secao.getFilme().getCodigo();
        String slq2 = "SELECT id_sala, id_filme FROM Sala, Filme WHERE numero = ? AND codigo = ?";
        int idSala = 0;
        int idFilme =0;
        try {
            conexao = ConnectionFactory.getConnection();
            comando = conexao.prepareStatement(slq2);
            comando.setString(1, numeroSala);
            comando.setInt(2, codigoFilme);
            ResultSet resultado = comando.executeQuery();
            idSala = resultado.getInt("id_sala");
            idFilme = resultado.getInt("id_filme");
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String sql = "UPDATE Sessao SET horario=?, valor=?, id_sala=?, id_filme=?";
        try {
            conexao = ConnectionFactory.getConnection();
            comando = conexao.prepareStatement(sql);
            comando.setDate(1, (java.sql.Date) secao.getHorario());
            comando.setDouble(2, secao.getValor());
            comando.setInt(3, idSala);
            comando.setInt(4, idFilme);
            comando.executeUpdate();
            conexao.close();
        }catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Secao> buscaPorHorarioEFilme(String titulo) {
        List<Secao> filmeList = new ArrayList<Secao>();
        /*
        String slq2 = "SELECT id_filme FROM Filme WHERE titulo = ?";
        int idFilme =0;
        try {
            comando = conexao.prepareStatement(slq2);
            comando.setString(1, titulo);
            ResultSet resultado = comando.executeQuery();
            idFilme = resultado.getInt("id_filme");
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sql = "SELECT * FROM Sessao WHERE id_filme=? ";
        try {
            comando = conexao.prepareStatement(sql);
            comando.setInt(1, idFilme);
            ResultSet resultado = comando.executeQuery();
            while(resultado.next()){
                Secao secao = new Secao(resultado.getInt("id_sala"),
                        resultado.getInt("id_filme"),
                        resultado.getDate("horario"),
                        resultado.getDouble("valor"));
                filmeList.add(Secao);
            }
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        */
        return filmeList;

    }

    @Override
    public List<Secao> buscaPorHorario(Date horario) {
        return null;
    }

    @Override
    public List<Secao> listar() {
        return null;
    }
}
