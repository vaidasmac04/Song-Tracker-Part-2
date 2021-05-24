package lt.vu.usecases;

import lt.vu.qualifiers.AccountInfo;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

@Model
public class AccountInfoForUser {
    @Inject
    private AccountInfo accountInfo;

    public String show() {
        return accountInfo.getInfo();
    }
}
