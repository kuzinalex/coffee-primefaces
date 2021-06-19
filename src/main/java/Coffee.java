import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Coffee {
    @Id
    //@GeneratedValue
    //private Long id;
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

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

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
        if(obj==null){
            return false;
        }
        if (!(obj instanceof Coffee)){
            return false;
        }
        Coffee coffee=(Coffee) obj;

       return (this.type.equals(coffee.type));

    }
}
