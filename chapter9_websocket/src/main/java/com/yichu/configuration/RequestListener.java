package com.yichu.configuration;

import org.springframework.stereotype.Component;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 监听器类
 * desc: 主要任务是用ServletRequest将我们的HttpSession携带过去
 */
@Component
public class RequestListener implements ServletRequestListener {
    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        //将所有request请求都携带上httpSession
        HttpSession httpSession= ((HttpServletRequest) sre.getServletRequest()).getSession();
        System.out.println("将所有request请求都携带上httpSession " + httpSession.getId());
    }

    public RequestListener() {
    }

    @Override
    public void requestDestroyed(ServletRequestEvent arg0) {
    }
}
