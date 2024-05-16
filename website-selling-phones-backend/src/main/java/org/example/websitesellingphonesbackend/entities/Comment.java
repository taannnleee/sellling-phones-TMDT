package org.example.websitesellingphonesbackend.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;


@Entity
@Table(name = "Comment")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter

public class Comment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long commentID;

    @ManyToOne
    private Product product;

    private Float star;
    private String review;

    @ManyToOne
    private Customer     customer;
}
