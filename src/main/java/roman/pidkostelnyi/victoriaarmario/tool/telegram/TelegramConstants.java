package roman.pidkostelnyi.victoriaarmario.tool.telegram;

public class TelegramConstants {
    public static final String URL_TEMPLATE = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s";
    public static final String ORDER_ITEM_TEMPLATE = "%s. %s: %dгрн. х %dшт. (%d)";
    public static final String ORDER_NOTIFICATION_TEMPLATE = "Замовлення %d:%nКлієнт: %s%nНомер: %s%nТовари: %n%s%nКоментар: %s%nСума: %d%n";
    public static final String COMMENT_NOTIFICATION_TEMPLATE = "Ім'я: %s%nРейтинг: %d%nКомментар: %s%nТовар(%d): %s%n";
}
