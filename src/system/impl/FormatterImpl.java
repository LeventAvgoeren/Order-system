package system.impl;

import java.util.Arrays;
import java.util.Map;

import datamodel.generated.Currency;
import datamodel.generated.Customer;
import system.Formatter;

class FormatterImpl implements Formatter {

    private final Map<Currency, String> CurrencySymbol = Map.of(
            Currency.EUR, "\u20ac", // Unicode: EURO
            Currency.USD, "$", // ASCII: US Dollar
            Currency.GBP, "\u00A3", // Unicode: UK Pound Sterling
            Currency.YEN, "\u00A5", // Unicode: Japanese Yen
            Currency.BTC, "BTC" // no Unicode for Bitcoin
    );

    @Override
    public String fmtPrice(long price, int... fmt) {
        final int ft = fmt.length > 0 ? fmt[0] : 0; // 0 is default format
        switch (ft) {
            case 0:
                return fmtDecimal(price, 2);
            case 1:
                return fmtDecimal(price, 2, " EUR");
            case 2:
                return fmtDecimal(price, 2, "EUR");
            case 3:
                return fmtDecimal(price, 2, CurrencySymbol.get(Currency.EUR));
            case 4:
                return fmtDecimal(price, 2, CurrencySymbol.get(Currency.USD));
            case 5:
                return fmtDecimal(price, 2, CurrencySymbol.get(Currency.GBP));
            case 6:
                return fmtDecimal(price, 0, CurrencySymbol.get(Currency.YEN));
            case 7:
                return fmtDecimal(price, 0);
            //
            default:
                return fmtPrice(price, 0);
        }
    }

    @Override
    public String fmtDecimal(long value, int decimalDigits, String... unit) {
        final String unitStr = unit.length > 0 ? unit[0] : null;
        final Object[][] dec = {
                { "%,d", 1L }, // no decimal digits: 16,000Y
                { "%,d.%01d", 10L },
                { "%,d.%02d", 100L }, // double-digit price: 169.99E
                { "%,d.%03d", 1000L }, // triple-digit unit: 16.999-
        };
        String result;
        String fmt = (String) dec[decimalDigits][0];
        if (unitStr != null && unitStr.length() > 0) {
            fmt += "%s"; // add "%s" to format for unit string
        }
        int decdigs = Math.max(0, Math.min(dec.length - 1, decimalDigits));
        //
        if (decdigs == 0) {
            Object[] args = { value, unitStr };
            result = String.format(fmt, args);
        } else {
            long digs = (long) dec[decdigs][1];
            long frac = Math.abs(value % digs);
            Object[] args = { value / digs, frac, unitStr };
            result = String.format(fmt, args);
        }
        return result;
    }

    @Override
    public String fmtCustomerName(Customer customer, int... fmt) {
        if (customer == null)
            throw new IllegalArgumentException("Customer null.");
        //
        String ln = customer.getLastName();
        String fn = customer.getFirstName();
        String fn1 = fn.substring(0, 1).toUpperCase();
        //
        final int ft = fmt.length > 0 ? fmt[0] : 0; // 0 is default format
        switch (ft) { // 0 is default
            case 0:
                return String.format("%s, %s", ln, fn);
            case 1:
                return String.format("%s %s", fn, ln);
            case 2:
                return String.format("%s, %s.", ln, fn1);
            case 3:
                return String.format("%s. %s", fn1, ln);
            case 4:
                return ln;
            case 5:
                return fn;
            //
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
                return fmtCustomerName(customer, ft - 10).toUpperCase();
            //
            default:
                return fmtCustomerName(customer, 0);
        }
    }

    @Override
    public String fmtCustomerContacts(Customer customer, int... fmt) {
        if (customer == null)
            throw new IllegalArgumentException("Customer null.");
        //
        var clen = customer.getContacts().length;
        final int ft = fmt.length > 0 ? fmt[0] : 0; // 0 is default format
        switch (ft) { // 0 is default
            case 0:
                return String.format("%s", clen > 0 ? customer.getContacts()[0] : "");

            case 1:
                String ext = clen > 1 ? String.format(", (+%d contacts)", clen - 1) : "";
                return String.format("%s%s", fmtCustomerContacts(customer, 0), ext);

            case 2:
                StringBuilder sb = new StringBuilder();
                Arrays.stream(customer.getContacts())
                        .forEach(contact -> sb.append(contact).append(sb.length() > 0 ? ", " : ""));
                return sb.toString();
            //
            default:
                return fmtCustomerContacts(customer, 0);
        }
    }

}
