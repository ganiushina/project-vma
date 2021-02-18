package gpn.interfaces.service;

import gpn.contract.Claim;
import gpn.contract.SystemUser;

import java.util.List;

/** Сервис для работы с пользователями */
public interface IUserService {
    /** Получить данные пользователя по УЗ*/
    SystemUser getUser(String userName);

    /**Добавить или обновить данные пользователя*/
    boolean update(SystemUser user);

    /* Получить права пользователя*/
    List<Claim> getClaims(Long userId);
}
