package bank;

import bank.authentication.Credential;
import bank.user.ContactInfo;
import bank.user.Documents;
import bank.user.GovernmentId;
import bank.user.User;

public interface AuthenticationService {
    boolean registerUser(Documents documents, ContactInfo contactInfo);

    boolean confirmPhone(String verificationCode);

    boolean confirmEmail(String verificationCode);

    User verifyIdentity(Credential credential);

    boolean verifyUser(User user, GovernmentId governmentId);

}
