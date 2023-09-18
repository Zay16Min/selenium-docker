package com.zna.util;

import com.zna.tests.vendorportal.model.VendorPortalTestData;

public class Demo {
	public static void main(String[] args) {
		VendorPortalTestData testData = JsonUtil.getTestData("test-data/vendor-portal/john.json", VendorPortalTestData.class);
		
		System.out.println(testData.monthlyEarning());
	}

}
