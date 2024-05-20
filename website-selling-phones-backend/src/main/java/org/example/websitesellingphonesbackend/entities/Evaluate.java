package org.example.websitesellingphonesbackend.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Evaluate")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Evaluate implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long evaluateId;

    private int rating;

    private String review;

    private String authorName;

    private String authorPhone;

    private Date reviewDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product product;

}
