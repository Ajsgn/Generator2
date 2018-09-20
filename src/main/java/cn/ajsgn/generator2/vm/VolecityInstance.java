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
package cn.ajsgn.generator2.vm;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

/**
 * <p>Volecity实例类</p>
 * @ClassName: VolecityInstance
 * @Description: Volecity实例类
 * @author Ajsgn@foxmail.com
 * @date 2017年10月22日 下午8:26:26
 */
public class VolecityInstance {
	
	private VelocityEngine ve = new VelocityEngine();

	private VolecityInstance(){
		
	}
	
	/**
	 * <p>实例&初始化</p>
	 * @Title: init
	 * @Description: 实例化 And 初始化对象
	 * @return VolecityInstance
	 * @author Ajsgn@foxmail.com
	 * @date 2017年10月22日 下午8:27:05
	 */
	public static VolecityInstance init(){
		VolecityInstance instance = new VolecityInstance();
		// 初始化模板引擎
		instance.ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		instance.ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
		instance.ve.loadDirective("cn.ajsgn.generator2.db.vm.func.BaseColumnsFunc");
		instance.ve.init();
	    return instance;
	}
	
	/**
	 * <p>模板文件合并输出</p>
	 * @Title: flush
	 * @Description: 模板文件合并输出
	 * @param resourcePath 模板文件路径
	 * @param context 模板文件所需要的参数
	 * @param targetWriter 模板文件输出流
	 * @author Ajsgn@foxmail.com
	 * @date 2017年10月22日 下午8:28:02
	 */
	public void flush(String resourcePath, Map<String,Object> context, Writer targetWriter) throws IOException{
		if(StringUtils.isBlank(resourcePath) || null == context || null == targetWriter){
			return;
		}
	    Template templete = ve.getTemplate(resourcePath);
	    VelocityContext vContext = new VelocityContext(new LinkedHashMap<String,Object>());
	    for(Map.Entry<String, Object> entry : context.entrySet()){
	    	vContext.put(entry.getKey(), entry.getValue());
	    }
	    templete.merge(vContext,targetWriter);
		targetWriter.flush();
	}
	
	/**
	 * test for Volecity
	 */
	public static void main(String[] args) throws IOException {
		VolecityInstance instance = init();
		
		FileWriter writer = new FileWriter(new File("e:/xxxx.java"));
		Map<String,Object> context = new HashMap<String,Object>();
		context.put("createDate", DateFormatUtils.format(new Date(), "yyyy年MM月dd日 HH时mm分ss秒"));
		
		instance.flush("cn/ajsgn/common/generator/templete/dao/DaoCondition.vm", context, writer);
		
	}
	
}
