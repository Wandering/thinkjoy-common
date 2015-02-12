package cn.thinkjoy.common.managerui.iauth.core;
import cn.thinkjoy.common.managerui.iauth.core.exception.AuthNotPassException;
import cn.thinkjoy.common.managerui.iauth.core.exception.CannotAuthException;

import java.io.IOException;

/**
 * Created by Michael on 11/10/14.
 *
 * 验证单元
 */
public interface Authentication {

    /**
     * 进行验证埋点
     * @param baseRequest
     * @return
     */
    void embed(BaseRequest baseRequest);

    /**
     * 执行验证
     * @param baseRequest
     * @return
     */
    void invoke(BaseRequest baseRequest) throws IOException;


    /**
     * 清除埋点
     * @param baseRequest
     */
    void clear(BaseRequest baseRequest);








}
