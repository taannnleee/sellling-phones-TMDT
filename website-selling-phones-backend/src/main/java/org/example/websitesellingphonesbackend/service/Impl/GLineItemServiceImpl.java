package org.example.websitesellingphonesbackend.service.Impl;

import org.example.websitesellingphonesbackend.entities.LineItem;
import org.example.websitesellingphonesbackend.repositories.GLineItemReponsitory;
import org.example.websitesellingphonesbackend.service.GLineItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
public class GLineItemServiceImpl implements GLineItemService {

    @Autowired
    private GLineItemReponsitory lineItemRepository;

    @Override
    public List<LineItem> getAllLineItems() {
        return lineItemRepository.findAll();
    }

    @Override
    public LineItem getLineItemById(Long id) {
        return lineItemRepository.findById(id).orElse(null);
    }

    @Override
    public LineItem createLineItem(LineItem lineItem) {
        return lineItemRepository.save(lineItem);
    }

    @Override
    public LineItem updateLineItem(Long id, LineItem lineItem) {
        LineItem existingLineItem = lineItemRepository.findById(id).orElse(null);
        if (existingLineItem != null) {
            existingLineItem.setQuanlity(lineItem.getQuanlity());
            existingLineItem.setPrice(lineItem.getPrice());
            existingLineItem.setCart(lineItem.getCart());
            existingLineItem.setProduct(lineItem.getProduct());
            existingLineItem.setCreatedDate(lineItem.getCreatedDate());
            return lineItemRepository.save(existingLineItem);
        }
        return null;
    }

    @Override
    public void deleteLineItem(Long id) {
        lineItemRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void checkAndDeleteLineItems() {
        List<LineItem> lineItems = lineItemRepository.findAll();

        for (LineItem lineItem : lineItems) {
            Date createdDate = lineItem.getCreatedDate();
            Instant now = Instant.now();
            Instant createdInstant = createdDate.toInstant();
            long daysBetween = Duration.between(createdInstant, now).toDays();

            if (daysBetween > 5) {
                lineItemRepository.delete(lineItem);
            } else {
                System.out.println("Số ngày còn lại: " + (5 - daysBetween));
            }
        }
    }
}
