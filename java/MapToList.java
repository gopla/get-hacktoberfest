import java.util.*;

public class MapList {

    public static void main(String[] args) {

        Map<Integer, String> map = new HashMap<>();
        map.put(1, "budi");
        map.put(2, "ani");
        map.put(3, "didi");
        map.put(4, "tio");
        map.put(5, "rara");

        List<Integer> keyList = new ArrayList(map.keySet());
        List<String> valueList = new ArrayList(map.values());

        System.out.println("Key : " + keyList);
        System.out.println("Val : " + valueList);

    }
}