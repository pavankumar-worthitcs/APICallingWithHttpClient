package com.wcs.APICallingWithHttpClient.DTO;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class UserDTORequest {
    private String name;
    private String job;

}
