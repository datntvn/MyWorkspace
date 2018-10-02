/**
 * @author DatNT29
 *
 * File       : CCServices.java
 * Created On : 11/09/2018 (dd/MM/YYYY)
 */

package com.datnt.services;

import org.springframework.http.ResponseEntity;

import com.datnt.models.Device;

public interface HousholdServices {
    public ResponseEntity<Device> getDevices(String quantity, String contractType);
    public ResponseEntity<Device> getDevicesVerifyError(String quantity, String contractType);
}
