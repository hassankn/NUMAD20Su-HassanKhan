package edu.neu.madcourse.numad20su_hassankhan.nameAndLinkURL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class NameAndLinkURLContent {

    /**
     * An array of NameAndLinkURL items.
     */
    public static final List<NameAndLinkURLItem> ITEMS = new ArrayList<NameAndLinkURLItem>();

    /**
     * A map of NameAndLinkURL items, by LinkName.
     */
    public static final Map<String, NameAndLinkURLItem> ITEM_MAP = new HashMap<String, NameAndLinkURLItem>();

    static {
        // Add some sample items.
        addItem(createNameAndLinkURLItem("Google", "https://www.google.com"));
        addItem(createNameAndLinkURLItem("NyTimes", "https://www.nytimes.com"));
        addItem(createNameAndLinkURLItem("Guardian", "https://www.theguardian.com"));
    }

    public static void addItem(NameAndLinkURLItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.linkName, item);
    }

    public static NameAndLinkURLItem createNameAndLinkURLItem(String linkName, String linkURL) {
        return new NameAndLinkURLItem(linkName, linkURL);
    }

    /**
     * A NameAndLinkURL item representing a piece of content.
     */
    public static class NameAndLinkURLItem {
        public final String linkName;
        public final String linkURL;

        public NameAndLinkURLItem(String linkName, String linkURL) {
            this.linkName = linkName;
            this.linkURL = linkURL;
        }

        @Override
        public String toString() {
            return this.linkName + " " + this.linkURL;
        }
    }
}
