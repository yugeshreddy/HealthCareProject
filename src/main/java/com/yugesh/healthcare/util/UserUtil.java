package com.yugesh.healthcare.util;

import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class UserUtil {

	public String genPwd() {
		return UUID.randomUUID()
				.toString()
				.replace("-","")
				.substring(0, 8);
	}
}
