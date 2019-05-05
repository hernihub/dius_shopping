package shopping.pricing.concrete;

import java.util.Properties;

import shopping.item.Item;
import shopping.pricing.PricingRule;
import shopping.utils.Utils;

public class ByBulkPR extends PricingRule {
	private Integer bulkNumber = 0;
	
	{
		Properties bkprProperties = Utils.getPricingRule(BY_BULK_PRICING_RULE_NAME);
		setBulkNumber(Integer.parseInt(((String)bkprProperties.get(BY_BULK_PRICING_RULE_NAME)).split(PRINCING_RULE_PROPERTY_SEPARATOR)[0]));		
		String floatString = ((String)bkprProperties.get(BY_BULK_PRICING_RULE_NAME)).split(PRINCING_RULE_PROPERTY_SEPARATOR)[1];
		setDiscountPercentage(Float.parseFloat(floatString));
	}
	
	/**
	 * 
	 * @param specialItem
	 */
	public ByBulkPR(Item specialItem) {
		super(specialItem);
		itemMap.put(specialItem, 0);
	}

	@Override
	public Float getTotal() {
		float total = 0f;
		int numberOfItemsToCharge = itemMap.get(specialItem).intValue();
		
		if (numberOfItemsToCharge > bulkNumber) {
			float newSpecialItemPrice = specialItem.getPrice() * (discountPercentage/100);
			newSpecialItemPrice = specialItem.getPrice() - newSpecialItemPrice;
			total = newSpecialItemPrice * numberOfItemsToCharge;
		} else
			total = specialItem.getPrice() * numberOfItemsToCharge;
		
		total += getOtherItemsTotal();
		
		return Utils.getFloatWithDecimals(total, 2);
	}

	public Integer getBulkNumber() {
		return bulkNumber;
	}

	public void setBulkNumber(Integer bulkNumber) {
		this.bulkNumber = bulkNumber;
	}

	@Override
	public String toString() {
		return "ByBulkPR";
	}
}
