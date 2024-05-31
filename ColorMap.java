package util;


import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ColorMap {
    static Map<Integer, Color> colorMap = new HashMap<>();

    //todo: complete the method to add other color

    //这段代码是 ColorMap 类的一部分，用于初始化颜色映射。具体来说，它将整数键 1 映射到颜色 Color.RED，将整数键 2 映射到颜色 Color.ORANGE。
    //
    //这里是代码的详细解释：
    //
    //public static void InitialColorMap()：这是一个公共静态方法，用于初始化颜色映射。静态方法意味着它不需要创建 ColorMap 类的实例就可以直接调用。
    //colorMap.put(1, Color.RED);：这行代码将整数键 1 映射到颜色 Color.RED。在Java中，Color.RED 是一个预定义的颜色常量，表示红色。
    //colorMap.put(2, Color.ORANGE);：这行代码将整数键 2 映射到颜色 Color.ORANGE。同样，Color.ORANGE 是一个预定义的颜色常量，表示橙色。
    //通过调用 InitialColorMap 方法，你可以为 colorMap 添加键值对，从而定义颜色映射。
    public static void InitialColorMap() {
        colorMap.put(1, Color.RED);//红
        colorMap.put(2, new Color(238,228, 218));
        colorMap.put(4, new Color(235, 218, 188));
        colorMap.put(8, new Color(241, 177, 121));
        colorMap.put(16, new Color(246, 145, 94));
        colorMap.put(32, new Color(210, 105, 30));
        colorMap.put(64, new Color(243, 81, 44));
        colorMap.put(128, new Color(237, 205, 113)) ;
        colorMap.put(256,new Color(45, 130, 128));
        colorMap.put(512, new Color(9, 97, 26));
        colorMap.put(1024, new Color(237, 205, 14));
        colorMap.put(2048, new Color(192, 20, 20));
        colorMap.put(4096, Color.CYAN); // 蓝绿
        colorMap.put(8192, new Color(255, 255, 128));
    }
    //getOrDefault 方法是 HashMap 类的一个方法，用于从映射中获取指定的键的值，如果不存在该键，则返回默认值。
//
//在 ColorMap 类中，getColor 方法应该返回与整数键 i 关联的颜色，如果不存在，则返回默认颜色 Color.black。
    public static Color getColor(int i) {
        return colorMap.getOrDefault(i, Color.black);
    }
}


