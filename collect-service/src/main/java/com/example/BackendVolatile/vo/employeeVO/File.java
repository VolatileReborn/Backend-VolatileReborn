package com.example.BackendVolatile.vo.employeeVO;

import com.example.BackendVolatile.dao.reportDAO.CooperationDefectPicture;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class File {
    private String fileURL;
    private String fileName;

    public File(CooperationDefectPicture cooperationDefectPicture){
        this.fileName = cooperationDefectPicture.getPicture_name();
        this.fileURL = cooperationDefectPicture.getPicture_url();
    }
}
