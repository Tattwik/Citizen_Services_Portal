package tech.csm.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString

@Table(name="district")
public class DistrictMaster implements Serializable {

	@Id
	@Column(name="d_id")
	private Integer districtId;
	
	@Column(name="district_name")
	private String districtName;
	
	@ManyToOne
	@JoinColumn(name = "s_id")
	private StateMaster state;
}
