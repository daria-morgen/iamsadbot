package home.dkio.iamsadbot.service;

import home.dkio.iamsadbot.domain.Moods;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class InlineKeyboardButtonService {

    static InlineKeyboardMarkup getMoodsMarkup() {

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
}
