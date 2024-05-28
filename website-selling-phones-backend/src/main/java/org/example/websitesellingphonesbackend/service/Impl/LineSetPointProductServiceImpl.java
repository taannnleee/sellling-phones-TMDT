package org.example.websitesellingphonesbackend.service.Impl;

import jakarta.transaction.Transactional;
import org.example.websitesellingphonesbackend.entities.Cart;
import org.example.websitesellingphonesbackend.entities.LineSetPointProduct;
import org.example.websitesellingphonesbackend.entities.Product;
import org.example.websitesellingphonesbackend.repositories.CartRepository;
import org.example.websitesellingphonesbackend.repositories.LineSetPointProductRepository;
import org.example.websitesellingphonesbackend.repositories.ProductRepository;
import org.example.websitesellingphonesbackend.service.LineSetPointProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class LineSetPointProductServiceImpl implements LineSetPointProductService {
    private final Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);
    @Autowired
    LineSetPointProductRepository lineSetPointProductRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public void insertLineSetPointProduct(Cart cart, Product product){
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();

        // Định dạng lại ngày giờ để loại bỏ phần nghìn giây
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = sdf.format(currentDate);
        System.out.println("date"+formattedDate);
        try {
            if(checkLineSetPointProduct(cart,product)==true){
                LineSetPointProduct lineSetPointProduct_exist = lineSetPointProductRepository.findByProductAndCart(product, cart);
                lineSetPointProduct_exist.setQuanlity(lineSetPointProduct_exist.getQuanlity()+1);
                lineSetPointProduct_exist.setDepositDate(currentDate);
                lineSetPointProductRepository.save(lineSetPointProduct_exist);
            }
            else{
                LineSetPointProduct lineSetPointProduct = new LineSetPointProduct();

                lineSetPointProduct.setProduct(product);
                lineSetPointProduct.setCart(cart);
                lineSetPointProduct.setPrice(product.getProductDetail().getPrice());
                lineSetPointProduct.setQuanlity(1);
                lineSetPointProduct.setCreatedDate(sdf.parse(formattedDate));
                lineSetPointProduct.setDepositDate(currentDate); // Set the deposit date
                lineSetPointProductRepository.save(lineSetPointProduct);
            }
        } catch (Exception e) {
            // Xử lý ngoại lệ
            e.printStackTrace();
            System.out.println("lỗi"+e.toString());
        }
    }

    @Override
    public void checkAndRemoveExpiredDeposits() {
        List<LineSetPointProduct> allDeposits = lineSetPointProductRepository.findAll();
        Date now = new Date();
        for (LineSetPointProduct deposit : allDeposits) {
            long diffInMillies = Math.abs(now.getTime() - deposit.getDepositDate().getTime());
            long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
            if (diff > 5) {
                lineSetPointProductRepository.delete(deposit);
            }
        }
    }
    public boolean checkLineSetPointProduct(Cart cart, Product product){
        LineSetPointProduct lineSetPointProduct = lineSetPointProductRepository.findByProductAndCart(product, cart);
        if (lineSetPointProduct!=null){
            return true ;
        }
        return false;
    }

    @Override
    public BigDecimal updateTotalPrice(Cart cart){
        BigDecimal total = BigDecimal.valueOf(0);
        List<LineSetPointProduct> lineSetPointProductList = listLineSetPointProductOfCart(cart);
        for(LineSetPointProduct lineSetPointProduct : lineSetPointProductList){
            BigDecimal itemTotal = lineSetPointProduct.getPrice().multiply(BigDecimal.valueOf(lineSetPointProduct.getQuanlity()));
            total = total.add(itemTotal);
        }
        cart.setTotalPrice(total);
        cartRepository.save(cart);
        return total;
    }



    @Override
    public List<LineSetPointProduct> listLineSetPointProductOfCart(Cart cart){

        try {
            return lineSetPointProductRepository.findByCart(cart);
        } catch (Exception e) {
            // Xử lý ngoại lệ
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public void increaseQuantity(Cart cart, Product product){
        LineSetPointProduct lineSetPointProduct = lineSetPointProductRepository.findByProductAndCart(product, cart);
        lineSetPointProduct.setQuanlity(lineSetPointProduct.getQuanlity()+1);
        lineSetPointProductRepository.save(lineSetPointProduct);
    }

    @Override
    public void decreaseQuantity(Cart cart, Product product){
        LineSetPointProduct lineSetPointProduct = lineSetPointProductRepository.findByProductAndCart(product, cart);
        if(lineSetPointProduct.getQuanlity()==1){
            return;
        }
        lineSetPointProduct.setQuanlity(lineSetPointProduct.getQuanlity()-1);
        lineSetPointProductRepository.save(lineSetPointProduct);
    }

    @Override
    public void deleteLineSetPointProductById(Long lineSetPointProductId){
        try {
            lineSetPointProductRepository.deleteById(lineSetPointProductId);
        } catch (Exception e) {
            // Xử lý ngoại lệ
            e.printStackTrace();
        }
    }
    @Override
    public void deleteAllLineSetPointProductByCart(Cart delete_cart){
        try {
            lineSetPointProductRepository.deleteAllByCart(delete_cart);
        } catch (Exception e) {
            // Xử lý ngoại lệ
            e.printStackTrace();
        }
    }

}
