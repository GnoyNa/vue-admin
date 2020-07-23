package pers.xp.bean;

public class Channel {
    private Integer id;

    private String channelname;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChannelname() {
        return channelname;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "id=" + id +
                ", channelname='" + channelname + '\'' +
                '}';
    }

    public void setChannelname(String channelname) {
        this.channelname = channelname == null ? null : channelname.trim();
    }
}