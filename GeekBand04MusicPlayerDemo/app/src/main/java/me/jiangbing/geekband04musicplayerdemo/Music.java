package me.jiangbing.geekband04musicplayerdemo;

import java.io.Serializable;

/**
 * Function: Music
 *
 * Create on 2016/6/5 17:37
 *
 * @Author: Allen
 * @Version: 1.0.0
 */
public class Music implements Serializable {
    /**
     * The title of the music.
     */
    private String title;
    /**
     * The name of the singer of the music.
     */
    private String singerName;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }
}
