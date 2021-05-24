package lt.vu.usecases;

import lt.vu.qualifiers.IAccountInfo;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

@Model
public class AccountInfoForUser {
    @Inject
    private IAccountInfo accountInfo;

    public String show() {
        return accountInfo.getInfo();
    }
}
