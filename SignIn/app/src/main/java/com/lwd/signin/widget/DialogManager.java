package com.lwd.signin.widget;

/**
 * User: ZRJ
 * Date: 2016/12/1
 * Email: 471564517@qq.com
 * Description: Dialog管理类
 */
public class DialogManager {
    private static DialogManager instance;
    private DialogManager(){
    }
    public static  DialogManager  getInstance(){
        if(instance ==null){
            instance = new DialogManager();
        }
        return instance;
    }


}

