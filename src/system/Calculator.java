package system;

import datamodel.generated.Order;
import datamodel.generated.OrderItem;
import datamodel.generated.TAX;

public interface Calculator {

public long calculateOrderValue(final Order order);
    

public long calculateOrderItemValue(final OrderItem item);


public long calculateOrderVAT(final Order order);


public long calculateOrderItemVAT(final OrderItem item);


public long calculateVAT(final long grossValue, final TAX tax) ;

public double value(TAX taxrate);
}
