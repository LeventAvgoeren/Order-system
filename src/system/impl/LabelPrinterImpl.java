package system.impl;

import datamodel.Customer;
import datamodel.generated.Order;
import system.Formatter;
import system.LabelPrinter;

 class LabelPrinterImpl implements LabelPrinter{
    private Formatter formatter;
    private final int labelWidth = 36;
    private final TableFormatter tf = new TableFormatter("|%-36s|");

    @Override
    public StringBuilder printLabel(Order order) {
       return tf.get();
    } 

    @Override
    public StringBuilder printLabels(Iterable<Order> orders) {
      return tf.get();
    }

    @Override
    public StringBuilder printLabel(String... lines) {
        return tf.get();
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

    @Override
    public StringBuilder printLabel(datamodel.generated.Customer customer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'printLabel'");
    }

}