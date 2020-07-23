package pers.xp.dto;

import java.util.Map;

public class UserInfo {
    private Map<String,String> links;
    private Data data;

    public Map<String, String> getLinks() {
        return links;
    }

    public void setLinks(Map<String, String> links) {
        this.links = links;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
