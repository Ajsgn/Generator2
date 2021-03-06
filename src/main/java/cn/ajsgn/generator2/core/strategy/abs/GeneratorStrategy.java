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
package cn.ajsgn.generator2.core.strategy.abs;

import cn.ajsgn.generator2.vm.VolecityInstance;

/**
 * <p>生成策略抽象接口</p>
 * @ClassName: GeneratorStrategy
 * @Description: 生成策略抽象接口
 * @author Ajsgn@foxmail.com
 * @date 2018年9月26日 下午8:28:06
 */
public interface GeneratorStrategy {
	
	/**
	 * <p>回调方法，用于执行生成策略<p>
	 * @Title: generator
	 * @Description: 回调方法，用于执行生成策略
	 * @param vm 模板引擎
	 * @author Ajsgn@foxmail.com
	 * @date 2018年9月26日 下午8:28:28
	 */
	public abstract void generator(VolecityInstance vm);
	
}
