package gpn.interfaces.service;

import gpn.contract.Claim;

import java.util.List;

/**Сервис для работы с правами пользователя*/
public interface IClaimsService {

    /**Установить пользователю новые права, при этом старые права удаляются*/
    void updateClaims(Long userId, List<Claim> claims);

    /**Получить список дефолтных прав для нового пользователя*/
    List<Claim> getDefaultUserClaims();
}
