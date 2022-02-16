package com.yuan.total;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RequestTotalListener implements ServletContextListener, ServletRequestListener {
  @Override
  public void requestDestroyed(ServletRequestEvent sre) {

  }

  @Override
  public void requestInitialized(ServletRequestEvent sre) {
    System.out.println("start init request");
    HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
    String url = request.getRequestURL().toString();
    if (url.endsWith("/rt")) {
      return;
    }

    List<String> timeList = (List<String>) sre.getServletContext().getAttribute("timeList");
    List<Integer> valueList = (List<Integer>) sre.getServletContext().getAttribute("valueList");

    Date date = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
    String time = sdf.format(date);

    // timestamp doesn't exist in timeList
    if (!timeList.contains(time)) {
      timeList.add(time);
      valueList.add(1);
    } else {
      int index = timeList.indexOf(time);
      int value = valueList.get(index);
      valueList.set(index, value + 1);
    }

    sre.getServletContext().setAttribute("timeList", timeList);
    sre.getServletContext().setAttribute("valueList", valueList);
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {

  }

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    // create time and value lists to save request timestamp and visit numbers
    List<String> timeList = new ArrayList<>();
    List<Integer> valueList = new ArrayList<>();

    sce.getServletContext().setAttribute("timeList", timeList);
    sce.getServletContext().setAttribute("valueList", valueList);

    System.out.println("request analysis initialized");
  }
}
