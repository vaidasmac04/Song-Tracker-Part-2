package lt.vu.qualifiers;

import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Model;

@Model
@Alternative
public class TestAccountInfoImpl implements IAccountInfo {
    @Override
    public String getInfo() {
        return "You are using test version of Song tracker. " +
               "The maximum number of songs is not limited but there may be some functionality that is not working properly.";
    }
}