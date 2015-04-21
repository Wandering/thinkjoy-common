package cn.thinkjoy.common.restful.apigen;

import cn.thinkjoy.common.restful.apigen.annotation.ApiDesc;
import cn.thinkjoy.common.restful.apigen.annotation.ApiParam;
import cn.thinkjoy.common.restful.apigen.annotation.ApiPropDesc;
import cn.thinkjoy.common.restful.apigen.domain.ApiDetail;
import cn.thinkjoy.common.restful.apigen.domain.ApiDoc;
import cn.thinkjoy.common.restful.apigen.domain.ApiSummary;
import cn.thinkjoy.common.restful.apigen.domain.Param;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ScannedGenericBeanDefinition;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.*;

//import cn.thinkjoy.mock.domain.ScanModel;

/**
 * Created by qyang on 15/4/4.
 */
public class ApiDocCollector {

    private static final Logger logger = LoggerFactory.getLogger(ApiDocCollector.class);

    /** 扫描路径 */
    private String scanPath;
    /** 协议版本 */
    private String protocolVersion;
    /** 当前系统 */
    private String sysCode;
    /** 开关 */
    private boolean onOff;
    /** apidoc 生成文档的数据模型 */
    private ApiDoc apiDoc = new ApiDoc();

    static final String DEFAULT_RESOURCE_PATTERN = "**/*.class";

    private String resourcePattern = DEFAULT_RESOURCE_PATTERN;

    private Environment environment = new StandardEnvironment();

    private ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();

    private MetadataReaderFactory metadataReaderFactory =
            new CachingMetadataReaderFactory(this.resourcePatternResolver);

    public ApiDoc collectData(){
        //进行扫描，拼接URL，处理路径参数，储备好之后，发起远程调用，传输给mock，进行备份与测试用例初始化&更新
        //合理性数据检查
        if (scanPath == null || "".equals(scanPath)) {
            return null;
        }

        doScan();

        return apiDoc;
    }

    /**
     * 扫描URL注解，先扫描类级注解进行初步过滤
     * 然后扫描方法级注解，进行二阶段定位与提纯
     * 然后扫描参数注解，请求&响应注解，进行三阶段合并
     *
     * 扫描URL注解，只扫描指定的部分注解，包括：
     * @Controller
     * @RequestMapping
     * @PathVariable
     * @ResponseBody
     * @RequestBody
     *
     * 扫描方法参数，包括参数个数，参数类型等
     *
     * 扫描方法描述
     */
    private void doScan() {
        if (!onOff) {
            return;
        }
        String[] basePackages = StringUtils.tokenizeToStringArray(scanPath,
                ConfigurableApplicationContext.CONFIG_LOCATION_DELIMITERS);

        //扫描@Controller，挑选出深入扫描目标
        Set<Class<?>> clazzSet = new LinkedHashSet<>();
        for(String basePackage : basePackages) {
            clazzSet.addAll(loadAndFilter(basePackage));
        }

        //扫描@RequestMapping，定位具体方法
        //深入扫描方法级注解，方法参数，方法描述
        for(Class<?> clazz : clazzSet) {
            //整合为自描述结构，临时存储
            detectControllerMethods(clazz);
        }
        //扫描结束，开始远程传输
//        try {
//            mockTransferService.transfers(list, protocolVersion, sysCode);
//        } catch (Exception e) {
//            logger.error(e.getMessage());
//        }
    }

    /**
     * 深入解析标记指定注解的方法
     * @param controller
     */
    private void detectControllerMethods(Class<?> controller) {
//        List<ScanModel> list = new LinkedList<>();
        RequestMapping controllerAnnotation = AnnotationUtils.findAnnotation(controller, RequestMapping.class);

        String prefix = "";
        if (controllerAnnotation != null) {
            String[] controllerValue = controllerAnnotation.value();
            if (controllerValue.length != 0) {
                prefix = controllerValue[0];
                checkUrlisOk(prefix);
            }
        }
        for(Method method : controller.getMethods()) {
            RequestMapping methodAnnotation = AnnotationUtils.findAnnotation(method, RequestMapping.class);

            if (methodAnnotation != null) {     //标记@RequestMapping，则进行深度解析
                ApiSummary apiSummary = new ApiSummary();
                ApiDetail apiDetail = new ApiDetail();
//                ScanModel scanModel = new ScanModel();
                StringBuffer url = new StringBuffer();

                String[] methodValue = methodAnnotation.value();
                RequestMethod[] methodType = methodAnnotation.method();

                if (methodValue.length != 0) {
                    url.append(prefix);
                    checkUrlisOk(methodValue[0]);
                    url.append(methodValue[0]);

                    //scanModel.setUrl(url.toString());
                    apiSummary.setUrl(url.toString());
                    apiDetail.setUrl(url.toString());
                }

                if (methodType.length != 0) {
//                    scanModel.setRequestType(methodType[0].name());
                    apiSummary.setRequestType(methodType[0].name());
                }

                detectRequest(apiDetail, method);
                detectResponse(apiDetail, method);

//                scanModel.setResponseBody(toJson(disposalResponse(method)));
//
//                disposalRequest(scanModel, method);

                ApiDesc mockDoc = AnnotationUtils.findAnnotation(method, ApiDesc.class);
                if (mockDoc != null) {
                    apiSummary.setName(mockDoc.value());
                    apiSummary.setDesc(mockDoc.value());
                    apiSummary.setOwner(mockDoc.owner());
                    apiDetail.setReturnDesc(mockDoc.returnDesc());
                    apiDetail.setName(mockDoc.value());
                    apiDetail.setDesc(mockDoc.value());
                }

                apiDoc.addApiDetail(apiDetail);
                apiDoc.addApiSummary(apiSummary);
//                list.add(scanModel);
            }
        }

        genApiDoc(apiDoc);
//        return list;
    }

