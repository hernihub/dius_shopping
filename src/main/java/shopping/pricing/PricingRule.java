package shopping.pricing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import shopping.item.Item;
import shopping.utils.Utils;

public abstract class PricingRule {
	public static final String BY_ITEM_PRICING_RULE_NAME = "bnpr";
	public static final String BY_BUNDLE_PRICING_RULE_NAME = "bdpr";
	public static final String BY_BULK_PRICING_RULE_NAME = "bkpr";
	public static final String PRINCING_RULE_PROPERTY_SEPARATOR = "-";
	
	protected float discountPercentage = 0;
	protected Item specialItem = null;
	protected List<Item> otherItems = new ArrayList<>();
	protected HashMap<Item, Integer> itemMap = new HashMap<>();
	
	public PricingRule(Item specialItem) {
		setSpecialItem(specialItem);
	}

	public void scan(Item item) {
		if (itemMap.containsKey(item)) {
			itemMap.put(item, itemMap.get(item) + 1);
		} else
			otherItems.add(item);
	}
	
	protected float getOtherItemsTotal() {
		return (float) (otherItems.stream().mapToDouble(Item::getPrice).sum());
	}
	protected void setSpecialItem(Item item) {
		this.specialItem = item;
	}
	
	protected float getDiscountPercentage() {
		return discountPercentage;
	}
	protected void setDiscountPercentage(float discountPercentage) {
		this.discountPercentage = discountPercentage;
	}
	public boolean containsItem(Item item) {
		return specialItem.equals(item);
	}
	public Float getTotal() {
		return Utils.getFloatWithDecimals(specialItem.getPrice() + getOtherItemsTotal(), 2);
	}
	
	@Override
	public abstract String toString();
}
