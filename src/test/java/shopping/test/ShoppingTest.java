package shopping.test;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import shopping.item.Item;
import shopping.pricing.PricingRule;

public class ShoppingTest {
	
	private List<Item> items = new ArrayList<>();
	private List<PricingRule> pricingRules = new ArrayList<>();
	
	@Before
	public void setupPricingRules() {
	}

	@Before
	public void setupItems() {
		Item item1 = new Item("Apple TV", 109.50f);
		Item item2 = new Item("Apple TV", 109.50f);
		Item item3 = new Item("Apple TV", 109.50f);
		Item item4 = new Item("VGA adapter", 30.00f);		
		Item item5 = new Item("Apple TV", 109.50f);
		Item item6 = new Item("Super iPad", 549.99f);
		Item item7 = new Item("Super iPad", 549.99f);
		Item item8 = new Item("Apple TV", 109.50f);
		Item item9 = new Item("Super iPad", 549.99f);
		Item item10 = new Item("Super iPad", 549.99f);
		Item item11 = new Item("Super iPad", 549.99f);		
		Item item12 = new Item("MacBook Pro", 1399.99f);
		Item item13 = new Item("VGA adapter", 30.00f);
		Item item14 = new Item("Super iPad", 549.99f);		
		items.add(item1);
		items.add(item2);
		items.add(item3);
		items.add(item4);
		items.add(item5);
		items.add(item6);
		items.add(item7);
		items.add(item8);
		items.add(item9);
		items.add(item10);
		items.add(item11);
		items.add(item12);
		items.add(item13);
		items.add(item14);
	}
}
