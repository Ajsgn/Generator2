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
package cn.ajsgn.generator2.core.generator.abs;

import java.io.FileWriter;

/**
 * <p>文件生成器</p>
 * @ClassName: FileTemplateGenerator
 * @Description: 文件生成器
 * @author Ajsgn@foxmail.com
 * @date 2018年9月26日 下午8:19:11
 */
public interface FileTemplateGenerator extends TemplateGenerator {
	
	/**
	 * <p>目标文件输出流<p>
	 * @Title: targetFileWriter
	 * @Description: 目标文件输出流
	 * @author Ajsgn@foxmail.com
	 * @date 2018年9月26日 下午8:19:29
	 * @return FileWriter 目标文件输出流
	 */
	public FileWriter targetFileWriter();
	
}
