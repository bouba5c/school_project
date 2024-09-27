package g55870.evaluation;

import be.he2b.esi.pbt.evaluation.stib.LinesUtil;

import java.util.Iterator;
import java.util.List;

public class Line {

    private List<Station> stations;
    private int number;

    /**
     * constructor of the class Line
     *
     * @param number the number's line
     * @throws IllegalArgumentException if the line is invalid.
     */
    public Line(int number) {
        if (this.number != 1 || this.number != 2 || this.number != 5 || this.number != 6) {
            throw new IllegalArgumentException("Le numero de ligne n'est pas valide");
        }
        this.number = number;
    }

    /**
     * @param name the name of the station
     * @return the name of the station
     */
    public Station getStations(String name) {
        if (name == null || !LinesUtil.getStations(number).contains(name)) {
            return null;
        }
        Station stationTemp = null;
        for (int i = 0; i < stations.size(); i++) {
            if (stations.get(i).toString() == name) {
                stationTemp.getName();
            }
        }
        return stationTemp;
    }

    /**
     * @param id the id of the station
     * @return index of the id station if doesn't exist return -1
     */
    public int indexOf(int id) {
        int index = -1;
        for (int i = 0; i < stations.size(); i++) {
            if (stations.get(i).getId() == id) {
                index = stations.indexOf(i);
            }
        }
        return index;
    }

    /**
     * @param index the index of a given id staion
     * @return position of the station
     */
    public Station get(int index) {
        Station station = stations.get(index);
        if (station == null || !LinesUtil.getStations(station.getId()).contains(station.getName())) {
            return null;
        }
        return station;
    }

    class LineIterable implements Iterable {

        /**
         * Iterator method from the class Iterable
         *
         * @return the station's iterator.
         */
        @Override
        public Iterator iterator() {
            return stations.iterator();
        }
    }
}
