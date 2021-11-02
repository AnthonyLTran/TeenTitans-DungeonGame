//import jdk.internal.org.objectweb.asm.tree.InsnList;
//import org.apache.commons.io.FileUtils;
//
//import java.io.File;
//import java.util.ArrayList;
//import java.util.List;
//
//public class ItemInput {
//    private static ArrayList<Items> items = new ArrayList<>();
//    public static ArrayList<Items> getItems() {
//        try {
//
//            List<String> lines = FileUtils.readLines(new File("Items.txt"), "UTF-8");
//            //System.out.println(lines);
//
//            List<String> itemRawData = lines;
//            for(int i = 1; i < itemRawData.size(); ++i) {
//                String itemType = (String)itemRawData.get(i);
//                ++i;
//                if (itemType.compareTo("equippable") == 0) {
//                    Equippable item = new Equippable();
//                    item.setItemType(itemType);
//                    item.setItemID(Integer.parseInt((String)itemRawData.get(i)));
//                    ++i;
//                    item.setItemName((String)itemRawData.get(i));
//                    ++i;
//                    item.setItemDescription((String)itemRawData.get(i));
//                    ++i;
//                    item.setDamageEffect(Integer.parseInt((String)itemRawData.get(i)));
//                    ++i;
//                    item.setEquipped(false);
//                    ++i;
//                    items.put(item.getItemID(), item);
//                } else {
//                    Consumable item = new Consumable();
//                    item.setItemType(itemType);
//                    item.setItemID(Integer.parseInt((String)itemRawData.get(i)));
//                    ++i;
//                    item.setItemName((String)itemRawData.get(i));
//                    ++i;
//                    item.setItemDescription((String)itemRawData.get(i));
//                    ++i;
//                    item.setHealthEffect(Integer.parseInt((String)itemRawData.get(i)));
//                    ++i;
//                    item.setConsumed(false);
//                    ++i;
//                    items.put(item.getItemID(), item);
//                }
//            }
//
//
//        }catch (Exception e) {
//            System.out.println("Items.txt file not found");
//            e.printStackTrace();
//        }
//        return items;
//
//    }
//}
