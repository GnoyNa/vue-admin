package pers.xp.dto;

import pers.xp.bean.UmsMember;

public class Data {
    private String type;
    private Integer id;
    private UmsMember attributes;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UmsMember getAttributes() {
        return attributes;
    }

    public void setAttributes(UmsMember attributes) {
        this.attributes = attributes;
    }
}
