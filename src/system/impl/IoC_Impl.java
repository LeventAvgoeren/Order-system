package system.impl;

import datamodel.generated.Customer;
import datamodel.generated.Order;
import system.*;
import system.impl.PrinterImpl;

/**
 * Implementation class of the {@link system.IoC} interface.
 * <p>
 * "Inversion-of-Control" (IoC) container creates and contains system component objects
 * such as {@link Calculator}, {@link DataStore}, {@link Formatter} and {@link Printer}.
 * </p>
 * 
 * @version <code style=color:green>{@value application.package_info#Version}</code>
 * @author <code style=color:blue>{@value application.package_info#Author}</code>
 */

public class IoC_Impl implements IoC {

    /**
     * Singleton instance of IoC component.
     */
    private final static IoC iocSingleton = new IoC_Impl();

    /**
     * Singleton instance of DataStore component.
     */
    private final DataStore dataStore;

    /**
     * Singleton instance of Calculator component.
     */
    private final Calculator calculator;


    /**
     * Singleton instance of Formatter component.
     */
    private final Formatter formatter;

    /**
     * Singleton instance of Printer component.
     */
    private final Printer printer;


    /**
     * Private constructor to implement Singleton pattern of IoC instance.
     */
    private IoC_Impl() {
        this.calculator = new CalculatorImpl();    // replace with new class CalculatorImpl.java
        this.formatter = new FormatterImpl();      // replace with new class FormatterImpl.java
        this.dataStore = new DataStoreImpl();
        //
        // inject dependencies into PrinterImpl constructor
        this.printer = new PrinterImpl(calculator, formatter);
    }


    /**
     * IoC component getter.
     *  
     * @return reference to IoC singleton instance. 
     */
    public static IoC getInstance() {
        return iocSingleton;
    }


    /**
     * DataStore component getter.
     *  
     * @return reference to DataStore singleton instance. 
     */
    @Override
    public DataStore getDataStore() {
        return dataStore;
    }


    /**
     * Calculator component getter.
     *  
     * @return reference to Calculator singleton instance. 
     */
    @Override
    public Calculator getCalculator() {
        return calculator;
    }


    /**
     * Formatter component getter.
     *  
     * @return reference to Formatter singleton instance. 
     */
    @Override
    public Formatter getFormatter() {
        return formatter;
    }


    /**
     * Printer component getter.
     *  
     * @return reference to Printer singleton instance. 
     */
    @Override
    public Printer getPrinter() {
        return printer;
    }
    @Override
    public DataFactory createDataFactory(DataStore dataStore) { 
        return new MockDataFactoryImpl(dataStore);
    }


    /*
     * Private methods used in handouts.
     */

    /**
     * REPLACE with OWN CLASS: CalculatorImpl.java.
     * Return dummy-Calculator instance that returns 0 for all calculations.
     * 
     * @return dummy-Calculator instance that returns 0 for all calculations.
     */
    private Calculator dummyCalculator() {
        var dummy = new Calculator() {

            @Override
            public long calculateOrderItemValue(datamodel.generated.OrderItem item) {
                // TODO Auto-generated method stub
                return 0;
            }

            @Override
            public long calculateOrderItemVAT(datamodel.generated.OrderItem item) {
                // TODO Auto-generated method stub
                return 0;
            }

            @Override
            public long calculateOrderValue(datamodel.generated.Order order) {
                // TODO Auto-generated method stub
                return 0;
            }

            @Override
            public long calculateOrderVAT(datamodel.generated.Order order) {
                // TODO Auto-generated method stub
                return 0;
            }

            @Override
            public long calculateVAT(long grossValue, datamodel.generated.TAX taxRate) {
                // TODO Auto-generated method stub
                return 0;
            }

            @Override
            public double value(datamodel.generated.TAX taxRate) {
                // TODO Auto-generated method stub
                return 0;
            }
        };
        return dummy;
    }


    /**
     * REPLACE with OWN CLASS: FormatterImpl.java.
     * Return dummy-Formatter instance that returns an empty String "" for all methods.
     * 
     * @return dummy-Formatter instance that returns empty String "" for all methods.
     */
    private Formatter dummyFormatter() {
        var dummy = new Formatter() {

            @Override
            public String fmtCustomerName(datamodel.generated.Customer customer, int... fmt) {
                // TODO Auto-generated method stub
                return "";
            }

            @Override
            public String fmtCustomerContacts(datamodel.generated.Customer customer, int... fmt) {
                // TODO Auto-generated method stub
                return "";
            }

            @Override
            public String fmtPrice(long price, int... fmt) {
                // TODO Auto-generated method stub
                return "";
            }

            @Override
            public String fmtDecimal(long value, int decimalDigits, String... unit) {
                // TODO Auto-generated method stub
                return "";
            }
        };
        return dummy;
    }
    @Override 
    public LabelPrinter getLabelPrinter() {
        return new LabelPrinter() { // return mock instance of LabelPrinter interface
    @Override 
    public StringBuilder printLabels(Iterable<Order> orders) {
        return sb.append("print FIRST MOCK address‐label.");
    }
    @Override 
        public StringBuilder printLabel(Order order) { return sb; } 

    @Override 
        public StringBuilder printLabel(Customer customer) { return sb; } 

    @Override 
        public StringBuilder printLabel(String... lines) { return sb; } 

    @Override 
        public int getWidth() { return 0; }
    @Override 
        public void clear() { }

    private final StringBuilder sb = new StringBuilder();

        }; 
    }
}
