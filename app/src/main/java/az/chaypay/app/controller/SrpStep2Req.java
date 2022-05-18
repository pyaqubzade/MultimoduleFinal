package az.chaypay.app.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SrpStep2Req {

    private String phoneNumber;

    private String srpA;

    private String srpM1;

}