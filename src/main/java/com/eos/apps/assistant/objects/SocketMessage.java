package com.eos.apps.assistant.objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SocketMessage {
    Object entity;
    MessageType type;
}
