package com.jm.students.bot;

import com.jm.students.model.ServiceRequest;
import com.jm.students.model.User;
import com.jm.students.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;


@Component
public class TelegramBot extends TelegramLongPollingBot {

    private final UserService userService;


    @Value("${bot.name}")
    private String botUsername;

    @Value("${bot.token}")
    private String botToken;

    @Autowired
    public TelegramBot(UserService userService) {
        this.userService = userService;
    }


    @Override
    public String getBotUsername() {
        return botUsername;
    }


    @Override
    public String getBotToken() {
        return botToken;
    }




    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();

        if (message != null && message.hasText()) {
            switch (message.getText()) {
                case "/start":
                    sendMessage(message, "Привет, это BestCRMBot!!!!!");
                    break;
                case "/hello":
                    sendMessage(message, "Чем могу помочь?");
                    break;
                case "/service":
                    sendMessageFromRequest(message);
                    break;
            }
        }
    }


    public void sendMessage(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            setButtons(sendMessage);
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendMessageFromRequest(Message message) {
        SendMessage sendMessage = new SendMessage();
        User userFromDb = userService.getUserByTelegramId(String.valueOf(message.getChatId()));
        List<ServiceRequest> requestList = userFromDb.getRequestList();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(userFromDb.getTelegramChatId());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(String.valueOf(requestList));
        try {
            setButtons(sendMessage);
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    public void setButtons(SendMessage sendMessage) {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(keyboardMarkup);
        keyboardMarkup.setSelective(true);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add(new KeyboardButton("/start"));
        keyboardRow.add(new KeyboardButton("/hello"));
        keyboardRow.add(new KeyboardButton("/service"));

        keyboardRowList.add(keyboardRow);
        keyboardMarkup.setKeyboard(keyboardRowList);
    }

    public void sendMessageRequest(User user, ServiceRequest request) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(user.getTelegramChatId());
        sendMessage.setText(request.toString());
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
