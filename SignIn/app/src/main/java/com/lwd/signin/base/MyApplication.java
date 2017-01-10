package com.lwd.signin.base;

import android.app.Application;
import android.content.Context;

import com.lwd.signin.R;
import com.lwd.signin.common.ActivityPageManager;
import com.lwd.signin.utils.LogUtil;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.utils.L;


/**
 * User: ZRJ
 * Date: 2016/11/30
 * Email: 471564517@qq.com
 * Description:
 */
public class MyApplication extends Application {
    private final String tag = MyApplication.class.getSimpleName();
    private static DisplayImageOptions options;
    private static MyApplication instance;
    public static Context myContext;

    /*
  * 是否完成  整个项目
  */
    public static boolean isCompleteProject = false;

    public static ImageLoader imageLoader;
    public static final boolean ISDEBUG = false;

    public static MyApplication getInstance() {
        return instance;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        myContext = this;
        init();

    }

    /**
     * 初始化
     */
    private void init() {

        LogUtil.setDebug(!isCompleteProject);

        LogUtil.e(tag, "isDebug: " + !isCompleteProject);

        initImageLoader();


    }


    private void initImageLoader() {

        //获取系统分配给每个应用程序的最大内存，每个应用系统分配32M
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int mCacheSize = maxMemory / 8;
        //给LruCache分配1/8 4M

        options = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.mipmap.ic_launcher)
                .showImageOnFail(R.mipmap.ic_launcher)
                .showImageOnLoading(R.mipmap.ic_launcher)
                .imageScaleType(ImageScaleType.EXACTLY)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();

        //初始化图片下载组件
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheSize(100 * 1024 * 1024)
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .memoryCache(new LruMemoryCache(mCacheSize))
                .defaultDisplayImageOptions(options)
                .build();

        ImageLoader.getInstance().init(config);
        imageLoader = ImageLoader.getInstance();
        //关闭 打开log  imgelog
        L.writeLogs(false);

    }

    /**
     * 退出应用
     */
    public void exit() {
        ActivityPageManager.getInstance().exit(this);
    }
}
