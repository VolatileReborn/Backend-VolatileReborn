package com.example.BackendVolatile.dao.taskDAO;

import com.example.BackendVolatile.dto.taskDTO.File;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
public class ExecutableFile {

    @Setter(AccessLevel.NONE)
    private Long executable_file_id;

    private String executable_file_name;

    private String executable_file_url;
    private Long task_id; //foreign key
    public ExecutableFile(File file){
        this.executable_file_name = file.getFileName();
        this.executable_file_url = file.getFileURL();

    }

    public void setTask_id(long task_id) {
        this.task_id = task_id;
    }
}
