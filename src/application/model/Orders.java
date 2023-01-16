package application.model;

public class Orders {
	private int OrderID;
	private int SensorID;
	private String ClientCode;
	private int OrderQuantity;

	public Orders(int orderID, int sensorID, String clientCode, int orderQty) {
		this.OrderID = orderID;
		this.SensorID = sensorID;
		this.ClientCode = clientCode;
		this.OrderQuantity = orderQty;
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
}