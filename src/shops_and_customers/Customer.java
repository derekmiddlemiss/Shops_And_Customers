package shops_and_customers;

public class Customer extends Transactee {

    private int customerID;

    public Customer(
            int transacteeID,
            int customerID,
            String name
    ){
        super(
                transacteeID,
                TransacteeType.CUSTOMER,
                name
        );
        this.customerID = customerID;
    }

    public void addAccount( Account account ){
        this.accounts.put( account.getType(), account );
    }

    public void receiveProduct( Product product, AccountType accountType ) {
        this.productList.storeProduct( product );
        this.accounts.get( accountType ).debit( product.getRetailPrice() );
    }

    public Product vendProduct( Product product, AccountType accountType ) {
        Product fetchProduct = this.productList.fetchProduct( product );
        this.accounts.get( accountType ).credit( product.getRetailPrice() );
        return fetchProduct;
    }

    public Boolean productInStore( Product product ) {
        return this.productList.productInStore( product );
    }


}
