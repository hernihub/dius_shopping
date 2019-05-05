package shopping.pricing.concrete;

import java.util.ArrayList;
import java.util.List;

import shopping.item.Item;
import shopping.pricing.PricingRule;

public class ByBundlePR extends PricingRule {
		
	/**
	 * The items that are gonna have a percentage of 100% discounted
	 */
	private List<Item> bundledItems = new ArrayList<>();
		
	/**
	 * You create this type of PR with N number of bundled items
	 * Afterwards, you can only add to the otherItems list
	 * @param specialItem
	 * @param bundledItem
	 */
	public ByBundlePR(Item specialItem, Item... bundledItem) {
		super(specialItem);
		itemMap.put(specialItem, 0);
		for (Item item : bundledItem)
		  bundledItems.add(item);
	}	

	public List<Item> getBundledItems() {
		return bundledItems;
	}

	public void setBundledItems(List<Item> bundledItems) {
		this.bundledItems = bundledItems;
	}

	@Override
	public void scan(Item item) {
		if (item != specialItem && !bundledItems.contains(item))
			otherItems.add(item);
		else if (item == specialItem && itemMap.get(specialItem).intValue() == 0)
			itemMap.put(item, 1);
		else if (item == specialItem && itemMap.get(specialItem).intValue() == 1)
			otherItems.add(item);
	}

	@Override
	public boolean containsItem(Item item) {
		return item.equals(specialItem) || bundledItems.contains(item) || otherItems.contains(item);
	}

	@Override
	public String toString() {
		return "ByBundlePR";
	}
}