    private void genApiDoc(ApiDoc apiDoc) {
    }

    private void checkUrlisOk(String url) {
        if (null == url || "".equals(url)) {
            return;
        } else {
            if (!url.startsWith("/")) {
                throw new IllegalArgumentException(url + "  请求协议格式错误!");
            }
        }
    }

    /**
     * 检查是否有 @PathVariable；@RequestBody；@RequestParam等注解
     * 有@PathVariable，则进行路径拼接
     * 有@RequestBody，则进行报文json化处理
     * 有@RequestParam，则进行URL参数拼接处理
     */
    private void detectRequest(ApiDetail apiDetail, Method method) {
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        Class<?>[] paramType = method.getParameterTypes();

        List<Param> pathVars = new ArrayList<>();
        List<Param> params = new ArrayList<>();
        String paramUrl = "";
        List<Param> request = new ArrayList<>();
        for(int i = 0; i < parameterAnnotations.length; i++) {
            Class<?> param = paramType[i];
            //肯定有两个annotion 第一个ApiParam，第二个为spring mvc的annotion
            Annotation descAnnotation = parameterAnnotations[i][0];
            Annotation annotation = parameterAnnotations[i][1];

            if (annotation instanceof RequestBody) {            //报文结构json化处理
                //获取RequestT的泛型类说明
                Type parameterizedType = ((ParameterizedTypeImpl)method.getGenericParameterTypes()[i]).getActualTypeArguments()[0];
                Class requestClass = null;
                if(parameterizedType instanceof Class){
                    requestClass = ((Class)parameterizedType);
                } else {
                    requestClass = ((ParameterizedTypeImpl)parameterizedType).getRawType();
                }
                //Class requestClass = ((Class));
                apiDetail.setRequestType(requestClass.getName());

                apiDetail.setRequestDesc(((ApiParam)descAnnotation).desc());
                if(requestClass.getName().indexOf(".dto.") != -1) {
                    //dto对象才进行这里的处理
//                    Field[] fields = requestClass.getDeclaredFields();

//                    Param param1 = null;
//                    ApiPropDesc apiPropDesc = null;
//                    for (Field field : fields) {
//                        param1 = new Param();
//                        param1.setName(field.getName());
//                        param1.setType(field.getType().toString());
//                        apiPropDesc = field.getAnnotation(ApiPropDesc.class);
//                        if (apiPropDesc != null) {
//                            param1.setDesc(apiPropDesc.value());
//                        }
//                        request.add(param1);
//                    }
                    handleApiPropDesc(requestClass, request);
                }
            } else if (annotation instanceof PathVariable) {    //URL路径参数化
                //拼接完整的url
                //apiDetail.setUrl(apiDetail.getUrl()+"/"+((PathVariable) annotation).value());
                Param param1 = new Param();
                param1.setName(((PathVariable) annotation).value());
                param1.setType(((Class)method.getGenericParameterTypes()[i]).getName());
                param1.setDesc(((ApiParam)descAnnotation).desc());
                pathVars.add(param1);
                //这里可以对参数的内容校验提供类型判断支持，暂不支持
            } else if (annotation instanceof RequestParam) {    //URL拼接
                //URL参数，用于拼接URL
                paramUrl = paramUrl + ((RequestParam) annotation).value() + "=&";
                Param param1 = new Param();
                param1.setName(((RequestParam) annotation).value());
                param1.setType(((Class)method.getGenericParameterTypes()[i]).getName());
                param1.setDesc(((ApiParam)descAnnotation).desc());
                params.add(param1);
//                    paramMap.put(((RequestParam) annotation).value(), param);
            }
        }

        if(paramUrl.length() > 0) {
            apiDetail.setUrl(apiDetail.getUrl() + "?" + paramUrl.substring(0, paramUrl.length() - 1));
        }

        apiDetail.setParams(params);
        apiDetail.setPathVar(pathVars);
        apiDetail.setRequest(request);
//        scanModel.setParamMap(paramMap);
//        scanModel.setRequestBody(toJson(requestMap));
    }

