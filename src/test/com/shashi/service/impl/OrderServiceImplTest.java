package com.shashi.service.impl;

import com.shashi.utility.DBUtil;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.shashi.beans.ProductBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Iterator;

class OrderServiceImplTest
{
    @Test
    void getPreferenceByUser()
    {
        OrderServiceImpl osi = new OrderServiceImpl();
        String type = osi.getPreferenceByUser("guest@gmail.com");
        assertTrue(type == "this" || type == "that");
    }

    @Test
    void getMostSellingItems()
    {

        OrderServiceImpl osi = new OrderServiceImpl();
        List<ProductBean> l = osi.getMostSellingItems();
        Connection con = DBUtil.provideConnection();

        PreparedStatement ps = null;
        ResultSet rs = null;
        Iterator i = l.iterator();
        int size = 0;

        try
        {
            ps = con.prepareStatement("SELECT  p.* FROM product p LEFT JOIN orders o ON p.pid = o.prodid GROUP BY p.pid, p.pname, p.ptype, p.pinfo, p.pprice, p.pquantity, p.image, p.usedpquantity, p.usedpprice, p.discountpprice ORDER BY SUM(o.quantity) DESC LIMIT 3");
            rs = ps.executeQuery();

            while (rs.next())
            {
                size++;
                if (i.hasNext())
                {
                    assertEquals(rs.getString("pid"), (((ProductBean) i.next()).getProdId()));
                }
            }
        }

        catch (SQLException e)
        {
            e.printStackTrace();
        }

        assertEquals(size, l.size());
    }

    @Test
    void getMostSellingItemsByType()
    {
        OrderServiceImpl osi = new OrderServiceImpl();
        List<ProductBean> l = osi.getMostSellingItems("");
    }

    @Test
    void getLeastSellingItems()
    {
        OrderServiceImpl osi = new OrderServiceImpl();
    }

    @Test
    void getLeastSellingItemsByType()
    {
        OrderServiceImpl osi = new OrderServiceImpl();
    }
}