package www.luoyin.com.jni;

/**
 * @author xuxiarong.
 * @time 2016/11/24 19:21.
 * @email 15889318212@163.com
 * @description
 */

public class Person {
    private  String yifu;
    private String name;
    private Person p;

    public Person(){
        p = new Person();
    }



    public Person chuanyifu(String yifu){
        p.yifu = yifu;
        return this;
    }

    public Person shangxue(String name){
        p.name = name;
        return this;
    }
}
