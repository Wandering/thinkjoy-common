package cn.thinkjoy.common.restful;

import cn.thinkjoy.common.exception.BizException;
import cn.thinkjoy.common.protocol.Response;
import cn.thinkjoy.common.protocol.ResponseT;
import cn.thinkjoy.common.protocol.ResponseTs;
import cz.jirutka.spring.exhandler.handlers.RestExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zxxiao on 14/10/30.
 */
public class BizExceptionHandler implements RestExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(BizExceptionHandler.class);

    @Override
    public ResponseEntity handleException(Exception exception, HttpServletRequest request) {
        boolean isDebug = false;
        if(request.getParameter("debug") != null){
            isDebug = true;
        }
//        Response response = new Response.ResponseBuilder((BizException) exception).build();
        ResponseT<String> responseT = ResponseTs.<String>newResponseException((BizException) exception, isDebug);
        logger.error(((BizException) exception).getMsg(), exception);

        return new ResponseEntity<>(responseT, HttpStatus.OK);
        //return null;
    }
}
