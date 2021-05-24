package lt.vu.qualifiers;

import javax.enterprise.inject.Model;

@Model
public class BasicAccountInfoImp implements AccountInfo {
    @Override
    public String getInfo() {
        return "You are using free version of Song tracker. The maximum number of songs that you can have is 50.";
    }
}
