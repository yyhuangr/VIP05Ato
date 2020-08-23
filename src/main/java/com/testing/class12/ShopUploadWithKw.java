package com.testing.class12;

import com.testing.common.AutoLogger;
import com.testing.inter.HttpClientKw;

public class ShopUploadWithKw {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HttpClientKw key = new HttpClientKw();
		String result = key.doUpload(
				"http://www.testingedu.com.cn:8000/index.php/home/Uploadify/imageUp/savepath/head_pic/pictitle/banner/dir/images",
				"{\"file\":\"E:\\\\QSwork\\\\素材\\\\图片素材\\\\疫情.png\"}", "{\"id\":\"WU_FILE_0\",\"type\":\"image/png\"}");
		AutoLogger.log.info(result);
	}

}
