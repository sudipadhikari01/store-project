package com.sudip.store.electronicstore.payload;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseMessage {
    private String message;
    private boolean success;
    private HttpStatus httpStatus;

}
