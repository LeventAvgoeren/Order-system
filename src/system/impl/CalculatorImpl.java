package system.impl;



import system.Calculator;
import datamodel.generated.Order;
import datamodel.generated.OrderItem;
import datamodel.generated.TAX;

import java.util.Map;

class CalculatorImpl implements Calculator {

    /**
     * Calculate value of an order, which is comprised of the value of each
     * ordered item. The value of each item is calculated with
     * calculateOrderItemValue(item).
     *
     * @param order to calculate value for.
     * @return value of order.
     */
    public long calculateOrderValue(final Order order) {
        if (order == null)
            throw new IllegalArgumentException("argument order is null.");
        long value = 0;
        for (OrderItem oi : order.getItems()) {
            value += calculateOrderItemValue(oi);
        }
        return value;
    }

    /**
     * Calculate value of an order item, which is calculated by:
     * article.unitPrice * number of units ordered.
     *
     * @param item to calculate value for.
     * @return value of item.
     */
    public long calculateOrderItemValue(OrderItem item) {
    if (item == null) {
        throw new IllegalArgumentException("argument item is null.");
    }
    
    // Implement the calculation logic to calculate the value of the order item
    long value = item.getArticle().getUnitPrice() * item.getUnitsOrdered();
    
    return value;
}

    /**
     * Calculate VAT of an order, which is comprised of the VAT of each
     * ordered item. The VAT of each item is calculated with
     * calculateOrderItemVAT(item).
     *
     * @param order to calculate VAT tax for.
     * @return VAT calculated for order.
     */
    public long calculateOrderVAT(final Order order) {
        if (order == null)
            throw new IllegalArgumentException("argument order is null.");

        long value = 0;
        for (OrderItem oi : order.getItems()) {
            value += calculateOrderItemVAT(oi);
        }
        return value;
    }

    /**
     * Calculate the included VAT of an order item calculated by the
     * applicable tax rate and the calculated order item value.
     *
     * @param item to calculate VAT for.
     * @return VAT for ordered item.
     */
    public long calculateOrderItemVAT(final OrderItem item) {
     if (item == null) {
        throw new IllegalArgumentException("argument order is null.");
    }  

    // Rest of the code remains the same
    double vatPercentage = 0;
    if (item.getArticle().getTax() == TAX.GER_VAT) {
        vatPercentage = 0.19;
    }
    if (item.getArticle().getTax() == TAX.GER_VAT_REDUCED) {
        vatPercentage = 0.07;
    }

    long priceInCent = item.getArticle().getUnitPrice() * item.getUnitsOrdered();
    double priceInEuro = (double) priceInCent / 100;
    double nettoPreis = priceInEuro / (1.0 + vatPercentage);
    double mehrwertsteuer = nettoPreis * vatPercentage;

    return Math.round(mehrwertsteuer * 100);
}

    final Map<TAX, Double> taxRateMapper = Map.of(
            TAX.TAXFREE, 0.0,                    // tax free rate
            TAX.GER_VAT, 0.19,                   // German VAT tax (MwSt) 19.0%
            TAX.GER_VAT_REDUCED, 0.07            // German reduced VAT tax (MwSt) 7.0%
    );

    /**
     * Calculate included VAT (Value-Added Tax) from a gross price/value based on
     * a tax rate (VAT is called "Mehrwertsteuer" (MwSt.) in Germany).
     *
     * @param grossValue value that includes the tax.
     * @param tax        applicable tax rate as a TAX enum value.
     * @return tax included in gross value.
     */
    public long calculateVAT(final long grossValue, final TAX tax) {
        if (tax == null) {
            throw new IllegalArgumentException("argument taxRate is null.");
        } else if(grossValue < 0){
            return 0;
        }

        double taxRate = taxRateMapper.get(tax);
        double netValue = grossValue / (1.0 + taxRate);
        double calculatedTax = grossValue - netValue;

        return Math.round(calculatedTax);
    }

    /**
     * Get the tax rate as a decimal value for the provided TAX enum value.
     *
     * @param tax the TAX enum value.
     * @return the tax rate as a decimal value.
     */
    public double value(final TAX tax) {
        if (tax == null) {
            throw new IllegalArgumentException("argument taxRate is null.");
        }

        return Math.round(taxRateMapper.get(tax) * 100);
    }
}
