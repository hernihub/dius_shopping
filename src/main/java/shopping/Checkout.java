package shopping;

import shopping.item.Item;
import shopping.pricing.PricingRule;

/**
 * Facade for a checkout at the registry
 * @author Hernán Camilo
 *
 */
public class Checkout {
	
	private PricingRule pricingRule = null;
	public Checkout(PricingRule pricingRule) {
		this.pricingRule = pricingRule;
	}

	public float getTotalPrice() {
		return pricingRule.getTotal();
	}
	
	public void scan(Item item) {
		pricingRule.scan(item);
	}
}
