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
package cn.ajsgn.generator2;

import cn.ajsgn.generator2.core.strategy.abs.GeneratorStrategy;
import cn.ajsgn.generator2.vm.VolecityInstance;

/**
 * <p>Generator2 入口执行类</p>
 * @ClassName: Generator2
 * @Description: 入口执行类
 * @author Ajsgn@foxmail.com
 * @date 2018年9月26日 下午8:15:17
 */
public class Generator2 {
	
	/**
	 * 模板引擎
	 */
	private static final VolecityInstance INSTANCE = VolecityInstance.init();
	
	/**
	 * <p>策略模式，回调不同的代码生成策略<p>
	 * @Title: generator
	 * @Description: 策略模式，回调不同的代码生成策略
	 * @param GeneratorStrategy 代码生成策略
	 * @author Ajsgn@foxmail.com
	 * @date 2018年9月26日 下午8:15:48
	 */
	public static final void generator(GeneratorStrategy strategy) {
		if(null != strategy) 
			strategy.generator(INSTANCE);
	}
	
}
