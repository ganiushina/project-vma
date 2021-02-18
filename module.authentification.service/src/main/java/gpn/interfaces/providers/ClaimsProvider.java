package gpn.interfaces.providers;

import gpn.contract.Claim;

import java.util.List;

/** Провайдер данных для прав пользователя */
public interface ClaimsProvider {

    /* Удалить права пользователя*/
    void deleteClaims(Long userId);

    /*Удалить текущие права пользователя и сохранить новые */
    void updateClaims(Long userId, List<Claim> claims);

    /*Получить из базы существующий клейм или создать новый*/
    Claim getOrCreate(Claim claim);
}

