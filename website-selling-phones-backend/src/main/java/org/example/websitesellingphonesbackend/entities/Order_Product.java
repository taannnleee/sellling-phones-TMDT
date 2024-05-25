package org.example.websitesellingphonesbackend.entities;

import jakarta.persistence.*;
import lombok.*;
import org.example.websitesellingphonesbackend.Enum.EStatus;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Order_Product")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Order_Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "order_date")
    private Date orderDate;

    @Column(name = "total")
    private BigDecimal total;

    @Column(name = "order_status")
    private EStatus orderStatus    ;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "cart_id", referencedColumnName = "cart_id")
    private Cart cart;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_id", referencedColumnName = "payment_id")
    private Payment payment;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private List<OrderDetailLine> orderDetailLines;
}
