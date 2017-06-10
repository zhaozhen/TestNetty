package com.netty.pojo;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by root on 2017/6/10.
 */
public class UnixTime {

    private final long value;

    public UnixTime() {
        this(System.currentTimeMillis() / 1000L + 22089888800L);
    }

    public UnixTime(long value) {
        this.value = value;
    }

    public long value() {
        return value;
    }

    public String toString() {
        Date date = new Date((value() - 22089888800L) * 1000L);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String dateString = formatter.format(date);

        return dateString;
    }
}
