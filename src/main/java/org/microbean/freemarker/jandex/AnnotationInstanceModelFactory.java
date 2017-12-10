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
package org.microbean.freemarker.jandex;

import freemarker.ext.beans.BeansWrapper;

import freemarker.ext.util.ModelFactory;

import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateModel;

import org.jboss.jandex.AnnotationInstance;

/**
 * A {@link ModelFactory} that {@linkplain #create(Object,
 * ObjectWrapper) makes} {@link AnnotationInstanceModel} instances.
 *
 * @author <a href="https://about.me/lairdnelson"
 * target="_parent">Laird Nelson</a>
 *
 * @see #create(Object, ObjectWrapper)
 *
 * @see ModelFactory#create(Object, ObjectWrapper)
 */
public class AnnotationInstanceModelFactory implements ModelFactory {

  
  /*
   * Constructors.
   */


  /**
   * Creates a new {@link AnnotationInstanceModelFactory}.
   */
  public AnnotationInstanceModelFactory() {
    super();
  }


  /*
   * Instance methods.
   */
  

  /**
   * Creates a new {@link AnnotationInstanceModel} and returns it.
   *
   * <p>This method never returns {@code null}.</p>
   *
   * @param object an {@link AnnotationInstance}; must not be {@code
   * null}
   *
   * @param objectWrapper an {@link ObjectWrapper}; must be an
   * instance of {@link BeansWrapper}
   *
   * @return a new {@link AnnotationInstanceModel}; never {@code null}
   *
   * @exception IllegalArgumentException if {@code object} is not an
   * instance of {@link AnnotationInstance} or if {@code
   * objectWrapper} is not an instance of {@link BeansWrapper}
   *
   * @see ModelFactory#create(Object, ObjectWrapper)
   */
  @Override
  public final TemplateModel create(final Object object, final ObjectWrapper objectWrapper) {    
    if (!(object instanceof AnnotationInstance)) {
      throw new IllegalArgumentException("!(object instanceof AnnotationInstance): " + object);
    }
    if (!(objectWrapper instanceof BeansWrapper)) {
      throw new IllegalArgumentException("!(objectWrapper instanceof BeansWrapper): " + objectWrapper);
    }
    return new AnnotationInstanceModel((BeansWrapper)objectWrapper, (AnnotationInstance)object);
  }
  
  
}
