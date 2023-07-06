package chand.security.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="user_info")
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UserInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Include
	private Integer id;
	
	
	private String email;
	
	private String name;
}
