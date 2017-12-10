/* -*- mode: Java; c-basic-offset: 2; indent-tabs-mode: nil; coding: utf-8-unix -*-
 *
 * Copyright © 2017 MicroBean.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.  See the License for the specific language governing
 * permissions and limitations under the License.
 */
package org.microbean.freemarker.jandex.beaninfo;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;

import java.util.List;
import java.util.LinkedList;

import org.jboss.jandex.ClassInfo;

public class ClassInfoBeanInfo extends AbstractBeanInfo<ClassInfo> {

  public ClassInfoBeanInfo() throws IntrospectionException {
    super(ClassInfo.class);
  }

  @Override
  protected PropertyDescriptor[] buildPropertyDescriptors() throws IntrospectionException, ReflectiveOperationException {
    final List<PropertyDescriptor> returnValue = new LinkedList<>();

    returnValue.add(readOnly("class", "getClass"));
    returnValue.add(readOnly("classAnnotations"));
    returnValue.add(readOnly("classFacet", "asClass"));
    returnValue.add(readOnly("enclosingClass"));
    returnValue.add(readOnly("enclosingMethod"));
    returnValue.add(readOnly("fieldFacet", "asField"));
    returnValue.add(readOnly("fields"));
    returnValue.add(readOnly("flags"));
    returnValue.add(readOnly("hasNoArgsConstructor"));
    returnValue.add(readOnly("interfaceNames"));
    returnValue.add(readOnly("interfaceTypes"));
    returnValue.add(readOnly("kind"));
    returnValue.add(readOnly("methodFacet", "asMethod"));
    returnValue.add(readOnly("methodParameterFacet", "asMethodParameter"));
    returnValue.add(readOnly("methods"));
    returnValue.add(readOnly("name"));
    returnValue.add(readOnly("nestingType"));
    returnValue.add(readOnly("simpleName"));
    returnValue.add(readOnly("superClassType"));
    returnValue.add(readOnly("superName"));
    returnValue.add(readOnly("typeFacet", "asType"));
    returnValue.add(readOnly("typeParameters"));
    
    return returnValue.toArray(new PropertyDescriptor[returnValue.size()]);
  }
  
}
