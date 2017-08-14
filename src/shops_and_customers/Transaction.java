package shops_and_customers;

import java.util.Date;

public class Transaction {

    int transactionID;
    int vendorID;
    AccountType vendorAccount;
    int receiverID;
    AccountType receiverAccount;
    String productIdentifier;

    public Transaction(
            int transactionID,
            Transactee vendor,
            AccountType vendorAccount,
            Transactee receiver,
            AccountType receiverAccount,
            Product product
    ){
        this.transactionID = transactionID;
        this.vendorID = vendor.getTransacteeID();
        this.vendorAccount = vendorAccount;
        this.receiverID = receiver.getTransacteeID();
        this.receiverAccount = receiverAccount;
        this.productIdentifier = product.getIdentifier();

        if ( vendor.productInStore( product ) && receiver.authorise( receiverAccount, product.getRetailPrice() ) ){
            Product vendProduct = vendor.vendProduct( product, vendorAccount );
            receiver.receiveProduct( vendProduct, receiverAccount );
        }
    }

}
