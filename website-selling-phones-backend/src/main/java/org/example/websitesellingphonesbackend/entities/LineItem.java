package org.example.websitesellingphonesbackend.entities;

import jakarta.persistence.*;
import lombok.*;
import org.example.websitesellingphonesbackend.entities.Product;

import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "LineItem")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class LineItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quanlity")
    private int quanlity;

    @Column(name = "price")
    private Float price;

    @ManyToOne
    @JoinColumn(name = "cart_id", referencedColumnName = "cart_id")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product product;

    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
}
