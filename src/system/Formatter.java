package system;


import datamodel.generated.Customer;


public interface Formatter {

public String fmtPrice(final long price, final int... fmt);

public String fmtDecimal(final long value, final int decimalDigits, final String... unit);

public String fmtCustomerName(final Customer customer, final int... fmt);

public String fmtCustomerContacts(final Customer customer, final int... fmt) ;


}
