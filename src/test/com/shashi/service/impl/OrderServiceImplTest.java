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
import java.util.Arrays;

class OrderServiceImplTest
{
    @Test
    void getPreferenceByUser()
    {
        String[] types = {"mobile", "laptop", "tv", "camera", "speaker", "tablet", "fan", "cooler"};
        OrderServiceImpl osi = new OrderServiceImpl();
        String type_guest = osi.getPreferenceByUser("guest@gmail.com");
        String type_admin = osi.getPreferenceByUser("admin@gmail.com");
        List types_list = Arrays.asList(types);
        assertTrue(types_list.contains(type_guest) && types_list.contains(type_admin));
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
        DBUtil.closeConnection(con);
        DBUtil.closeConnection(ps);
    }

    @Test
    void getMostSellingItemsByType()
    {
        String[] types = {"mobile", "laptop", "tv", "camera", "speaker", "tablet", "fan", "cooler"};
        OrderServiceImpl osi = new OrderServiceImpl();
        Connection con = DBUtil.provideConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try
        {
            for (String s : types)
            {
                List<ProductBean> l = osi.getMostSellingItems(s);
                Iterator i = l.iterator();
                int size = 0;
                ps = con.prepareStatement("SELECT  p.* FROM product p LEFT JOIN orders o ON p.pid = o.prodid WHERE p.ptype=? GROUP BY p.pid, p.pname, p.ptype, p.pinfo, p.pprice, p.pquantity, p.image, p.usedpquantity, p.usedpprice, p.discountpprice ORDER BY SUM(o.quantity) DESC LIMIT 3");
                ps.setString(1, s);
                rs = ps.executeQuery();
                while (rs.next())
                {
                    size++;
                    if (i.hasNext())
                    {
                        assertEquals(rs.getString("pid"), (((ProductBean) i.next()).getProdId()));
                    }
                }
                assertEquals(size, l.size());
            }
        }

        catch (SQLException e)
        {
            e.printStackTrace();
        }

        DBUtil.closeConnection(con);
        DBUtil.closeConnection(ps);
    }

    @Test
    void getLeastSellingItems()
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
            ps = con.prepareStatement("SELECT  p.* FROM product p LEFT JOIN orders o ON p.pid = o.prodid GROUP BY p.pid, p.pname, p.ptype, p.pinfo, p.pprice, p.pquantity, p.image, p.usedpquantity, p.usedpprice, p.discountpprice ORDER BY SUM(o.quantity) LIMIT 3");
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
        DBUtil.closeConnection(con);
        DBUtil.closeConnection(ps);
    }

    @Test
    void getLeastSellingItemsByType()
    {
        String[] types = {"mobile", "laptop", "tv", "camera", "speaker", "tablet", "fan", "cooler"};
        OrderServiceImpl osi = new OrderServiceImpl();
        Connection con = DBUtil.provideConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try
        {
            for (String s : types)
            {
                List<ProductBean> l = osi.getMostSellingItems(s);
                Iterator i = l.iterator();
                int size = 0;
                ps = con.prepareStatement("SELECT  p.* FROM product p LEFT JOIN orders o ON p.pid = o.prodid GROUP BY p.pid, p.pname, p.ptype, p.pinfo, p.pprice, p.pquantity, p.image, p.usedpquantity, p.usedpprice, p.discountpprice ORDER BY SUM(o.quantity) LIMIT 3");
                ps.setString(1, s);
                rs = ps.executeQuery();
                while (rs.next())
                {
                    size++;
                    if (i.hasNext())
                    {
                        assertEquals(rs.getString("pid"), (((ProductBean) i.next()).getProdId()));
                    }
                }
                assertEquals(size, l.size());
            }
        }

        catch (SQLException e)
        {
            e.printStackTrace();
        }

        DBUtil.closeConnection(con);
        DBUtil.closeConnection(ps);
    }
}