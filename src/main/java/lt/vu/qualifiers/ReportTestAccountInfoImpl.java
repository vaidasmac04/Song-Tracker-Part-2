package lt.vu.qualifiers;

import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Specializes;

@Model
@Specializes
@Alternative
public class ReportTestAccountInfoImpl extends TestAccountInfoImpl {
    @Override
    public String getInfo() {
        return "You are using test version of Song tracker. " +
               "The maximum number of songs is not limited but there may be some functionality that is not working properly. " +
               "You can report bugs via email songtracker@gmail.com.";
    }
}
