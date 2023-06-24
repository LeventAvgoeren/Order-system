package system.impl;

import java.util.Map;

import datamodel.generated.Article;
import datamodel.generated.Order;
import datamodel.generated.OrderItem;
import datamodel.generated.TAX;
import system.Calculator;

class CalculatorImpl implements Calculator {

    /**
     * Map TAX enum to tax rate as number.
     */
    final Map<TAX, Double> taxRateMapper = Map.of(
            TAX.TAXFREE, 0.0, // tax free rate
            TAX.GER_VAT, 19.0, // German VAT tax (MwSt) 19.0%
            TAX.GER_VAT_REDUCED, 7.0 // German reduced VAT tax (MwSt) 7.0%
    );

    @Override
    public long calculateOrderValue(Order order) {
        long preis = 0;
        if (order == null)
            throw new IllegalArgumentException("argument order is null.");

        for (OrderItem orders : order.getItems()) {
            long zwischenpreis = calculateOrderItemValue(orders);
            preis += zwischenpreis;
        }

        return preis;
    }

    @Override
    public long calculateOrderItemValue(OrderItem item) {
        if (item == null) {
            throw new IllegalArgumentException("argument item is null.");
        }
        Article artic = item.getArticle();
        long preis = artic.getUnitPrice();

        int anzahl = item.getUnitsOrdered();
        long gesamtPreis = preis * anzahl;

        return gesamtPreis;
    }

    @Override
    public long calculateOrderVAT(Order order) {
        long result = 0;
        if (order == null)
            throw new IllegalArgumentException("argument order is null.");

        for (OrderItem item : order.getItems()) {
            long zwischenergebniss = calculateOrderItemVAT(item);
            result += zwischenergebniss;
        }
        return result;
    }

    @Override
    public long calculateOrderItemVAT(OrderItem item) {
        if (item == null) {
            throw new IllegalArgumentException("argument order is null.");
        }
        double steuersatz = taxRateMapper.get(item.getArticle().getTax()) / 100;
        long bruttoWert = calculateOrderItemValue(item);
        long result = Math.round(bruttoWert - (bruttoWert / (1 + steuersatz)));

        return result;
    }

    @Override
    public long calculateVAT(long grossValue, TAX tax) {
        if (tax == null) {
            throw new IllegalArgumentException("argument taxRate is null.");
        }
        if(grossValue<0){
            return 0;
        }
        double steuren = taxRateMapper.get(tax);
        return Math.round((grossValue * steuren) / (100.0 + steuren));
    }

    @Override
    public double value(TAX taxrate) {
        if (taxrate == null) {
            throw new IllegalArgumentException("argument taxRate is null.");
        }
        return taxRateMapper.get(taxrate);
    }

}
