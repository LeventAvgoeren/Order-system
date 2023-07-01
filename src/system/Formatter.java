package system;

import datamodel.generated.Customer;

/**
 * {@link Formatter} is a singleton {@link system} compoment that perorms formatting operations.
 */

public interface Formatter {

    String fmtCustomerName(Customer customer, int... fmt);
    
    String fmtCustomerContacts(Customer customer, int... fmt);

    String fmtPrice(long price, int... fmt);

    String fmtDecimal(long value, int decimalDigits, String... unit); 
    
}
