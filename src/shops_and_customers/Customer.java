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

    public int getCustomerID(){
        return this.customerID;
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

}
