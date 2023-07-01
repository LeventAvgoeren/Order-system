package datamodel.generated;
/**
 * Interface of a type to represent a postal address that is comprised of:
 * <pre>
 * - country code, e.g. "D" for Germany, "AU" for Austria, etc.
 * - zip code as 4 or 5 digit postal routing number (Postleitzahl).
 * - city as name of a city, town or village as used in a postal address.
 * - street as name of the street with house number.
 * </pre>
 * 
 * @version <code style=color:green>{@value application.package_info#Version}</code>
 * @author <code style=color:blue>{@value application.package_info#Author}</code>
 */

public interface Address {

    /**
     * Enum to represent country codes, e.g. "D" for Germany, "AU" for Austria, etc.
     *
     */
    enum Country { D, AU, CH, NL, GB, IT, SP, USA };

    /**
     * Return country code.
     * 
     * @return country code.
     */
    public Country getCountry();

    /**
     * Return zip code (Postleitzahl).
     * 
     * @return zip code.
     */
    public String getZip();

    /**
     * Return name of city, town or village as used in a postal address.
     * 
     * @return name of city, town or village.
     */
    public String getCity();

    /**
     * Return name of the street with house number.
     * 
     * @return name of the street with house number.
     */
    public String getStreet();

}

