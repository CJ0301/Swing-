package Entity;

public class Car {
	String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	String name;
	String type;
	String price;
	float insure;
	String image;
	String desp;

	public String getDesp() {
		return desp;
	}

	public void setDesp(String desp) {
		this.desp = desp;
	}

	public Car(String name, String type, String price, String image, float insure) {
		super();
		this.name = name;
		this.type = type;
		this.price = price;
		this.insure = insure;
		this.image = image;
	}

	public Car() {

	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public float getInsure() {
		return insure;
	}

	public void setInsure(float insure) {
		this.insure = insure;
	}

}
