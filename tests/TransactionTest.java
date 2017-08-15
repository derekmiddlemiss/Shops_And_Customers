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

    private int transactionID;
    Transactee vendor;
    AccountType vendorAccount;
    Transactee receiver;
    AccountType receiverAccount;
    Product product;

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
    public void testTransaction__lotrToFrank(){
        assertFalse( frank.productInStore( lotr ) );
        assertTrue( bookShop.productInStore( lotr ) );
        assertEquals( 491.01, bookShop.getBalance( AccountType.CURRENT ), 0.001 );

        transactionID = 1;
        vendor = bookShop;
        vendorAccount = null;
        receiver = frank;
        receiverAccount = AccountType.CREDITCARD;
        product = lotr;
        Transaction lotrToFrank = new Transaction( transactionID, vendor, vendorAccount, receiver, receiverAccount, product );

        assertEquals( -12.99, frank.getBalance( AccountType.CREDITCARD ), 0.001 );
        assertEquals( 12.99, bookShop.getBalance( AccountType.SALES ), 0.001 );
        assertEquals( 0.00, bookShop.getBalance( AccountType.REFUNDS ), 0.001 );
        assertTrue( frank.productInStore( lotr ) );
        assertFalse( bookShop.productInStore( lotr ) );
    }

    @Test
    public void testTransaction__lotrToFrank__lotrToShop(){
        assertFalse( frank.productInStore( lotr ) );
        assertTrue( bookShop.productInStore( lotr ) );
        assertEquals( 491.01, bookShop.getBalance( AccountType.CURRENT ), 0.001 );

        transactionID = 1;
        vendor = bookShop;
        vendorAccount = AccountType.SALES;
        receiver = frank;
        receiverAccount = AccountType.CREDITCARD;
        product = lotr;
        Transaction lotrToFrank = new Transaction( transactionID, vendor, vendorAccount, receiver, receiverAccount, product );

        transactionID = 2;
        vendor = frank;
        vendorAccount = AccountType.CREDITCARD;
        receiver = bookShop;
        receiverAccount = AccountType.REFUNDS;
        product = lotr;
        Transaction lotrToShop = new Transaction( transactionID, vendor, vendorAccount, receiver, receiverAccount, product );

        assertEquals( 0.00, frank.getBalance( AccountType.CREDITCARD ), 0.001 );
        assertEquals( 12.99, bookShop.getBalance( AccountType.SALES ), 0.001 );
        assertEquals( -12.99, bookShop.getBalance( AccountType.REFUNDS ), 0.001 );

        assertFalse( frank.productInStore( lotr ) );
        assertTrue( bookShop.productInStore( lotr ) );
    }

}
