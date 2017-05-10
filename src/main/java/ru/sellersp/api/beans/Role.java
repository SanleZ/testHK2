package ru.sellersp.api.beans;

/**
 * Created by veldyasov on 16/04/2017.
 * Роли пользователя в сервисе
 */
public enum Role {
    ADMIN,          // админ
    SUPPORT,        // тех. поддержка сервиса
    ORG,            // организатор СП
    ASSISTANT,      // помощник орга
    BUYER,          // покупатель
    CUSTOMER        // поставщик (компания)
}
