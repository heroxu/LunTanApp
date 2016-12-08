package www.luoyin.com.model.dao.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author xuxiarong.
 * @time 2016/11/14 20:43.
 * @email 15889318212@163.com
 * @description
 */

public class UserBean implements Parcelable{
    private int code;
    private String reason;
    /**
     * id : 5768d9cc866c84116c000002
     * topic : 扯几把蛋，蛋疼
     * voice : ss
     * images : ss
     * likes : 1
     * comments : 2
     * vs_level : 1
     * top : 0
     * sys_level : 0
     * created_at : 2016-06-21T06:08:12.945Z
     * updated_at : 2016-06-21T06:16:21.061Z
     * user : {"id":"5757d2fa44bc4e4c13000001","mobile":"18657183613","alias":"wind","username":"ss","utype":1,"avatar":"adsfadsdskdsdsads.jpg","city":"ss","sign":"ss","occup":"ss","loc":"ss"}
     * tribe : {"id":"ss","name":"ss","level":"ss","check_type":"ss"}
     */

    private String data;

    protected UserBean(Parcel in) {
        code = in.readInt();
        reason = in.readString();
        data = in.readString();
    }

    public static final Creator<UserBean> CREATOR = new Creator<UserBean>() {
        @Override
        public UserBean createFromParcel(Parcel in) {
            return new UserBean(in);
        }

        @Override
        public UserBean[] newArray(int size) {
            return new UserBean[size];
        }
    };

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(code);
        parcel.writeString(reason);
        parcel.writeString(data);
    }
}
