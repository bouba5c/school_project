/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g55870.evaluation;

/**
 *
 * @author balde boubacar <baldeboubacar11 at gmail.com>
 */
public class StibTaxApp {

    /**
     * Will display the line of the station
     *
     * @param line the line station
     */
    public static void displayLine(Line line) {
        System.out.println("Line " + line);
    }

    /**
     * Will calculate the amount tax
     *
     * @param lineNumber
     * @param start the first station index
     * @param stop the second station index
     * @return the calcul of the tax beetween the start and the stop
     */
    public static double calculate(int lineNumber, int start, int stop) {
        return ((start + stop) * 2.2) / 100;
    }

    public static void main(String[] args) {
        var line = new Line(5);
        displayLine(line);
        var stationCeria = line.getStations("CERIA");
        var stationAumale = line.getStations("AUMALE");
        System.out.printf("TAXE CERIA -AUMALE %2.2f\n",
                calculate(5, line.indexOf(stationCeria.getId()), line.indexOf(stationAumale.getId())));
    }
}
