package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modelo.Tipo;
import utils.ConexionDB;

public class TipoDAO 
{
	ConexionDB conexion = new ConexionDB();

public boolean insertar(Tipo ti) {
	Connection con= conexion.connect();
	String retorno = "";
	PreparedStatement statement = null;
	try {
		String sql = "insert into tbtipo (numero, descripcion) values( ?,?)";
		statement = con.prepareStatement(sql);
		statement.setInt(1, ti.getNumero());
		statement.setString(2, ti.getDescripcion());
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

public int actualizarTipo(Tipo t, int num) {
	Connection con= conexion.connect();
    int ac = 0;
    String sql = "UPDATE tbtipo set descripcion = ? WHERE numero = ? ;";
    PreparedStatement preparedStatement = null;
    try {
        preparedStatement = con.prepareStatement(sql);
        preparedStatement.setString(1,t.getDescripcion());
        preparedStatement.setInt(2, num);
        ac = preparedStatement.executeUpdate();
        con.close();
    } catch (SQLException e) {
        System.out.println(e.getSQLState());
    }
    System.out.println(preparedStatement.toString());
    return ac;
}

public Tipo read(int numero) {
	
	Tipo untipo = new Tipo();
	Connection con = conexion.connect();
	PreparedStatement statement =null;
	ResultSet rs= null;
	String sql="SELECT numero, descripcion from tbtipo where numero = ?;";
	try {
		statement = con.prepareStatement(sql);
		statement.setInt(1, numero);
		rs= statement.executeQuery();
		while (rs.next()) {
			untipo.setNumero(rs.getInt(1));
			untipo.setDescripcion(rs.getString(2));
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
	System.out.println(untipo);
	return untipo;
}
	public ArrayList<Tipo> getTipos() {
	conexion= new ConexionDB();
	PreparedStatement statement = null;
	ResultSet resultSet = null;
	ArrayList<Tipo> lista = new ArrayList<>();
	try {
		String sql = "SELECT numero, descripcion FROM tbtipo;";
		statement= conexion.connect().prepareStatement(sql);
		resultSet= statement.executeQuery();
		while (resultSet.next()) {
			Tipo ti = new Tipo();
			ti.setNumero(resultSet.getInt(1));
			ti.setDescripcion(resultSet.getString(2));
			lista.add(ti);
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
	public int getNumeroT() {
		Connection con = conexion.connect();
		int idUltimo = 0;
		try {
			String sql = "select max(numero) from tbtipo;";
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


}