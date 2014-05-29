package com.baidu.yun.core.json;

import java.util.List;
import java.util.Map;

public class JsonParserDomTree {

    private String json = null;
    private JSONParser parser = null;
    private Map<String, Object> map = null;

    public JsonParserDomTree(String json) {
        this.json = json;
        this.process();
    }

    private void process() {
        try {
            parser = new JSONParser();
            map = parser.parser(this.json);
        } catch (Throwable e) {
            // e.printStackTrace();
            e.printStackTrace();
        }
    }

    public Object query(String path) {

        Object obj = map;
        String[] segs = path.split("\\\\");

        for (int i = 0; i < segs.length; i++) {
            if (obj == null)
                return null;
            if (segs[i].length() > 0) {
                if (obj instanceof Map) {
                    Map curMap = (Map) obj;
                    obj = curMap.get(segs[i]);
                } else if (obj instanceof List) {
                    List curList = (List) obj;
                    obj = null;
                    for (Object o : curList) {
                        if (o instanceof Map) {
                            if (((Map) o).containsKey(segs[i])) {
                                obj = o;
                                break;
                            }
                        }
                    }
                } else {
                    return null;
                }
            }
        }
        return obj;

    }

}
