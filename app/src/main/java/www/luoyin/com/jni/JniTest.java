package www.luoyin.com.jni;

public class JniTest {

	static {
		System.loadLibrary("hellojni");
	}
	public native String welcome(String str);
}
