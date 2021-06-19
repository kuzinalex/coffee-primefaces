import org.primefaces.context.PrimeFacesContext;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@SessionScoped
public class CoffeeOrderCDI {

    private Long id;
    private String delivery;
    private Integer amount;
    private String deliveryTime = "10-11";
    private Integer cost;
    private Coffee coffee;

    private List<Coffee> coffeeList;

    @EJB
    private CoffeeOrderEJB coffeeOrderEJB;
    @EJB
    private CoffeeEJB coffeeEJB;

    @PostConstruct
    public void init() {
        this.coffeeEJB=new CoffeeEJB();
        this.coffeeOrderEJB = new CoffeeOrderEJB();
        coffeeList=coffeeEJB.coffeeList();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Coffee getCoffee() {
        return coffee;
    }

    public void setCoffee(Coffee coffee) {
        this.coffee = coffee;
    }

    public void validateAmount(FacesContext context,
                               UIComponent toValidate,
                               Object value) throws ValidatorException {
        Integer amount = (Integer) value;
        if (amount < 100) {
            FacesMessage message = new FacesMessage("Cannot buy less then 100g.");
            message.setSeverity(FacesMessage.SEVERITY_INFO); //как выглядит окошко с сообщением
            PrimeFacesContext.getCurrentInstance().addMessage(null, message);
            throw new ValidatorException(message);
        }
    }

    public void validateDeliveryTime(FacesContext context,
                                     UIComponent toValidate,
                                     Object value) throws ValidatorException {
        String time = (String) value;
        if (!time.matches("\\b(([0-1][0-9]|[2][0-4]):([0-5][0-9])-([0-1][0-9]|[2][0-4]):([0-5][0-9]))")) {
            FacesMessage message = new FacesMessage("Invalid delivery time format.");
            throw new ValidatorException(message);
        }
        int fromHours = Integer.parseInt(time.substring(0, 2));
        int fromMinutes = Integer.parseInt(time.substring(3, 5));
        int toHours = Integer.parseInt(time.substring(6, 8));
        int toMinutes = Integer.parseInt(time.substring(9));

        if (fromHours > toHours) {
            FacesMessage message = new FacesMessage("Invalid delivery time format.");
            throw new ValidatorException(message);
        }
    }

    public List<CoffeeOrder> orderList() {
        return coffeeOrderEJB.getOrders();
    }

    public List<Coffee> coffeeList() {
        final List<Coffee> coffeeList = new ArrayList<>();
        orderList().stream().forEach(order -> coffeeList.add(order.getCoffee()));
        return coffeeList;
    }

    public String save() {
        coffeeOrderEJB.saveOrUpdate(amount, delivery, deliveryTime, coffee,id);
        id=null;
        return "start";
    }

    public String update(CoffeeOrder coffeeOrder){
        setId(coffeeOrder.getId());
        setCoffee(coffeeOrder.getCoffee());
        setAmount(coffeeOrder.getAmount());
        setDelivery(coffeeOrder.getDelivery());
        setDeliveryTime(coffeeOrder.getDeliveryTime());
        return "addOrder";
    }

    public String deletePage(CoffeeOrder coffeeOrder){
        setId(coffeeOrder.getId());
        setCoffee(coffeeOrder.getCoffee());
        setAmount(coffeeOrder.getAmount());
        setDelivery(coffeeOrder.getDelivery());
        setDeliveryTime(coffeeOrder.getDeliveryTime());
        setCost(coffeeOrder.getCost());
        return "deletePage";
    }

    public String delete(){
        coffeeOrderEJB.delete(id);
        return "start";
    }
}
