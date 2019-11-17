package com.example.hibernatedemo.controller;

import com.example.hibernatedemo.entity.manyToOne.nonPrimaryKey.Order;
import com.example.hibernatedemo.entity.manyToOne.nonPrimaryKey.Shipment;
import com.example.hibernatedemo.repository.OrderRepository;
import com.example.hibernatedemo.repository.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="shipment")
public class ShipmentController {

    @Autowired
    private ShipmentRepository shipmentRepository;

    @Autowired
    private OrderRepository orderRepository;

    @PostMapping(value = "post")
    public @ResponseBody String post(@RequestParam String code) {
        Shipment shipment = new Shipment();
        shipment.setShipmentCode(code);
        setOrderInfo(shipment);
        shipmentRepository.save(shipment);
        return "post";
    }
    @PostMapping(value = "change/order")
    public @ResponseBody String postOrder(@RequestParam Integer id, @RequestParam Integer shipmentId) {
        Order order = orderRepository.findById(id).get();
        Shipment shipment = shipmentRepository.findById(shipmentId).get();
        order.setShipment(shipment);
        orderRepository.save(order);
        return "change-order";
    }
    @GetMapping(value = "read")
    public String read(@RequestParam Integer id) {
        Shipment shipment = shipmentRepository.findById(id).get();
        System.out.println(shipment.getShipmentCode());
        for (Order order : shipment.getOrders()) {
            System.out.println(order.getOrderName());
        }
        return "read";
    }

    @GetMapping(value = "read/order")
    public String readInfo(@RequestParam Integer id) {
        Order order = orderRepository.findById(id).get();
        System.out.println(order.getOrderName());
        System.out.println(order.getShipment().getShipmentCode());
        return "read";
    }
    private void setOrderInfo(Shipment shipment) {
        List<Order> orders = new ArrayList<>();
        Order order = new Order();
        order.setOrderName(shipment.getShipmentCode()+"_1");
        order.setShipment(shipment);
        orders.add(order);
        Order order1 = new Order();
        order1.setOrderName(shipment.getShipmentCode()+"_2");
        order1.setShipment(shipment);
        orders.add(order1);
        shipment.setOrders(orders);
    }
}
