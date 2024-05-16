package org.example.websitesellingphonesbackend.service;

import org.example.websitesellingphonesbackend.entities.LineItem;
import java.util.List;

public interface GLineItemService {
    List<LineItem> getAllLineItems();
    LineItem getLineItemById(Long id);
    LineItem createLineItem(LineItem lineItem);
    LineItem updateLineItem(Long id, LineItem lineItem);
    void deleteLineItem(Long id);
    void checkAndDeleteLineItems();
}
