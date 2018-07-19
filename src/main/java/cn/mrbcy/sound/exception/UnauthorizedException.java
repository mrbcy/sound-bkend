package cn.mrbcy.sound.exception;

/**
 * Created by Yang on 2018/7/14.
 */
public class UnauthorizedException extends RuntimeException{
    public UnauthorizedException(String msg) {
        super(msg);
    }

    public UnauthorizedException() {
        super();
    }

}
