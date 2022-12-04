package com.domain

import org.telegram.telegrambots.meta.api.methods.send.SendMessage

class SendMessageContext {
    var sendMessage: SendMessage
    var command: String?

    constructor(sendMessage: SendMessage, command: String) {
        this.sendMessage = sendMessage
        this.command = command
    }
}