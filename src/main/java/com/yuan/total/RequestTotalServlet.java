package com.yuan.total;

import com.alibaba.fastjson.JSON;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "RequestTotalServlet", value = "/rt")
public class RequestTotalServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    ServletContext context = request.getServletContext();
    List<String> timeList = (List<String>) context.getAttribute("timeList");
    List<Integer> valueList = (List<Integer>) context.getAttribute("valueList");

    response.setContentType("text/html;charset=utf-8");
//    response.getWriter().println(timeList.toString());
//    response.getWriter().println("<br />");
//    response.getWriter().println(valueList.toString());

    Map<String, List> result = new HashMap<>();
    result.put("timeList", timeList);
    result.put("valueList", valueList);
    String jsonStr = JSON.toJSONString(result);
    response.getWriter().println(jsonStr);

  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

  }
}
