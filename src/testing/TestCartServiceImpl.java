package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.jupiter.api.Test;
import com.shashi.service.impl.CartServiceImpl;
import com.shashi.utility.DBUtil;

class TestCartServiceImpl
{

	@Test
	void addUsedProductToCart()
	{
		CartServiceImpl csi = new CartServiceImpl();
		Connection con = DBUtil.provideConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		int oldQuantity = 0;
		try
		{

			ps = con.prepareStatement("select * from usercart where username=? and prodid=? and used=?");
			ps.setString(1, "guest@gmail.com");
			ps.setString(2, "P20230423083830");
			ps.setBoolean(3, true);
			rs = ps.executeQuery();
			
			if (rs.next())
			{
				oldQuantity = rs.getInt(6);
			}
			
			csi.addProductToCart("guest@gmail.com", "P20230423083830", 2, true);
			rs = ps.executeQuery();
			int newQuantity = rs.getInt(6);
			assertTrue(newQuantity == oldQuantity || newQuantity == oldQuantity + 2);
		}
		
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		DBUtil.closeConnection(con);
		DBUtil.closeConnection(ps);
	}
	
	@Test
	void updateProductFromCart()
	{
		CartServiceImpl csi = new CartServiceImpl();
	}

}
