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
import java.beans.SimpleBeanInfo;

import java.lang.reflect.Method;

import java.util.Objects;

public abstract class AbstractBeanInfo<T> extends SimpleBeanInfo {

  private final Class<T> cls;

  private final MethodDescriptor[] methodDescriptors;
  
  private final PropertyDescriptor[] propertyDescriptors;

  protected AbstractBeanInfo(final Class<T> c) throws IntrospectionException {
    super();
    Objects.requireNonNull(c);
    this.cls = c;

    MethodDescriptor[] methodDescriptors = null;
    try {
      methodDescriptors = this.buildMethodDescriptors();
    } catch (final ReflectiveOperationException reflectiveOperationException) {
      throw (IntrospectionException)new IntrospectionException(reflectiveOperationException.getMessage()).initCause(reflectiveOperationException);
    } finally {
      this.methodDescriptors = methodDescriptors;
    }

    
    PropertyDescriptor[] propertyDescriptors = null;
    try {
      propertyDescriptors = this.buildPropertyDescriptors();
    } catch (final ReflectiveOperationException reflectiveOperationException) {
      throw (IntrospectionException)new IntrospectionException(reflectiveOperationException.getMessage()).initCause(reflectiveOperationException);
    } finally {
      this.propertyDescriptors = propertyDescriptors;
    }
  }

  protected MethodDescriptor[] buildMethodDescriptors() throws IntrospectionException, ReflectiveOperationException {
    return null;
  }
  
  protected PropertyDescriptor[] buildPropertyDescriptors() throws IntrospectionException, ReflectiveOperationException {
    return null;
  }

  protected final Method getMethod(final String name, final Class<?>... parameterTypes) throws ReflectiveOperationException {
    return this.cls.getMethod(name, parameterTypes);
  }

  protected PropertyDescriptor readOnly(final String propertyName) throws IntrospectionException, ReflectiveOperationException {
    return this.readOnly(propertyName, propertyName);
  }
  
  protected PropertyDescriptor readOnly(final String propertyName, final String methodName) throws IntrospectionException, ReflectiveOperationException {
    return new PropertyDescriptor(propertyName, this.getMethod(methodName), null);
  }
  
  @Override
  public PropertyDescriptor[] getPropertyDescriptors() {
    return this.propertyDescriptors;
  }

  @Override
  public MethodDescriptor[] getMethodDescriptors() {
    return this.methodDescriptors;
  }

}
