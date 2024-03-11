package com.paymentDashboard.dashboard.repository;
import com.paymentDashboard.dashboard.domain.MyOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MyOrderRepository extends JpaRepository<MyOrder,Long> {
public MyOrder findByOrderId(String orderId);
List<MyOrder> findByEmail(String email);
}

