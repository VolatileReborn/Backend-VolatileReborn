package com.example.BackendVolatile.dao.reportDAO;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
public class Cooperation {


    @Setter(AccessLevel.NONE)
    private Long cooperation_id;

    private Long report_id;

    private Long user_id;

    private Integer cooperation_state;
}
