package com.yijiacc.canvas.app.bank;

import com.yijiacc.canvas.app.bank.authentication.Credential;
import com.yijiacc.canvas.app.bank.user.ContactInfo;
import com.yijiacc.canvas.app.bank.user.Documents;
import com.yijiacc.canvas.app.bank.user.GovernmentId;
import com.yijiacc.canvas.app.bank.user.User;

public interface AuthenticationService {
    boolean registerUser(Documents documents, ContactInfo contactInfo);

    boolean confirmPhone(String verificationCode);

    boolean confirmEmail(String verificationCode);

    User verifyIdentity(Credential credential);

    boolean verifyUser(User user, GovernmentId governmentId);
}
