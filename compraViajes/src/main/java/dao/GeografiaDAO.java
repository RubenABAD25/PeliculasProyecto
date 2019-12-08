package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modelo.Geografia;
import modelo.Tipo;
import utils.ConexionDB;

public class GeografiaDAO {
	ConexionDB conexion = new ConexionDB();
	public boolean insertar(Geografia g) {
		System.out.println("RUEBN "+g);
		Connection con = conexion.connect();
		String retorno = "";
		PreparedStatement statement = null;
		try {
			String sql = //"insert into ticket (numero, fInicio, tiempo, valor, placaVehiculo) values(?,?,?,?,?)";
			"insert into tbgeografia (numero, descripcion,padre,tipoId) values(?,?,?,?)";
			statement = con.prepareStatement(sql);
			statement.setInt(1, g.getId());
			statement.setString(2,g.getDescripcion());
			statement.setInt(3, g.getPadre());
			statement.setInt(4, g.getUnTipo().getNumero());
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

	public boolean editarG(Geografia g,int num) {
		boolean retorno = false;
		Connection con = conexion.connect();
		String sql = "UPDATE tbgeografia set descripcion=?,padre=? WHERE numero = ? ;";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1,g.getDescripcion());
			preparedStatement.setInt(2, g.getPadre());
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
	public int getNumeroG() {
		Connection con = conexion.connect();
		int idUltimo = 0;
		try {
			String sql = "select max(numero) from tbgeografia;";
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
public Geografia read(int numero) {
		
		Geografia ge = new Geografia();
		Connection con = conexion.connect();
		PreparedStatement statement =null;
		ResultSet rs= null;
		String sql="SELECT numero, descripcion, padre, tipoId,(select descripcion from tbtipo where numero=tipoId) from tbgeografia where numero =?";//
				//"SELECT numero, fInicio, fFin, tiempo, valor from ticket where numero =?;";
	
		try {
			statement = con.prepareStatement(sql);
			statement.setInt(1, numero);
			rs= statement.executeQuery();
			while (rs.next()) {
				ge.setId(rs.getInt(1));
				ge.setDescripcion(rs.getString(2));
				ge.setPadre(rs.getInt(3));
				Tipo ti=new Tipo();
				ti.setNumero(rs.getInt(4));
				ti.setDescripcion(rs.getString(5));;
				ge.setUnTipo(ti);
	
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
	
public ArrayList<Geografia> getG() {
	conexion= new ConexionDB();
	PreparedStatement statement = null;
	ResultSet resultSet = null;
	ArrayList<Geografia> lista = new ArrayList<>();
	try {
		String sql = "SELECT numero, descripcion, padre, tipoId FROM tbgeografia;";
		statement= conexion.connect().prepareStatement(sql);
		resultSet= statement.executeQuery();
		while (resultSet.next()) {
			Geografia geo = new Geografia();
			geo.setId(resultSet.getInt(1));
			geo.setDescripcion(resultSet.getString(2));
			geo.setPadre(resultSet.getInt(3));
			Tipo t = new  Tipo();
			t.setNumero(resultSet.getInt(4));
			geo.setUnTipo(t);
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
