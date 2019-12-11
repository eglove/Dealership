package objects;

public class Vehicle {
	/*
	 * Bidding Math:
	 * 
	 * When the vehicle is created a 'discount' price is set at (price - (price *
	 * .1)), 10%. That becomes the mimimum (bid) after the vehicle has been in the
	 * inventory for > 120 days. When a user places a bid the new 'discount' becomes
	 * that bid and the (bid) or minimum bid becomes discount + 1.
	 */
	private String date; // 0 Date posted to inventory
	private String price; // 1 Original price of vehicle
	private String make; // 2 Make
	private String model; // 3 Model
	private String carDescription; // 4 Description of vehicle
	private String mainImage; // 5 Image to be displayed on homepage for vehicle.
	private String year; // 6 Year of vehicle
	private String daysInInventory; // 7 Days vehicle has been in inventory.
	private String discount; // 8 Current price of vehicle if it has been in inventory > 120 days
	private String used; // 9 // 'New' or 'Used'
	private String kilosRan; // 10 Kilometers vehicle has ran.
	private String bid; // 11 Current minimum bid on vehicle
	private String vin; // 12 Unique identifier for vehicle

	public Vehicle() {
	}

	public Vehicle(String date, String price, String make, String model, String carDescription, String mainImage,
			String year, String daysInInventory, String discount, String used, String kilosRan, String bid, String vin) {

		this.date = date;
		this.price = price;
		this.make = make;
		this.model = model;
		this.carDescription = carDescription;
		this.mainImage = mainImage;
		this.year = year;
		this.daysInInventory = daysInInventory;
		this.discount = discount;
		this.used = used;
		this.kilosRan = kilosRan;
		this.bid = bid;
		this.vin = vin;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getCarDescription() {
		return carDescription;
	}

	public void setCarDescription(String carDescription) {
		this.carDescription = carDescription;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getMainImage() {
		return mainImage;
	}

	public void setMainImage(String mainImage) {
		this.mainImage = mainImage;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getDaysInInventory() {
		return daysInInventory;
	}

	public void setDaysInInventory(String daysInInventory) {
		this.daysInInventory = daysInInventory;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getUsed() {
		return used;
	}

	public void setUsed(String used) {
		this.used = used;
	}

	public String getKilosRan() {
		return kilosRan;
	}

	public void setKilosRan(String kilosRan) {
		this.kilosRan = kilosRan;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}
}
