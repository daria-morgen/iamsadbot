package home.dkio.iamsadbot.service.impl;

import home.dkio.iamsadbot.domain.Moods;
import home.dkio.iamsadbot.domain.User;
import home.dkio.iamsadbot.domain.Wish;
import home.dkio.iamsadbot.domain.Wishes;
import home.dkio.iamsadbot.utils.DialogTypes;
import home.dkio.iamsadbot.utils.KeyIdentifiers;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import javax.validation.constraints.NotNull;
import java.util.*;

public class InlineKeyboardButtonService {

    public static InlineKeyboardMarkup getMoodsMarkup() {

        Moods[] moods = Moods.values();

        List<InlineKeyboardButton> allKeyboardButtons = new ArrayList<>();

        for (Moods e : moods) {
            InlineKeyboardButton button = InlineKeyboardButton.builder()
                    .text(e.getName())
                    .callbackData(e.getName())
                    .build();

            allKeyboardButtons.add(button);
        }

        List<InlineKeyboardButton> keyboardButtonsRow = new ArrayList<>();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        int countButton = 0;
        for (InlineKeyboardButton e : allKeyboardButtons) {
            if (countButton == 2) {
                rowList.add(List.copyOf(keyboardButtonsRow));
                keyboardButtonsRow = new ArrayList<>();
                countButton = 0;
            }
            keyboardButtonsRow.add(e);
            countButton++;
        }

        return InlineKeyboardMarkup.builder().keyboard(rowList).build();
    }

    public static InlineKeyboardMarkup getYesNoMarkup() {

        InlineKeyboardButton yesButton = InlineKeyboardButton.builder()
                .text(DialogTypes.YES)
                .callbackData(DialogTypes.SUPPORT)
                .build();

        InlineKeyboardButton noButton = InlineKeyboardButton.builder()
                .text(DialogTypes.NO)
                .callbackData(DialogTypes.NO)
                .build();

        List<InlineKeyboardButton> keyboardButtonsRow = Arrays.asList(yesButton, noButton);
        List<List<InlineKeyboardButton>> rowList = Collections.singletonList(keyboardButtonsRow);

        return InlineKeyboardMarkup.builder().keyboard(rowList).build();
    }

    public static ReplyKeyboard getFewUser(Set<User> users) {

        List<User> fourRandomUser = UserService.getFourRandomUser(users);

        List<InlineKeyboardButton> allKeyboardButtons = new ArrayList<>();

        for (User e : fourRandomUser) {
            InlineKeyboardButton button = InlineKeyboardButton.builder()
                    .text(e.getName())
                    .callbackData(KeyIdentifiers.userName + e.getName())
                    .build();

            allKeyboardButtons.add(button);
        }

        List<InlineKeyboardButton> keyboardButtonsRow = new ArrayList<>();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

        if (allKeyboardButtons.size() <= 4) {
            for (int i = 0; i < allKeyboardButtons.size(); i++) {
                InlineKeyboardButton e = allKeyboardButtons.get(i);
                keyboardButtonsRow.add(e);
                if (i == 1 || i == 3) {
                    rowList.add(List.copyOf(keyboardButtonsRow));
                    keyboardButtonsRow = new ArrayList<>();
                }
            }
        } else if (allKeyboardButtons.size() > 4) {
            int countButton = 0;
            for (InlineKeyboardButton e : allKeyboardButtons) {
                if (countButton == 2) {
                    rowList.add(List.copyOf(keyboardButtonsRow));
                    keyboardButtonsRow = new ArrayList<>();
                    countButton = 0;
                }
                keyboardButtonsRow.add(e);
                countButton++;
            }
        }


        return InlineKeyboardMarkup.builder().keyboard(rowList).build();
    }

    public static ReplyKeyboard getWishes(@NotNull String userName, Iterable<Wish> allWishes) {

        List<InlineKeyboardButton> allKeyboardButtons = new ArrayList<>();

        for (Wish e : allWishes) {
            InlineKeyboardButton button = InlineKeyboardButton.builder()
                    .text(e.getName())
                    .callbackData(KeyIdentifiers.id + e.getId() + " " + KeyIdentifiers.userName + userName)
                    .build();

            allKeyboardButtons.add(button);
        }

        List<InlineKeyboardButton> keyboardButtonsRow = new ArrayList<>();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

        for (InlineKeyboardButton e : allKeyboardButtons) {

            keyboardButtonsRow.add(e);
            rowList.add(List.copyOf(keyboardButtonsRow));
            keyboardButtonsRow = new ArrayList<>();

        }
        return InlineKeyboardMarkup.builder().keyboard(rowList).build();
    }


}
