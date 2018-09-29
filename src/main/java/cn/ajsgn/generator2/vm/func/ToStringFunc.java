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
package cn.ajsgn.generator2.vm.func;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.directive.Directive;
import org.apache.velocity.runtime.parser.node.Node;

import cn.ajsgn.generator2.db.column.ColumnInfo;

/**
 * <p>model里的toString方法</p>
 * @ClassName: ToStringFunc
 * @Description: model里的toString方法
 * @author g.yang@i-vpoints.com
 * @date 2018年9月25日 下午7:15:09
 */
public class ToStringFunc extends Directive{

	@Override
	public String getName() {
		return "toString";
	}

	@Override
	public int getType() {
		return LINE;
	}

	@Override
	public boolean render(InternalContextAdapter context, Writer writer, Node node) throws IOException, ResourceNotFoundException, ParseErrorException, MethodInvocationException {
		Object obj = context.get("_columnInfos");
		String toStringClassName = String.valueOf(context.get("_toStringClassName"));
		String lineSeparator = System.getProperty("line.separator");
		StringBuilder sb = new StringBuilder("\t\treturn \"").append(toStringClassName).append(" [\" + ");
		sb.append(lineSeparator).append("\t\t\t");
		if(obj instanceof List){
			@SuppressWarnings("rawtypes")
			List list = (List) obj;
			for(int i=0; i<list.size(); i++){
				Object value = list.get(i);
				if(value instanceof ColumnInfo){
					ColumnInfo columnInfo = (ColumnInfo) value;
					sb.append(0 != i ? "\", " : "\"").append(columnInfo.getColumnCamelName()).append("=\" + ")
						.append(columnInfo.getColumnCamelName()).append(" + ");
					//每4个字段换行
					if(0 == (i+1) %4) {
						if(i != list.size() -1) {
							sb.append(lineSeparator);
							sb.append("\t\t\t");
						}
					}
				}
			}
			sb.append(lineSeparator).append("\t\t\"]\";").append(lineSeparator);
		}
		writer.write(sb.toString());
		return true;
	}

}
