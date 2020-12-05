package io.github.darryring.learn.test.extend;

/**
 * @author darryring
 **/
public class Main {

    public static void sendMessage(Message message) {
        if (message instanceof PopupMessage) {
            sendMessage2((PopupMessage) message);
            return;
        }
        System.out.println(message.getTime());
    }

    public static void sendMessage2(PopupMessage message) {
        System.out.println(message.getBizId());
    }

    public static void main(String[] args) {
        int way = 1;
        Message message;
        switch (way) {
            case 1:
                message = new Message();
                break;
            case 2:
                PopupMessage popupMessage = new PopupMessage();
                popupMessage.setBizId("1");
                message = popupMessage;
                break;
            default:
                throw new RuntimeException("消息类型错误，way：" + way);
        }
        message.setTime(System.currentTimeMillis());

        sendMessage(message);
    }
}
