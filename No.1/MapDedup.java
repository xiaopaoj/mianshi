import java.util.HashMap;
import java.util.Map;

/**
 * @author : nate.jiang
 * @date : 2023/4/10 11:49
 */
public class MapDedup {

    public static void main(String[] args) {
        Map[] maps = new Map[4];
        maps[0] = new HashMap<String, Object>(){{
            put("id", 1);
        }};
        maps[1] = new HashMap<String, Object>(){{
            put("id", 2);
        }};
        maps[2] = new HashMap<String, Object>(){{
            put("id", 3);
        }};
        maps[3] = new HashMap<String, Object>(){{
            put("id", 1);
        }};
        for (Map map : dedup(maps, "id")) {
            System.out.println(map.toString());
        }
    }

    public static Map[] dedup(Map[] maps, String key){
        Map<Object, Map> keyMap = new HashMap<>(maps.length);
        for(Map map : maps) {
            if(null != map && map.containsKey(key)) {
                keyMap.put(map.get(key), map);
            }
        }
        Map[] newMaps = new Map[keyMap.values().size()];
        int i = 0;
        for(Map map: keyMap.values()) {
            newMaps[i++] = map;
        }
        return newMaps;
    }
}
