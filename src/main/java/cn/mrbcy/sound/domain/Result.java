package cn.mrbcy.sound.domain;

import org.springframework.http.HttpStatus;

/**
 * Created by Yang on 2018/7/7.
 */
public class Result {
    private int status = HttpStatus.OK.value();

    private Object data;

    private String msg;

    public Result(int status) {
        this.status = status;
    }

    public Result(int status, String msg){
        this.status = status;
        this.msg = msg;
    }

    public Result(int status, String msg, Object data) {
        this(status);
        this.msg = msg;
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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
