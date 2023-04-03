package int204.exam.controllers;

import int204.exam.dtos.OrderDto;
import int204.exam.dtos.PageDto;
import int204.exam.entities.Order;
import int204.exam.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("")
    public List<Order> getAllOrder(){
        return orderService.getAllOrder();
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Integer id){
        return orderService.getOrderById(id);
    }

    @PutMapping("/{id}")
    public Order updateOrder(@PathVariable Integer id,@RequestBody Order order){
        return orderService.updateOrder(id,order);
    }

    @PostMapping("")
    public void createOrder(@RequestBody Order order){
        orderService.createOrder(order);
    }

    @DeleteMapping("/{id}")
    public Order deleteOrder(@PathVariable Integer id){
        return orderService.deleteOrder(id);
    }

    @GetMapping("/page")
    public Page<Order> getOrders(@RequestParam(defaultValue = "0") Integer page,
                                 @RequestParam(defaultValue = "3") Integer size,
                                 @RequestParam(defaultValue = "id") String sort){
        return orderService.getOrders(page,size,sort);
    }
    @GetMapping("/status/{status}")
    public List<Order> getOrdersByStatus(@PathVariable String status){
        return orderService.geOrderByStatus(status);
    }
    @GetMapping("/dto")
    public List<OrderDto> getOrderDto(){
        return orderService.getOrdersDto();
    }
    @GetMapping("/dto/{id}")
    public OrderDto getSingleOrderDto(@PathVariable Integer id){
        return orderService.getSingleOrderDto(id);
    }
    @GetMapping("/page/dto")
    public PageDto<OrderDto> getPageDtoOrders(@RequestParam(defaultValue = "0") Integer page,
                                           @RequestParam(defaultValue = "1") Integer size,
                                           @RequestParam(defaultValue = "id") String sort){
        return orderService.getPageDtoOrders(page,size,sort);
    }
}
