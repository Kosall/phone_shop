package org.kosal.phoneshop.kosal1_phoneshop.security;
import static org.kosal.phoneshop.kosal1_phoneshop.security.PermissionEnum.BRAND_READ;
import static org.kosal.phoneshop.kosal1_phoneshop.security.PermissionEnum.BRAND_WRITE;
import static org.kosal.phoneshop.kosal1_phoneshop.security.PermissionEnum.MODEL_READ;
import static org.kosal.phoneshop.kosal1_phoneshop.security.PermissionEnum.MODEL_WRITE;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum RoleEnum {
	ADMIN(Set.of(BRAND_WRITE,BRAND_READ,MODEL_READ,MODEL_WRITE)),
	
	
	SALE(Set.of(MODEL_READ,BRAND_READ));
	private Set<PermissionEnum> permissionEnums;
	public Set<SimpleGrantedAuthority> grantedAuthorities(){
		
		Set<SimpleGrantedAuthority> collect = permissionEnums.stream()
			.map(permissionEnums-> new SimpleGrantedAuthority(permissionEnums.getDescription()))
			.collect(Collectors.toSet());
		SimpleGrantedAuthority role= new SimpleGrantedAuthority("ROLE_"+this.name());
		collect.add(role);
		System.out.println(collect);
		return collect;
		
	}

}
