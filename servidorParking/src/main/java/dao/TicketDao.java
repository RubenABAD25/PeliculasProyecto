package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.transform.Result;

import modelo.Ticket;
import modelo.Vehiculo;
import utils.ConeccionDB;

public class TicketDao {
 ConeccionDB conexion = new ConeccionDB();
 private VehiculoDao vdao;
	public boolean insertar(Ticket ticket) {
		System.out.println("RUEBN "+ticket.toString());
		Connection con = conexion.connect();
		String retorno = "";
		PreparedStatement statement = null;
		try {
			String sql = //"insert into ticket (numero, fInicio, tiempo, valor, placaVehiculo) values(?,?,?,?,?)";
			"insert into tbticket (numero, fecha,horaInicio,horaFin, placaVehiculo) values(?,?,?,?,?)";
			statement = con.prepareStatement(sql);
			statement.setInt(1, ticket.getNumero());
			statement.setString(2, "" + ticket.getFecha());
			statement.setString(3, ""+ticket.getHoraInicio());
			statement.setString(4, ticket.getHoraFin());
			statement.setString(5, ticket.getUveh().getPlaca());
			statement.executeUpdate();

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} finally {

			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			conexion.cerrarConexion();
		}
		System.out.println(statement.toString());
		return true;

	}

	public boolean editarTicket(Ticket ticket) {
		boolean retorno = false;
		Connection con = conexion.connect();
		String sql = "UPDATE tbticket set tiempo=?,valor=? WHERE numero = ? ;";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setInt(1, ticket.getTiempo());
			preparedStatement.setDouble(2, ticket.getValor());
			preparedStatement.setInt(3, ticket.getNumero());
			preparedStatement.executeUpdate();
			
			retorno = true;
		} catch (SQLException e) {
			System.out.println(e.getSQLState());
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			conexion.cerrarConexion();
		}
		System.out.println(preparedStatement.toString());
		return retorno;
	}

	public ArrayList<String> obtenerTickets() {
		Connection con = conexion.connect();
		// System.out.println(con.toString());
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

	public int getNumeroTicket() {
		Connection con = conexion.connect();
		int idUltimo = 0;
		try {
			String sql = "select max(numero) from tbticket;";
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			String id = "";
			while (resultSet.next()) {
				id = resultSet.getString(1);
			}

			idUltimo = Integer.parseInt(id);

			if (idUltimo == 0) {
				return 1;
			} else {
				idUltimo++;
			}

			con.close();

		} catch (SQLException ex) {
			System.out.println(ex.getSQLState());
		}
		System.out.println("ULTIMO NUMERO "+idUltimo);
		conexion.cerrarConexion();
		return idUltimo;

	}


	public Ticket read(int numeroT) {
		
		Ticket unticket = new Ticket();
		/*int numero, t=0;
		Date fechaInicio=null;
		Date fechaFin=null;
		Double valorT=0.0;*/
		Connection con = conexion.connect();
		PreparedStatement statement =null;
		ResultSet rs= null;
		String sql="SELECT numero, fecha, horaInicio, horaFin, tiempo, valor, placaVehiculo,(select marca from tbvehiculo where placa=placaVehiculo) from tbticket where numero =?";//
				//"SELECT numero, fInicio, fFin, tiempo, valor from ticket where numero =?;";
	
		try {
			statement = con.prepareStatement(sql);
			statement.setInt(1, numeroT);
			rs= statement.executeQuery();
			while (rs.next()) {
				unticket.setNumero(rs.getInt(1));
				unticket.setFecha(rs.getString(2));
				unticket.setHoraInicio(rs.getString(3));
				unticket.setHoraFin(rs.getString(4));
				unticket.setTiempo(rs.getInt(5));
				unticket.setValor(rs.getDouble(6));
				Vehiculo unVehiculo=new Vehiculo();
				unVehiculo.setPlaca(rs.getString(7));
				unVehiculo.setMarca(rs.getString(8));
				unticket.setUveh(unVehiculo);
	
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
		System.out.println(unticket.toString());
		return unticket;
	}
	
	public ArrayList<Ticket> obtenerTicket(String fecha) {
		System.out.println("FECHA "+fecha);
		conexion= new ConeccionDB();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		ArrayList<Ticket> lista = new ArrayList<>();
		try {
			String sql = "SELECT numero,fecha,horaInicio,horaFin,tiempo,valor,placaVehiculo FROM tbticket where fecha = ?;";
			statement= conexion.connect().prepareStatement(sql);
			statement.setString(1, fecha);
			resultSet= statement.executeQuery();
			while (resultSet.next()) {
				Ticket t = new Ticket();

				t.setNumero(resultSet.getInt(1));
				t.setFecha(resultSet.getString(2));
				t.setHoraInicio(resultSet.getString(3));
				t.setHoraFin(resultSet.getString(4));
				t.setTiempo(resultSet.getInt(5));
				t.setValor(resultSet.getDouble(6));
				Vehiculo aux = new Vehiculo();
				aux.setPlaca(resultSet.getString(7));
				t.setUveh(aux);
				lista.add(t);
				 
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
		System.out.println(lista.size()+" SABIAS QUE?");

		return lista;
	}
}
