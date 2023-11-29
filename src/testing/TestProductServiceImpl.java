package testing;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.shashi.service.impl.ProductServiceImpl;
import java.util.List;
import com.shashi.beans.ProductBean;
import java.util.Iterator;

class ProductServiceImplTest
{

    @Test
    void sellNUsedProduct()
    {
        ProductServiceImpl psi = new ProductServiceImpl();
        psi.sellNUsedProduct("pid", 2);
    }

    @Test
    void getLowStockProducts()
    {
        ProductServiceImpl psi = new ProductServiceImpl();
        List<ProductBean> l = psi.getLowStockProducts();
        Iterator<ProductBean> i = l.iterator();
        while (i.hasNext())
        {
            assertTrue((i.next()).getProdQuantity() <= 3);
        }
    }
}
