package com.project.projectback.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OmOdDtl2 implements Serializable {
	private static final long serialVersionUID = -5200824753588401614L;
	
	@Id
	private String odNo;
	private Integer odSeq;
	private Integer procSeq;
	private String odTypCd;
	private String odPrgsStepCd;

}
