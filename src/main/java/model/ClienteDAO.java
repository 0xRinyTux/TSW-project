package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

/**
 * Data Access Object for Cliente (Customer) operations.
 * Handles CRUD operations for ClienteBean.
 *
 * @author Rinaldo Perna
 */
public class ClienteDAO {


    //inserisce i dati dei form inseriti dall'utente nel database
    public boolean insertIntoDatabase(ClienteBean clienteBean) throws SQLException, ClassNotFoundException {

        try {
            Connection connection = ConnectionDatabase.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Cliente(nickname, passwordCliente, email, nome, cognome, data_di_nascita) values (?, ? , ?, ?, ?, ?)");
            preparedStatement.setString(1, clienteBean.getNickname());
            preparedStatement.setString(2, clienteBean.getPassword());
            preparedStatement.setString(3, clienteBean.getEmail());
            preparedStatement.setString(4, clienteBean.getNome());
            preparedStatement.setString(5, clienteBean.getCognome());
            preparedStatement.setDate(6, Date.valueOf(clienteBean.getDataDiNascita()));
            preparedStatement.execute();
        }
        catch (SQLException | ClassNotFoundException e){
            return false;
        }
        return true;

    }

    public boolean updateCliente(ClienteBean clienteBean){
        try(Connection connection = ConnectionDatabase.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Cliente SET passwordCliente=?, email=?, nome=?, cognome=?, numero_carta=?, indirizzo_fatturazione=?, tipo_carta=? WHERE nickname=?");
            preparedStatement.setString(8, clienteBean.getNickname());
            preparedStatement.setString(1, clienteBean.getPassword());
            preparedStatement.setString(2, clienteBean.getEmail());
            preparedStatement.setString(3, clienteBean.getNome());
            preparedStatement.setString(4, clienteBean.getCognome());
            preparedStatement.setString(5, clienteBean.getNumeroCarta());
            preparedStatement.setString(6, clienteBean.getIndirizzoFatturazione());
            preparedStatement.setString(7, clienteBean.getTipoCarta());
            return preparedStatement.execute();

        }catch (SQLException | ClassNotFoundException e){
            return true;
        }
    }

    public boolean updateClienteWithoutPassword(ClienteBean clienteBean){
        try(Connection connection = ConnectionDatabase.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Cliente SET  email=?, nome=?, cognome=?, numero_carta=?, indirizzo_fatturazione=?, tipo_carta=? WHERE nickname=?");
            preparedStatement.setString(7, clienteBean.getNickname());
            preparedStatement.setString(1, clienteBean.getEmail());
            preparedStatement.setString(2, clienteBean.getNome());
            preparedStatement.setString(3, clienteBean.getCognome());
            preparedStatement.setString(4, clienteBean.getNumeroCarta());
            preparedStatement.setString(5, clienteBean.getIndirizzoFatturazione());
            preparedStatement.setString(6, clienteBean.getTipoCarta());
            return preparedStatement.execute();

        }catch (SQLException | ClassNotFoundException e){
            return true;
        }
    }


    //Controlla le credenziali inserite dall'utente per i login e restituisce il nickname
    public String matchCredential(String email, String password) throws SQLException, ClassNotFoundException {
        System.out.println("[DEBUG] matchCredential called with email: " + email + ", password: " + password);
        String username = null;
        String query = "SELECT nickname, passwordCliente FROM Cliente WHERE email = ?";
        System.out.println("[DEBUG] SQL Query: " + query);
        try (Connection connection = ConnectionDatabase.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String dbPassword = resultSet.getString("passwordCliente");
                    String dbNickname = resultSet.getString("nickname");
                    System.out.println("[DEBUG] DB returned nickname: " + dbNickname + ", passwordCliente: " + dbPassword);
                    String hashedInput = ClienteBean.hashText(password);
                    if (dbPassword != null && (dbPassword.equals(password) || dbPassword.equals(hashedInput))) {
                        username = dbNickname;
                        System.out.println("[DEBUG] Password match. Returning username: " + username);
                    } else {
                        System.out.println("[DEBUG] Password does not match. Input: " + password + ", DB: " + dbPassword);
                    }
                } else {
                    System.out.println("[DEBUG] No user found with email: " + email);
                }
            }
        }
        return username;
    }

    public ClienteBean retrieveClientByNickname(String nickname){
        try(Connection connection = ConnectionDatabase.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT nickname, email, nome, cognome, tipo_carta, numero_carta, indirizzo_fatturazione FROM Cliente WHERE nickname=?");
            preparedStatement.setString(1, nickname);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return retrieveClient(resultSet);
            }
            return  null;
        }catch (SQLException | ClassNotFoundException e){
            return null;
        }
    }

    private ClienteBean retrieveClient(ResultSet resultSet) throws SQLException {
        ClienteBean clienteBean = new ClienteBean();
        clienteBean.setNickname(resultSet.getString(1));
        clienteBean.setEmail(resultSet.getString(2));
        clienteBean.setNome(resultSet.getString(3));
        clienteBean.setCognome(resultSet.getString(4));
        clienteBean.setTipoCarta(resultSet.getString(5));
        clienteBean.setNumeroCarta(resultSet.getString(6));
        clienteBean.setIndirizzoFatturazione(resultSet.getString(7));
        return clienteBean;


    }

    public boolean updatePaymentData(String nickname, String indirizzo, String tipoCarta, String numeroCarta){
        try(Connection connection = ConnectionDatabase.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Cliente SET numero_carta = ?, indirizzo_fatturazione = ?, tipo_carta = ? WHERE nickname = ?");
            preparedStatement.setString(1, numeroCarta);
            preparedStatement.setString(2, indirizzo);
            preparedStatement.setString(3, tipoCarta);
            preparedStatement.setString(4, nickname);
            return preparedStatement.execute();

        }catch (SQLException | ClassNotFoundException e){
            return true;
        }
    }

    public boolean seachNickname(String nickname){
        try(Connection connection = ConnectionDatabase.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT nickname FROM Cliente WHERE nickname=?");
            preparedStatement.setString(1, nickname);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return true;
            }else{
                return false;
            }
        }catch (SQLException | ClassNotFoundException e){
            return false;
        }
    }


    public boolean seachEmail(String email){
        try(Connection connection = ConnectionDatabase.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT email FROM Cliente WHERE email=?");
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return true;
            }else{
                return false;
            }
        }catch (SQLException | ClassNotFoundException e){
            return false;
        }
    }


}
