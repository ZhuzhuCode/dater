package com.zjr.dater.common.utils;

import com.zjr.dater.business.model.User;
import com.zjr.dater.common.SysConstant;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by zhujr on 2018/10/15.
 * session管理工具
 */
public class SessionUtils {
    //全局删除id标识
    public static String GLOB_DELETE_ID_VAL = "globDeleteIdVal";

    /**
     * 获取request
     *
     * @return
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return requestAttributes == null ? null : requestAttributes.getRequest();
    }

    /**
     * 获取session
     *
     * @return
     */
    public static HttpSession getSession() {
        return getRequest().getSession(true);
    }

    /**
     * 获取真实路径
     *
     * @return
     */
    public static String getRealRootPath() {
        return getRequest().getServletContext().getRealPath("/");
    }

    /**
     * 获取IP
     */
    public static String getIP() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        if(servletRequestAttributes != null){
            HttpServletRequest request = servletRequestAttributes.getRequest();
            return request.getRemoteAddr();
        }
        return null;
    }

    /**
     * 获取session中的Attribute
     * @param name
     * @return
     */
    public static Object getSessionAttribute(String name){
        HttpServletRequest request = getRequest();
        return request == null?null:request.getSession().getAttribute(name);
    }
    /**
     * 设置session的Attribute
     * @param name
     * @param value
     */
    public static void setSessionAttribute(String name,Object value){
        HttpServletRequest request = getRequest();
        if(request!=null){
            request.getSession().setAttribute(name, value);
        }
    }
    /**
     * 获取request中的Attribute
     * @param name
     * @return
     */
    public static Object getRequestAttribute(String name){
        HttpServletRequest request = getRequest();
        return request == null?null:request.getAttribute(name);
    }

    /**
     * 设置request的Attribute
     * @param name
     * @param value
     */
    public static void setRequestAttribute(String name,Object value){
        HttpServletRequest request = getRequest();
        if(request!=null){
            request.setAttribute(name, value);
        }
    }
    /**
     * 获取上下文path
     * @return
     */
    public static String getContextPath() {
        return getRequest().getContextPath();
    }
    /**
     * 删除session中的Attribute
     * @param name
     */
    public static void removeSessionAttribute(String name) {
        getRequest().getSession().removeAttribute(name);
    }

    /**
     * 获取当前登录用户
     * @return
     */
    public static User getUser(){
        return (User) getSession().getAttribute(SysConstant.CURRENTUSER);
    }

    /**
     * 设置用户
     * @param u
     */
    public static void setUser(User u){
        getSession().setAttribute(SysConstant.CURRENTUSER,u);
    }

    /**
     * 销毁session
     */
    public static void removeUser(){
        HttpSession session = getSession();
        session.removeAttribute(SysConstant.CURRENTUSER);
        session.invalidate();
    }
}
