package com.eos.apps.assistant.objects;

import com.google.cloud.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShelfItem {
    String category;
    String id;
    String name;
    Timestamp needTime;
//    String createdById;
}
