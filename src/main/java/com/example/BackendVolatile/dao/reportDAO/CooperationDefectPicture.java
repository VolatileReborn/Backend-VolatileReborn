package com.example.BackendVolatile.dao.reportDAO;

import com.example.BackendVolatile.dto.taskDTO.File;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
public class CooperationDefectPicture {

    @Setter(AccessLevel.NONE)
    private Long picture_id;

    private String picture_name;

    private String picture_url;

    private Long report_id;

    public CooperationDefectPicture(File file){
        this.picture_name = file.getFileName();
        this.picture_url = file.getFileURL();
    }

}
