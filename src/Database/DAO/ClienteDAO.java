package Database.DAO;

import Clases.Cliente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class ClienteDAO {
    Connection conn;
    public ClienteDAO(Connection conn){
        this.conn = conn;
    }

    public ObservableList<Cliente> fetchAll() {
        ObservableList<Cliente> clientes = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM InformacionCliente";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            Cliente p = null;
            while(rs.next()) {
                p = new Cliente(
                        rs.getString("RFC"),
                        rs.getString("Nombre"),
                        rs.getInt("Telefono"),
                        rs.getString("Direccion"),
                        rs.getString("Ciudad"),
                        rs.getString("NombreEstado"));
                clientes.add(p);
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return clientes;
    }

    public Boolean delete(String clave) {
        try {
            String query = "call borrarCliente(?)";
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, clave);
            st.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public Boolean insert(Cliente cliente) {
        try {
            String query = "insert into cliente "
                    + " (RFC, Nombre, Telefono, Direccion, Ciudad, NombreEstado)"
                    + " values (?, ?, ?, ?, ?, ?)";
            PreparedStatement st =  conn.prepareStatement(query);
            st.setString(1, cliente.getRFC());
            st.setString(2, cliente.getNombre());
            st.setInt(3, cliente.getTelefono());
            st.setString(4, cliente.getDireccion());
            st.setString(5, cliente.getCveCiudad());
            st.setString(6, cliente.getCveEstado());
            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }


    public Boolean update(Cliente cliente) {
        try {
            String query = "UPDATE cliente SET Nombre = ?, Telefono = ?, Direccion = ?, Ciudad = ?, NombreEstado = ? WHERE RFC = ?";
            PreparedStatement st =  conn.prepareStatement(query);
            st.setString(6, cliente.getRFC());
            st.setString(1, cliente.getNombre());
            st.setInt(2, cliente.getTelefono());
            st.setString(3, cliente.getDireccion());
            st.setString(4, cliente.getCveCiudad());
            st.setString(5, cliente.getCveEstado());
            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }
}
