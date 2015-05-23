package test;

import util.DateUtil;

import java.text.ParseException;
import java.util.Date;

/**
 * Created by marcus.rodrigues on 20/05/2015.
 */
public class TesteDataPostgre {

    public static void main(String[] args) {

        String data1 = "12/05/2015";


        Date data = new Date();

            data = DateUtil.stringToDatePostgre(data1);


        System.out.println("String to date Postgre: "+ data);
        //System.out.println("String to date: "+ dataDate);

    }
    /*
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
        */
}
