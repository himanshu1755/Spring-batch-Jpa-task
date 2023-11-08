package com.dataflow.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

import javax.persistence.*;

//@Entity
@Table(name = "eit_dmn_staging_shadow", schema = "public")
@Getter
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EitDmnStagingShadow {

	@Id
	@Column(name = "id", nullable = false, length = 255)
	private String id;

	@Column(name = "lanip1", length = 255)
	private String lanip1;

	@Column(name = "model", length = 255)
	private String model;

	@Column(name = "softwareversion", length = 255)
	private String softwareVersion;

	@Column(name = "diuserialnum", length = 255)
	private String diuserialnum;

	@Column(name = "userdef2", length = 255)
	private String userdef2;

	@Column(name = "userdef1", length = 255)
	private String userdef1;

	@Column(name = "nmd", length = 255)
	private String nmd;

	@Column(name = "nocid", length = 255)
	private String nocid;

	@Column(name = "update_dttm")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateDttm;

	@Column(name = "fetch_dttm")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fetchDttm;



}
