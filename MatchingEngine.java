public class MatchingEngine //This class implements the logic for matching buy and sell orders based on their price levels. 
{
    private final Orderbook orderbook;
    
    public MatchingEngine() //Match the new order with existing orders in the orderbook and execute the trades at the best available price
	{
        orderbook = new Orderbook();
    }
    
    public void addOrder(Order order) 
	{
        orderbook.addOrder(order);
    }
    
    public void deleteOrder(String id) 
	{
        orderbook.deleteOrder(id);
    }
    
    public void modifyOrder(String id, int newQuantity) 
	{
        orderbook.modifyOrder(id, newQuantity);
	}
	
	public void printOrderbook() 
	{
        orderbook.print();
    }
}