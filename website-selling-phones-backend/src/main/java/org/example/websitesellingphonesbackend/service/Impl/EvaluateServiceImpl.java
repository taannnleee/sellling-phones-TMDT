package org.example.websitesellingphonesbackend.service.Impl;

import org.example.websitesellingphonesbackend.entities.Evaluate;
import org.example.websitesellingphonesbackend.entities.Product;
import org.example.websitesellingphonesbackend.repositories.EvaluateRepository;
import org.example.websitesellingphonesbackend.service.EvaluateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluateServiceImpl implements EvaluateService {
    @Autowired
    EvaluateRepository evaluateRepository;

    @Override
    public void insetEvaluate(Evaluate evaluate){
        try {
            evaluateRepository.save(evaluate);
        }catch (Exception e){
            System.out.println("Error: " + e);
        }
    }

    @Override
    public  List<Evaluate> findEvaluateByProduct(Product product){
        try {
            List<Evaluate> evaluateList = evaluateRepository.findByProduct(product);
            return evaluateList;
        }catch (Exception e){
            System.out.println("Error: " + e);
        }
        return null;
    }


}
