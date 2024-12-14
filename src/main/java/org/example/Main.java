package org.example;

import java.util.ArrayList;
import java.util.List;

// Base class for products
abstract class Product {
    private String productId;
    private String label;
    private float quantity;
    private float price;

    public Product(String productId, String label, float quantity, float price) throws NegativePriceException {
        if (price < 0) {
            throw new NegativePriceException("Price cannot be negative: " + price);
        }
        this.productId = productId;
        this.label = label;
        this.quantity = quantity;
        this.price = price;
    }

    public String getProductId() {
        return productId;
    }

    public String getLabel() {
        return label;
    }

    public float getQuantity() {
        return quantity;
    }

    public float getPrice() {
        return price;
    }

    public abstract String determineProductType();
}

// Fruit class
class ProductFruit extends Product {
    private String harvestSeason;

    public ProductFruit(String productId, String label, float quantity, float price, String harvestSeason) throws NegativePriceException {
        super(productId, label, quantity, price);
        this.harvestSeason = harvestSeason;
    }

    public String getHarvestSeason() {
        return harvestSeason;
    }

    @Override
    public String determineProductType() {
        return "Fruit";
    }
}

// Vegetable class
class ProductVegetable extends Product {
    private String harvestSeason;

    public ProductVegetable(String productId, String label, float quantity, float price, String harvestSeason) throws NegativePriceException {
        super(productId, label, quantity, price);
        this.harvestSeason = harvestSeason;
    }

    public String getHarvestSeason() {
        return harvestSeason;
    }

    @Override
    public String determineProductType() {
        return "Vegetable";
    }
}

// Custom Exceptions
class FullStoreException extends Exception {
    public FullStoreException(String message) {
        super(message);
    }
}

class NegativePriceException extends Exception {
    public NegativePriceException(String message) {
        super(message);
    }
}

// Store class
class Store {
    private List<Product> products;
    private static final int MAX_SIZE = 2;

    public Store() {
        products = new ArrayList<>();
    }

    public void add(Product p) throws FullStoreException {
        if (products.size() >= MAX_SIZE) {
            throw new FullStoreException("Store is full. Cannot add product: " + p.getLabel());
        }
        products.add(p);
    }

    public void listProducts() {
        for (Product p : products) {
            System.out.println(p.getProductId() + " - " + p.getLabel() + " - " + p.determineProductType());
        }
    }

    public float calculateStock() {
        float total = 0;
        for (Product p : products) {
            if (p instanceof ProductFruit) {
                total += p.getQuantity();
            }
        }
        return total;
    }
}

class StoreManagement {
    public static void main(String[] args) {
        try {
            // Create Store
            Store store = new Store();

            // Create Products
            Product p1 = new ProductFruit("1254", "Strawberry", 12.3f, 5.0f, "March");
            Product p2 = new ProductFruit("1224", "Watermelon", 50f, 10.0f, "June");
            Product p3 = new ProductVegetable("8521", "Artichokes", 14f, 7.0f, "January");

            // Add Products to Store
            store.add(p1);
            store.add(p2);

            // Attempt to add a third product (should throw exception)
            store.add(p3);
        } catch (FullStoreException | NegativePriceException e) {
            System.out.println("Exception: " + e.getMessage());
        }

        // Freshness Check and other features remain unchanged.
    }
}
