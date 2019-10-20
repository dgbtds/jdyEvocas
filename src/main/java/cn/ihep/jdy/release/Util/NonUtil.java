package cn.ihep.jdy.release.Util;

import java.util.Collection;
import java.util.Map;

public class NonUtil {
    public  static boolean isNon(Object ...objects){
        for(Object o:objects){
            if(isNon(o))
                return true;
        }
        return  false;
    }

    public static boolean isNon(Object object)
    {
        boolean isnon = false;
        if (object == null) {
            return true;
        }
        if ((object instanceof String))
        {
            String str = (String)object;
            if (str.trim().length() == 0) {
                isnon = true;
            }
        }
        else if ((object instanceof Collection))
        {
            Collection collection = (Collection)object;
            if (collection.size() == 0) {
                isnon = true;
            }
        }
        else if ((object instanceof Map))
        {
            Map map = (Map)object;
            if (map.size() == 0) {
                isnon = true;
            }
        }
        return isnon;
    }

    public static boolean isNotNon(Object object)
    {
        return !isNon(object);
    }

}
