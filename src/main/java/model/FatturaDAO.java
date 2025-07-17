package model;

import controller.RegistrazioneServlet;

import java.sql.*;
import java.util.ArrayList;

public class FatturaDAO {

    public boolean insertIntoDatabase(FatturaBean fatturaBean) {
        try (Connection connection = ConnectionDatabase.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO fattura(data_acquisto, nickname, carta, numero_carta, indirizzoFatturazione) values (?, ?, ?, ?, ?)");
            preparedStatement.setDate(1, fatturaBean.getDataAcquisto());
            preparedStatement.setString(2, fatturaBean.getNicknameCliente());
            preparedStatement.setString(3, fatturaBean.getTipoCarta());
            preparedStatement.setString(4, fatturaBean.getNumeroCarta());
            preparedStatement.setString(5, fatturaBean.getIndirizzoFatturazione());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException e) {
            return false;
        }
        return true;

    }
        public int getLastIDFattura(String nickname) {
            try (Connection connection = ConnectionDatabase.getConnection()) {
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT id_fattura FROM fattura WHERE nickname=?");
                preparedStatement.setString(1, nickname);
                ResultSet resultSet = preparedStatement.executeQuery();
                int lastId = 0;
                while (resultSet.next()) {
                    lastId = resultSet.getInt(1);
                }
                return lastId;
            } catch (SQLException | ClassNotFoundException e) {
                return 0;
            }

        }

        public ArrayList<Integer> retriveIDsFatturaByNickname(String nickname){
            try(Connection connection = ConnectionDatabase.getConnection()){
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT id_fattura FROM fattura WHERE nickname=?");
                preparedStatement.setString(1, nickname);
                ResultSet resultSet = preparedStatement.executeQuery();
                ArrayList<Integer> ids = new ArrayList<>();
                while(resultSet.next()){
                    ids.add(resultSet.getInt(1));
                }
                return ids;
            }catch (SQLException | ClassNotFoundException c){
                return null;
            }
        }



}