package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CollocamentoDAO {
    public void insetIntoDatabase(CollocamentoBean collocamentoBean){
        try(Connection connection = ConnectionDatabase.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Collocamento(id_gioco, id_fattura) values (?, ?)");
            preparedStatement.setString(1, collocamentoBean.getIdGioco());
            preparedStatement.setInt(2, collocamentoBean.getIdFattura());
            preparedStatement.execute();
        }catch (SQLException | ClassNotFoundException e){

        }
    }

    public ArrayList<CollocamentoBean> getCollocamentoByFattura(int idFattura){
        try(Connection connection = ConnectionDatabase.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Collocamento WHERE id_fattura = ?");
            preparedStatement.setInt(1, idFattura);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<CollocamentoBean> collocamentoBeans = new ArrayList<>();
            while(resultSet.next()){
                collocamentoBeans.add(getCollecamentoQuery(resultSet));
            }
            preparedStatement.close();
            resultSet.close();
            return  collocamentoBeans;
        }catch (SQLException | ClassNotFoundException e){
            return null;
        }
    }

    public ArrayList<CollocamentoBean> retrieveIDsGiocoByFatture(ArrayList<Integer> idsFattura) {
        try (Connection connection = ConnectionDatabase.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Collocamento WHERE id_fattura=?");
            ArrayList<CollocamentoBean> collocamentoBeans = new ArrayList<>();
            for(Integer idFattura : idsFattura) {
                preparedStatement.setInt(1, idFattura.intValue());
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    collocamentoBeans.add(getCollecamentoQuery(resultSet));
                }
            }
            return collocamentoBeans;
        } catch (SQLException | ClassNotFoundException e) {
            return null;
        }
    }

    private CollocamentoBean getCollecamentoQuery(ResultSet resultSet) throws SQLException {
        CollocamentoBean collocamentoBean = new CollocamentoBean();
        collocamentoBean.setIdGioco(resultSet.getString(1));
        collocamentoBean.setIdFattura(resultSet.getInt(2));
        return collocamentoBean;

    }
}
