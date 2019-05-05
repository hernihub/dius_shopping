package shopping.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

import shopping.Checkout;
import shopping.item.Item;
import shopping.pricing.PricingRule;
import shopping.pricing.concrete.ByBulkPR;
import shopping.pricing.concrete.ByBundlePR;
import shopping.pricing.concrete.ByItemNumberPR;
import shopping.utils.Utils;

public class ShoppingTest {
	
	private List<Item> items = new ArrayList<>();
	private List<PricingRule> pricingRules = new ArrayList<>();
	
	@Before
	public void setupPricingRules() {		
		ByItemNumberPR bnpr = new ByItemNumberPR(items.get(0));				
		ByBulkPR bkpr = new ByBulkPR(items.get(5));		
		ByBundlePR bdpr = new ByBundlePR(items.get(11), items.get(12));
		pricingRules.add(bnpr);
		pricingRules.add(bkpr);
		pricingRules.add(bdpr);
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
	
	
	/**
	 * Good old BNPR as described in problem statement
	 */
	@Test
	public void testCheckoutBNPR() {
		Checkout co = new Checkout(pricingRules.get(0));
		for (Item item: items.subList(0, 4))
		  co.scan(item);
		
		float total = co.getTotalPrice();
		assertEquals(249.00f, total, 0);
	}
	
	/**
	 * What if more special items get scanned? Try to go with multiple of the discount number
	 */
	@Test
	public void testCheckoutBNPR_ThreeSpecialItems() {
		Checkout co = new Checkout(pricingRules.get(0));
		for (Item item: items.subList(0, 4))
		  co.scan(item);
		co.scan(items.get(0));
		co.scan(items.get(0));
		co.scan(items.get(0));
		float total = co.getTotalPrice();
		assertEquals(249.00f + 219.00f, total, 0);
	}
	
	@Test
	public void testCheckoutBKPR() {
		Checkout co = new Checkout(pricingRules.get(1));
		for (Item item: items.subList(4, 11))
		  co.scan(item);
		
		float total = co.getTotalPrice();
		assertEquals(2718.95f, total, 0);
	}
	
	/**
	 * What if more special items get scanned? It doesn't matter, you buy 8 Super iPads, same discount for every one  
	 */
	@Test
	public void testCheckoutBKPR_ThreeSpecialItems() {
		Checkout co = new Checkout(pricingRules.get(1));
		for (Item item: items.subList(4, 11))
		  co.scan(item);
		co.scan(items.get(10));
		co.scan(items.get(10));
		co.scan(items.get(10));
		float total = co.getTotalPrice();
		assertEquals(499.99f*8 + 219.00f, total, 0);
	}
	
	@Test
	public void testCheckoutBDPR() {

		Checkout co = new Checkout(pricingRules.get(2));
		for (Item item: items.subList(11, 14))
		  co.scan(item);
		float total = co.getTotalPrice();
		assertEquals(1949.98f, total, 0);
	}
	
	/**
	 * What if more special items get scanned? It doesn't matter, you buy 8 Super iPads, same discount for every one  
	 */
	@Test
	public void testCheckoutBDPR_ThreeSpecialItems() {

		Checkout co = new Checkout(pricingRules.get(2));
		for (Item item: items.subList(11, 14))
		  co.scan(item);
		co.scan(items.get(11));
		co.scan(items.get(11)); // What if this PR gets two other Macs? Too bad, you pay full price for them
		float total = co.getTotalPrice();
		assertEquals(1949.98f + 1399.99f + 1399.99f, total, 0);
	}

	@Test
	public void testFormatStrings(){
		String prname = "bnpr";
		String r = String.format("%s.%s", prname, "properties");
		assertEquals("bnpr.properties", r);
	}
	
	@Test
	public void testPropertiesLoading(){
		String prname = "bnpr";
		java.util.Properties pop = Utils.getPricingRule(prname);
		assertNotNull(pop);
		String property = (String) pop.get(prname);
		assertNotNull(property);
		assertEquals("3-2", property);
		
		prname = "bkpr";
		pop = Utils.getPricingRule(prname);
		assertNotNull(pop);
		property = ((String) pop.get(prname)).split(PricingRule.PRINCING_RULE_PROPERTY_SEPARATOR)[1];
		float discountPercentage = Float.valueOf(property);
		assertNotNull(property);
		assertEquals("9.091", property);
		assertEquals(9.091f, discountPercentage, 0);
		
		prname = "bdpr";
		pop = Utils.getPricingRule(prname);
		assertNotNull(pop);
		property = (String) pop.get(prname);
		assertNotNull(property);
		assertEquals("vga", property);
	}
	
	@Test
	public void testBNPRTotal(){
		Item item = new Item("Apple TV", 109.50f);
		Item item2 = new Item("Apple TV", 109.50f);
		Item item3 = new Item("Apple TV", 109.50f);
		Item item4 = new Item("VGA adapter", 30.00f);
		//Item itemx = new Item("VGA adapter", 30.00f);
		ByItemNumberPR bnpr = new ByItemNumberPR(item);
		bnpr.scan(item);
		bnpr.scan(item2);
		bnpr.scan(item3);
		bnpr.scan(item4);
		//bnpr.scan(itemx);
		float total = bnpr.getTotal();
		assertEquals(249.00f, total, 0);
	}
	
	@Test
	public void testBKPRTotal(){
		Item item5 = new Item("Super iPad", 549.99f);
		Item item6 = new Item("Super iPad", 549.99f);
		Item item7 = new Item("Super iPad", 549.99f);
		Item item8 = new Item("Super iPad", 549.99f);
		Item item9 = new Item("Super iPad", 549.99f);
		Item item1 = new Item("AppleTV", 109.50f);
		Item item2 = new Item("AppleTV", 109.50f);
		ByBulkPR bnpr = new ByBulkPR(item5);
		bnpr.scan(item5);
		bnpr.scan(item6);
		bnpr.scan(item7);
		bnpr.scan(item8);
		bnpr.scan(item9);
		bnpr.scan(item1);
		bnpr.scan(item2);
		float total = bnpr.getTotal();
		assertEquals(2718.95f, total, 0);
	}
	
	@Test
	public void testBDPRTotal(){
		Item item9 = new Item("Super iPad", 549.99f);
		Item item91 = new Item("Super iPad", 549.99f);
		Item item10 = new Item("MacBook Pro", 1399.99f);
		Item item11 = new Item("VGA adapter", 30.00f);
		ByBundlePR bdpr = new ByBundlePR(item10, item11);
		bdpr.scan(item9);
		bdpr.scan(item10);
		bdpr.scan(item11);
		bdpr.scan(item91);
		float total = bdpr.getTotal();
		assertEquals(2499.97f, total, 0);
	}
	
	
	@Test
	public void testFloatWithTwoDecimals(){
		float total = 45.551f;
		assertEquals(45.55f, Utils.getFloatWithDecimals(total, 2), 0);
	}
}
