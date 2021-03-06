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
import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;

import java.util.List;
import java.util.LinkedList;

import org.jboss.jandex.AnnotationValue;

public class AnnotationValueBeanInfo extends AbstractBeanInfo<AnnotationValue> {

  public AnnotationValueBeanInfo() throws IntrospectionException {
    super(AnnotationValue.class);
  }

  @Override
  protected MethodDescriptor[] buildMethodDescriptors() throws IntrospectionException, ReflectiveOperationException {
    return null;
  }
  
  @Override
  protected PropertyDescriptor[] buildPropertyDescriptors() throws IntrospectionException, ReflectiveOperationException {
    // TODO: implement to make template usage easier
    return null;
  }
  
}
