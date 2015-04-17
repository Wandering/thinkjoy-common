package cn.thinkjoy;

import cn.thinkjoy.common.restful.apigen.annotation.ApiPropDesc;

import java.lang.reflect.Field;

/**
 * TODO 一句话描述该类用途
 * <p/>
 * 创建时间: 15/4/17 下午10:10<br/>
 *
 * @author qyang
 * @since v0.0.1
 */
public class TestAnnotation {
    public static void main(String[] args) {
        for(Field field : DTOTest.class.getDeclaredFields()){
            ApiPropDesc apiPropDesc = field.getAnnotation(ApiPropDesc.class);
            System.out.println(apiPropDesc.value());
        }

    }
}
