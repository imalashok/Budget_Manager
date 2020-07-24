package budget;

import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SortingAlgorithm {

    public static List<Product> sortByPriceDesc(List<Product> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - i - 1; j++) {
                if (list.get(j).getProductPrice() < list.get(j + 1).getProductPrice()) {
                    Product tempProduct = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, tempProduct);
                }
            }
        }
        return list;
    }

    public static Map<String, Double> sortByTypeOfPurchase(Map<String, Double> types) {
        Map<String, Double> sortedTypes = new LinkedHashMap();
        List<Double> values = new ArrayList<>();

        for (Double value : types.values()) {
            values.add(value);
        }

        for (int i = 0; i < values.size() - 1; i++) {
            for (int j = 0; j < values.size() - i - 1; j++) {
                if (values.get(j) < values.get(j + 1)) {
                    double temp = values.get(j);
                    values.set(j, values.get(j + 1));
                    values.set(j + 1, temp);
                }
            }
        }

        for (int i = 0; i < values.size(); i++) {
            for (var entry : types.entrySet()) {
                if (entry.getValue().equals(values.get(i))) {
                    String s = entry.getKey();
                    sortedTypes.put(s, values.get(i));
                }
            }
        }

        return sortedTypes;
    }
}
