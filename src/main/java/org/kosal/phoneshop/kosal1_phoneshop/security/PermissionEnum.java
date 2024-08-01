package org.kosal.phoneshop.kosal1_phoneshop.security;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum PermissionEnum {
	BRAND_READ("brand:read"),
	BRAND_WRITE("brand:write"),
	MODEL_READ("model:read"),
	MODEL_WRITE("model:write");
	
	private String description;
	/*private PermissionEnum(String description) {
		
	}*/

}
