package cn.mrbcy.sound.domain;

import org.springframework.http.HttpStatus;

/**
 * Created by Yang on 2018/7/7.
 */
public class Result {
    private int status = HttpStatus.OK.value();

    private Object data;

    public Result(Object data) {
        this.data = data;
    }

    public Result(int status, String data) {
        this(status);
        this.data = data;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
