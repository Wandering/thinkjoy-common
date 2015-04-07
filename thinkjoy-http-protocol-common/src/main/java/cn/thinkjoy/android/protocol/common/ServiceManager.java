/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: samples
 * $$Id: ServiceManager.java 14-11-25 下午1:39 $$
 */

package cn.thinkjoy.android.protocol.common;

import com.squareup.okhttp.OkHttpClient;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * http bussiness 入口类
 * <p/>
 * 创建时间: 14/11/25 下午1:39<br/>
 *
 * @author qyang
 * @since v0.0.1
 */
public class ServiceManager {
    protected static IEndpointAware endpointAware;

    public <T> T innerGetService(Class<T>  service){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(endpointAware.getEndpoint())
                .setConverter(FastJSONConverter.getInstance())
                .setClient(new OkClient(new OkHttpClient()))
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                        request.addHeader("Accept", "application/json");
                    }
                })
                .build();

        return restAdapter.create(service);
    }


}
