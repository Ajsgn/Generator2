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

import java.util.Map;

/**
 * <p>模板生成器接口</p>
 * @ClassName: TemplateGenerator
 * @Description: 模板生成器接口
 * @author Ajsgn@foxmail.com
 * @date 2018年9月26日 下午8:20:05
 */
public interface TemplateGenerator {
	
	/**
	 * <p>模板中需要用到的参数集合<p>
	 * @Title: targetParam
	 * @Description: 模板中需要用到的参数集合
	 * @author Ajsgn@foxmail.com
	 * @date 2018年9月26日 下午8:20:22
	 * @return Map<String,Object> 模板中的参数集合
	 */
	public abstract Map<String, Object> targetParam();
	
	/**
	 * <p>模板资源路径<p>
	 * @Title: templateResource
	 * @Description: 模板资源路径
	 * @author Ajsgn@foxmail.com
	 * @date 2018年9月26日 下午8:20:50
	 * @return String 模板资源路径
	 */
	public abstract String templateResource();
	
}
