package content;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

public class Story implements Serializable {
    String sTitle;

    ArrayList<Pages<String, Bitmap>> sPages = new ArrayList<Pages<String, Bitmap>>();

    public Story(String title, List<Pages<String, Bitmap>> pages) {
        sTitle = title;
        if (pages != null) {
            sPages = new ArrayList<Pages<String, Bitmap>>(pages);
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

    public List<Pages<String, Bitmap>> getPages() {
        return sPages;
    }

    public void setPages(List<Pages<String, Bitmap>> pages) {
        sPages = new ArrayList<Pages<String, Bitmap>>(pages);
    }

    public String toString() {
        return sTitle;
    }

}
