package cn.mrbcy.sound.domain;

/**
 * Created by Yang on 2018/7/4.
 */
public class RestErrorInfo {
    public final String detail;
    public final String message;

    public RestErrorInfo(Exception ex, String detail) {
        this.message = ex.getLocalizedMessage();
        this.detail = detail;
    }
}
