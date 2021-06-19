import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("CoffeeConverter")
public class CoffeeConverter implements Converter {

    CoffeeEJB coffeeEJB=new CoffeeEJB();
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
       Coffee coffee= coffeeEJB.getByType(s);
        return (Object) coffee;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        return o.toString();
    }
}
