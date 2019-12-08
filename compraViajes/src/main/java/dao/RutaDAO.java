package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modelo.Geografia;
import modelo.Ruta;
import utils.ConexionDB;

public class RutaDAO {
	ConexionDB conexion = new ConexionDB();
	public boolean insertar(Ruta r) {
		System.out.println("RUEBN "+r);
		Connection con = conexion.connect();
		String retorno = "";
		PreparedStatement statement = null;
		try {
			String sql = //"insert into ticket (numero, fInicio, tiempo, valor, placaVehiculo) values(?,?,?,?,?)";
			"insert into tbruta (numero,descripcion,valor,origen,destino) values(?,?,?,?,?)";
			statement = con.prepareStatement(sql);
			statement.setInt(1,r.getId());
			statement.setString(2,r.getDescripcion());
			statement.setDouble(3,r.getValor());
			statement.setInt(4,r.getOrigen().getId());
			statement.setInt(5,r.getDestino().getId());
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

	public boolean editarR(Ruta g,int num) {
		boolean retorno = false;
		Connection con = conexion.connect();
		String sql = "UPDATE tbgeografia set descripcion=?,valor=? WHERE numero = ? ;";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1,g.getDescripcion());
			preparedStatement.setDouble(2, g.getValor());
			preparedStatement.setInt(3,num);
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
	public int getNumeroR() {
		Connection con = conexion.connect();
		int idUltimo = 0;
		try {
			String sql = "select max(numero) from tbruta;";
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
public Ruta read(int numero) {
		
		Ruta ge = new Ruta();
		Connection con = conexion.connect();
		PreparedStatement statement =null;
		ResultSet rs= null;
		String sql="SELECT numero, descripcion, valor, origen,destino,(select descripcion from tbgeografia where numero=origen),(select descripcion from tbgeografia where numero=destino) from tbruta where numero =?";//
				//"SELECT numero, fInicio, fFin, tiempo, valor from ticket where numero =?;";
	
		try {
			statement = con.prepareStatement(sql);
			statement.setInt(1, numero);
			rs= statement.executeQuery();
			while (rs.next()) {
				ge.setId(rs.getInt(1));
				ge.setDescripcion(rs.getString(2));
				ge.setValor(rs.getDouble(3));
				Geografia o=new Geografia();
				o.setId(rs.getInt(4));
				o.setDescripcion(rs.getString(6));
				Geografia d=new Geografia();
				d.setId(rs.getInt(5));
				d.setDescripcion(rs.getString(7));
				ge.setOrigen(o);
				ge.setDestino(d);
	
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
		System.out.println(ge);
		return ge;
	}
	
public ArrayList<Ruta> getR() {
	conexion= new ConexionDB();
	PreparedStatement statement = null;
	ResultSet resultSet = null;
	ArrayList<Ruta> lista = new ArrayList<>();
	try {
		String sql = "SELECT numero,descripcion,valor,origen,destino FROM tbruta;";
		statement= conexion.connect().prepareStatement(sql);
		resultSet= statement.executeQuery();
		while (resultSet.next()) {
			Ruta geo = new Ruta();
			geo.setId(resultSet.getInt(1));
			geo.setDescripcion(resultSet.getString(2));
			geo.setValor(resultSet.getDouble(3));
			Geografia o = new  Geografia();
			o.setId(resultSet.getInt(4));
			Geografia d = new  Geografia();
			d.setId(resultSet.getInt(5));
			geo.setOrigen(o);
			geo.setDestino(d);
			lista.add(geo);
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
