package org.example.websitesellingphonesbackend.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Cart")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter

public class Cart implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long cartId;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "total_price")
    private Float totalPrice;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "cart")
    private List<LineItem> lineItem;

//    cascade = CascadeType.ALL
    @OneToMany(mappedBy = "cart" )
    private List<Order_Product> orders;
}
