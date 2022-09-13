package Database.DAO;

import Clases.Alquiler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class AlquilerDAO {
    Connection conn;
    public AlquilerDAO(Connection conn){
        this.conn = conn;
    }

    public ObservableList<Alquiler> fetchAll() {
        ObservableList<Alquiler> alquilers = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM AlquilerVista";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            Alquiler p = null;
            while(rs.next()) {
                p = new Alquiler(
                        rs.getInt("CveAlquiler"),
                        rs.getInt("CveAgenciaRenta"),
                        rs.getInt("CveAgenciaDev"),
                        rs.getString("Matricula"),
                        rs.getString("RFC"),
                        rs.getString("TipoPago"),
                        rs.getDate("FechaRenta"),
                        rs.getDate("FechaDev"),
                        rs.getString("HoraRenta"),
                        rs.getString("HoraDev"),
                        rs.getDouble("CostoTotal")
                        );
                alquilers.add(p);
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return alquilers;
    }

    public Boolean insert(Alquiler alquiler) {
        try {
            String query = "call nuevoAlquiler (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement st =  conn.prepareStatement(query);
            st.setInt(1, alquiler.getCveAlquiler());
            st.setInt(2, alquiler.getCveAgenciaRenta());
            st.setInt(3, alquiler.getCveAgenciaDev());
            st.setString(4, alquiler.getMatricula());
            st.setString(5, alquiler.getRFC());
            st.setString(6, alquiler.getCveFormaPago());
            st.setDate(7, alquiler.getFechaRenta());
            st.setDate(8, alquiler.getFechaDev());
            st.setString(9, alquiler.getHoraRenta());
            st.setString(10, alquiler.getHoraDev());
            st.setDouble(11, alquiler.getCostoTotal());
            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }

    public Boolean delete(int clave) {
        try {
            String query = "call borrarAlquiler (?)";
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, clave);
            st.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
