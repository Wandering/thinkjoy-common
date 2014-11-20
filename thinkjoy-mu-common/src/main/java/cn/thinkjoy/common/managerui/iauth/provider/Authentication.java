package cn.thinkjoy.common.managerui.iauth.provider;
import java.io.IOException;

/**
 * Created by Michael on 11/10/14.
 *
 * 验证单元
 */
public interface Authentication {

      /**
     * 进行验证埋点
     * 返回true，继续
     * 返回false，停止继续
     * @param baseRequest
     * @return
     */
    boolean embed(BaseRequest baseRequest);

    /**
     * 执行验证
     * 返回true，继续
     * 返回false，验证不通过
     * @param baseRequest
     * @return
     */
    boolean invoke(BaseRequest baseRequest) throws CannotAuthException, IOException;


}
