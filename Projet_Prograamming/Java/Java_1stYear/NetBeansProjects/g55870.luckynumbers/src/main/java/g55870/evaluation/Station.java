package g55870.evaluation;

public class Station {

    private int id;
    private String name;
    private double tax;

    /**
     * Constructor of the class Station
     *
     * @param id is the id of the station
     * @param name the name of the station
     * @param tax the amount of the tax
     */
    public Station(int id, String name, double tax) {
        this.id = id;
        this.name = name;
        this.tax = tax;
    }

    /**
     * Will get the id
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Will get the name of the station
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Will get the price of the tax
     *
     * @return amount of the tax
     */
    public double getTax() {
        return tax;
    }

}
