package entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Coffee {
    @Id
    private String type;

    private Integer price;

    @OneToMany(mappedBy = "coffee")
    private List<CoffeeOrder> coffeeOrders;

    public List<CoffeeOrder> getOrders() {
        return coffeeOrders;
    }

    public void setOrders(List<CoffeeOrder> coffeeOrders) {
        this.coffeeOrders = coffeeOrders;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return type;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Coffee coffee = (Coffee) obj;
        if (type == null) {
            if (coffee.type != null)
                return false;
        } else if (!type.equals(coffee.type))
            return false;
        return true;
    }
}
