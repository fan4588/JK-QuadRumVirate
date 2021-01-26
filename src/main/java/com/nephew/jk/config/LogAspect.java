package com.nephew.jk.config;

import com.alibaba.fastjson.JSONObject;
import com.nephew.jk.domain.SysLog;
import com.nephew.jk.manager.annotation.Log;
import com.nephew.jk.utils.IpUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Aspect
@Component
public class LogAspect {

    @Pointcut("@annotation(com.nephew.jk.manager.annotation.Log)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) {
        Object result = null;
        // 保存日志
        SysLog sysLog = saveLog(point);
        try {
            // 执行方法
            result = point.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        //返回
        String s = JSONObject.toJSONString(((List) result).get(0));
        System.out.println("方法执行后" + s);
        sysLog.setResult(s);
        //sysLogMapper.insert(sysLog);
        return result;
    }

    private SysLog saveLog(ProceedingJoinPoint joinPoint) {
        SysLog sysLog = new SysLog();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Log logAnnotation = method.getAnnotation(Log.class);
        if (logAnnotation != null) {
            // 注解上的描述
            sysLog.setOperation(logAnnotation.operation());
            sysLog.setModule(logAnnotation.module());
            sysLog.setType(logAnnotation.type());
        }
        // 请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setMethod(className + "." + methodName + "()");
        // 请求的方法参数值
        Object[] args = joinPoint.getArgs();
        // 请求的方法参数名称
        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        String[] paramNames = u.getParameterNames(method);
        if (args != null && paramNames != null) {
            Map<String, Object> map = new HashMap<>();
            for (int i = 0; i < args.length; i++) {
                map.put(paramNames[i], args[i]);
            }
            sysLog.setParams(JSONObject.toJSONString(map));
        }
        // 获取request
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        // 设置IP地址
        sysLog.setIp(IpUtils.getIpAddr(request));
        // 模拟一个用户名
        sysLog.setUsername("mrbird");
        sysLog.setCreateTime(new Date());
        // 保存系统日志
        String s = JSONObject.toJSONString(sysLog);
        System.out.println("方法执行前" + s);
        return sysLog;
    }

}
