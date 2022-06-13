package com.prolosys.rfid.microservices.masters.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mot.rfid.api3.ReaderInfo;
import com.mot.rfid.api3.SystemInfo;
import com.mot.rfid.api3.VersionInfo;
import com.prolosys.rfid.common.constants.RfidReaderTypeEnum;
import com.prolosys.rfid.common.models.MainModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RfidReaderModel extends MainModel{

	private static final long serialVersionUID = 7865564178817138890L;
	
	private Long id;
    private String name;
    private String username;
    private String password;
    private String ip;
    private String location;
    private String message;
    private Integer port;
    private Integer rssi;
    private Integer attempts = 0;
    private RfidReaderTypeEnum type;
    private Boolean connected = false;
	private Boolean scanning  = false;
	private Boolean accessSequenceRunning  = false;
	private Boolean loggedIn  = false;
	private Boolean recording = false;
	private Boolean tasks =  false;
	
	private VersionInfo versionInfo;
	private List<AntennaModel> antennas = new ArrayList<AntennaModel>();
	private ReaderInfo readerInfo;
	private SystemInfo systemInfo;
	private Map<String, String> capabilities = new HashMap<String, String>();
	
	private Boolean monitoring = false;
	private Date monitoringDate;
	private Boolean toRefresh = false;
	
	private CenterModel center;

}