    private String toJson(Object body) {
        return JSON.toJSONString(body,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullBooleanAsFalse,
                SerializerFeature.WriteNullListAsEmpty,
                SerializerFeature.WriteNullNumberAsZero,
                SerializerFeature.WriteNullStringAsEmpty);
    }

    /**
     * 将方法的响应处理为json报文
     * @param method
     * @return
     */
    private void detectResponse(ApiDetail apiDetail, Method method) {
        ResponseBody responseBody = AnnotationUtils.findAnnotation(method, ResponseBody.class);
        if (responseBody != null) {
            Type type = method.getGenericReturnType();//.getReturnType();
            Class clazz = null;
            String clazzName = null;
            if(type instanceof Class){
                clazz = (Class)type;
                clazzName = clazz.getName();
            } else {
                clazzName = type.toString().replace("<", "&lt").replace(">", "&gt");
                clazz = ((ParameterizedTypeImpl)type).getRawType();
            }

            //String clazzName = clazz.getName();

            apiDetail.setResponseType(clazzName);
            //Object
            List<Param> response = new ArrayList<>();
            method.getParameterAnnotations();
            if("cn.thinkjoy.common.domain.ListWrapper".equals(clazz.getName())) {
                //对 listwrapper进行特殊处理
                Type t = ((ParameterizedTypeImpl)type).getActualTypeArguments()[0];

                if(t instanceof Class) {
                    if(((Class) t).getName().indexOf("java.") == -1) {
                        handleApiPropDesc((Class) t, response);
                    }
                }
            } else if(clazzName.indexOf(".dto.") == -1){
//                Param param = new Param();
//                param.setN
//                response.add(param);
                apiDetail.setResponse(response);
            } else {
                //只做一层属性展示，不处理嵌套  对 listwrapper进行特殊处理
                handleApiPropDesc(clazz, response);
            }

            apiDetail.setResponse(response);
        }
    }

    private void handleApiPropDesc(Class clazz, List<Param> params){
        Param param1 = null;
        Field[] fields = clazz.getDeclaredFields();
        ApiPropDesc apiPropDesc = null;
        for(Field field : fields){
            param1 = new Param();
            param1.setName(field.getName());
            param1.setType(field.getType().toString());
            apiPropDesc = field.getAnnotation(ApiPropDesc.class);
            if(apiPropDesc != null) {
                param1.setDesc(apiPropDesc.value());
            }
            params.add(param1);
        }
    }

    /**
     * 加载类并且进行过滤
     * @param basePackage
     * @return
     */
    private Set<Class<?>> loadAndFilter(String basePackage) {
        Set<Class<?>> beanClasses = new HashSet<>();
        try {
            String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX +
                    resolveBasePackage(basePackage) + "/" + this.resourcePattern;
            Resource[] resources = this.resourcePatternResolver.getResources(packageSearchPath);
            for (Resource resource : resources) {
                if (resource.isReadable()) {
                    try {
                        MetadataReader metadataReader = this.metadataReaderFactory.getMetadataReader(resource);
                        ScannedGenericBeanDefinition sbd = new ScannedGenericBeanDefinition(metadataReader);
                        sbd.setResource(resource);
                        sbd.setSource(resource);

                        Class<?> beanClass = Class.forName(sbd.getBeanClassName());
                        if (isHandler(beanClass)) {
                            beanClasses.add(beanClass);
                        }
                    }
                    catch (Throwable ex) {
                        throw new BeanDefinitionStoreException(
                                "Failed to read candidate component class: " + resource, ex);
                    }
                }
            }
        }
        catch (IOException ex) {
            throw new BeanDefinitionStoreException("I/O failure during classpath scanning", ex);
        }
        return beanClasses;
    }

    private String resolveBasePackage(String basePackage) {
        return ClassUtils.convertClassNameToResourcePath(this.environment.resolveRequiredPlaceholders(basePackage));
    }


    private boolean isHandler(Class<?> beanType) {
        return ((AnnotationUtils.findAnnotation(beanType, Controller.class) != null) ||
                (AnnotationUtils.findAnnotation(beanType, RequestMapping.class) != null));
    }

    public void setScanPath(String scanPath) {
        this.scanPath = scanPath;
    }

    public void setProtocolVersion(String protocolVersion) {
        this.protocolVersion = protocolVersion;
    }

    public void setSysCode(String sysCode) {
        this.sysCode = sysCode;
    }

    public void setOnOff(boolean onOff) {
        this.onOff = onOff;
    }
}
