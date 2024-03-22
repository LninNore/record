package per.me.record.annotation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.sql.Time;
import java.util.Iterator;
import java.util.stream.Stream;

public class MultiRequestBodyResolver implements HandlerMethodArgumentResolver {

    //因为request只能取一次，需要全局变量存起来
    private String requestBody = null;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasMethodAnnotation(MultiRequestBody.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        //保存requestbody
        HttpServletRequest httpServletRequest = (HttpServletRequest) webRequest.getNativeRequest();
        if(parameter.getParameterIndex() == 0){
            requestBody = getRequestBody(httpServletRequest);
        }

        //把requestbody（json字符串）装换成json节点树方便读取
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(requestBody);

        boolean isCustomObject = jsonNode.get(parameter.getParameterName()).isObject();
        Object value = getValueFromJsonNode(jsonNode, parameter.getParameter(), isCustomObject);

        return value;
    }

    private Object getValueFromJsonNode(JsonNode jsonNode, Parameter parameter, boolean isCustomObject) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if (!isCustomObject){
            Object object = null;
            Class<?> parameterType = parameter.getType();
            if (parameterType.equals(Integer.class)){
                object = jsonNode.get(parameter.getName()).asInt();
            }
            else if(parameterType.equals(String.class)){
                object = jsonNode.get(parameter.getName()).asText();
            }
            else if(parameterType.equals(Time.class)){
                String time = jsonNode.get(parameter.getName()).asText();
                object = Time.valueOf(time);
            }
            return object;
        }
        else {
            jsonNode = jsonNode.get(parameter.getName());
            Class<?> parameterType = parameter.getType();
            Class clazz = Class.forName(parameterType.getName());
            Object instance = null;
            for (Constructor c : clazz.getConstructors()) {
                if(c.getParameterCount() == jsonNode.size()){
                    Object[] values = new Object[c.getParameterCount()];
                    int i = 0;
                    for (Parameter p : c.getParameters()) {
                        isCustomObject = jsonNode.get(p.getName()).isObject();
                        values[i++] = getValueFromJsonNode(jsonNode, p, isCustomObject);
                    }
                    instance = c.newInstance(values);
                }
            }
            return instance;
        }
    }

    /**
     * 获取请求体
     * @param httpServletRequest
     * @return 返回json
     */
    private String getRequestBody(HttpServletRequest httpServletRequest) {
        try {
            BufferedReader reader = httpServletRequest.getReader();
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            return stringBuilder.toString();
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
            return "";
        }
    }

}
