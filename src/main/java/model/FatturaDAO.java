package model;

import controller.RegistrazioneServlet;

import java.sql.*;
import java.util.ArrayList;

public class FatturaDAO {

    public boolean insertIntoDatabase(FatturaBean fatturaBean) {
        try (Connection connection = ConnectionDatabase.getConnection()) {
            System.out.println("[DEBUG] FatturaDAO - Attempting to insert invoice for: " + fatturaBean.getNicknameCliente());
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Fattura(data_acquisto, nickname, carta, numero_carta, indirizzoFatturazione, chiave, titolo) values (?, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setDate(1, fatturaBean.getDataAcquisto());
            preparedStatement.setString(2, fatturaBean.getNicknameCliente());
            preparedStatement.setString(3, fatturaBean.getTipoCarta());
            preparedStatement.setString(4, fatturaBean.getNumeroCarta());
            preparedStatement.setString(5, fatturaBean.getIndirizzoFatturazione());
            preparedStatement.setString(6, fatturaBean.getChiave());
            preparedStatement.setString(7, fatturaBean.getTitolo());
            System.out.println("[DEBUG] FatturaDAO - Executing insert query");
            preparedStatement.execute();
            System.out.println("[DEBUG] FatturaDAO - Insert successful");
            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("[DEBUG] FatturaDAO - Insert failed: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;

    }
        public int getLastIDFattura(String nickname) {
            try (Connection connection = ConnectionDatabase.getConnection()) {
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT id_fattura FROM Fattura WHERE nickname=?");
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
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT id_fattura FROM Fattura WHERE nickname=?");
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

        public ArrayList<FatturaBean> retrieveFattureWithKeysAndTitles(String nickname){
            try(Connection connection = ConnectionDatabase.getConnection()){
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT id_fattura, data_acquisto, chiave, titolo FROM Fattura WHERE nickname=? ORDER BY data_acquisto DESC");
                preparedStatement.setString(1, nickname);
                ResultSet resultSet = preparedStatement.executeQuery();
                ArrayList<FatturaBean> fatture = new ArrayList<>();
                while(resultSet.next()){
                    FatturaBean fattura = new FatturaBean();
                    fattura.setIdFattura(String.valueOf(resultSet.getInt("id_fattura")));
                    fattura.setDataAcquisto(resultSet.getDate("data_acquisto"));
                    fattura.setChiave(resultSet.getString("chiave"));
                    fattura.setTitolo(resultSet.getString("titolo"));
                    fattura.setNicknameCliente(nickname);
                    fatture.add(fattura);
                }
                return fatture;
            }catch (SQLException | ClassNotFoundException c){
                return null;
            }
        }
}