package com.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

public class Hierarchy {
    @Setter
    class HeadTail {
        private String head;

        HeadTail(String head) {
            this.head = head;
        }

        public String getHead() {
            return head;
        }
    }

    public static HashMap<String, String> hierarchyMap;

    Hierarchy() {
        hierarchyMap = new HashMap<>();
        hierarchyMap.put(TypeKeyBoard.MENU_KEY, TypeKeyBoard.START);
        hierarchyMap.put(TypeKeyBoard.PROFILE_KEY, TypeKeyBoard.START);
        hierarchyMap.put(TypeKeyBoard.HISTORY_KEY, TypeKeyBoard.MENU_KEY);
        hierarchyMap.put(TypeKeyBoard.SEND_SMS_KEY, TypeKeyBoard.MENU_KEY);
    }
}
