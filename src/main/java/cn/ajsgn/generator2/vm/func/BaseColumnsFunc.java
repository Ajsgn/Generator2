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
 * <p>#baseColumns()指令，用于Mapper中的Base_column</p>
 * @ClassName: BaseColumnsFunc
 * @Description: #baseColumns()指令
 * @author Ajsgn@foxmail.com
 * @date 2017年10月22日 下午8:40:36
 */
public class BaseColumnsFunc extends Directive{

	@Override
	public String getName() {
		return "baseColumns";
	}

	@Override
	public int getType() {
		return LINE;
	}

	@Override
	public boolean render(InternalContextAdapter context, Writer writer, Node node) throws IOException, ResourceNotFoundException, ParseErrorException, MethodInvocationException {
		Object obj = context.get("_columnInfos");
		String lineSeparator = System.getProperty("line.separator");
		StringBuilder sb = new StringBuilder("\t");
		if(obj instanceof List){
			@SuppressWarnings("rawtypes")
			List list = (List) obj;
			for(int i=0; i<list.size(); i++){
				Object value = list.get(i);
				if(value instanceof ColumnInfo){
					ColumnInfo columnInfo = (ColumnInfo) value;
					sb.append(columnInfo.getColumnName()).append(", ");
				}
				if(0 == (i+1) % 6){
					sb.append(lineSeparator);
					sb.append("\t");
				}
			}
			if(sb.length() > 2){
				sb.delete(sb.lastIndexOf(","), sb.length());
				sb.append(lineSeparator);
			}
		}
		writer.write(sb.toString());
		return true;
	}

}
