package roman.pidkostelnyi.victoriaarmario.tool.telegram;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import roman.pidkostelnyi.victoriaarmario.entity.Comment;
import roman.pidkostelnyi.victoriaarmario.entity.Order;
import roman.pidkostelnyi.victoriaarmario.entity.Product;
import roman.pidkostelnyi.victoriaarmario.entity.ProductForOrder;

import static roman.pidkostelnyi.victoriaarmario.tool.telegram.TelegramConstants.*;

@Component
public class TelegramTool {

    @Value("${telegram.chat.id}")
    private String chatId;

    @Value("${telegram.token}")
    private String token;

    public void sendMsg(String token, String chatId, String msg) {
        RestTemplate restTemplate = new RestTemplate();
        String url = String.format(URL_TEMPLATE, token, chatId, msg);
        String response = restTemplate.getForObject(url, String.class);
    }

    public void sendCommentNotification(Comment comment) {
        String msg = String.format(COMMENT_NOTIFICATION_TEMPLATE, comment.getUsername(), comment.getRating(), comment.getText(), comment.getProduct().getId(), comment.getProduct().getName());
        sendMsg(token, chatId, msg);
    }

    public void sendOrderNotification(Order order) {
        StringBuilder sb = new StringBuilder();
        order.getProductsForOrder().stream().map(this::orderItemToStringForNotification).forEach(sb::append);
        final String orderMsg = String.format(ORDER_NOTIFICATION_TEMPLATE, order.getId(), order.getName(), order.getPhoneNumber(), sb.toString(), order.getComment(), order.getSum());
        sendMsg(token, chatId, orderMsg);
    }

    private String orderItemToStringForNotification(ProductForOrder productForOrder) {
        Product product = productForOrder.getProduct();
        final long totalPrice = product.getPrice() * productForOrder.getCount();
        return String.format(ORDER_ITEM_TEMPLATE, product.getCatalogNumber(), product.getName(), product.getPrice(), productForOrder.getCount(), totalPrice);
    }
}
