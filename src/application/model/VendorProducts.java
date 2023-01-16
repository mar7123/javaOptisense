package application.model;

public class VendorProducts {
	private String CompanyCode;
	private int SensorID;
	private String CompanyName;
	private String SensorName;
	private int SensorStock;

	public VendorProducts(String companyCode, int sensorID, String companyName, String sensorName, int sensorStock) {
		this.CompanyCode = companyCode;
		this.SensorID = sensorID;
		this.CompanyName = companyName;
		this.SensorName = sensorName;
		this.SensorStock = sensorStock;
	}

	public String getCompanyCode() {
		return CompanyCode;
	}

	public int getSensorID() {
		return SensorID;
	}

	public String getCompanyName() {
		return CompanyName;
	}

	public String getSensorName() {
		return SensorName;
	}

	public int getSensorStock() {
		return SensorStock;
	}
}