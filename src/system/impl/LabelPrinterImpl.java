package system.impl;

import java.util.stream.Stream;

import datamodel.generated.Customer;
import datamodel.generated.Order;
import system.Formatter;
import system.LabelPrinter;

 class LabelPrinterImpl implements LabelPrinter{
    private Formatter formatter;
    private final int labelWidth = 36;
    private final TableFormatter tf = new TableFormatter("|%-36s|");

     @Override
    public StringBuilder printLabel(Order order) {
        Customer customer = order.getCustomer();
        StringBuilder sb = new StringBuilder();

       StringBuilder sb2 = new StringBuilder(printLabel(customer));

   
        TableFormatter tf = new TableFormatter(
            "| %-32s |"
        )
        .line()
        .row("                      " + order.getId());
     
        
        return  sb.append(tf.get()).append(sb2);
    }

   @Override
    public StringBuilder printLabels(Iterable<Order> orders) {
        StringBuilder sb = new StringBuilder();
        for (Order order : orders) {
            sb.append(printLabel(order));
        }
        return sb;
    } 

    @Override
    public StringBuilder printLabel(Customer customer) {

       StringBuilder sb = new StringBuilder();

        TableFormatter tf = new TableFormatter(
            "| %-32s |"
        )
        .row(customer.getName())
        .row("Musterstrasse 10")
        .row("D-13353 Mustersatdt")
        .line();

        return tf.get();
    }

   @Override
    public StringBuilder printLabel(String... lines) {
        if(lines==null)
            throw new IllegalArgumentException("argument lines is null.");
        TableFormatter tf = new TableFormatter("|%-36s|");
        tf.line();
        Stream.of(lines).forEach(line -> tf.row(line));
        tf.line();
        return new StringBuilder(tf.get());
    }

    @Override
    public int getWidth() {
        return labelWidth;
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'clear'");
    }

}