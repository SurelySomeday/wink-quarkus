package top.yxlgx.core.exception;

/**
 * @author yanxin
 * @description
 */
public class LoginFailedException extends RuntimeException {
    public final String msg;

    public LoginFailedException(String msg) {
        this.msg = msg;
    }
}
