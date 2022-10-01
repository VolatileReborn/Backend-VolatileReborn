package com.example.BackendVolatile.vo.stakeholder;

import com.example.BackendVolatile.util.constant.TestDevice;
import com.example.BackendVolatile.vo.ResultVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrowserDevicesVO {
    private ResultVO response;
    private List<DeviceStateVO> deviceList;
    private Integer currSumSize = TestDevice.DEVICE_TYPE_NUM;
}
