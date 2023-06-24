package datamodel.generated;      // <- add package


/**
 * Enumeration type for  tax rates that apply for various types of articles.
 * <p>
 * A business is obligated to collect taxes on sales based on an applicable tax rate.
 * German sales tax (Umsatzsteuer) is a compounded value-added tax (VAT, Mehrwertsteuer, MwSt.), see
 *  <a href="https://de.wikipedia.org/wiki/Umsatzsteuer_(Deutschland)">Umsatzsteuer (Deutschland)</a>. VAT is included in final prices.
 *  </p>
 * <p>
 *  The regular tax rate is 19%. A reduced rate of 7% applies to food, books, medication and health care items.
 * </p>
 * 
 * @version <code style=color:green>{@value application.package_info#Version}</code>
 * @author <code style=color:blue>{@value application.package_info#Author}</code>
 */

public enum TAX {
    TAXFREE,
    GER_VAT,
    GER_VAT_REDUCED
}