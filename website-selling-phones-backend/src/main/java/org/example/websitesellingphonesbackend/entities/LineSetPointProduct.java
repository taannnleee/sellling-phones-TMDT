package org.example.websitesellingphonesbackend.entities;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Entity
@Table(name = "LineSetPointProduct")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class LineSetPointProduct implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quanlity")
    private int quanlity;

    @Column(name = "price")
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "cart_id", referencedColumnName = "cart_id")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product product;

    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "deposit_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date depositDate;
}
