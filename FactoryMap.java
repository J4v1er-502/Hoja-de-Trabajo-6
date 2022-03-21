import java.util.List;

public class FactoryMap {

    public IMap<String, List<String>> getMap(Integer implementacion) {

        if (implementacion.equals(1)) { // HashMap

            return new HashMap<String, List<String>>();

        } else if (implementacion.equals(2)) { // TreeMap

            return new TreeMap<String, List<String>>();

        } else if (implementacion.equals(3)) { // LinkedHashMap
            
            return new LinkedHashMap<String, List<String>>();

        }

        return null;
    }

}
