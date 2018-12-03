package content;

import java.util.List;
import java.util.ArrayList;

public class Story {
    String sTitle;

    ArrayList<Pages<String, Integer>> sPages = new ArrayList<Pages<String, Integer>>();

    public Story(String title, List<Pages<String, Integer>> pages) {
        sTitle = title;
        if (pages != null) {
            sPages = new ArrayList<Pages<String, Integer>>(pages);
        }
    }

    public Story(String title) {
        this(title, null);
    }

    public Story() {
        this("Default", null);
    }

    public void addPage(Pages newPage) {
        sPages.add(newPage);
    }

    public String getTitle() {
        return sTitle;
    }

    public void setTitle(String title) {
        sTitle = title;
    }

    public List<Pages<String, Integer>> getPages() {
        return sPages;
    }

    public void setPages(List<Pages<String, Integer>> pages) {
        sPages = new ArrayList<Pages<String, Integer>>(pages);
    }

    public String toString() {
        return sTitle;
    }

}
