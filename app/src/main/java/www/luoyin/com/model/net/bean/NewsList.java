package www.luoyin.com.model.net.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by laucherish on 16/3/15.
 */
public class NewsList implements Parcelable {
    private String date;
    private List<News> stories;
    private List<TopNews> top_stories;

    public List<TopNews> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopNews> top_stories) {
        this.top_stories = top_stories;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<News> getStories() {
        return stories;
    }

    public void setStories(List<News> stories) {
        this.stories = stories;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.date);
        dest.writeTypedList(stories);
        dest.writeTypedList(top_stories);
    }

    public NewsList() {
    }

    protected NewsList(Parcel in) {
        this.date = in.readString();
        this.stories = in.createTypedArrayList(News.CREATOR);
        this.top_stories = in.createTypedArrayList(TopNews.CREATOR);
    }

    public static final Parcelable.Creator<NewsList> CREATOR = new Parcelable.Creator<NewsList>() {
        public NewsList createFromParcel(Parcel source) {
            return new NewsList(source);
        }

        public NewsList[] newArray(int size) {
            return new NewsList[size];
        }
    };
}
