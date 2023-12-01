package Usecase.UpdateGPTcomment;

import domain.User;

public interface GPTupdateDAI {
    String getComment(User user, String weather);
}
