package lt.vu.qualifiers;

import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Model;

@Model
@Alternative
public class VipAccountInfoImp implements IAccountInfo {
    @Override
    public String getInfo() {
        return "You are using VIP version of Song tracker. You can have as many songs as you want.";
    }
}
