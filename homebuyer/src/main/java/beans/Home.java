package beans;

public class Home {
	
	private int id;
	private String address;
	private int sqft;
	private int rooms;
	private int bath;
	private int price;
	
	
	
	@Override
	public String toString() {
		return "Home [id=" + id + ", address=" + address + ", sqft=" + sqft + ", rooms=" + rooms + ", bath=" + bath
				+ ", price=" + price + "]";
	}
	public Home() {
		super();
	}
	public Home(int id, String address, int sqft, int rooms, int bath, int price) {
		super();
		this.id = id;
		this.address = address;
		this.sqft = sqft;
		this.rooms = rooms;
		this.bath = bath;
		this.price = price;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getSqft() {
		return sqft;
	}
	public void setSqft(int sqft) {
		this.sqft = sqft;
	}
	public int getRooms() {
		return rooms;
	}
	public void setRooms(int rooms) {
		this.rooms = rooms;
	}
	public int getBath() {
		return bath;
	}
	public void setBath(int bath) {
		this.bath = bath;
	}
	
}