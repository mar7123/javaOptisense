package application.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import application.DBConnection;

public class Orders {
	private int OrderID;
	private int SensorID;
	private String ClientCode;
	private String VendorName;
	private String SensorName;
	private int OrderQuantity;

	public Orders(int orderID, int sensorID, String clientCode, int orderQty) {
		this.OrderID = orderID;
		this.SensorID = sensorID;
		this.ClientCode = clientCode;
		this.OrderQuantity = orderQty;
		try {
			Connection c = DBConnection.getKoneksi();
			PreparedStatement statement = c.prepareStatement("SELECT DISTINCT CompanyName, SensorName FROM companies e inner join orders f on e.CompanyCode = f.ClientCode inner join sensors g on f.SensorID = g.SensorID WHERE SensorID = ? and CompanyCode = ?");
			statement.setInt(1, this.SensorID);
			statement.setString(2, this.ClientCode);
			ResultSet rs = statement.executeQuery();
			this.VendorName = rs.getString("CompanyName");
			this.SensorName = rs.getString("SensorName");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getOrderID() {
		return this.OrderID + "";
	}

	public String getSensorID() {
		return this.SensorID + "";
	}
	
	public String getClientCode() {
		return this.ClientCode;
	}

	public String getOrderQuantity() {
		return this.OrderQuantity + "";
	}
	
	public int getOrderQuantityInt() {
		return this.OrderQuantity;
	}
	
	public String getVendorName() {
		return this.VendorName;
	}
	
	public String getSensorName() {
		return this.SensorName;
	}
}