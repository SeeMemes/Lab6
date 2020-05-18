/**
 * This class contains the list of HumanBeings
 *
 * @param getHumanBeings allows us to get the List of HumanBeings from this class
 * @param getHumanBeing allows us to get concrete HumanBeing from list
 * @param setHumanBeings allows us to set our List of HumanBeings to this class
 */

package Server.MyOwnClasses;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name="humanList")
public class HumanList {
    private List<HumanBeing> humanBeings = new ArrayList<HumanBeing>(); //список людей (HumanBeing)
    private java.time.LocalDateTime creationDate;



    @XmlElement(name="humanBeing")
    public List<HumanBeing> getHumanBeings() {  //getter для списка людей
        return humanBeings;
    }

    public HumanBeing getHumanBeing(int i){ //getter для конкретного человека
        return humanBeings.get(i);
    }

    public void setHumanBeings(List<HumanBeing> humanBeings) {
        this.humanBeings = humanBeings;
    }

    public void creationDate_Now() { this.creationDate = LocalDateTime.now(); }

    public LocalDateTime getCreationDate() { return creationDate; }
}
