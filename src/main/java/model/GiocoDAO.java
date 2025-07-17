package model;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;

public class GiocoDAO {

    public void insertIntoDatabase(GiocoBean giocoBean){
        try(Connection connection = ConnectionDatabase.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Gioco values (?, ?, ?, ?, ?, ?, ?, ? ,?, ?, ?, ?, ?)");
            preparedStatement.setString(1, giocoBean.getNomeGioco());
            preparedStatement.setDouble(2, giocoBean.getPrezzoGioco());
            preparedStatement.setString(3, giocoBean.getDescrizione());
            preparedStatement.setString(4, giocoBean.getDataGioco());
            preparedStatement.setString(5, giocoBean.getConsole());
            preparedStatement.setString(6, giocoBean.getSoftwareHouse());
            preparedStatement.setString(7, giocoBean.getUrl());
            preparedStatement.setInt(8, giocoBean.isInSconto());
            preparedStatement.setDouble(9, giocoBean.getPrezzoScontato());
            preparedStatement.setString(10, giocoBean.getGenereGioco());
            preparedStatement.setInt(11, giocoBean.getInVendita());
            preparedStatement.setString(12, giocoBean.getImmagine());
            preparedStatement.setInt(13, giocoBean.getNumeroVendite());
            preparedStatement.execute();
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public ArrayList<GiocoBean> search(String ricerca) {
        try (Connection connection = ConnectionDatabase.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Gioco WHERE titolo_gioco LIKE  '" + ricerca + "%' ");
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<GiocoBean> giocoBeans = new ArrayList<>();
            while (resultSet.next()) {
                giocoBeans.add(retrieveGiocoBean(resultSet));
            }
            return giocoBeans;
        }catch (SQLException | ClassNotFoundException e){
            return null;
        }
    }

    public boolean rimuoviGioco(String titoloGioco){
        try(Connection connection = ConnectionDatabase.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Gioco WHERE titolo_gioco = ?");
            preparedStatement.setString(1, titoloGioco);
            if(!preparedStatement.execute()){
               return true;
            }else{
                return false;
            }
        }catch (ClassNotFoundException | SQLException e){
            return false;
        }
    }

    public boolean updateGioco(GiocoBean giocoBean){
        try(Connection connection = ConnectionDatabase.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Gioco SET titolo_gioco = ?, prezzo_gioco = ?, descrizione_gioco = ?, anno_gioco = ?, console = ?, softwarehouse = ?, url = ?, in_sconto = ?, prezzo_scont_gioco = ?, genere = ?, in_vendita = ? WHERE titolo_gioco=?");
            preparedStatement.setString(1, giocoBean.getNomeGioco());
            preparedStatement.setDouble(2, giocoBean.getPrezzoGioco());
            preparedStatement.setString(3, giocoBean.getDescrizione());
            preparedStatement.setString(4, giocoBean.getDataGioco());
            preparedStatement.setString(5, giocoBean.getConsole());
            preparedStatement.setString(6, giocoBean.getSoftwareHouse());
            preparedStatement.setString(7, giocoBean.getUrl());
            preparedStatement.setInt(8, giocoBean.isInSconto());
            preparedStatement.setDouble(9, giocoBean.getPrezzoScontato());
            preparedStatement.setString(10, giocoBean.getGenereGioco());
            preparedStatement.setInt(11, giocoBean.getInVendita());
            preparedStatement.setString(12, giocoBean.getNomeGioco());
            return preparedStatement.execute();
        }catch (ClassNotFoundException | SQLException e){
            return false;
        }

    }


    public GiocoBean doRetrieveGameByName(String titolo_gioco){
        GiocoBean giocoBean = new GiocoBean();
        try(Connection connection = ConnectionDatabase.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Gioco WHERE titolo_gioco = ?");
            preparedStatement.setString(1, titolo_gioco);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                giocoBean = retrieveGiocoBean(resultSet);
            }

        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return giocoBean;
    }

    public ArrayList<GiocoBean> doRetrieveAll(){
        try(Connection connection = ConnectionDatabase.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Gioco");
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<GiocoBean> giochi = new ArrayList<>();
            while (resultSet.next()) {
                giochi.add(retrieveGiocoBean(resultSet));
            }
            return giochi;
        }catch (SQLException | ClassNotFoundException e){
            return null;
        }
    }

    public GiocoBean doRetrieveByImage(String image){
        GiocoBean giocoBean = new GiocoBean();
        try(Connection connection = ConnectionDatabase.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT *FROM Gioco WHERE nome_immagine = ?");
            preparedStatement.setString(1, image);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                giocoBean = retrieveGiocoBean(resultSet);
            }

        }catch (SQLException | ClassNotFoundException e){
            return null;
        }
        return giocoBean;


    }

    public ArrayList<GiocoBean> retrieveMostSellerGames(){
        ArrayList<GiocoBean> giocoBeans = new ArrayList<>();
        try(Connection connection = ConnectionDatabase.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT  * FROM Gioco WHERE numero_vendite >= 500");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                giocoBeans.add(retrieveGiocoBean(resultSet));
            }
        }catch (SQLException | ClassNotFoundException e){
            return null;
        }
        return giocoBeans;
    }

    private GiocoBean retrieveGiocoBean(ResultSet resultSet) throws SQLException {
       GiocoBean giocoBean = new GiocoBean();
       giocoBean.setNomeGioco(resultSet.getString(1));
       giocoBean.setPrezzoGioco(resultSet.getDouble(2));
       giocoBean.setDescrizione(resultSet.getString(3));
       giocoBean.setDataGioco(resultSet.getString(4));
       giocoBean.setConsole(resultSet.getString(5));
       giocoBean.setSoftwareHouse(resultSet.getString(6));
       giocoBean.setUrl(resultSet.getString(7));
       giocoBean.setInSconto(resultSet.getInt(8));
       giocoBean.setPrezzoScontato(resultSet.getDouble(9));
       giocoBean.setGenereGioco(resultSet.getString(10));
       giocoBean.setInVendita(resultSet.getInt(11));
       giocoBean.setImmagine(resultSet.getString(12));
       giocoBean.setNumeroVendite(resultSet.getInt(13));
    return giocoBean;
    }



}
