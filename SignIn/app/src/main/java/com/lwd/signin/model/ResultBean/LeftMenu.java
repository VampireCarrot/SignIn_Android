package com.lwd.signin.model.ResultBean;

/**
 * User: LWD
 * Date: 2016/12/11.
 * Email: 13102169005@163.com
 * Desc:
 */

public class LeftMenu {

    /**
     * id : 1
     * name : 医疗
     */

    private String id;
    private String name;
    private int sourceId;

    public LeftMenu(String id, String name, int sourceId) {
        this.id = id;
        this.name = name;
        this.sourceId = sourceId;
    }

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

    public int getSourceId() {
        return sourceId;
    }

    public void setSourceId(int sourceId) {
        this.sourceId = sourceId;
    }
}
