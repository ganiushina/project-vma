package gpn.interfaces.providers;

import gpn.contract.Claim;
import gpn.contract.SystemUser;

import java.util.List;

public interface IUserProvider {
    /*Получить данные пользователя по УЗ*/
    SystemUser getUser(String userName);

    /**Проверить сущесвтует ли пользователь в базе*/
    boolean isUserExists(String userName);

    /**Добавить или обновить данные пользователя вместе с ролями*/
    void update(SystemUser user);

    /**Получить права пользователя*/
    List<Claim> getClaims(Long userId);
}