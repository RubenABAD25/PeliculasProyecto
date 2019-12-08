package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modelo.Vehiculo;
import utils.ConeccionDB;


public class VehiculoDao {
	ConeccionDB conexion = new ConeccionDB();
	public boolean insertar(Vehiculo vehiculo) {
		Connection con= conexion.connect();
		String retorno = "";
		PreparedStatement statement = null;
		try {
			String sql = "insert into tbvehiculo (placa, marca) values( ?,?)";
			statement = con.prepareStatement(sql);
			statement.setString(1, vehiculo.getPlaca());
			statement.setString(2, vehiculo.getMarca());
			statement.executeUpdate();

		} catch (SQLException ex) {
			System.out.println(ex.getCause());
		}finally {
			System.out.println(statement.toString());
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			conexion.cerrarConexion();

		}
		return true;
		
	}
	
	public ArrayList<String> obtenerVehiculos() {
		Connection con= conexion.connect();
		//System.out.println(con.toString());
		ArrayList<String> lista = new ArrayList<>();
		try {
			String sql = "SELECT placa, marca FROM tbvehiculo";
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				lista.add(resultSet.getString(1));
			}
			con.close();

			
		} catch (SQLException ex) {
			System.out.println(ex.getSQLState());
		}

		return lista;
	}


    public int actualizarVehiculo(Vehiculo vehiculoUpdate, String placa) {
    	Connection con= conexion.connect();
        int ac = 0;
        String sql = "UPDATE tbvehiculo set placa = ?, marca = ? WHERE placa = ? ;";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, vehiculoUpdate.getPlaca());
            preparedStatement.setString(2, vehiculoUpdate.getMarca());
            preparedStatement.setString(3, placa);
            ac = preparedStatement.executeUpdate();
            con.close();
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
        }
        System.out.println(preparedStatement.toString());
        return ac;
    }
    
public Vehiculo read(String placa) {
		
		Vehiculo unVehiculo = new Vehiculo();
		Connection con = conexion.connect();
		PreparedStatement statement =null;
		ResultSet rs= null;
		String sql="SELECT placa, marca from tbvehiculo where placa = ?;";
		try {
			statement = con.prepareStatement(sql);
			statement.setString(1, placa);
			rs= statement.executeQuery();
			while (rs.next()) {
				unVehiculo.setPlaca(rs.getString(1));
				unVehiculo.setMarca(rs.getString(2));
			}
		} catch (SQLException e) {
		
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			conexion.cerrarConexion();
		}
		System.out.println(unVehiculo.toString());
		return unVehiculo;
	}
public ArrayList<Vehiculo> obtenerVehiculos(String placa) {
	conexion= new ConeccionDB();
	PreparedStatement statement = null;
	ResultSet resultSet = null;
	ArrayList<Vehiculo> lista = new ArrayList<>();
	try {
		String sql = "SELECT placa, marca FROM tbehiculo where placa = ?;";
		statement= conexion.connect().prepareStatement(sql);
		statement.setString(1, placa);
		resultSet= statement.executeQuery();
		while (resultSet.next()) {
			Vehiculo veh = new Vehiculo();
			veh.setPlaca(resultSet.getString(1));
			veh.setMarca(resultSet.getString(2));
			lista.add(veh);
		}
	
	} catch (SQLException ex) {
		ex.printStackTrace();
	}finally {
		try {
			resultSet.close();
			statement.close();
			conexion.cerrarConexion();
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	return lista;
}

public Vehiculo obtenerVehiculo(String placa) {
	conexion= new ConeccionDB();
	PreparedStatement statement = null;
	ResultSet resultSet = null;
	Vehiculo veh = new Vehiculo();
	try {
		String sql = "SELECT placa, marca FROM tbvehiculo where placa = ?;";
		statement= conexion.connect().prepareStatement(sql);
		statement.setString(1, placa);
		resultSet= statement.executeQuery();
		while (resultSet.next()) {
			veh.setPlaca(resultSet.getString(1));
			veh.setMarca(resultSet.getString(2));
		}
	
	} catch (SQLException ex) {
		ex.printStackTrace();
	}finally {
		try {
			resultSet.close();
			statement.close();
			conexion.cerrarConexion();
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	return veh;
}
public ArrayList<Vehiculo> getVehiculos() {
	conexion= new ConeccionDB();
	PreparedStatement statement = null;
	ResultSet resultSet = null;
	ArrayList<Vehiculo> lista = new ArrayList<>();
	try {
		String sql = "SELECT placa, marca FROM tbvehiculo;";
		statement= conexion.connect().prepareStatement(sql);
		resultSet= statement.executeQuery();
		while (resultSet.next()) {
			Vehiculo veh = new Vehiculo();
			veh.setPlaca(resultSet.getString(1));
			veh.setMarca(resultSet.getString(2));
			lista.add(veh);
		}
	
	} catch (SQLException ex) {
		ex.printStackTrace();
	}finally {
		try {
			resultSet.close();
			statement.close();
			conexion.cerrarConexion();
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	return lista;
	}
}
