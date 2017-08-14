import org.junit.Before;
import org.junit.Test;
import shops_and_customers.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CustomerTest {

    private Customer frank;
    private Account creditCard;
    private Product lotr;
    private Product ofotcn;

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
        ofotcn = new Product(
                "OneFlewOverTheCuckoosNest16723",
                7.99,
                10.99,
                "Copy of One Flew Over the Cuckoos Nest by Ken Kesey"
        );
    }

    @Test
    public void testGetName(){
        assertEquals( "Frank", frank.getName() );
    }

    @Test
    public void testGetTransacteeType(){
        assertEquals( TransacteeType.CUSTOMER, frank.getType() );
    }

    @Test
    public void testGetCustomerID(){
        assertEquals(1, frank.getCustomerID() );
    }

    @Test
    public void testGetTransacteeID(){
        assertEquals( 1, frank.getTransacteeID() );
    }

    @Test
    public void testHasAccountType__False(){
        assertFalse( frank.hasAccountType( AccountType.CREDITCARD ) );
    }

    @Test
    public void testAddAccount(){
        frank.addAccount( creditCard );
        assertTrue( frank.hasAccountType(AccountType.CREDITCARD) );
    }

    @Test
    public void testReceiveProduct(){
        frank.addAccount( creditCard );
        frank.receiveProduct( lotr, AccountType.CREDITCARD );
        assertEquals( -12.99, frank.getBalance( AccountType.CREDITCARD ), 0.001 );
        assertTrue( frank.productInStore( lotr ) );
    }

    @Test
    public void testVendProduct(){
        frank.addAccount( creditCard );
        frank.receiveProduct( lotr, AccountType.CREDITCARD );
        frank.receiveProduct( ofotcn, AccountType.CREDITCARD );
        Product vendProduct = frank.vendProduct( lotr, AccountType.CREDITCARD );
        assertEquals( vendProduct, lotr );
        assertFalse( frank.productInStore( lotr ) );
        assertTrue( frank.productInStore( ofotcn ) );
        assertEquals( -10.99, frank.getBalance( AccountType.CREDITCARD ), 0.001 );
    }

}
