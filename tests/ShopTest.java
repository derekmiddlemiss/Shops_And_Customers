import org.junit.Before;
import org.junit.Test;
import shops_and_customers.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ShopTest {

    private Shop bookShop;
    private Product lotr;
    private Product ofotcn;

    @Before
    public void before(){
        bookShop = new Shop(
                1,
                1,
                "Black Books"
        );
        lotr = new Product(
                "LordOfTheRings11234",
                8.99,
                12.99,
                "Unabridged copy of The Lord of the Rings by J.R.R. Tolkien"
        );
        ofotcn = new Product(
                "OneFlewOverTheCuckoosNest16723",
                7.99,
                10.99,
                "Copy of One Flew Over the Cuckoos Nest by Ken Kesey"
        );
    }

    @Test
    public void testGetName(){
        assertEquals( "Black Books", bookShop.getName() );
    }

    @Test
    public void testGetTransacteeType(){
        assertEquals( TransacteeType.SHOP, bookShop.getType() );
    }

    @Test
    public void testGetShopID(){
        assertEquals(1, bookShop.getShopID() );
    }

    @Test
    public void testGetTransacteeID(){
        assertEquals( 1, bookShop.getTransacteeID() );
    }

    @Test
    public void testHasAccountType__False(){
        assertFalse( bookShop.hasAccountType( AccountType.CREDITCARD ) );
    }

    @Test
    public void testRestockProduct(){
        bookShop.restockProduct( lotr );
        assertTrue( bookShop.productInStore( lotr ) );
        assertEquals( 491.01, bookShop.getBalance( AccountType.CURRENT), 0.001 );
    }


}
