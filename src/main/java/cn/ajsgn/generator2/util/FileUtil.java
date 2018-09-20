/*
 * Copyright (c) 2017, Ajsgn 杨光 (Ajsgn@foxmail.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.ajsgn.generator2.util;

import java.io.File;

import org.apache.commons.lang3.StringUtils;

/**
 * <p>File Util</p>
 * @ClassName: FileUtil
 * @Description: File Util
 * @author Ajsgn@foxmail.com
 * @date 2017年10月22日 下午8:21:10
 */
public class FileUtil {
	
	/**
	 * <p>如果文件夹不存在，就创建文件夹</p>
	 * @Title: mkdirsIfNotExists
	 * @Description: 如果当前文件夹不存在，就创建当前文件夹
	 * @param files 文件夹列表
	 * @author Ajsgn@foxmail.com
	 * @date 2017年10月22日 下午8:21:28
	 */
	public static void mkdirsIfNotExists(File... files){
		for(File file:files){
			if(false == file.exists()){
				file.mkdirs();
			}else{
				if(false == file.isDirectory()){
					throw new IllegalArgumentException(String.format("the same file has exist ~", file.getAbsolutePath()));
				}
			}
		}
	}

	public static void fileReady(File file) {
		if(null != file) {
			if(true == file.exists()) {
				//如果文件存在，则不需要特殊处理
			}else {
				if(true == file.isDirectory()) {
					//文件不存在，并且是个文件夹，则直接创建文件目录
					file.mkdirs();
				}else {
					//文件不存在，并且文件不是文件夹，则创建父文件目录
					file.getParentFile().mkdirs();
				}
			}
		}
	}
	
	public static String filePathCompatible(String filePath) {
		if(true == StringUtils.isNotBlank(filePath)) {
			//创建文件，兼容文件的目录分隔符
			String separator = File.separator;
			if(StringUtils.equals("\\", separator))
				separator += separator;
			
			filePath = filePath.replaceAll("/|\\\\", separator);
		}
		return filePath;
	}
	
	public static String pathEndWithFileSeparator(String filePath) {
		filePath = StringUtils.isNotBlank(filePath) && StringUtils.endsWith(filePath, File.separator) ? filePath : filePath.concat(File.separator);
		return filePath;
	}
	
}
