import java.util.*;


public class Order  //This class will represent an order and has the following fields and return method
{
    private final String id; //Unique ID for the order
    private final double price; //Price of the order
    private int quantity; //Quantity of the order
    private final String side; //Side of the order (Buy or Sell)
    
    public Order(String id, double price, int quantity, String side) 
	{
        this.id = id;
        this.price = price;
        this.quantity = quantity;
        this.side = side;
    }
    
    public String getId() 
	{
        return id;
    }
    
    public double getPrice() 
	{
        return price;
    }
    
    public int getQuantity() 
	{
        return quantity;
    }
    
    public void setQuantity(int quantity) 
	{
        this.quantity = quantity;
    }
    
    public String getSide() 
	{
        return side;
    }
}



public class Orderbook //This class holds all the distinct orders ordered on their price level has the following methods.  This class uses two TreeMap data structures to keep all distinct orders ordered on their price level
{
    private final Map<Double, List<Order>> bids;
    private final Map<Double, List<Order>> asks;
    private final Map<String, Order> orders;
    
    public Orderbook() 
	{
		//The bids(buyOrders) TreeMap is sorted in descending order and the asks(sellOrders) TreeMap is sorted in ascending order
        bids = new TreeMap<>(Collections.reverseOrder());
        asks = new TreeMap<>();
        orders = new HashMap<>();
    }
	
	public void addOrder(Order order) //This method adds a new order to the appropriate TreeMap, generating a unique ID for the order and ordering it based on its price level
	{
        Map<Double, List<Order>> book = order.getSide().equals("BUY") ? bids : asks;
        if (!book.containsKey(order.getPrice())) 
		{
            book.put(order.getPrice(), new LinkedList<>());
        }
        book.get(order.getPrice()).add(order);
        orders.put(order.getId(), order);
    }
	
	public void deleteOrder(String id) //This deletes an order with a given ID from both TreeMaps
	{
        Order order = orders.get(id);
        if (order == null) 
		{
            return;
        }
        Map<Double, List<Order>> book = order.getSide().equals("BUY") ? bids : asks;
        List<Order> orders = book.get(order.getPrice());
        orders.remove(order);
        if (orders.isEmpty()) 
		{
            book.remove(order.getPrice());
        }
        this.orders.remove(id);
    }
	
	public void modifyOrder(String id, int newQuantity)  //Modifies an order's quantity based on its ID, causing it to lose priority in the orderbook queue
	{
        Order order = orders.get(id);
        if (order == null) 
		{
            return;
        }
        order.setQuantity(newQuantity);
        deleteOrder(id);
        addOrder(order);
    }
	
	public void print()  //Outputs buy and sell orders
	{
        System.out.println("BIDS:");
        for (Map.Entry<Double, List<Order>> entry : bids.entrySet()) 
		{
            double price = entry.getKey();
            List<Order> orders = entry.getValue();
            System.out.println(String.format("%.2f", price) + " " + orders.toString());
        }
        System.out.println("ASKS:");
        for (Map.Entry<Double, List<Order>> entry : asks.entrySet()) 
		{
            double price = entry.getKey();
            List<Order> orders = entry.getValue();
            System.out.println(String.format("%.2f", price) + " " + orders.toString());
        }
    }
}



