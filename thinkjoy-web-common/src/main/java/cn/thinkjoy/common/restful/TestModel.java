package cn.thinkjoy.common.restful;

/**
 * TODO 一句话描述该类用途
 * <p/>
 * 创建时间: 14/11/25 下午3:05<br/>
 *
 * @author qyang
 * @since v0.0.1
 */
public class TestModel<T> {
    private String s1;
    private T s2;

    public String getS1() {
        return s1;
    }

    public void setS1(String s1) {
        this.s1 = s1;
    }

    public T getS2() {
        return s2;
    }

    public void setS2(T s2) {
        this.s2 = s2;
    }
}
