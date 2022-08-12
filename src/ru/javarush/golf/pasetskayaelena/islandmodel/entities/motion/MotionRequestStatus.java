package ru.javarush.golf.pasetskayaelena.islandmodel.entities.motion;

public enum MotionRequestStatus {
    /**
     * Запрос создан в локации отправления
     * = 1
     */
    Outcoming,

    /**
     * Запрос создан в локации назначения
     * = 2
     */
    Incoming,

    /**
     * Запрос одобрен в локации назначения: животное перемещено
     * = 3
     */
    Approved,

    /**
     * Запрос отклонен в локации назначения: животное не может быть перемещено
     * = 4
     */
    Declined
}
