package com.zcj.test;

import org.junit.Test;

import com.zcj.shm.user.domain.ShmUser;
import com.zcj.shm.util.ObjectUtil;

public class test {

	@Test
	public void test1() {
		ShmUser user = new ShmUser();
		user.setUserName("123");
		boolean checkUNPE = ObjectUtil.checkUNPE(new String[] { "U", "N" }, user);
		System.out.println(checkUNPE);
	}

}
