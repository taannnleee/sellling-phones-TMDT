package org.example.websitesellingphonesbackend.entities;


import jakarta.persistence.*;
import lombok.*;
import org.example.websitesellingphonesbackend.Enum.EpaymentMethod;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Payment")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Payment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long paymentId;

    @Column(name = "payment_date")
    private Date paymentDate;

    @Column(name = "epayment_method")
    private EpaymentMethod epaymentMethod;

    @OneToOne(mappedBy = "payment")
    private Order_Product order;



}
