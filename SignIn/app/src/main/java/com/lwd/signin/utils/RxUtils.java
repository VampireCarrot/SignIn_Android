package com.lwd.signin.utils;


import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * User: ZRJ
 * Date: 2016/12/12
 * Email: 471564517@qq.com
 * Description:
 */
public class RxUtils {
    private static volatile RxUtils defaultInstance;

    private final Subject<Object, Object> bus;
    // PublishSubject只会把在订阅发生的时间点之后来自原始Observable的数据发射给观察者
    public RxUtils() {
        bus = new SerializedSubject<>(PublishSubject.create());
    }
    // 单例RxUtils
    public static RxUtils getDefault() {
        if (defaultInstance == null) {
            synchronized (RxUtils.class) {
                if (defaultInstance == null) {
                    defaultInstance = new RxUtils();
                }
            }
        }
        return defaultInstance ;
    }
    // 发送一个新的事件
    public void post (Object o) {
        bus.onNext(o);
    }
    // 根据传递的 eventType 类型返回特定类型(eventType)的 被观察者
    public <T> Observable<T> toObservable (Class<T> eventType) {
        return bus.ofType(eventType);

    }


    
}
