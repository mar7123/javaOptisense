package application.model;

public class Sensor {
	private int SensorID;
	private String VendorCode;
	private String SensorName;
	private String SensorType;
	private int SensorSpeed;
	private int SensorPrice;
	private int SensorStock;

	public Sensor(int id, String vendcode, String name, String type, int speed, int price, int stock) {
		this.SensorID = id;
		this.VendorCode = vendcode;
		this.SensorName = name;
		this.SensorType = type;
		this.SensorSpeed = speed;
		this.SensorPrice = price;
		this.SensorStock = stock;
	}

	public String getSensorID() {
		return this.SensorID + "";
	}

	public String getVendorCode() {
		return this.VendorCode;
	}
	
	public String getSensorName() {
		return this.SensorName;
	}

	public String getSensorType() {
		return this.SensorType;
	}

	public String getSensorSpeed() {
		return this.SensorSpeed + "";
	}

	public String getSensorPrice() {
		return this.SensorPrice + "";
	}

	public String getSensorStock() {
		return this.SensorStock + "";
	}
}