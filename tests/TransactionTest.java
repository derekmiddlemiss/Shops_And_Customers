import org.junit.Test;
import shops_and_customers.*;
import org.junit.Before;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TransactionTest {

    private Customer frank;
    private Shop bookShop;
    private Account creditCard;
    private Product lotr;

    @Before
    public void before(){
        frank = new Customer(
                1,
                1,
                "Frank"
        );
        creditCard = new Account(
                AccountType.CREDITCARD,
                "Frank Mitty, Number 3434676784841212",
                0.0,
                -100.0
        );
        lotr = new Product(
                "LordOfTheRings11234",
                8.99,
                12.99,
                "Unabridged copy of The Lord of the Rings by J.R.R. Tolkien"
        );
        bookShop = new Shop(
                2,
                1,
                "Black Books"
        );
        frank.addAccount( creditCard );
        bookShop.restockProduct( lotr );
    }

    @Test
    public void testTransaction(){
        assertFalse( frank.productInStore( lotr ) );
        assertTrue( bookShop.productInStore( lotr ) );
        assertEquals( 491.01, bookShop.getBalance( AccountType.CURRENT ), 0.001 );
        Transaction lotrToFrank = new Transaction(
                1,
                bookShop,
                null,
                frank,
                AccountType.CREDITCARD,
                lotr
                );
        assertEquals( -12.99, frank.getBalance( AccountType.CREDITCARD ), 0.001 );
        assertEquals( 12.99, bookShop.getBalance( AccountType.SALES ), 0.001 );
        assertTrue( frank.productInStore( lotr ) );
        assertFalse( bookShop.productInStore( lotr ) );
    }

}
