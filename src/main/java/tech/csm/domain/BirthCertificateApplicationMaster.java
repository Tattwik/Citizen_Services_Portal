package tech.csm.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@	Getter
@Setter
@ToString

@Table(name="birth_certificate_application_master")
public class BirthCertificateApplicationMaster implements Serializable {

	@Id	
	@Column(name="b_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer bId;
	
	@Column(name="child_name")
	private String childName;
	
	@Column(name="father_name")
	private String fatherName;
	
	@Column(name="mother_name")
	private String motherName;
	
	@Column(name="place_of_birth")
	private String placeOfBirth;
	
	@Column(name="dob")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String dob;
	
	@Column(name="sex")
	private String gender;
	
	@ManyToOne
	@JoinColumn(name="s_id")
	private StateMaster state;
	
	@ManyToOne
	@JoinColumn(name="d_id")
	private DistrictMaster district;
	
	@Column(name="house_no_locality_zip")
	private String houseNoLocalityZip;
	
	@Transient
	MultipartFile file;
	
	@Column(name="address_proof")
	private String addressProof;
	
	@Column(name="applied_on")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private String appliedOn;
	
	@ManyToOne
	@JoinColumn(name="r_id")
	private UserRegistration userRegd;
	
	@Column(name="status")
	private String status;
	
	@Column(name="approved_by")
	private String approvedBy;
}
