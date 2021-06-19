import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@SessionScoped
public class CoffeeCDI implements Serializable {

    private String type;
    private Integer price;

    @EJB
    private CoffeeEJB coffeeEJB;

    @PostConstruct
    public void init() {
        this.coffeeEJB = new CoffeeEJB();
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

    public void add() {
        coffeeEJB.add(type, price);
    }

    public List<Coffee> coffeeList() {
        return coffeeEJB.coffeeList();
    }
}
