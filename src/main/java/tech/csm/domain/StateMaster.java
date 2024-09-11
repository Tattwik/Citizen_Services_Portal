package tech.csm.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString

@Table(name = "state_master")
public class StateMaster implements Serializable {

	@Id
	@Column(name="s_id")
	private Integer stateId;
	
	@Column(name="state_name")
	private String stateName;
	
}
