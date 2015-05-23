package dao;

import banco.ConnectionFactory;
import model.Sala;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcus.rodrigues on 16/05/2015.
 */
public class SalaDaoBd implements SalaDao{

    private Connection conexao;
    private PreparedStatement comando;

    @Override
    public void inserir(Sala sala) {
        String sql = "INSERT INTO Sala (numero, qtd_assentos) VALUES(?,?)";
        try {

            conexao = ConnectionFactory.getConnection();
            comando = conexao.prepareStatement(sql);


            comando.setString(1, sala.getNumero());
            comando.setInt(2, sala.getQtdAssentos());
            comando.executeUpdate();
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletar(Sala sala) {

        String sql = "DELETE FROM Sala WHERE numero = ?";
        try {
            conexao = ConnectionFactory.getConnection();
            comando = conexao.prepareStatement(sql);
            comando.setString(1, sala.getNumero());
            comando.executeUpdate();
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(Sala sala) {

        String sql = "UPDATE Sala SET numero=?, qtd_assentos=?";
        try {
            conexao = ConnectionFactory.getConnection();
            comando = conexao.prepareStatement(sql);
            comando = conexao.prepareStatement(sql);
            comando.setString(1, sala.getNumero());
            comando.setInt(2, sala.getQtdAssentos());
            comando.executeUpdate();
            conexao.close();
        }catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Sala buscarPorNumero(int numero) {

        Sala sala = null;
        String sql = "SELECT * FROM Sala WHERE numero = ?";
        try {
            conexao = ConnectionFactory.getConnection();
            comando = conexao.prepareStatement(sql);
            comando.setInt(1, numero);
            ResultSet resultado = comando.executeQuery();
            if(resultado.next()){
                sala = new Sala(resultado.getString("numero"),
                        resultado.getInt("qtd_assentos"));
            }
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return sala;
    }

    @Override
    public Sala buscaPorID(int id){
        Sala sala = null;
        String sql = "SELECT * FROM Sala WHERE id_sala = ?";
        try {
            conexao = ConnectionFactory.getConnection();
            comando = conexao.prepareStatement(sql);
            comando.setInt(1, id);
            ResultSet resultado = comando.executeQuery();
            if (resultado.next()){
                sala = new Sala(resultado.getString("numero"),
                            resultado.getInt("qtd_assentos"));
            }
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return sala;
    }

    @Override
    public List<Sala> listar() {
        List<Sala> salaList = new ArrayList<Sala>();
        Sala sala = null;
        String sql = "SELECT * FROM Sala";
        try {
            conexao = ConnectionFactory.getConnection();
            comando = conexao.prepareStatement(sql);
            ResultSet resultado = comando.executeQuery();
            while (resultado.next()){
                sala = new Sala(resultado.getString("numero"),
                        resultado.getInt("qtd_assentos"));
                salaList.add(sala);
            }
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return salaList;
    }
}
