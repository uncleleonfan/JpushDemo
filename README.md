# 介绍 #
本项目是极光推送的示例项目

# 相关产品 #
国内平台：个推 极光 百度等
国外平台：GCM

# 实现原理 #
[Android SDK 概述](http://docs.jiguang.cn/jpush/client/Android/android_sdk/)

# 集成步骤 #
## 1. 创建应用 ##
登录[极光推送官网](https://www.jiguang.cn/), 注册账号后登录创建应用，获取appkey。

>注意填写包名要与app的包名一致

## 2. 添加依赖 ##
    defaultConfig {
        applicationId "com.itheima.jpush"
        minSdkVersion 15
        targetSdkVersion 24
        versionCode 1
        versionName "1.0"

        ndk {
            //选择要添加的对应cpu类型的.so库。
            abiFilters 'armeabi', 'armeabi-v7a', 'armeabi-v8a', 'x86', 'x86_64'
            // 还可以添加 'x86', 'x86_64', 'mips', 'mips64'
        }

        manifestPlaceholders = [
                JPUSH_PKGNAME : applicationId,
                JPUSH_APPKEY : "3b5402b02ae690f656088009", //JPush上注册的包名对应的appkey.
                JPUSH_CHANNEL : "developer-default", //暂时填写默认值即可.
        ]
    }


	dependencies {
	    compile 'cn.jiguang:jpush:2.1.8'  // 此处以SDK 2.1.8版本为例
	}

## 3. 配置AndroidManifest.xml ##

  	<permission
        android:name="${applicationId}.permission.JPUSH_MESSAGE"
        android:protectionLevel="signature" />

    <!-- Required  一些系统要求的权限，如访问网络等-->
    <uses-permission android:name="${applicationId}.permission.JPUSH_MESSAGE" />
    <uses-permission android:name="android.permission.RECEIVE_USER_PRESENT" />
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.WAKE_LOCK" />
    <uses-permission android:name="android.permission.READ_PHONE_STATE" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.WRITE_SETTINGS" />
    <uses-permission android:name="android.permission.VIBRATE" />
    <uses-permission android:name="android.permission.MOUNT_UNMOUNT_FILESYSTEMS" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
    <uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW"/>

    <!-- Optional. Required for location feature -->
    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
    <uses-permission android:name="android.permission.CHANGE_WIFI_STATE" />
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
    <uses-permission android:name="android.permission.ACCESS_LOCATION_EXTRA_COMMANDS" />
    <uses-permission android:name="android.permission.CHANGE_NETWORK_STATE" />

## 4. 初始化SDK ##
	public class JpushApplication extends Application {
	
	    @Override
	    public void onCreate() {
	        super.onCreate();
	        JPushInterface.setDebugMode(true);
	        JPushInterface.init(this);
	    }
	}

## 5. 生命周期管理 ##
	public class MainActivity extends AppCompatActivity {
	
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);
	    }
	
	    @Override
	    protected void onResume() {
	        super.onResume();
	        JPushInterface.onResume(MainActivity.this);
	    }
	
	    @Override
	    protected void onPause() {
	        super.onPause();
	        JPushInterface.onPause(MainActivity.this);
	    }
	}

## 6. 发送通知 ##
进入Jpush应用后台，发送一条通知

## 7. 推送富文本消息 ##
示例：推送一条新闻链接

## 8. 接收自定义通知 ##
[通知VS消息](http://docs.jiguang.cn/jpush/client/Android/android_senior/#vs)

[接收推送消息Receiver](http://docs.jiguang.cn/jpush/client/Android/android_api/#receiver)

    <receiver
        android:name="MyReceiver"
        android:enabled="true">
        <intent-filter>
            <action android:name="cn.jpush.android.intent.MESSAGE_RECEIVED" />
			<!--替换成自己app的包名-->
            <category android:name="com.itheima.jpush" />
        </intent-filter>
    </receiver>
	
	public class MyReceiver extends BroadcastReceiver {
	
	    private static final String TAG = "MyReceiver";
	
	    @Override
	    public void onReceive(Context context, Intent intent) {
	        Bundle bundle = intent.getExtras();
	        String title = bundle.getString(JPushInterface.EXTRA_TITLE);
	        String msg = bundle.getString(JPushInterface.EXTRA_MESSAGE);
	        Log.d(TAG, "onReceive: " + title + " " + msg);
	    }
	}


# 参考 #

[Android SDK 集成指南](http://docs.jiguang.cn/jpush/client/Android/android_guide/)

[Android SDK 进阶教程](http://docs.jiguang.cn/jpush/client/Android/android_senior/)