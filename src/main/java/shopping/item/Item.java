package shopping.item;

public class Item {
	private String sku;
	private Float price;
	private String name;
	
	public Item() {
	}
	
	public Item(String name, Float price) {
		this.price = price;
		this.name = name;
	}
	
	@Override
	public boolean equals(Object obj) {
		Item other = (Item) obj;
		return name.equals(other.name) && price.equals(other.price);
	}
	
	@Override
	public int hashCode() {
		return name.hashCode() + price.hashCode();
	}
	
	@Override
	public String toString() {
		return name;
	}

	public String getName() {
		return name;
	}
	
	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}
}
