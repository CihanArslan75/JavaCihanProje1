package com.cihan.swing.dao.product;

import java.util.List;

import org.hibernate.Criteria;

import com.cihan.swing.model.product.ProductOrder;
import com.cihan.swing.utils.DatabaseBaseService;
import org.hibernate.criterion.Order;


public class ProductOrderDao extends DatabaseBaseService<ProductOrder>{
    @Override
    public List<ProductOrder> getAllList(ProductOrder t) {
        try
        {
            openSession();
            Criteria cr=ss.createCriteria(t.getClass());
            cr.addOrder(Order.desc("orderCount"));
            List<ProductOrder> list = cr.list();
            closeSession();
            return list;
        }
        catch (Exception e) 
        {  logProduct("getAllListOrder  ; "  + t.getClass()+" ; "+e.getMessage() );
            return null;
        }
     }

}
