package com.lwd.signin.model.ResultBean;

/**
 * User: LWD
 * Date: 2016/12/11.
 * Email: 13102169005@163.com
 * Desc:
 */

public class RightMenu {
    public RightMenu() {
    }

    public RightMenu(String id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }

    /**
     * id : 1
     * name : 医疗
     */

    private String id;
    private String name;
    private String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
