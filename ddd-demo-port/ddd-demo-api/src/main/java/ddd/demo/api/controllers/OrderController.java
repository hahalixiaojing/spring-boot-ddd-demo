package ddd.demo.api.controllers;

import com.alibaba.fastjson.JSON;
import ddd.demo.application.order.OrderApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderApplication orderApplication;

    @GetMapping("/id")
    @ResponseBody
    public String findById() throws Exception {

        return orderApplication.findById(0L).result(s -> {

            Map<String, Object> map = new HashMap<>();

            map.put("orderId", s.getId());
            map.put("createTime", s.getCreateTime().toString());
            map.put("orderStatus", s.getOrderStatus().value());

            return JSON.toJSONString(map);


        });
    }

    @GetMapping("/test")
    public void test(HttpServletResponse response) throws Exception {
        this.orderApplication.eventTest();
    }
}
