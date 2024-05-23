package org.example.websitesellingphonesbackend.service.Impl;

import jakarta.transaction.Transactional;
import org.example.websitesellingphonesbackend.entities.Cart;
import org.example.websitesellingphonesbackend.entities.LineItem;
import org.example.websitesellingphonesbackend.entities.Product;
import org.example.websitesellingphonesbackend.repositories.CartRepository;
import org.example.websitesellingphonesbackend.repositories.LineItemRepository;
import org.example.websitesellingphonesbackend.repositories.ProductRepository;
import org.example.websitesellingphonesbackend.service.LineItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class LineItemServiceImpl implements LineItemService {
    private final Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);
    @Autowired
    LineItemRepository lineItemRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ProductRepository productRepository;
    @Override
    public void insertLineItem(Cart cart, Product product){
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();

        // Định dạng lại ngày giờ để loại bỏ phần nghìn giây
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = sdf.format(currentDate);
        System.out.println("date"+formattedDate);
        try {
            if(checkLineItem(cart,product)==true){
                LineItem lineItem_exist   = lineItemRepository.findByProductAndCart(product, cart);
                lineItem_exist.setQuanlity(lineItem_exist.getQuanlity()+1);
                lineItemRepository.save(lineItem_exist);
            }
            else{
                LineItem lineItem = new LineItem();

                lineItem.setProduct(product);
                lineItem.setCart(cart);
                lineItem.setPrice(product.getProductDetail().getPrice());
                lineItem.setQuanlity(1);
                lineItem.setCreatedDate(sdf.parse(formattedDate));
                lineItemRepository.save(lineItem);
            }
        } catch (Exception e) {
            // Xử lý ngoại lệ
            e.printStackTrace();
            System.out.println("lỗi"+e.toString());
        }
    }

    public boolean checkLineItem(Cart cart, Product product){
        LineItem lineItem = lineItemRepository.findByProductAndCart(product, cart);
        if (lineItem!=null){
            return true ;
        }
        return false;
    }

    @Override
    public BigDecimal updateTotalPrice(Cart cart){
        BigDecimal total = BigDecimal.valueOf(0);
        List<LineItem> lineItemList = listLineItemOfCart(cart);
        for(LineItem lineItem : lineItemList){
            BigDecimal itemTotal = lineItem.getPrice().multiply(BigDecimal.valueOf(lineItem.getQuanlity()));
            total = total.add(itemTotal);
        }
        cart.setTotalPrice(total);
        cartRepository.save(cart);
        return total;
    }

    @Override
    public List<LineItem> listLineItemOfCart(Cart cart){

        try {
            return lineItemRepository.findByCart(cart);
        } catch (Exception e) {
            // Xử lý ngoại lệ
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public void increaseQuantity(Cart cart, Product product){
        LineItem lineItem = lineItemRepository.findByProductAndCart(product, cart);
        lineItem.setQuanlity(lineItem.getQuanlity()+1);
        lineItemRepository.save(lineItem);
    }

    @Override
    public void decreaseQuantity(Cart cart, Product product){
        LineItem lineItem = lineItemRepository.findByProductAndCart(product, cart);
        if(lineItem.getQuanlity()==1){
            return;
        }
        lineItem.setQuanlity(lineItem.getQuanlity()-1);
        lineItemRepository.save(lineItem);
    }

    @Override
    public void deleteLineItemById(Long lineItemId){
        try {
            lineItemRepository.deleteById(lineItemId);
        } catch (Exception e) {
            // Xử lý ngoại lệ
            e.printStackTrace();
        }
    }
    @Override
    public void deleteAllLineItemByCart(Cart delete_cart){
        try {
            lineItemRepository.deleteAllByCart(delete_cart);
        } catch (Exception e) {
            // Xử lý ngoại lệ
            e.printStackTrace();
        }
    }

}
