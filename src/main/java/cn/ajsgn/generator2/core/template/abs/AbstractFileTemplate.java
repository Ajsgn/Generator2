/*
 * Copyright (c) 2018, Ajsgn 杨光 (Ajsgn@foxmail.com).
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
package cn.ajsgn.generator2.core.template.abs;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import cn.ajsgn.generator2.util.FileUtil;

/**
 * <p>文件输出</p>
 * @ClassName: AbstractFileTemplate
 * @Description: 文件输出
 * @author Ajsgn@foxmail.com
 * @date 2018年9月26日 下午8:16:57
 */
public abstract class AbstractFileTemplate implements FileTemplate {
	
	/**
	 * <p>需要写出的文件<p>
	 * @Title: targetFile
	 * @Description: 需要写出的文件
	 * @author Ajsgn@foxmail.com
	 * @date 2018年9月26日 下午8:17:25
	 * @return File 需要写出的文件
	 */
	protected abstract File targetFile();

	@Override
	public FileWriter targetFileWriter() {
		FileWriter fw = null;
		File file = targetFile();
		if(null!= file) {
			FileUtil.fileReady(file);	// 文件目录的生成
			try {
				fw = new FileWriter(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return fw;
	}
	
	
}
