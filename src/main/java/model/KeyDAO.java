package model;

import java.awt.image.RescaleOp;
import java.security.Key;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class KeyDAO {
    public void insertIntoDatabase(KeyBean keyBean){
        try(Connection connection = ConnectionDatabase.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Chiave(gioco, fattura, keyGioco) values (?, ?, ?)");
            preparedStatement.setString(1, keyBean.getNomeGioco());
            preparedStatement.setInt(2, keyBean.getFattura());
            preparedStatement.setString(3,keyBean.getKey());
            preparedStatement.execute();
        }catch (SQLException | ClassNotFoundException e){

        }
    }

    public int retrieveIDKeyByGiocoAndFattura(int idFattura, String nomeGioco){
        try(Connection connection = ConnectionDatabase.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id_chiave FROM Chiave WHERE fattura=? and gioco=?");
            preparedStatement.setInt(1, idFattura);
            preparedStatement.setString(2, nomeGioco);
            int lastId = 0;
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                lastId = resultSet.getInt(1);
            }
            return lastId;

        }catch (SQLException | ClassNotFoundException e){
            return 0;

        }
    }

    public ArrayList<KeyBean> retrieveIDKeyByGiocchiAndFatture(ArrayList<CollocamentoBean> collocamentoBeans){
        try (Connection connection = ConnectionDatabase.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Chiave WHERE gioco=? and fattura=?");
            ArrayList<KeyBean> keyBeans = new ArrayList<>();
            for(CollocamentoBean collocamentoBean : collocamentoBeans){
                preparedStatement.setString(1, collocamentoBean.getIdGioco());
                preparedStatement.setInt(2, collocamentoBean.getIdFattura());
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    keyBeans.add(retrieveKeyBean(resultSet));
                }
            }
            return keyBeans;
        }catch (SQLException | ClassNotFoundException e){
            return null;
        }
    }

    private KeyBean retrieveKeyBean(ResultSet resultSet) throws SQLException {
        KeyBean keyBean = new KeyBean();
        keyBean.setIdChiave(resultSet.getInt(1));
        keyBean.setNomeGioco(resultSet.getString(2));
        keyBean.setFattura(resultSet.getInt(3));
        keyBean.setKey(resultSet.getString(4));
        return keyBean;

    }


}
