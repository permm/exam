package int204.exam.repositories;


import int204.exam.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//                                                     class  datatype id
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findAllByStatus(String status);
    List<Order> findAllByCusNo(Integer cusNo);

    @Query("select o from Order o where o.status =?1")
    List<Order> findWithStatus(String status);
}
