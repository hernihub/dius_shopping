package shopping.pricing.concrete;

import java.util.Properties;

import shopping.item.Item;
import shopping.pricing.PricingRule;
import shopping.utils.Utils;

public class ByItemNumberPR extends PricingRule {
	
	/**
	 * The number of items to be bought in order to get the deal number
	 */
	private Integer buyNumber;
	
	/**
	 * The number of items that are actually gonna be charged
	 */
	private Integer dealNumber;
	
	{
		Properties bnprProperties = Utils.getPricingRule(BY_ITEM_PRICING_RULE_NAME);
		setBuyNumber(Integer.parseInt(((String)bnprProperties.get(BY_ITEM_PRICING_RULE_NAME)).split(PRINCING_RULE_PROPERTY_SEPARATOR)[0]));
		setDealNumber(Integer.parseInt(((String)bnprProperties.get(BY_ITEM_PRICING_RULE_NAME)).split(PRINCING_RULE_PROPERTY_SEPARATOR)[1]));
	}
	
	/**
	 * You always instantiate a pricing rule with the special item
	 * @param specialItem
	 */
	public ByItemNumberPR(Item specialItem) {
		super(specialItem);
		itemMap.put(specialItem, 0);
	}

	@Override
	public Float getTotal() {
		float total = 0f;
		int numberOfItemsToCharge = itemMap.get(specialItem).intValue();
		
		if (numberOfItemsToCharge == buyNumber)
			  total = specialItem.getPrice() * dealNumber;
		
		else if (numberOfItemsToCharge > buyNumber) {
			int numSpecials = numberOfItemsToCharge/buyNumber; // Number of specials applied
			int extraSpecialsFullPrice = numberOfItemsToCharge%buyNumber; // Additional special items at full price
			
			total = specialItem.getPrice() * (dealNumber * numSpecials) + extraSpecialsFullPrice * specialItem.getPrice();
		}
		else
		  total = specialItem.getPrice() * numberOfItemsToCharge;
			
		total += getOtherItemsTotal();
		return Utils.getFloatWithDecimals(total, 2);
	}

	public Integer getBuyNumber() {
		return buyNumber;
	}

	public void setBuyNumber(Integer buyNumber) {
		this.buyNumber = buyNumber;
	}

	public Integer getDealNumber() {
		return dealNumber;
	}

	public void setDealNumber(Integer dealNumber) {
		this.dealNumber = dealNumber;
	}

	@Override
	public String toString() {
		return "ByItemNumberPR";
	}
}