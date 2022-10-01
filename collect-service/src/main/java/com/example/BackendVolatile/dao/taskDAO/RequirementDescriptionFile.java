package com.example.BackendVolatile.dao.taskDAO;

import com.example.BackendVolatile.dto.taskDTO.File;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
public class RequirementDescriptionFile {

    @Setter(AccessLevel.NONE)
    private Long file_id;

    private String file_name;

    private String file_url;
    private Long task_id; //foreign key

    public RequirementDescriptionFile(File file){
        this.file_name = file.getFileName();
        this.file_url = file.getFileURL();

    }
}
