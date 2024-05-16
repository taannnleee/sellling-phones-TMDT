package org.example.websitesellingphonesbackend.service;

import org.example.websitesellingphonesbackend.entities.Evaluate;
import org.example.websitesellingphonesbackend.entities.Product;

import java.util.List;

public interface EvaluateService {
    void insetEvaluate(Evaluate evaluate);
    List<Evaluate> findEvaluateByProduct(Product product);
}
