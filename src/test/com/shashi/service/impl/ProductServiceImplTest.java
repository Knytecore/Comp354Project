package com.shashi.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import com.shashi.beans.ProductBean;
import java.util.List;
import java.util.Iterator;

class ProductServiceImplTest
{

    @org.junit.jupiter.api.Test
    void sellNUsedProduct()
    {
    }

    @org.junit.jupiter.api.Test
    void getLowStockProducts()
    {
        ProductServiceImpl psi = new ProductServiceImpl();
        List<ProductBean> l = psi.getLowStockProducts();
        Iterator i = l.iterator();
        while (i.hasNext())
        {
            assertTrue(((ProductBean) i.next()).getProdQuantity() <= 3);
        }
    }
}