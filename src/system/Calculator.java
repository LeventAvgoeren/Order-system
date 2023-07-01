package system;

import datamodel.generated.TAX;
import datamodel.generated.OrderItem;
import datamodel.generated.Order;

 /**
  * {@link Calculator} is a singelton {@link system} compoment that perfoms calculations.
  */

public interface Calculator {

    long calculateOrderItemValue(OrderItem item);

    long calculateOrderItemVAT(OrderItem item);

    long calculateOrderValue(Order order);

    long calculateOrderVAT(Order order);

    long calculateVAT(long grossValue, TAX taxRate);

        /**
   * Return taxRate as double value, e.g. 19.0(%) for taxRate: TAX.GER_VAT
   * or 7.0(%) for taxRate: TAX.GER_VAT_REDUCED.
   *
   * @param taxRate applicable tax rate in percent.
   * @return taxRate as double value.
   */
  double value(TAX taxRate);

}