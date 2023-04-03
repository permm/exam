package int204.exam.services;

import int204.exam.dtos.OrderDto;
import int204.exam.dtos.PageDto;
import int204.exam.entities.Order;
import int204.exam.repositories.OrderRepository;
import int204.exam.utils.ListMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ListMapper listMapper;

    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Integer id) {
        return orderRepository.findById(id).orElseThrow(()->new RuntimeException("id Not found!"));
    }

    public Order updateOrder(Integer id,Order order) {
        if(orderRepository.existsById(id)){
            Order updateOrder = getOrderById(id);
            updateOrder.setOrderDate(order.getOrderDate());
            updateOrder.setRequiredDate(order.getRequiredDate());
            updateOrder.setShippedDate(order.getShippedDate());
            updateOrder.setStatus(order.getStatus());
            updateOrder.setComments(order.getComments());
            orderRepository.saveAndFlush(updateOrder);
            return updateOrder;
        }
        else {
            throw new RuntimeException("cannot update");
        }
    }

    public void createOrder(Order order) {
        if(orderRepository.existsById(order.getId())){
            throw new RuntimeException("this id is exists");
        }else {
            orderRepository.saveAndFlush(order);
        }
    }

    public Order deleteOrder(Integer id) {
        if(orderRepository.existsById(id)){
            Order order = getOrderById(id);
            orderRepository.deleteById(id);
            return order;
        }else{
            throw new RuntimeException("cannot delete this id!");
        }
    }

    public Page<Order> getOrders(Integer page, Integer size, String sort) {
        Sort sort1 = Sort.by(sort);
        Pageable pageable = PageRequest.of(page,size,sort1);
        return orderRepository.findAll(pageable);
    }

    public List<Order> geOrderByStatus(String status) {
        return orderRepository.findWithStatus(status);
    }

    public List<OrderDto> getOrdersDto() {
        List<Order> orders = getAllOrder();
        //return orders.stream().map(o -> modelMapper.map(o, OrderDto.class)).collect(Collectors.toList());
        return listMapper.mapList(getAllOrder(), OrderDto.class,modelMapper);
    }

    public OrderDto getSingleOrderDto(Integer id) {
        return modelMapper.map(getOrderById(id), OrderDto.class);
    }

    public PageDto<OrderDto> getPageDtoOrders(Integer page,Integer size,String sort) {
        //
        return listMapper.toPageDTO(getOrders(page,size,sort),OrderDto.class,modelMapper);
    }
}
