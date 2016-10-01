package com.example.imran.simpleapp.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DummyContent {
    public static final List<DummyItem> ITEMS = new ArrayList<DummyItem>();
    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();
    private static final int COUNT = 25;

    static {
        addItem(new DummyItem("1." , "কাজিপাড়া" , "বৈশাখি"));
        addItem(new DummyItem("2." , "শেওড়াপাড়া" , "বৈশাখি"));
        addItem(new DummyItem("3." , "তালতলা" , "বৈশাখি"));
        addItem(new DummyItem("4." , "মিরপুর-১০" , "বৈশাখি"));
        addItem(new DummyItem("5." , "মিরপুর-১৪" , "বৈশাখি"));
        addItem(new DummyItem("6." , "মিরপুর-১২" , "চৈতালি"));
        addItem(new DummyItem("7." , "মিরপুর-১" , "চৈতালি"));
        addItem(new DummyItem("8." , "মিরপুর-২" , "চৈতালি"));
        addItem(new DummyItem("9." , "শেরে বাংলা স্টোডিয়াম" , "চৈতালি"));
        addItem(new DummyItem("10." , "শ্যামলী" , "চৈতালি"));
        addItem(new DummyItem("11." , "মুগদা" , "শ্রাবণ"));
        addItem(new DummyItem("12." , "বৌদ্ধ মন্দির" , "শ্রাবণ"));
        addItem(new DummyItem("13." , "খিলগাঁও" , "শ্রাবণ"));
        addItem(new DummyItem("14." , "বাসাবো" , "শ্রাবণ"));
        addItem(new DummyItem("15." , "রাম্পুরা টিভি সেন্টার" , "বসন্ত"));
        addItem(new DummyItem("16." , "রাম্পুরা বাজার" , "বসন্ত"));
        addItem(new DummyItem("17." , "হাজিপাড়া পেট্রল পাম্প" , "বসন্ত"));
        addItem(new DummyItem("18." , "মালিবাগ কমিউনিটি সেন্টার" , "বসন্ত"));
        addItem(new DummyItem("19." , "খিলগাঁও পুলিশফাঁড়ি" , "বসন্ত"));
        addItem(new DummyItem("20." , "সাভার" , "হেমন্ত"));
        addItem(new DummyItem("21." , "মোহাম্মদপুর বাস স্ট্যান্ড" , "তরঙ্গ"));
        addItem(new DummyItem("22." , "সঙ্কর" , "তরঙ্গ"));
        addItem(new DummyItem("23." , "সাত মসজিদ রোড" , "তরঙ্গ"));
        addItem(new DummyItem("24." , "জিগাতলা" , "তরঙ্গ"));
        addItem(new DummyItem("25." , "ঢানমন্ডি- ১৫" , "তরঙ্গ"));

    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    public static class DummyItem {
        public final String id;
        public final String content;
        public final String details;

        public DummyItem(String id, String content, String details) {
            this.id = id;
            this.content = content;
            this.details = details;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
